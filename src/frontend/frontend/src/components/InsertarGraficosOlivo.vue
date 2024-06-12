<template>
  <div>
    <button @click="fetchHistorico" class="btn btn-success btn-year" style="display: block;">Cargar Datos</button>
    <div v-if="reflectanciaDataS.length">
      <Graficos
          v-if="reflectanciaDataS.length"
          :id="id"
          :reflectanciaDataS="reflectanciaDataS"
          :reflectanciaDataD="reflectanciaDataD"
          :reflectanciaDataSF="reflectanciaDataSF"
          :reflectanciaDataFD="reflectanciaDataFD"
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
      reflectanciaDataSF: [],
      reflectanciaDataFD: [],
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
      const fincaUrl = `/TFG/olivo/${this.id}/finca`;
      fetch(fincaUrl)
          .then(response => response.json())
          .then(fincaData =>
          {
            this.finca = fincaData;
            console.log("FINCA:"+fincaData);

            const urlSF = `/TFG/provincia/23/municipio/${this.finca.municipioCodigo}/finca/${this.finca.poligono}/${this.finca.parcela}/${this.finca.recinto}/historico/${this.year}/sat`;
            const urlDF = `/TFG/provincia/23/municipio/${this.finca.municipioCodigo}/finca/${this.finca.poligono}/${this.finca.parcela}/${this.finca.recinto}/historico/${this.year}/dron`;

            // Fetch para los datos satelitales
            fetch(urlSF)
                .then(response => response.json())
                .then(data => {
                  if (Array.isArray(data))
                  {
                    this.reflectanciaDataSF = [];
                    data.sort((a, b) => new Date(a.fecha) - new Date(b.fecha));
                    data.forEach(item => {
                      if (item.reflectancia)
                      {
                        const reflectanciaJSON = JSON.parse(atob(item.reflectancia));
                        const fecha = new Date(item.fecha);
                        let mes = fecha.toLocaleString('default', { month: 'long' });
                        mes = mes.charAt(0).toUpperCase() + mes.slice(1);
                        reflectanciaJSON.mes = mes;
                        this.reflectanciaDataSF.push(reflectanciaJSON);
                      } else {
                        console.error('El item no contiene los datos esperados.');
                      }
                    });
                  } else {
                    console.error('La respuesta no es un array.');
                  }
                })
                .catch(error =>
                {
                  console.error('Error fetching sat data:', error);
                });

            fetch(urlDF)
                .then(response => response.json())
                .then(data => {
                  if (Array.isArray(data))
                  {
                    this.reflectanciaDataFD = [];
                    data.sort((a, b) => new Date(a.fecha) - new Date(b.fecha));
                    data.forEach(item => {
                      if (item.reflectancia)
                      {
                        const reflectanciaJSON = JSON.parse(atob(item.reflectancia));
                        this.reflectanciaDataFD.push(reflectanciaJSON);
                      } else {
                        console.error('El item no contiene los datos esperados.');
                      }
                    });
                  } else
                  {
                    console.error('La respuesta no es un array.');
                  }
                })
                .catch(error => {
                  console.error('Error fetching dron data:', error);
                });
          })
          .catch(error => {
            console.error('Error fetching finca data:', error);
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
