<template>
  <div>
    <button @click="fetchHistorico" class="btn btn-success btn-year" style="display: block;">Cargar Datos</button>
    <div v-if="reflectanciaDataS.length">
      <Graficos
          v-if="reflectanciaDataS.length"
          :id="id"
          :reflectanciaDataS="reflectanciaDataS"
          :reflectanciaDataD="reflectanciaDataD"
      />
    </div>
  </div>
</template>

<script>
import Graficos from './GraficosOlivo.vue';

export default {
  components: {
    Graficos
  },
  props: {
    year: Number,
    id: Number
  },
  data() {
    return {
      reflectanciaDataS: [],
      reflectanciaDataD: [],
    };
  },
  methods: {
    fetchHistorico()
    {
      const urlS = `/TFG/historico/${this.year}/olivo/${this.id}/sat`;
      fetch(urlS)
          .then(response => {
            console.log(response);
            return response.json();
          })
          .then(data => {
            console.log(data);
            if (Array.isArray(data))
            {
              this.reflectanciaDataS= [];
              data.sort((a, b) => new Date(a.fecha) - new Date(b.fecha));

              data.forEach(item =>
              {
                if (item.reflectancia)
                {
                  const reflectanciaJSON = JSON.parse(atob(item.reflectancia));
                  this.reflectanciaDataS.push(reflectanciaJSON);
                } else {
                  console.error('El item no contiene los datos esperados.');
                }
              });
            } else {
              console.error('La respuesta no es un array.');
            }
          })
          .catch(error => {
            console.error('Error fetching historical data:', error);
          });

      const urlD = `/TFG/historico/${this.year}/olivo/${this.id}/dron`;
      fetch(urlD)
          .then(response => response.json())
          .then(data => {
            if (Array.isArray(data))
            {
              this.reflectanciaDataD= [];
              data.sort((a, b) => new Date(a.fecha) - new Date(b.fecha));

              data.forEach(item =>
              {
                if (item.reflectancia)
                {
                  const reflectanciaJSON = JSON.parse(atob(item.reflectancia));
                  const fecha = new Date(item.fecha);
                  let mes = fecha.toLocaleString('default', { month: 'long' });
                  // Convertir la primera letra a mayúscula
                  mes = mes.charAt(0).toUpperCase() + mes.slice(1);
                  reflectanciaJSON.mes = mes;
                  this.reflectanciaDataD.push(reflectanciaJSON);
                } else {
                  console.error('El item no contiene los datos esperados.');
                }
              });
            } else {
              console.error('La respuesta no es un array.');
            }
          })
          .catch(error => {
            console.error('Error fetching historical data:', error);
          });
      console.log("DRON: "+ this.reflectanciaDataD.length);
      console.log("SATELITE: "+ this.reflectanciaDataS.length);
    },
  }
};
</script>

<style>
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
</style>
