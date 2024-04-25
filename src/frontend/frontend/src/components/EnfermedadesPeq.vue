<template>
  <div class="table-container">
    <table class="custom-table">
      <thead>
      <tr>
        <th v-for="header in headers" :key="header" style="font-weight: bold; color: #004d40;">{{ header }}</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(row, index) in data" :key="index">
        <td v-for="(cell, cellIndex) in row" :key="cellIndex" style="color: black;">{{ cell }}</td>
      </tr>
      </tbody><div class="button-container">
      <router-link class="btn btn-primary button-custom" to="/enfermedad">Más información</router-link>
    </div>
    </table>

  </div>
</template>

<script setup>
const headers = ['ENFERMEDAD', 'NOMBRE CIENTÍFICO', 'DESCRIPCIÓN'];

const data = [
  ['Abichado del olivo', 'Euzophera Pinguis',
    'El adulto es una mariposa de 2-2,5 cm con alas de color marrón con dos bandas transversales más claras. Depositva ' +
    'unos huevos ovalados, aplanados y reticulados, de color blanquecino que posteriormente viran al rosa, difíciles de ' +
    'ver (1x0,75 mm). La larva llega a alcanzar los 2,5 cm.'],
  ['Mosca del olivo', 'Bactrocera oleae Gmel',
    'Es la plaga que más perjuicio económico produce en el cultivo del olivo, su incidencia es muy variable en cada campaña.' +
    ' El individuo es una mosca típica de colores marrones. Las hembras son mayores que los machos. El huevo es blanco, ' +
    'alargado y cilíndrico. Son depositados bajo la epidermis de la aceituna en una pequeña cámara que la hembra prepara con su oviscapto. Solo se aprecia un pequeño corte y una manchita'],
  ['Repilo', 'Spilocea Oleagina',
    'Ocasionada por un hongo, afecta principalmente a hojas ocasionando defoliaciones y a veces ataca a los frutos.'],
  ['Prais del olivo ', 'Prays oleae Bern',
    'Su principal huésped es el olivo. Tiene tres generaciones anuales en tres órganos vegetativos diferentes:' +
    ' flor, fruto y hoja. Provocan pérdidas considerables. El adulto es una polilla gris plateada. El huevo es ' +
    'blanquecino y amarillento al eclosionar. La crisálida la realiza protegida por sedas y restos vegetales.'],
  ['Verticilosis', 'Verticillum dahliae Kleb',
    'Enfermedad causada por un hongo. La fuente principal de infecciones son los microesclerocios que están en el suelo,' +
    ' donde pueden persistir más de diez años. La mayoría están en la capa arable aunque pueden hallarse a un metro de ' +
    'profundidad.']
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
  background-color: rgba(255, 255, 255, 0.7);
}

.custom-table th,
.custom-table td
{
  border: 2px solid #000;
  padding: 8px;
  text-align: left;
}

.button-container
{
  padding-top: 10px;
  width: 200%;
  align-content: center;
}
.button-custom
{
  background-color: #264d34;
  color: white;
  border-color: white;
  text-align: center;
}

</style>
