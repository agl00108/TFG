<template>
  <div>
    <h1>Página de Fincas</h1>
    <!--
    <DatosFinca :url="urlFinca" />
    -->
    <div v-for="finca in fincas" :key="finca.id">
      <Finca :finca="finca" />
    </div>
  </div>
</template>

<script>
import Finca from '../components/Finca.vue';

export default {
  components: {
    Finca
  },
  data() {
    return {
      fincas: []
    };
  },
  mounted() {
    fetch('/TFG/FincasConHistorico')
        .then(response => response.json())
        .then(data => {
          this.fincas = data;
          console.log(data);
        });
  }
}
</script>


