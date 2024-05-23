<template>
  <div class="indices-container">
    <IndiceVegetacion v-for="(indice, index) in indices" :key="index" :indice="indice" />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import IndiceVegetacion from '../components/IndiceVegetación.vue'

const indices = ref([
  {
    nombre: 'NDVI',
    nombre_c:'(Normalized Difference Vegetation Index)',
    descripcion: 'Utiliza las bandas del rojo y el infrarrojo cercano y nos permite calcular el vigor de una planta en un estado fenológico concreto, el estado del cultivo y si la planta está sufriendo para poder llegar a tiempo y corregirlo.',
    rango: '[0.2-0.4]: corresponden a áreas con vegetación escasa' +
        '[0.4-0.6]: la vegetación moderada' +
        '>0.6: mayor densidad posible de hojas verdes.'
  },
  {
    nombre: 'NDMI',
    nombre_c:'(Normalized Difference Moisture Index)',
    descripcion: 'Detecta los niveles de humedad en la vegetación mediante una combinación de bandas espectrales de infrarrojo cercano (NIR) e infrarrojo de onda corta (SWIR). Es un indicador fiable del estrés hídrico en los cultivos. Las condiciones severas de sequía no solo estresan los cultivos sino que pueden destruir todo el rendimiento. El NDMI mide la humedad en las hojas. Su rango es de -1 a 1.',
    rango: '[0,2–1]: superficie del agua' +
        '[0.0–0.2]: inundación, humedad ' +
        '[-0.3–0.0]: sequía moderada, superficies sin agua, ' +
        '[-1–-0,3]: sequía, superficies sin agua'
  },
  {
    nombre: 'SAVI',
    nombre_c:'(Soil Adjusted Vegetation Index)',
    descripcion: 'Fue diseñado para minimizar las influencias del brillo del suelo. Su creador, Huete, agregó un factor de ajuste del suelo L a la ecuación de NDVI para corregir los efectos del ruido del suelo (color del suelo, humedad del suelo, variabilidad del suelo a través de la región, etc.), que tienden a afectar a los resultados. Este Índice de Vegetación se utiliza para el análisis de cultivos jóvenes, para regiones áridas con vegetación escasa (menos del 15% del área total) y superficies expuestas del suelo.',
    rango: '[-1.5-0]: presencia de agua' +
        '[0.01-0.37]: alta' +
        '[0.38-0.76]: moderada' +
        '[0.77-1.10]: débil' +
        '[1.11-1.50]: sin presencia de agua'
  }
]);
</script>

<style scoped>
.indices-container
{
  padding-top: 120px;
  padding-bottom: 90px;
}
</style>
