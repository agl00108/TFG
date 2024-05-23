<template>
  <div>
    <h1>Detalles del Olivo</h1>
    <p>ID del Olivo: {{ idObjeto }}</p>
    <!-- Aquí puedes agregar más detalles del olivo si lo deseas -->
  </div>
</template>

<script setup>
import { ref, onMounted, defineProps } from 'vue';

const props = defineProps({
  idObjeto: {
    type: String,
    required: true
  }
});

// Si necesitas cargar detalles adicionales del olivo basados en el ID, puedes hacerlo aquí

</script>

<style scoped>
/* Estilos específicos para este componente */
</style>
