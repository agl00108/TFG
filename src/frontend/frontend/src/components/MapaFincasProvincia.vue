<template>
  <h3>Fincas</h3>
  <div class="map-container">
    <div id="map" style="height: 400px; width: 400px"></div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import "leaflet/dist/leaflet.css";
import * as L from 'leaflet';

const initialMap = ref(null);

onMounted(() => {
  initialMap.value = L.map('map').setView([37.9749, -3.9903], 9);
  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
  }).addTo(initialMap.value);

  const fincas = [
    { lat: 38.058554, lng: -4.154917, name: 'J1' },
    { lat: 38.014274, lng: -4.020375, name: 'J2' },
    { lat: 37.998493, lng: -3.997321, name: 'J3' },
    { lat: 38.009695, lng: -3.872684, name: 'J4' },
    { lat: 37.83342069, lng: -4.1029011, name: 'T18' },
    { lat: 37.81824, lng: -4.12898, name: 'H4' },
    { lat: 37.832884, lng: -4.13162632, name: 'T27' },
    { lat: 37.821513, lng: -4.11933117, name: 'T20' }
  ];

  fincas.forEach(finca => {
    L.marker([finca.lat, finca.lng]).addTo(initialMap.value).bindPopup(finca.name);
  });
});
</script>

<style scoped>
.map-container {
  width: 100%;
  height: 100%;
}
</style>