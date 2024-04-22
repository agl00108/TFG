<script setup>
import { ref } from 'vue';
import IndiceVegetacion from '../components/IndiceVegetación.vue'

const indices = ref([
  {
    nombre: 'NDVI (Normalized Difference Vegetation Index)',
    descripcion: 'Utiliza las bandas del rojo y el infrarrojo cercano y nos permite calcular el vigor de una planta en un estado fenológico concreto, el estado del cultivo y si la planta está sufriendo para poder llegar a tiempo y corregirlo.',
    imagen: '../assets/img/NDVI.jpg'
  },
  {
    nombre: 'NDWI (Normalized Difference Water Index)',
    descripcion: 'Compara la diferencia entre la banda del verde y la del infrarrojo cercano.\n' +
        'Es un índice apropiado para mapear cuerpos de agua y zonas anegadas, ya que el agua tiene una alta absorción y baja irradiación en el rango visible del infrarojo. Para este índice, los cuerpos de agua presentan valores mayores a 0, mientras que el suelo presenta valores menores a 0. No es un indicador de la humedad del suelo, pero si permite determinar el momento del ciclo en que se produce encharcamiento o anegamiento.\n',
    imagen: '../assets/img/NDWI.jpg'
  },
  {
    nombre: 'SAVI (Soil Adjusted Vegetation Index)',
    descripcion: 'Fue diseñado para minimizar las influencias del brillo del suelo. Su creador, Huete, agregó un factor de ajuste del suelo L a la ecuación de NDVI para corregir los efectos del ruido del suelo (color del suelo, humedad del suelo, variabilidad del suelo a través de la región, etc.), que tienden a afectar a los resultados. Este Índice de Vegetación se utiliza para el análisis de cultivos jóvenes, para regiones áridas con vegetación escasa (menos del 15% del área total) y superficies expuestas del suelo.',
    imagen: '../assets/img/SAVI.jpg'
  }
]);
</script>

<template>
  <div>
    <IndiceVegetacion v-for="(indice, index) in indices" :key="index" :indice="indice" />
  </div>
</template>

<style scoped>

</style>