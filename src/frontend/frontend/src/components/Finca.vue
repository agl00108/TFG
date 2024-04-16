<template>
  <div class="finca-details">
    <h2>Datos de la Finca</h2>
    <div v-if="finca">
      <p><strong>Polígono:</strong> {{ finca.poligono }}</p>
      <p><strong>Parcela:</strong> {{ finca.parcela }}</p>
      <p><strong>Recinto:</strong> {{ finca.recinto }}</p>
      <p><strong>Área:</strong> {{ finca.area }}</p>
      <p><strong>Año SIGPAC:</strong> {{ finca.anioSigpac }}</p>
      <p><strong>Zona Ubicación:</strong> {{ finca.zonaUbicacion }}</p>
      <p><strong>Código Municipio:</strong> {{ finca.municipioCodigo }}</p>
      <p><strong>Código Provincia:</strong> {{ finca.provinciaCodigo }}</p>
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
.finca-details {
  background-color: rgba(255, 255, 255, 0.7);
  border-radius: 10px;
  padding: 20px;
}
</style>