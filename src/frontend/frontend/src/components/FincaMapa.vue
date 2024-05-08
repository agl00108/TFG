<template>
  <div class="map-container">
    <div ref="map" class="map"></div>
  </div>
</template>

<script setup>
import { ref, onMounted, defineProps, watch } from 'vue';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import 'leaflet-draw/dist/leaflet.draw.css';
import 'leaflet-draw';

const props = defineProps({
  latitud: Number,
  longitud: Number,
  zona: String,
});

const map = ref(null);
let marker = null;

onMounted(async () => {
  map.value = L.map(map.value).setView([props.latitud, props.longitud], 16);

  // Agregar capa de OpenStreetMap
  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
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
    style: {"color": "#224930", "fill": false}
  }).addTo(map.value);

  marker = L.marker([props.latitud, props.longitud]).addTo(map.value).bindPopup('Finca');
});

watch(() => [props.latitud, props.longitud], ([newLat, newLon]) => {
  if (marker) {
    marker.setLatLng([newLat, newLon]);
    map.value.setView([newLat, newLon], 14);
  }
});

</script>

<style scoped>
.map-container
{
  width: 100%;
}
.map{
  height: 250px;
  width: 280px;
  overflow: auto;
}
</style>
