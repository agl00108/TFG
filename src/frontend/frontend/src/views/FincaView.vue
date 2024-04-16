<template>
  <div>
    <h1>Página de Fincas</h1>
    <DatosFinca :url="urlFinca" />
  </div>
</template>

<script>
import DatosFinca from '../components/Finca.vue';

export default {
  components: {
    DatosFinca
  },
  data() {
    return {
      urlFinca: '/TFG/provincias/23/municipios/59/zonas/J1/fincas/6/211/2'
    };
  }
}
</script>


