<script setup>

</script>

<template>
  <div class="enfermedad-details">
    <h2>Datos de la Enfermedad</h2>
    <div v-if="enfermedad">
      <p><strong>Nombre:</strong> {{ enfermedad.nombre }}</p>
      <p><strong>Descripción:</strong> {{ enfermedad.descripcion }}</p>
      <p><strong>Tratamiento:</strong> {{ enfermedad.tratamiento }}</p>
      <p><strong>Fecha de inicio:</strong> {{ enfermedad.fechaInicio }}</p>
      <p><strong>Fecha de fin:</strong> {{ enfermedad.fechaFin }}</p>
    </div>
    <div v-else>
      <p>Cargando datos de la enfermedad...</p>
    </div>
  </div>
</template>

<style scoped>

</style>