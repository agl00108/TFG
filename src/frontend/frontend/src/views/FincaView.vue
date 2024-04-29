<template>
    <h3>Buscador de fincas</h3>
    <div class="search-form-container">
      <form @submit.prevent="buscarFinca" class="search-form">
        <div class="input-group">
          <input v-model="codigoSIGPAC" type="text" class="form-control" placeholder="Introduce el código SIGPAC">
          <button type="submit" class="btn btn-success"><i class="bi bi-search"></i> Buscar</button>
        </div>
        <div v-if="mostrarErrorFormato" class="error-message">
          Formato SIGPAC no válido (provincia:municipio:agregado:zona:polígono:parcela:recinto)
        </div>
        <div v-if="mostrarErrorEncontrado" class="error-message">
          Código SIGPAC no encontrado.
        </div>
      </form>
    </div>
    <h3 class="subtitulo">Fincas Esenciales</h3>
    <div class="grid-container">
      <div v-for="finca in fincas" :key="finca.id" class="grid">
        <Finca :finca="finca" :zonaUrl="getZonaUrl(finca.zonaUbicacion)" />
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
      codigoSIGPAC: '',
      mostrarErrorFormato: false,
      mostrarErrorEncontrado: false
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
    getZonaUrl(zonaUbicacion)
    {
      return `../assets/shp/${zonaUbicacion}.geojson`;
    },
    buscarFinca()
    {
      const formatoValido = /^[0-9]+:[0-9]+:[0-9]+:[0-9]+:[0-9]+:[0-9]+:[0-9]+$/.test(this.codigoSIGPAC);
      const [provinciaCodigo, municipioCodigo, , , poligono, parcela, recinto] = this.codigoSIGPAC.split(':');
      fetch(`/TFG/provincia/${provinciaCodigo}/municipio/${municipioCodigo}/finca/${poligono}/${parcela}/${recinto}`)
          .then(response =>
          {
            if (!response.ok) {
              throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();})
            .then(data => {
              this.$store.commit('setFinca', data);
              this.$router.push({name: 'fincaEsp'});
            })
            .catch(error => {
              console.error('Error en la solicitud:', error);
              if (!formatoValido)
              {
                this.mostrarErrorFormato = true;
                this.mostrarErrorEncontrado = false;
              }
              else
              {
                this.mostrarErrorFormato = false;
                this.mostrarErrorEncontrado = true;
              }
            });
      }
  }
  };
</script>

<style scoped>
.subtitulo
{
  text-align: center;
  margin-top: 15px
}

h3
{
  margin-top: 100px;
  display: inline-block;
  padding: 10px;
  background-color: rgba(38, 77, 52, 0.9);
  color: white;
  border-radius: 5px;
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
.search-form-container
{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 14vh;
  width: 110%;
  border-radius: 20px;
  background-color: rgba(255, 255, 255, 0.9);
}

.search-form
{
  width: 90%;
  border-radius: 20px;
}

.input-group
{
  display: flex;
}

.input-group input
{
  flex: 1;
  border-radius: 20px;
}

.input-group button
{
  border-radius: 20px;
}

.error-message
{
  margin-top: 10px;
  color: red;
}
</style>
