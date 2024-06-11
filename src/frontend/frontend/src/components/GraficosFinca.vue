<template>
  <div>
    <div v-if="loaded">
      <div class="chart">
        <apexchart type="line" :options="SAVIOptions" :series="SAVIseries" height="350"></apexchart>
      </div>
      <div class="chart">
        <apexchart type="line" :options="NDVIOptions" :series="NDVIseries" height="350"></apexchart>
      </div>
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
    reflectanciaDataS: {
      type: Array,
      required: true,
    },
    reflectanciaDataD: {
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
      NDVIseries: [],
      SAVIseries: [],
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
      SAVIOptions: {
        chart: {
          height: 350,
          type: 'line',
          stacked: false,
          zoom: {
            enabled: false
          },
        },
        colors: ['#224930', '#09f15d'],
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
          text: 'Índice de Vegetación Ajustado al Suelo (SAVI)',
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
      NDVIOptions:
      {
        chart:
        {
          height: 350,
          type: 'line',
          stacked: false,
          zoom: {
            enabled: false
          },
        },
        colors: ['#224930', '#09f15d'],
        dataLabels:
        {
          enabled: false
        },
        stroke:
        {
          width: [2],
          curve: 'smooth',
        },
        markers:
        {
          size: 5,
          shape: "circle",
        },
        title:
        {
          text: 'Índice de Vegetación de Diferencia Normalizada (NDVI)',
          align: 'center',
          style: {
            fontSize: '24px',
          },
        },
        xaxis: {
          categories: []
        },
        tooltip:
        {
          y: [
            {
              title: {
                formatter: function (val) {
                  return val
                }
              }
            }]},
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
      this.NDVIseries=[];
      this.SAVIseries=[];
      const indicesSeries = [
        {
          name: "NDMI",
          data: this.reflectanciaDataS.map(item =>
          {
            if (item && item.data)
            {
              const ndmiObj = item.data.find(subItem => Object.keys(subItem)[0] === 'NDMI');
              return ndmiObj ? parseFloat(Object.values(ndmiObj)[0].replace(',', '.')) : null;
            }
            return null;
          }),
          options:
          {
            ...this.chartOptions,
            title: {
              text: 'Índice de Humedad de Diferencia Normalizada (NDMI)',
              align: 'center',
              style: {
                fontSize: '24px',
              },
            }
          }
        }
      ];

      const NDVISeries = [
        {
          name: "Sentinel-2:",
          data: this.reflectanciaDataS.map(item => {
            if (item && item.data) {
              const ndviObj = item.data.find(subItem => Object.keys(subItem)[0] === 'NDVI');
              return ndviObj ? parseFloat(Object.values(ndviObj)[0].replace(',', '.')) : null;
            }
            return null;
          }),
        },];

      const SAVISeries= [
        {
          name: "Sentinel-2: ",
          data: this.reflectanciaDataS.map(item =>
          {
            if (item && item.data)
            {
              const saviObj = item.data.find(subItem => Object.keys(subItem)[0] === 'SAVI');
              return saviObj ? parseFloat(Object.values(saviObj)[0].replace(',', '.')) : null;
            }
            return null;
          })
        }];

      const temperaturaSeries = [
        {
          name: "Máxima Día °C:",
          data: this.temperaturaData.map(item => {
            const maxDiaObj = item.data.find(subItem => Object.keys(subItem)[0] === 'max_dia');
            return maxDiaObj ? parseFloat(Object.values(maxDiaObj)[0].replace(',', '.')).toFixed(2) : null;
          }),
          markers: {
            size: 5,
            shape: "circle",
          },
          color: "#264d34",
        },
        {
          name: "Mínima Día °C:",
          data: this.temperaturaData.map(item => {
            const minDiaObj = item.data.find(subItem => Object.keys(subItem)[0] === 'min_dia');
            return minDiaObj ? parseFloat(Object.values(minDiaObj)[0].replace(',', '.')).toFixed(2) : null;
          }),
          markers: {
            size: 5,
            shape: "circle",
          },
          color: "#467c59",
        },
        {
          name: "Media Día °C:",
          data: this.temperaturaData.map(item => {
            const meanDiaObj = item.data.find(subItem => Object.keys(subItem)[0] === 'mean_dia');
            return meanDiaObj ? parseFloat(Object.values(meanDiaObj)[0].replace(',', '.')).toFixed(2) : null;
          }),
          markers: {
            size: 5,
            shape: "circle",
          },
          color: "#00ff64",
        },
        {
          name: "Máxima Noche °C:",
          data: this.temperaturaData.map(item => {
            const maxNocheObj = item.data.find(subItem => Object.keys(subItem)[0] === 'max_noc');
            return maxNocheObj ? parseFloat(Object.values(maxNocheObj)[0].replace(',', '.')).toFixed(2) : null;
          }),
          markers: {
            size: 5,
            shape: "diamond",
          },
          color: "#264d34",
        },
        {
          name: "Mínima Noche °C:",
          data: this.temperaturaData.map(item => {
            const minNocheObj = item.data.find(subItem => Object.keys(subItem)[0] === 'min_noc');
            return minNocheObj ? parseFloat(Object.values(minNocheObj)[0].replace(',', '.')).toFixed(2) : null;
          }),
          markers: {
            size: 5,
            shape: "diamond",
          },
          color: "#467c59",
        },
        {
          name: "Media Noche °C:",
          data: this.temperaturaData.map(item => {
            const meanNocheObj = item.data.find(subItem => Object.keys(subItem)[0] === 'mean_noc');
            return meanNocheObj ? parseFloat(Object.values(meanNocheObj)[0].replace(',', '.')).toFixed(2) : null;
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
          name: "Lluvia (litros):",
          data: this.lluviaData.map(item => parseFloat(item).toFixed(2)),
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

      const allMonths = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];
      const dataPerMonthDNDVI = new Array(12).fill(null);
      const dataPerMonthDSAVI = new Array(12).fill(null);


      this.reflectanciaDataD.forEach(item =>
      {
        if (item && item.data)
        {
          const ndviDron = item.data.find(subItem => Object.keys(subItem)[0] === 'NDVI');
          const saviDron = item.data.find(subItem => Object.keys(subItem)[0] === 'SAVI');
          if (ndviDron)
          {
            const value = typeof Object.values(ndviDron)[0] === 'string' ? parseFloat(Object.values(ndviDron)[0].replace(',', '.')) : Object.values(ndviDron)[0];
            const monthIndex = allMonths.indexOf(item.mes);
            if (monthIndex !== -1)
            {
              dataPerMonthDNDVI[monthIndex] = value > 0 ? value : null;
            }
          }
          if (saviDron)
          {
            const value = typeof Object.values(saviDron)[0] === 'string' ? parseFloat(Object.values(saviDron)[0].replace(',', '.')) : Object.values(saviDron)[0];
            const monthIndex = allMonths.indexOf(item.mes);
            if (monthIndex !== -1)
            {
              dataPerMonthDSAVI[monthIndex] = value > 0 ? value : null;
            }
          }
        }
      });

      NDVISeries.push({
        name: "Dron: ",
        data: dataPerMonthDNDVI,
      });

      SAVISeries.push({
        name: "Dron: ",
        data: dataPerMonthDSAVI,
      });

      this.NDVIOptions.xaxis.categories = allMonths;
      this.SAVIOptions.xaxis.categories = allMonths;

      this.chartOptions.xaxis.categories = this.temperaturaData.map(item => {
        const mesObj = item.data.find(subItem => Object.keys(subItem)[0] === 'Mes');
        return mesObj ? Object.values(mesObj)[0] : null;
      });

      this.series = [];
      this.temperaturaSeries=[];
      this.NDVIseries = [...this.NDVIseries, ...NDVISeries];
      this.SAVIseries = [...this.SAVIseries, ...SAVISeries];

      if (this.reflectanciaDataS.length > 0)
      {
        this.series = [...this.series, ...indicesSeries];
      }
      if (this.temperaturaData.length > 0)
      {
        this.temperaturaSeries = [...this.temperaturaSeries, ...temperaturaSeries];
      }
      if (this.lluviaData.length > 0)
      {
        this.series = [...this.series, ...lluviaSeries];
      }
    }
  },

  watch: {
    reflectanciaDataS: {
      handler() {
        this.prepareChartData();
      },
      deep: true,
    },
    reflectanciaDataD: {
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
