<template>
  <div>
    <button @click="fetchHistorico" class="btn btn-success btn-year" style="display: block;">Cargar Datos</button>
    <div v-if="historico.length">
      <Graficos
          v-if="historico.length"
          :finca="finca"
          :reflectanciaDataS="reflectanciaDataS"
          :reflectanciaDataD="reflectanciaDataD"
          :temperaturaData="temperaturaData"
          :lluviaData="lluviaData"
      />
    </div>
  </div>
</template>

<script>
import Graficos from './GraficosFinca.vue';

export default {
  components: {
    Graficos
  },
  props: {
    year: Number,
    finca: Object
  },
  data() {
    return {
      historico: [],
      reflectanciaDataS: [],
      reflectanciaDataD: [],
      temperaturaData: [],
      lluviaData: []
    };
  },
  methods: {
    fetchHistorico()
    {
      const urlS = `/TFG/provincia/${this.finca.codigoProvincia}/municipio/${this.finca.municipioCodigo}/finca/${this.finca.poligono}/${this.finca.parcela}/${this.finca.recinto}/historico/${this.year}/sat`;
      fetch(urlS)
          .then(response => response.json())
          .then(data => {
            if (Array.isArray(data))
            {
              this.historico = [];
              this.reflectanciaDataS= [];
              this.temperaturaData= [];
              this.lluviaData= [];
              this.historico =data;
              data.sort((a, b) => new Date(a.fecha) - new Date(b.fecha));

              data.forEach(item =>
              {
                if (item.reflectancia && item.temperatura && item.lluvia)
                {
                  const reflectanciaJSON = JSON.parse(atob(item.reflectancia));
                  const temperaturaJSON = JSON.parse(atob(item.temperatura));
                  const lluvia = item.lluvia;

                  this.reflectanciaDataS.push(reflectanciaJSON);
                  this.temperaturaData.push(temperaturaJSON);
                  this.lluviaData.push(lluvia);
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

      this.reflectanciaDataD= [];
      const urlD = `/TFG/provincia/${this.finca.codigoProvincia}/municipio/${this.finca.municipioCodigo}/finca/${this.finca.poligono}/${this.finca.parcela}/${this.finca.recinto}/historico/${this.year}/dron`;
      fetch(urlD)
          .then(response => response.json())
          .then(data => {
            if (Array.isArray(data))
            {
              data.sort((a, b) => new Date(a.fecha) - new Date(b.fecha));

              data.forEach(item =>
              {
                if (item.reflectancia)
                {
                  const reflectanciaJSON = JSON.parse(atob(item.reflectancia));
                  this.reflectanciaDataD.push(reflectanciaJSON);
                } else {
                  console.error('El item no contiene los datos esperados.');
                }
              });
              console.log(this.reflectanciaDataD);
            } else {
              console.error('La respuesta no es un array.');
            }
          })
          .catch(error => {
            console.error('Error fetching historical data:', error);
          });
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
