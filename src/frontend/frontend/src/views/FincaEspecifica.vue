<template>
  <div class="finca-details">
    <h2>Datos de la Finca</h2>
    <div v-if="finca">
      <p><strong>Polígono:</strong> {{ finca.poligono }}</p>
      <p><strong>Parcela:</strong> {{ finca.parcela }}</p>
      <p><strong>Recinto:</strong> {{ finca.recinto }}</p>
      <p><strong>Área:</strong> {{ finca.area }}</p>
      <p><strong>Año SIGPAC:</strong> {{ finca.anioSigpac }}</p>
      <p><strong>Zona Ubicación:</strong> {{ finca.zonaUbicacion }}</p>
      <p><strong>Código Municipio:</strong> {{ finca.municipioCodigo }}</p>
      <p><strong>Código Provincia:</strong> {{ finca.codigoProvincia }}</p>
      <FincaMapa :latitud="zona.latitud" :longitud="zona.longitud" :geoJSONUrl="zonaUrl" />
    </div>
    <div v-else>
      <p>Cargando datos de la finca...</p>
    </div>

    <p><strong>{{ zona.latitud }} {{ zona.longitud }}</strong> {{zonaUrl }}</p>
  </div>
</template>

<script>
import FincaMapa from "@/components/FincaMapa.vue";

export default {
  components: { FincaMapa },
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
      handler(newFinca) {
        if (newFinca) {
          this.fetchZona(newFinca);
        }
      },
    },
  },
  methods: {
    fetchZona(fincaData) {
      fetch(`/TFG/provincias/${fincaData.codigoProvincia}/municipios/${fincaData.municipioCodigo}/zonas/${fincaData.zonaUbicacion}`)
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.json();
          })
          .then((data) => {
            this.zona = data;
            console.log(data);
          })
          .catch((error) => {
            console.error('Error al obtener la zona:', error);
          });
    },
  },
  data() {
    return {
      zona: { latitud: 0, longitud: 0, geoJSONUrl: '' },
    };
  },
};
</script>

<style scoped>
.finca-details {
  background-color: rgba(255, 255, 255, 0.7);
  border-radius: 10px;
  padding: 10px;
}
</style>
