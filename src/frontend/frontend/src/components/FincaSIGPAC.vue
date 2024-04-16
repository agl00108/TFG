<template>
  <div class="finca-details">
    <div v-if="finca">
      <h3>Finca {{finca.zonaUbicacion}}</h3>
      <p><strong>SIGPAC:</strong> {{ finca.provinciaCodigo }}:{{ finca.municipioCodigo }}:0:0:{{ finca.poligono }}:{{ finca.parcela }}:{{ finca.recinto }} </p>
      <p><strong>Municipio:</strong> {{ finca.municipioCodigo }}</p>
      <p><strong>Provincia:</strong> {{ finca.provinciaCodigo }}</p>
    </div>
    <div v-else>
      <p>Cargando datos de la finca...</p>
    </div>
  </div>
</template>
<script>
export default
{
  props: {
    finca: {
      type: Object,
      required: true
    }
  }
}
</script>

<style scoped>
.finca-details
{
  background-color: rgba(255, 255, 255, 0.7);
  border-radius: 10px;
  padding: 10px;
}
</style>