<template>
  <div class="container">
    <div class="finca-details">
      <h2>Informe de la Finca {{finca.zonaUbicacion }}</h2>
      <div v-if="finca">
        <div class="containerDatos">
          <p class="data">
            <strong>Municipio:</strong> {{ municipio.nombre }} ({{ finca.municipioCodigo }}) <br>
            <strong>Provincia:</strong> {{ provincia.nombreProvincia }} ({{ finca.codigoProvincia }}) <br>
            <strong>Polígono:</strong> {{ finca.poligono }} <br>
            <strong>Parcela:</strong> {{ finca.parcela }} <br>
            <strong>Recinto:</strong> {{ finca.recinto }} <br>
            <strong>Área:</strong> {{ finca.area }} ha <br>
            <strong>Zona:</strong> {{ finca.zonaUbicacion }}
          </p>
          <MapaPNOA class="finca-mapa" :latitud="zona.latitud" :longitud="zona.longitud" :zona="finca.zonaUbicacion" :olivos="olivos"/>
        </div>
          <CosechaGrafica class="cosecha-grafica" :finca="finca"/>
      </div>
        <div v-else>
          <p>Cargando datos de la finca...</p>
        </div>
      <div>
        <h3>Histórico de datos </h3>
        <p>Seleccione año</p>
        <button  v-for="year in years"  :key="year" @click="selectYear(year)" class="btn btn-success btn-year"
                 :class="{ 'selected': selectedYear === year }" >{{ year }}</button>
      </div>
      <IndiceGrafica v-if="selectedYear" :year="selectedYear" :finca="finca" />
    </div>
  </div>
</template>

<script>
import CosechaGrafica from "@/components/CosechaGrafica.vue";
import IndiceGrafica from "@/components/InsertarGraficosFinca.vue";
import MapaPNOA from "@/components/MapaPNOA.vue";

export default
{
  components: {IndiceGrafica, CosechaGrafica, MapaPNOA },

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
      fetch(`/TFG/provincia/${fincaData.codigoProvincia}/municipio/${fincaData.municipioCodigo}/zona/${fincaData.zonaUbicacion}/objetos`)
          .then((response) => response.json())
          .then((data) => {
            this.olivos = data;
            console.log(data);
          });
    },
    selectYear(year)
    {
      this.selectedYear = year;
    },
  },
  data()
  {
    return {
      zona: { latitud: 0, longitud: 0, geoJSONUrl: '' },
      provincia: {},
      municipio: {},
      olivos: [],
      selectedYear: null,
      years: Array.from({ length: 8 }, (_, i) => 2016 + i),
    };
  },
};
</script>

<style scoped>
h2
{
  font-size: 2.5em;
  margin-bottom: 20px;
  text-align: center;
}
.container
{
  padding-bottom: 50px;
  margin-bottom: 50px;
  padding-top: 120px;
  width: 1000%;
}
.finca-details
{
  background-color: rgba(255, 255, 255);
  border: 2px solid #ccc;
  border-radius: 10px;
  padding: 20px;
}
.btn-year
{
  color: white;
  border: 2px solid #ccc;
  margin-right: 30px;
  margin-bottom: 10px;
  background-color: #264d34;
  text-align: center;
}

.btn-year.selected
{
  background-color: #477a58;
}

.data
{
  color: #000000;
  font-size: 1.2em;
  margin-bottom: 10px;
  margin-right: 120px;
}

.data strong {
  color: #477a58;
}
.containerDatos
{
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.finca-mapa,
.cosecha-grafica {
  flex: 1;
}

</style>
