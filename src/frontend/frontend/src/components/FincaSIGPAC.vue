<script>
import FincaMapa from "@/components/FincaMapa.vue";
export default {
  components: {
    FincaMapa,
  },
  data() {
    return {
      zona: {},
    };
  },
  props: {
    finca: {
      type: Object,
      required: true,
    },
    zonaUrl: {
      type: String,
      required: true,
    },
  },
  watch: {
    finca: {
      immediate: true,
      handler(newFinca) {
        if (newFinca) {
          fetch(`/TFG/provincias/${newFinca.provinciaCodigo}/municipios/${newFinca.municipioCodigo}/zonas/${newFinca.zonaUbicacion}`)
              .then((response) => response.json())
              .then((data) => {
                this.zona = data;
                //console.log(this.zona);
              });
        }
      },
    },
  },
};
</script>

<template>
  <div class="finca-details">
    <div v-if="finca && Object.keys(zona).length">
      <h3>Finca {{finca.zonaUbicacion}}</h3>
      <p><strong>SIGPAC:</strong> {{ finca.provinciaCodigo }}:{{ finca.municipioCodigo }}:0:0:{{ finca.poligono }}:{{ finca.parcela }}:{{ finca.recinto }} </p>
      <p><strong>Municipio:</strong> {{ finca.municipioCodigo }}</p>
      <p><strong>Provincia:</strong> {{ finca.provinciaCodigo }}</p>
      <p><strong>Coordenadas:</strong> {{zona.latitud}}, {{zona.longitud}}</p>
      <FincaMapa :latitud="zona.latitud" :longitud="zona.longitud" :geoJSONUrl="zonaUrl" />
    </div>
    <div v-else>
      <p>Cargando datos de la finca...</p>
    </div>
  </div>
</template>


<style scoped>
.finca-details {
  background-color: rgba(255, 255, 255, 0.7);
  border-radius: 10px;
  padding: 10px;
}
</style>
