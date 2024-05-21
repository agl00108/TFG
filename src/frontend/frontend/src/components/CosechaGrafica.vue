<template>
  <div>
    <div id="chart">
      <apexchart type="line" height="350" :options="chartOptions" :series="series"></apexchart>
    </div>
  </div>
</template>

<script>
import VueApexCharts from 'vue3-apexcharts';

export default {
  components:
      {
    apexchart: VueApexCharts,
  },
  props: {
    finca:
        {
      type: Object,
      required: true,
    },
  },
  data()
  {
    return {
      series: [{
        name: "Kg de Aceitunas",
        data: []
      },
        {
          name: "Kg de Aceite",
          data: []
        }
      ],
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
        colors: ['#264d34', '#35c567'],
        stroke: {
          width: [5, 7, 5],
          curve: 'straight',
          dashArray: [0, 8, 5]
        },
        title: {
          text: 'Estadísticas de Cosechas Anuales',
            align: 'center',
            style: {
              fontSize: '24px',
            },
        },
        legend: {
          tooltipHoverFormatter: function(val, opts)
          {
            return val + ' - <strong>' + opts.w.globals.series[opts.seriesIndex][opts.dataPointIndex] + '</strong>'
          }
        },
        markers: {
          size: 0,
          hover: {
            sizeOffset: 6
          }
        },
        xaxis: {
          categories: []
        },
        tooltip: {
          y: [
            {
              title: {
                formatter: function (val)
                {
                  return val + " (kg)"
                }
              }
            },
            {
              title: {
                formatter: function (val)
                {
                  return val + " (kg)"
                }
              }
            }
          ]
        },
        grid:
            {
              borderColor: '#f1f1f1',
            }
      }
    };
  },
  mounted() {
    this.fetchCosechasData();
  },
  methods: {
    async fetchCosechasData() {
      try {
        const url=`/TFG/provincia/${this.finca.codigoProvincia}/municipio/${this.finca.municipioCodigo}/finca/${this.finca.poligono}/${this.finca.parcela}/${this.finca.recinto}/cosechas`;
        const response = await fetch(url);
        let data = await response.json();
        data = data.sort((a, b) => a.campania - b.campania);

        const categories = [];
        data.forEach(entry =>
        {
          this.series[0].data.push(Math.round(entry.aceitunas));
          this.series[1].data.push(Math.round(entry.aceite));
          categories.push(entry.campania);
        });

        this.chartOptions = {
          ...this.chartOptions,
          xaxis: {
            ...this.chartOptions.xaxis,
            categories: categories
          }
        };
      } catch (error) {
        console.error('Error al obtener los datos de cosechas:', error);
      }
    },
  },
};
</script>

<style>
  #chart {
    margin-top: 20px;
  }
</style>
