<template>
  <h2>Fincas Almacenadas</h2>
  <div class="map-container">
    <div id="map" style="height: 500px; width: 500px"></div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import "leaflet/dist/leaflet.css";
import * as L from 'leaflet';
import iconoUbicacion from '@/assets/img/iconoUbicacion.png';

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
  const greenIcon = L.icon({
    iconUrl: iconoUbicacion,
    iconSize: [40, 45],
  });

  const zonas = await response.json();
  zonas.forEach(zona => {
    L.marker([zona.latitud, zona.longitud], {icon: greenIcon}).addTo(initialMap.value).bindPopup(zona.ubicacion + " " + zona.descripcion);
  });
});
</script>

<style scoped>
</style>