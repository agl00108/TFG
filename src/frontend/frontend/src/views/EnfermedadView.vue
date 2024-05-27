<template>
  <div class="table-container">
    <table class="custom-table">
      <thead>
      <tr>
        <th v-for="header in headers" :key="header" style="font-weight: bold;background-color: #0c4128; color: white;">{{ header }}</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(row, index) in data" :key="index">
        <td v-for="(cell, cellIndex) in row" :key="cellIndex" style="color: black;">
          <strong v-if="cellIndex < 2">{{ cell }}</strong>
          <span v-else v-html="cell.replace(/\n/g, '<br>')"></span>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
const headers = ['ENFERMEDAD', 'NOMBRE CIENTÍFICO', 'RIESGO NULO', 'RIESGO BAJO', 'RIESGO MEDIO', 'RIESGO ALTO', 'RIESGO EXTREMO'];

const data = [
  ['Abichado del olivo', 'Euzophera Pinguis',
   '-Temperatura: inferior a 10°C o superior a 35°C \n' + '-Humedad: inferior al 40% \n' + '-Presencia de depredadores\n' + '-Fuera de la época\n',
  '-Temperatura:' + 'entre 10°C y 20°C\n' + '-Humedad: \n' + 'entre 40% y 60%\n' + '-Presencia de depredadores\n' + '-Fuera de la época\n',
  '-Temperatura: entre 25°C y 30°C\n' + '-Humedad: entre 40% y 60%\n' + '-Presencia de depredadores\n' + '-Época: abril-junio, agosto-octubre\n',
  '-Temperatura: entre 25°C y 30°C\n' + '-Humedad: superior al 80%\n' + '-Ausencia de depredadores\n' + '-Época: abril-junio, agosto-octubre',
  '-Presencia de larvas\n' + '-Temperatura: entre 25°C y 30°C\n' + '-Humedad: superior al 80%\n' + '-Ausencia de depredadores\n' + '-Época: abril-junio, agosto-octubre'],
  ['Mosca del olivo', 'Bactrocera oleae Gmel',
    '-Fuera de la época\n' + '-Ausencia de frutos\n' + '-Temperatura: por encima de 35°C\n' + '-Humedad: por debajo del 40% durante períodos prolongados.\n',
    '-Temprano o tarde (julio-agosto  octubre-noviembre)\n' + '-Presencia de frutos: aceitunas verdes jóvenes\n' + '-Temperatura: medias diarias por debajo de 20°C.\n' + 'Humedad: 40-60%',
    '-Temporada: agosto-septiembre\n' + '-Presencia de frutos maduros\n' + '-Temperatura: medias diarias entre 20-30°C\n' + '-Humedad: 60-80%',
    '-Temporada alta con olas de calor (+ de 3 días con 30º)\n' + '-Presencia de frutos abundantes.\n' + '-Temperatura: 25-30º\n' + '-Humedad: niveles superiores al 80%.',
    '-Presencia de larvas\n' + '-Temporada alta con olas de calor (+ de 3 días con 30º)\n' + '-Presencia de frutos abundantes.\n' + '-Temperatura: 25-30º\n' + '-Humedad: niveles superiores al 80%.'],
  ['Repilo', 'Spilocea Oleagina',
    '-Fuera de la época.\n' + '-Temperatura: <10°C o > 32°C.\n' + '-Humedad: < 40%',
    '-Época: Invierno o primavera\n' + '-Temperatura: 10°C-15°C.\n' + '-Humedad: 40%-60%.',
    '-Época: Invierno o primavera.\n' + '-Temperatura: 15°C-20°C.\n' + '-Humedad: 60%-80%.',
    '-Época: Invierno o primavera.\n' + '-Temporada alta: olas de calor.\n' + '-Presencia de frutos.\n' + '-Temperatura: 25°C-30°C.\n' + '-Humedad: \n' + '> al 80%.\n',
    '-Epidemia en curso.\n' + '-Época: Invierno o primavera.\n' + '-Temporada alta: olas de calor.\n' + '-Presencia de frutos.\n' + '-Temperatura: 25°C-30°C.\n' + '-Humedad: \n' + '> al 80%.\n'],
  ['Prais del olivo ', 'Prays oleae Bern',
    '-Época: fuera de la temporada alta.\n' + '-Temperatura: <10°C o > 40°C.\n' + '-Humedad: < 40%.\n' + '-Ausencia de frutos.\n',
    '-Época: \n' + 'Generación: Filófaga (invierno) o antófaga (primavera).\n' + '-Temperatura:\n' + '10°C-15°C.\n' + '-Humedad: 40%-60%.\n' + '-Presencia de frutos limitada.\n',
    '-Época: Antófaga (primavera) o carpófaga (verano).\n' + '-Temperatura: 15°C-25°C.\n' + '-Humedad: 60%-80%.\n' + '-Presencia de frutos jóvenes.\n',
    '-Temporada alta: primavera y verano.\n' + '-Presencia de frutos  maduros.\n' + '-Temperatura: 25°C-35°C.\n' + '-Humedad: > 80%\n' + '-Generación: Carpófaga (verano) con condiciones climáticas ideales.',
    '-Epidemia en curso\n' + '-Temporada alta: primavera y verano.\n' + '-Presencia de frutos  maduros.\n' + '-Temperatura: 25°C-35°C.\n' + '-Humedad: > 80%\n' + '-Generación: Carpófaga (verano) con condiciones climáticas ideales.'],
  ['Verticilosis', 'Verticillum dahliae Kleb',
    '-Inóculo del patógeno: ausente\n' + '-Temperatura: <10°C o > 40°C\n' + '-Precipitación: Anual < 200 mm\n' + '-Humedad: < 40%\n' + '-Época: Invierno',
    '-Inóculo del patógeno: Niveles muy bajos (< 1 ppg)\n' + '-Temperatura: 15°C - 22°C\n' + '-Precipitación: Anual 400-600 mm\n' + '-Humedad: 40%-60%\n' + '-Época: Cualquier época',
    '-Inóculo del patógeno: Niveles bajos ( 1- 10 ppg)\n' + '-Temperatura: 10°C - 25°C\n' + '-Precipitación: Anual 600-800 mm\n' + '-Humedad: 40% - 60%\n' + '-Época: Cualquier época',
    '-Inóculo del patógeno: Niveles moderados (entre 10 y 40 ppg)\n' + '-Temperatura: 20°C - 30°C\n' + '-Precipitación: Anual entre 800 y 1000 mm\n' + '-Humedad: 60% - 80%\n' + '-Época: Primavera, Verano',
    '-Inóculo del patógeno: Niveles altos (> 40 ppg)\n' + '-Temperatura: > 25°C\n' + '-Precipitación: Anual > 1000 mm\n' + '-Humedad: > 80%\n' + '-Época: Primavera, Verano']
];
</script>

<style scoped>
.table-container
{
  display: flex;
  justify-content: center;
  padding-top: 100px;
  padding-bottom: 90px;
  width: 160%;
}
.custom-table
{
  border-collapse: collapse;
  background-color: rgba(255, 255, 255, 0.9);
}

.custom-table th,
.custom-table td
{
  border: 2px solid #000;
  padding: 8px;
  text-align: left;
}

</style>
