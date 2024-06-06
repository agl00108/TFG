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
    id: {
      type: Number,
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
  },
  data()
  {
    return {
      series: [],
      NDVIseries: [],
      SAVIseries: [],
      chartOptions: {
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
          text: 'Chart Title',
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
  mounted()
  {
    this.loaded = true;
    console.log("RECIBIDO: "+ this.reflectanciaDataS);

    this.prepareChartData();
  },
  methods: {
    prepareChartData()
    {
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
          name: "Sentinel-2",
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
          name: "Sentinel-2",
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

      //Si se le han mandado datos de refectancia de dron
      if (Array.isArray(this.reflectanciaDataD) && this.reflectanciaDataD.length > 0 && this.reflectanciaDataD.some(item => item && item.data))
      {
        NDVISeries.push
        ({
          name: "Dron",
          data: this.reflectanciaDataD.map(item =>
          {
            if (item && item.data)
            {
              const ndviDron = item.data.find(subItem => Object.keys(subItem)[0] === 'NDVI');
              if (ndviDron) {
                const value = typeof Object.values(ndviDron)[0] === 'string' ? parseFloat(Object.values(ndviDron)[0].replace(',', '.')) : Object.values(ndviDron)[0];
                return value > 0 ? value : null;
              }
            }
            return null;
          }),
        });

        SAVISeries.push
        ({
          name: "Dron",
          data: this.reflectanciaDataD.map(item =>
          {
            if (item && item.data)
            {
              const saviSat = item.data.find(subItem => Object.keys(subItem)[0] === 'SAVI');
              if (saviSat)
              {
                const value = Object.values(saviSat)[0];
                return typeof value === 'string' ? parseFloat(value.replace(',', '.')) : value;
              }
            }
            return null;
          }),
        });
      }

      const categories = this.reflectanciaDataS.map(item =>
      {
        const mesObj = item.data.find(subItem => Object.keys(subItem)[0] === 'Mes');
        console.log(Object.values(mesObj)[0]);
        return mesObj ? Object.values(mesObj)[0] : null;
      });

      this.chartOptions.xaxis.categories = categories;
      this.SAVIOptions.xaxis.categories = categories;
      this.NDVIOptions.xaxis.categories = categories;

      this.series = [];
      this.NDVIseries= [];
      this.SAVIseries= [];

      if (this.reflectanciaDataS.length > 0)
      {
        this.series = [...this.series, ...indicesSeries];
        this.NDVIseries = [...this.NDVIseries, ...NDVISeries];
        this.SAVIseries = [...this.SAVIseries, ...SAVISeries];
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
        console.log("RECIBIDO: "+ this.reflectanciaDataD);
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
.chart
{
  margin-bottom: 20px;
}
</style>
