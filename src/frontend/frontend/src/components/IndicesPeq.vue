<template>
  <div class="container" >
  <div class="indices-container">
    <h2>Índices de vegetación</h2>
    <div v-for="indice in indices" :key="indice.nombre" class="indice-card">
      <h4>{{ indice.nombre }} </h4>
      <p>{{ indice.nombre_c }}</p>
      <img v-if="indice.nombre === 'NDVI'" src="../assets/img/indices/NDVI.png" alt="Fórmula NDVI" class="formula" >
      <img v-else-if="indice.nombre === 'NDMI'" src="../assets/img/indices/NDMI.png" alt="Fórmula NDMI" class="formula">
      <img v-else-if="indice.nombre === 'SAVI'" src="../assets/img/indices/SAVI.png" alt="Fórmula SAVI" class="formula">
    </div>
    <div class="button-container">
      <router-link class="btn btn-primary button-custom" to="/indices">Más información</router-link>
    </div>
  </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const indices = ref([
  {
    nombre: 'NDVI',
    nombre_c:'Normalized Difference Vegetation Index'
  },
  {
    nombre: 'NDMI',
    nombre_c:'Normalized Difference Moisture Index',
  },
  {
    nombre: 'SAVI',
    nombre_c:'Soil Adjusted Vegetation Index',
  }
]);
</script>

<style scoped>
.container
{
  padding-top: 20px;
  padding-bottom: 190px;
  text-align: center;
  width: 150%;
}
.indices-container
{
  background-color: rgba(255, 255, 255, 0.8);
  border: 2px solid #ccc;
  border-radius: 10px;
  padding: 20px;
}

.indice-card
{
  margin-bottom: 20px;
  display: inline-block;
}

.formula
{
  width: 60%;
}

.button-custom
{
  background-color: #264d34;
  color: white;
  border-color: white;
  text-align: center;
}
</style>