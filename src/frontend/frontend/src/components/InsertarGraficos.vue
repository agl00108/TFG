<template>
  <div>
    <button @click="fetchHistorico" class="btn btn-success btn-year" style="display: block;">Cargar Datos</button>
    <div v-if="historico.length">
      <Graficos
          v-if="historico.length"
          :finca="finca"
          :reflectanciaData="reflectanciaData"
          :temperaturaData="temperaturaData"
          :lluviaData="lluviaData"
      />
    </div>
  </div>
</template>

<script>
import Graficos from './Graficos.vue';

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
      reflectanciaData: [],
      temperaturaData: [],
      lluviaData: []
    };
  },
  methods: {
    fetchHistorico()
    {
      const url = `/TFG/provincia/${this.finca.codigoProvincia}/municipio/${this.finca.municipioCodigo}/finca/${this.finca.poligono}/${this.finca.parcela}/${this.finca.recinto}/historico/${this.year}`;
      fetch(url)
          .then(response => response.json())
          .then(data => {
            if (Array.isArray(data))
            {
              this.reflectanciaData = [];
              this.temperaturaData = [];
              this.lluviaData = [];
              this.historico = [];
              this.historico =data;
              console.log(this.historico);
              data.sort((a, b) => new Date(a.fecha) - new Date(b.fecha));

              data.forEach(item =>
              {
                if (item.reflectancia && item.temperatura && item.lluvia)
                {
                  const reflectanciaJSON = JSON.parse(atob(item.reflectancia));
                  const temperaturaJSON = JSON.parse(atob(item.temperatura));
                  const lluvia = item.lluvia;

                  this.reflectanciaData.push(reflectanciaJSON);
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
