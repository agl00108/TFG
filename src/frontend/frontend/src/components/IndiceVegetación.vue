<template>
  <div class="indice-details">
    <div class="indice-card" v-if="props.indice">
      <h2>{{ props.indice.nombre }}</h2>
      <p>{{ props.indice.descripcion }}</p>
      <img :src="props.indice.imagen" alt="Formula" class="formula">
    </div>
    <div class="loading-message" v-else>
      <p>Cargando datos de la enfermedad...</p>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  indice: {
    type: Object,
    required: true
  }
});
</script>

<style scoped>
.indice-details
{
  display: flex;
  justify-content: center;
  align-items: center;
}

.indice-card
{
  background-color: rgba(255, 255, 255, 0.8);
  border: 2px solid #ccc;
  border-radius: 10px;
  padding: 20px;
  max-width: 400px;
  text-align: center;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease-in-out;
}

.indice-card:hover
{
  transform: translateY(-5px);
}

.loading-message
{
  background-color: rgba(255, 255, 255, 0.8);
  border: 2px solid #ccc;
  border-radius: 10px;
  padding: 20px;
  max-width: 400px;
  text-align: center;
}
</style>
