<script setup>

</script>

<template>
  <div class="indice-vegetacion-details">
    <h2>Índice de Vegetación</h2>
    <div v-if="indiceVegetacion">
      <p><strong>Nombre:</strong> {{ indiceVegetacion.nombre }}</p>
      <p><strong>Descripción:</strong> {{ indiceVegetacion.descripcion }}</p>
      <p><strong>Fecha de inicio:</strong> {{ indiceVegetacion.fechaInicio }}</p>
      <p><strong>Fecha de fin:</strong> {{ indiceVegetacion.fechaFin }}</p>
    </div>
    <div v-else>
      <p>Cargando datos del índice de vegetación...</p>
    </div>
  </div>
</template>

<style scoped>

</style>