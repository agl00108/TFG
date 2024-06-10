<template>
  <div class="map-container">
    <div id="map" class="map"></div>
  </div>
</template>

<script setup>
import { ref, onMounted, defineProps, watch } from 'vue';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import 'leaflet-draw/dist/leaflet.draw.css';
import 'leaflet-draw';
import proj4 from 'proj4';
import pointInPolygon from '@turf/boolean-point-in-polygon';
import { point, polygon } from '@turf/helpers';
import { useRouter } from 'vue-router';

const props = defineProps({
  latitud: Number,
  longitud: Number,
  zona: String,
  olivos: Array
});
const router = useRouter();

let map = ref(null);
let centerMarker = null;
let markers = ref([]);

const utmProjection = `+proj=utm +zone=30 +datum=WGS84 +units=m +no_defs`; // Replace with your specific UTM zone if different

const convertUTMToLatLng = (x, y) =>
{
  const [lon, lat] = proj4(utmProjection, 'EPSG:4326', [x, y]);
  return [lat, lon];
};

let geojsonLayer = null;

onMounted(async () =>
{
  if (!map.value)
  {
    map.value = L.map('map').setView([props.latitud, props.longitud], 18);
    L.tileLayer.wms("http://www.ign.es/wms-inspire/pnoa-ma", {
      layers: 'OI.OrthoimageCoverage',
      attribution: "Fuente: Ortofotos PNOA"
    }).addTo(map.value);

    let geojson;
    try {
      const module = await import(`@/assets/geojson/${props.zona}.json`);
      geojson = module.default;
    } catch (error)
    {
      console.error('Error al cargar el archivo GeoJSON:', error);
      return;
    }
    geojsonLayer = L.geoJson(geojson, {
      style: {"color": "#224930", "fill": false }
    }).addTo(map.value);

    const drawnItems = new L.FeatureGroup();
    map.value.addLayer(drawnItems);
    const drawControl = new L.Control.Draw({
      draw: {
        polyline: false,
        polygon: false,
        circle: false,
        rectangle: false,
        marker: false,
        circlemarker: false,
      },
      edit: {
        featureGroup: drawnItems,
        remove: false,
      }
    });
    map.value.addControl(drawControl);
  }
});

watch(() => [props.latitud, props.longitud], ([newLat, newLon]) =>
{
  if (centerMarker)
  {
    centerMarker.setLatLng([newLat, newLon]);
    map.value.setView([newLat, newLon], 14);
  }

  if (!map.value)
  {
    map.value = L.map('map').setView([newLat, newLon], 18);
    L.tileLayer.wms("http://www.ign.es/wms-inspire/pnoa-ma", {
      layers: 'OI.OrthoimageCoverage',
      attribution: "Fuente: Ortofotos PNOA"
    }).addTo(map.value);
  } else {
    map.value.setView([newLat, newLon], 18);
  }
});

watch(() => props.olivos, (newOlivos) =>
{
  if (map.value)
  {
    markers.value.forEach(marker => map.value.removeLayer(marker));
    markers.value = [];

    newOlivos.forEach(olivo =>
    {
      const [lat, lon] = convertUTMToLatLng(olivo.puntoMedio.x, olivo.puntoMedio.y);
      const olivoPoint = point([lon, lat]);
      const isInside = geojsonLayer.getLayers().some(layer =>
      {
        const olivoPolygon = polygon(layer.toGeoJSON().geometry.coordinates);
        return pointInPolygon(olivoPoint, olivoPolygon);
      });

      if (isInside)
      {
        const circle = L.circle([lat, lon], {
          color: "rgba(34,73,48,0)",
          radius: 3
        }).addTo(map.value).bindPopup(`Olivo: ${olivo.idObjeto}`);

        circle.on('click', () => { router.push({ name: 'OlivoView', params: { idObjeto: olivo.idObjeto }});});

        markers.value.push(circle);
      }
    });
  }
}, { immediate: true });
</script>

<style scoped>
.map-container {
  width: 100%;
}
.map {
  height: 500px;
  width: 860px;
}
</style>
