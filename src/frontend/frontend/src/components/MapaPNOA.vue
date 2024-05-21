<template>
  <div class="map-container">
    <div id="map" class="map"></div>
  </div>
</template>

<script setup>
import {ref, onMounted, defineProps, watch, onUnmounted} from 'vue';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import 'leaflet-draw/dist/leaflet.draw.css';
import 'leaflet-draw';

const props = defineProps({
  latitud: Number,
  longitud: Number,
  zona:String,
});

let map = ref(null);
let marker = null;

onMounted(async () =>
{
  //PARA CREAR EL MAPA
  if (!map.value)
  {
    map.value = L.map('map').setView([props.latitud, props.longitud], 17);
    var capa = L.tileLayer.wms("http://www.ign.es/wms-inspire/pnoa-ma",
    {
      layers: 'OI.OrthoimageCoverage',
      attribution: "Fuente: Ortofotos PNOA"
    });
    capa.addTo(map.value);

    //PARA CARGAR EL CONTORNO DEL MAPA
    let geojson;
    try {
      const module = await import(`@/assets/geojson/${props.zona}.json`);
      geojson = module.default;
    } catch (error) {
      console.error('Error al cargar el archivo GeoJSON:', error);
      return;
    }
    L.geoJson(geojson, {
      style: {"color": "#224930", "fill": false }
    }).addTo(map.value);

    // Inicializar el plugin Leaflet Draw
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

    //PARA AÑADIR EL MARCADOR DEL CENTRO
    marker = L.marker([props.latitud, props.longitud]).addTo(map.value).bindPopup('Finca');
  }

});

watch(() => [props.latitud, props.longitud], ([newLat, newLon]) => {
  if (marker) {
    marker.setLatLng([newLat, newLon]);
    map.value.setView([newLat, newLon], 14);
  }

  if (!map.value)
  {
    map.value = L.map('map').setView([newLat, newLon], 17);
    var capa = L.tileLayer.wms("http://www.ign.es/wms-inspire/pnoa-ma", {
      layers: 'OI.OrthoimageCoverage',
      attribution: "Fuente: Ortofotos PNOA"
    });
    capa.addTo(map.value);
  } else {
    map.value.setView([newLat, newLon], 17);
  }
});

</script>

<style scoped>
.map-container
{
  width: 100%;
}
.map
{
  height: 500px;
  width: 860px;
}
</style>
