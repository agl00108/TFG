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
          stacked: false,
          zoom: {
            enabled: false
          },
        },
        colors: ['#008FFB', '#00E396', '#FEB019', '#FF4560', '#775DD0', '#546E7A', '#26a69a'],
        dataLabels: {
          enabled: false
        },
        stroke: {
          width: [2],
          curve: 'smooth',
        },
        markers: {
          size: 5,
          shape: "circle",
        },
        title: {
          text: 'Temperaturas registradas',
          align: 'center',
          style: {
            fontSize: '24px',
          },
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
    prepareChartData()
    {
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
              text: 'Índice de Vegetación de Diferencia Normalizada (NDVI)',
              align: 'center',
              style: {
                fontSize: '24px',
              },
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
              text: 'Índice de Humedad de Diferencia Normalizada (NDMI)',
              align: 'center',
              style: {
                fontSize: '24px',
              },
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
              text: 'Índice de Vegetación Ajustado al Suelo (SAVI)',
              align: 'center',
              style: {
                fontSize: '24px',
              },
            }
          }
        }
      ];

      const temperaturaSeries = [
        {
          name: "Máxima Día",
          data: this.temperaturaData.map(item => {
            const maxDiaObj = item.data.find(subItem => Object.keys(subItem)[0] === 'max_dia');
            return maxDiaObj ? parseFloat(Object.values(maxDiaObj)[0].replace(',', '.')) : null;
          }),
          markers: {
            size: 5,
            shape: "circle",
          },
          color: "#264d34",
        },
        {
          name: "Mínima Día",
          data: this.temperaturaData.map(item => {
            const minDiaObj = item.data.find(subItem => Object.keys(subItem)[0] === 'min_dia');
            return minDiaObj ? parseFloat(Object.values(minDiaObj)[0].replace(',', '.')) : null;
          }),
          markers: {
            size: 5,
            shape: "circle",
          },
          color: "#467c59",
        },
        {
          name: "Media Día",
          data: this.temperaturaData.map(item => {
            const meanDiaObj = item.data.find(subItem => Object.keys(subItem)[0] === 'mean_dia');
            return meanDiaObj ? parseFloat(Object.values(meanDiaObj)[0].replace(',', '.')) : null;
          }),
          markers: {
            size: 5,
            shape: "circle",
          },
          color: "#00ff64",
        },
        {
          name: "Máxima Noche",
          data: this.temperaturaData.map(item => {
            const maxNocheObj = item.data.find(subItem => Object.keys(subItem)[0] === 'max_noc');
            return maxNocheObj ? parseFloat(Object.values(maxNocheObj)[0].replace(',', '.')) : null;
          }),
          markers: {
            size: 5,
            shape: "diamond",
          },
          color: "#264d34",
        },
        {
          name: "Mínima Noche",
          data: this.temperaturaData.map(item => {
            const minNocheObj = item.data.find(subItem => Object.keys(subItem)[0] === 'min_noc');
            return minNocheObj ? parseFloat(Object.values(minNocheObj)[0].replace(',', '.')) : null;
          }),
          markers: {
            size: 5,
            shape: "diamond",
          },
          color: "#467c59",
        },
        {
          name: "Media Noche",
          data: this.temperaturaData.map(item => {
            const meanNocheObj = item.data.find(subItem => Object.keys(subItem)[0] === 'mean_noc');
            return meanNocheObj ? parseFloat(Object.values(meanNocheObj)[0].replace(',', '.')) : null;
          }),
          markers: {
            size: 5,
            shape: "diamond",
          },
          color: "#00ff64",
        }
      ];

      const lluviaSeries = [
        {
          name: "Lluvia",
          data: this.lluviaData,
          options: {
            ...this.chartOptions,
            title: {
              text: 'Precipitaciones registradas',
              align: 'center',
              style: {
                fontSize: '24px',
              },
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
      this.temperaturaSeries=[];
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
  watch: {
    reflectanciaData: {
      handler() {
        this.prepareChartData();
      },
      deep: true,
    },
    temperaturaData: {
      handler() {
        this.prepareChartData();
      },
      deep: true,
    },
    lluviaData: {
      handler() {
        this.prepareChartData();
      },
      deep: true,
    },
  },
};
</script>

<style scoped>
.chart {
  margin-bottom: 20px;
}

</style>
