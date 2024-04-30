<template>
  <div class="container">
    <div class="finca-details">
      <h2>Datos de la Finca</h2>
      <div v-if="finca">
        <p><strong>Polígono:</strong> {{ finca.poligono }} <strong>Parcela:</strong> {{ finca.parcela }} <strong>Recinto:</strong> {{ finca.recinto }}</p>
        <p><strong>Área:</strong> {{ finca.area }} m^2 <strong>Nombre Zona:</strong> {{ finca.zonaUbicacion }}</p>
        <p><strong>Municipio:</strong> {{ municipio.nombre }} ({{ finca.municipioCodigo }})<strong>Provincia:</strong> {{ provincia.nombreProvincia }} ({{ finca.codigoProvincia }})</p>
        <FincaMapa :latitud="zona.latitud" :longitud="zona.longitud" :geoJSONUrl="zonaUrl" />
        <CosechaGrafica :finca="finca"/>
        <div>
          <h3>Histórico de datos de la finca {{finca.zonaUbicacion }} </h3>
          <p>Seleccione año</p>
          <button v-for="year in years" :key="year" @click="selectYear(year)">{{ year }}</button>
        </div>
        <IndiceGrafica v-if="selectedYear" :year="selectedYear" :finca="finca" />
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
import IndiceGrafica from "@/components/InsertarGraficos.vue";

export default {
  components: {IndiceGrafica, CosechaGrafica, FincaMapa },
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
    selectYear(year)
    {
      this.selectedYear = year;
      console.log(this.selectedYear);
    },
  },
  data()
  {
    return {
      zona: { latitud: 0, longitud: 0, geoJSONUrl: '' },
      provincia: {},
      municipio: {},
      selectedYear: null,
      years: Array.from({ length: 8 }, (_, i) => 2016 + i),
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
