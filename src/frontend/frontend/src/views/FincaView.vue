<template>
  <div>
    <h3>Buscador de fincas</h3>
    <form @submit.prevent="buscarFinca">
      <input v-model="codigoSIGPAC" type="text" placeholder="Introduce el código SIGPAC">
      <button type="submit">Buscar</button>
    </form>
    <h1>Fincas Esenciales</h1>
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
      fincas: [],
      codigoSIGPAC: ''
    };
  },
  mounted() {
    fetch('/TFG/FincasConHistorico')
        .then(response => response.json())
        .then(data => {
          this.fincas = data;
        });
  },
  methods: {
    getZonaUrl(zonaUbicacion) {
      return `../assets/shp/${zonaUbicacion}.geojson`;
    },
    buscarFinca() {
      const [provinciaCodigo, municipioCodigo, , , poligono, parcela, recinto] = this.codigoSIGPAC.split(':');
      fetch(`/TFG/provincia/${provinciaCodigo}/municipio/${municipioCodigo}/finca/${poligono}/${parcela}/${recinto}`)
          .then(response => response.json())
          .then(data => {
            this.$store.commit('setFinca', data);
            this.$router.push({ name: 'fincaEsp' });
          });
    }
  }
};
</script>

<style scoped>
h1
{
  text-align: center;
  margin-top: 120px;
  color: #e8e4df;
}

h3
{
  text-align: center;
  margin-top: 120px;
  color: #e8e4df;
}

.grid-container
{
  display: grid;
  grid-template-columns: repeat(4, minmax(200px, 1fr));
  gap: 110px;
  padding-top: 20px;
  padding-bottom: 90px;
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
