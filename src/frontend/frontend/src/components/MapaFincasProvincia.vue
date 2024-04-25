<template>
  <h3>Fincas Almacenadas</h3>
  <div class="map-container">
    <div id="map" style="height: 400px; width: 400px"></div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import "leaflet/dist/leaflet.css";
import * as L from 'leaflet';

const initialMap = ref(null);

onMounted(async () => {
  initialMap.value = L.map('map').setView([37.9749, -3.9903], 9);
  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
  }).addTo(initialMap.value);

  const response = await fetch('/TFG/ZonasCompletas');
  if (!response.ok) {
    throw new Error('Network response was not ok');
  }
  const zonas = await response.json();
  zonas.forEach(zona => {
    L.marker([zona.latitud, zona.longitud]).addTo(initialMap.value).bindPopup(zona.ubicacion + " " + zona.descripcion);
  });
});
</script>

<style scoped>
.map-container
{
  width: 100%;
  height: 100%;
}
</style>