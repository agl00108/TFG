<template>
  <div class="container">
  <div class="finca-details">
    <h2>Datos de la Finca</h2>
    <div v-if="finca">
      <p><strong>Polígono:</strong> {{ finca.poligono }}</p>
      <p><strong>Parcela:</strong> {{ finca.parcela }}</p>
      <p><strong>Recinto:</strong> {{ finca.recinto }}</p>
      <p><strong>Área:</strong> {{ finca.area }}</p>
      <p><strong>Año SIGPAC:</strong> {{ finca.anioSigpac }}</p>
      <p><strong>Zona Ubicación:</strong> {{ finca.zonaUbicacion }}</p>
      <p><strong>Municipio:</strong> {{municipio.nombre}} ({{ finca.municipioCodigo }})</p>
      <p><strong>Provincia:</strong>{{provincia.nombreProvincia}} ({{finca.codigoProvincia}})</p>
      <FincaMapa :latitud="zona.latitud" :longitud="zona.longitud" :geoJSONUrl="zonaUrl" />
      <CosechaGrafica :finca="finca"/>
    </div>
    <div v-else>
      <p>Cargando datos de la finca...</p>
    </div>
  </div>
  </div>
</template>

<script>
import FincaMapa from "@/components/FincaMapa.vue";
import CosechaGrafica from "@/components/CosechaGrafica.vue";

export default {
  components: {CosechaGrafica, FincaMapa },
  props: {
    zonaUrl: {
      type: String,
      required: true,
    },
  },
  computed: {
    finca() {
      return this.$store.state.finca;
    },
  },
  watch: {
    finca: {
      immediate: true,
      handler(newFinca)
      {
        if (newFinca)
        {
          this.fetchZona(newFinca);
        }
      },
    },
  },
  methods: {
    fetchZona(fincaData)
    {
      fetch(`/TFG/provincias/${fincaData.codigoProvincia}/municipios/${fincaData.municipioCodigo}/zonas/${fincaData.zonaUbicacion}`)
          .then((response) => response.json())
          .then((data) => {
            this.zona = data;
          });
      fetch(`/TFG/provincias/${fincaData.codigoProvincia}`)
          .then((response) => response.json())
          .then((data) => {
            this.provincia=data;
          });
      fetch(`/TFG/provincias/${fincaData.codigoProvincia}/municipios/${fincaData.municipioCodigo}`)
          .then((response) => response.json())
          .then((data) => {
            this.municipio=data;
          });
    },
  },
  data() {
    return {
      zona: { latitud: 0, longitud: 0, geoJSONUrl: '' },
      provincia: {},
      municipio: {}
    };
  },
};
</script>

<style scoped>
.container
{
  padding-top: 120px;
  padding-bottom: 90px;
  width: 200%;
}
.finca-details
{
  background-color: rgba(255, 255, 255, 0.9);
  border: 2px solid #ccc;
  border-radius: 10px;
  padding: 20px;
}
</style>
