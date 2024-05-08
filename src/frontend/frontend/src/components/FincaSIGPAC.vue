<template>
  <div class="finca-details">
    <div v-if="finca && Object.keys(zona).length">
      <h3>Finca {{finca.zonaUbicacion}}</h3>
      <p><strong>Zona:</strong> {{zona.descripcion}}</p>
      <p><strong>SIGPAC:</strong> {{ finca.provinciaCodigo }}:{{ finca.municipioCodigo }}:0:0:{{ finca.poligono }}:{{ finca.parcela }}:{{ finca.recinto }} </p>
      <p><strong>Municipio:</strong>  {{municipio.nombre}} ({{ finca.municipioCodigo }})</p>
      <p><strong>Provincia:</strong>  {{provincia.nombreProvincia}} ({{ finca.provinciaCodigo }})</p>
      <FincaMapa :latitud="zona.latitud" :longitud="zona.longitud" :zona="finca.zonaUbicacion"/><div class="button-container">
        <button @click="verMasInformacion" class="btn btn-primary button-custom">Más información</button>
      </div>
    </div>
    <div v-else>
      <p>Cargando datos de la finca...</p>
    </div>
  </div>
</template>

<script>
import FincaMapa from "@/components/FincaMapa.vue";
import FincaEspecifica from "@/views/FincaEspecifica.vue";
export default {
  components: {
    FincaEspecifica,
    FincaMapa,
  },
  data() {
    return {
      zona: {},
      provincia: {},
      municipio: {}
    };
  },
  props: {
    finca: {
      type: Object,
      required: true,
    },
    zonaUrl: {
      type: String,
      required: true,
    },
  },
  watch: {
    finca: {
      immediate: true,
      handler(newFinca)
      {
        if (newFinca)
        {
          fetch(`/TFG/provincias/${newFinca.provinciaCodigo}/municipios/${newFinca.municipioCodigo}/zonas/${newFinca.zonaUbicacion}`)
              .then((response) => response.json())
              .then((data) => {
                this.zona = data;
              });
          fetch(`/TFG/provincias/${newFinca.provinciaCodigo}`)
              .then((response) => response.json())
              .then((data) => {
                this.provincia=data;
              });
          fetch(`/TFG/provincias/${newFinca.provinciaCodigo}/municipios/${newFinca.municipioCodigo}`)
              .then((response) => response.json())
              .then((data) => {
                this.municipio=data;
              });
        }
      },
    },
  },
  methods: {
    verMasInformacion() {
      const url = `/TFG/provincia/${this.finca.provinciaCodigo}/municipio/${this.finca.municipioCodigo}/finca/${this.finca.poligono}/${this.finca.parcela}/${this.finca.recinto}`;
      fetch(url)
          .then(response => {
            if (!response.ok) {
              throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
          })
          .then(data => {
            this.$store.commit('setFinca', data);
            this.$router.push({name: 'fincaEsp', query: {zonaUrl: this.zonaUrl}});
          })
          .catch(error => {
            console.error('Error en la solicitud:', error);
          });
    }
  }
};
</script>
<style scoped>
.finca-details {
  background-color: rgba(255, 255, 255, 0.8);
  border-radius: 10px;
  padding: 10px;
}
h3
{
  text-align: center;
  color: #000000;
}
.button-container
{
  padding-top: 10px;
  display: flex;
  justify-content: center;
}
.button-custom
{
  background-color: #264d34;
  color: white;
  border-color: white;
  text-align: center;
}

</style>
