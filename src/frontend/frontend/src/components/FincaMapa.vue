<template>
  <div class="map-container">
    <div ref="map" style="height: 400px;"></div>
  </div>
</template>

<script setup>
import { ref, onMounted, defineProps } from 'vue';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import 'leaflet-draw/dist/leaflet.draw.css';
import 'leaflet-draw';

const props = defineProps({
  latitud: Number,
  longitud: Number,
  geoJSONUrl: String,
});

const map = ref(null);

onMounted(() => {
  map.value = L.map(map.value).setView([props.latitud, props.longitud], 15);

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

  // Marcar la finca en el mapa
  L.marker([props.latitud, props.longitud]).addTo(map.value).bindPopup('Finca');
});

</script>

<style scoped>
.map-container {
  width: 100%;
  height: 100%;
}
</style>
