<template>
  <div>
    <h1>Página de Fincas</h1>
    <div class="grid-container">
      <div v-for="finca in fincas" :key="finca.id" class="grid">
        <Finca :finca="finca" :zonaUrl="getZonaUrl(finca.zonaUbicacion)" />
      </div>
    </div>
  </div>
</template>

<script>
import Finca from '../components/FincaSIGPAC.vue';

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
  },
  methods: {
    getZonaUrl(zonaUbicacion) {
      return `../assets/shp/${zonaUbicacion}.shp`;
    }
  }
}
</script>

<style scoped>
.grid-container
{
  display: grid;
  grid-template-columns: repeat(4, minmax(200px, 1fr));
  gap: 110px;
  padding-top: 80px;
  padding-bottom: 100px;
}

.grid
{
  width: 150%;
}

@media screen and (max-width: 1307px)
{
  .grid-container
  {
    grid-template-columns: repeat(2,1fr);
    gap: 200px;
  }
  .grid
  {
    width: 100%;
  }
}

@media screen and (max-width: 630px)
{
  .grid-container
  {
    grid-template-columns: repeat(1, 1fr);
    gap: 10px;
  }
  .grid
  {
    width: 75%;
  }
}
</style>
