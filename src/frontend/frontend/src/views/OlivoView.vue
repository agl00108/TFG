<template>
  <div class="container">
    <div class="finca-details">
    <h1>Informe del Olivo {{ idObjeto }}</h1>
    <div v-if="olivo">
      <p>ID: {{ this.olivo.idObjeto }}</p>
      <p>Ubicación: {{this.olivo.zonaUbicacion}}</p>
      <p>Municipio: {{ olivo.zonaMunicipioCodigo }}</p>
      <p>Provincia: {{ olivo.zonaProvinciaCodigo }}</p>
    </div>
    <div>
      <h3>Histórico de datos </h3>
      <p>Seleccione año</p>
      <button  v-for="year in years"  :key="year" @click="selectYear(year)" class="btn btn-success btn-year"
               :class="{ 'selected': selectedYear === year }" >{{ year }}</button>
    </div>
    <IndiceGrafica v-if="selectedYear" :year="selectedYear" :id="Number(idObjeto)" />
  </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import IndiceGrafica from "@/components/InsertarGraficosOlivo.vue";

export default
{
  components: {IndiceGrafica},
  props: {
    idObjeto: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      olivo: null,
      selectedYear: null,
      years: Array.from({ length: 8 }, (_, i) => 2016 + i),
    };
  },
  methods: {
    fetchOlivo(id)
    {
      fetch(`/TFG/olivos/${id}`)
          .then((response) => response.json())
          .then((data) => {
            this.olivo = data;
            console.log(this.olivo);
          });
    },
    selectYear(year)
    {
      this.selectedYear = year;
    },
  },
  mounted() {
    this.fetchOlivo(this.idObjeto);
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
</style>
