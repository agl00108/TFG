<template>
  <div>
    <div v-if="loaded">
      <div class="chart" v-for="(serie, index) in series" :key="index">
        <apexchart type="line" :options="serie.options" :series="[serie]" height="350"></apexchart>
      </div>
      <div id="chart">
        <apexchart type="line" height="350" :options="chartOptions" :series="temperaturaSeries"></apexchart>
      </div>
    </div>
    <div v-else>
      <p>Cargando datos...</p>
    </div>
  </div>
</template>

<script>
import VueApexCharts from 'vue3-apexcharts';

export default {
  components: {
    apexchart: VueApexCharts,
  },
  props: {
    finca: {
      type: Object,
      required: true,
    },
    reflectanciaData: {
      type: Array,
      required: true,
    },
    temperaturaData: {
      type: Array,
      required: true,
    },
    lluviaData: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      series: [],
      temperaturaSeries: [],
      chartOptions: {
        chart: {
          height: 350,
          type: 'line',
          zoom: {
            enabled: false
          },
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          width: [5],
          curve: 'smooth',
        },
        title: {
          text: 'Información sobre temperaturas',
          align: 'left'
        },
        xaxis: {
          categories: []
        },
        tooltip: {
          y: [
            {
              title: {
                formatter: function (val) {
                  return val
                }
              }
            }
          ]
        },
      },
      loaded: false
    };
  },
  mounted() {
    this.loaded = true;
    this.prepareChartData();
  },
  methods: {
    prepareChartData() {
      const indicesSeries = [
        {
          name: "NDVI",
          data: this.reflectanciaData.map(item => {
            if (item && item.data) {
              const ndviObj = item.data.find(subItem => Object.keys(subItem)[0] === 'NDVI');
              return ndviObj ? parseFloat(Object.values(ndviObj)[0].replace(',', '.')) : null;
            }
            return null;
          }),
          options: {
            ...this.chartOptions,
            title: {
              text: 'Índice de Vegetación de Diferencia Normalizada | NDVI |',
              align: 'left'
            }
          }
        },
        {
          name: "NDMI",
          data: this.reflectanciaData.map(item => {
            if (item && item.data) {
              const ndmiObj = item.data.find(subItem => Object.keys(subItem)[0] === 'NDMI');
              return ndmiObj ? parseFloat(Object.values(ndmiObj)[0].replace(',', '.')) : null;
            }
            return null;
          }),
          options: {
            ...this.chartOptions,
            title: {
              text: 'Índice de Humedad de Diferencia Normalizada | NDMI |',
              align: 'left'
            }
          }
        },
        {
          name: "SAVI",
          data: this.reflectanciaData.map(item => {
            if (item && item.data) {
              const saviObj = item.data.find(subItem => Object.keys(subItem)[0] === 'SAVI');
              return saviObj ? parseFloat(Object.values(saviObj)[0].replace(',', '.')) : null;
            }
            return null;
          }),
          options: {
            ...this.chartOptions,
            title: {
              text: 'Índice de Vegetación Ajustado al Suelo | SAVI |',
              align: 'left'
            }
          }
        }
      ];

      const temperaturaSeries = [
        {
          name: "Max Día",
          data: this.temperaturaData.map(item => {
            const maxDiaObj = item.data.find(subItem => Object.keys(subItem)[0] === 'max_dia');
            return maxDiaObj ? parseFloat(Object.values(maxDiaObj)[0].replace(',', '.')) : null;
          })
        },
        {
          name: "Min Día",
          data: this.temperaturaData.map(item => {
            const minDiaObj = item.data.find(subItem => Object.keys(subItem)[0] === 'min_dia');
            return minDiaObj ? parseFloat(Object.values(minDiaObj)[0].replace(',', '.')) : null;
          })
        },
        {
          name: "Media Día",
          data: this.temperaturaData.map(item => {
            const meanDiaObj = item.data.find(subItem => Object.keys(subItem)[0] === 'mean_dia');
            return meanDiaObj ? parseFloat(Object.values(meanDiaObj)[0].replace(',', '.')) : null;
          })
        },
        {
          name: "Max Noche",
          data: this.temperaturaData.map(item => {
            const maxNocheObj = item.data.find(subItem => Object.keys(subItem)[0] === 'max_noc');
            return maxNocheObj ? parseFloat(Object.values(maxNocheObj)[0].replace(',', '.')) : null;
          })
        },
        {
          name: "Min Noche",
          data: this.temperaturaData.map(item => {
            const minNocheObj = item.data.find(subItem => Object.keys(subItem)[0] === 'min_noc');
            return minNocheObj ? parseFloat(Object.values(minNocheObj)[0].replace(',', '.')) : null;
          })
        },
        {
          name: "Media Noche",
          data: this.temperaturaData.map(item => {
            const meanNocheObj = item.data.find(subItem => Object.keys(subItem)[0] === 'mean_noc');
            return meanNocheObj ? parseFloat(Object.values(meanNocheObj)[0].replace(',', '.')) : null;
          })
        }
      ];

      const lluviaSeries = [
        {
          name: "Lluvia",
          data: this.lluviaData,
          options: {
            ...this.chartOptions,
            title: {
              text: 'Información sobre Precipitaciones',
              align: 'left'
            }
          }
        }
      ];

      const categories = this.temperaturaData.map(item => {
        const mesObj = item.data.find(subItem => Object.keys(subItem)[0] === 'Mes');
        return mesObj ? Object.values(mesObj)[0] : null;
      });

      this.chartOptions.xaxis.categories = categories;

      this.series = [];
      if (this.reflectanciaData.length > 0)
      {
        this.series = [...this.series, ...indicesSeries];
        console.log('Indices');
      }
      if (this.temperaturaData.length > 0)
      {
        this.temperaturaSeries = [...this.temperaturaSeries, ...temperaturaSeries];
        console.log('Temperatura');
      }
      if (this.lluviaData.length > 0)
      {
        this.series = [...this.series, ...lluviaSeries];
        console.log('lluvia');
      }
    }
  },
};
</script>

<style scoped>
.chart {
  margin-bottom: 20px;
}
</style>
