<template>
  <div class="container">
    <div class="content">
      <div class="search-form-container">
    <form @submit.prevent="buscarFinca" class="search-form">
      <LeafletMap />
      <div class="input-group">
        <input v-model="codigoSIGPAC" type="text" class="form-control" placeholder="Introduce el código SIGPAC">
        <button type="submit" class="btn btn-success"><i class="bi bi-search"></i> Buscar</button>
      </div>
      <div v-if="mostrarErrorFormato" class="error-message">
        Formato SIGPAC no válido (provincia:municipio:agregado:zona:polígono:parcela:recinto)
      </div>
      <div v-if="mostrarErrorEncontrado" class="error-message">
        Código SIGPAC no encontrado.
      </div>
    </form>
      </div>
        <div class="info-ind">
          <IndicesPeq />
        </div>
      </div>
    <div class="info-enf">
      <EnfermedadesPeq />
    </div>
      <main>
      </main>
    </div>
</template>

<script>
import LeafletMap  from "@/components/MapaFincasProvincia.vue";
import EnfermedadesPeq from "@/components/EnfermedadesPeq.vue";
import IndicesPeq from "@/components/IndicesPeq.vue";
export default
{
  components:
      {
    IndicesPeq,
    EnfermedadesPeq,
    LeafletMap,
  },
  methods:
  {
    buscarFinca()
    {
      const formatoValido = /^[0-9]+:[0-9]+:[0-9]+:[0-9]+:[0-9]+:[0-9]+:[0-9]+$/.test(this.codigoSIGPAC);
      const [provinciaCodigo, municipioCodigo, , , poligono, parcela, recinto] = this.codigoSIGPAC.split(':');
      fetch(`/TFG/provincia/${provinciaCodigo}/municipio/${municipioCodigo}/finca/${poligono}/${parcela}/${recinto}`)
          .then(response =>
          {
            if (!response.ok) {
              throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();})
          .then(data => {
            this.$store.commit('setFinca', data);
            this.$router.push({name: 'fincaEsp'});
          })
          .catch(error => {
            console.error('Error en la solicitud:', error);
            if (!formatoValido)
            {
              this.mostrarErrorFormato = true;
              this.mostrarErrorEncontrado = false;
            }
            else
            {
              this.mostrarErrorFormato = false;
              this.mostrarErrorEncontrado = true;
            }
          });
    }
  }
};
</script>

<style scoped>
.container
{
  padding-top: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.content
{
  display: flex;
  justify-content: space-around;
  width: 130%;
  margin-bottom: 20px;
}

.search-form-container
{
  background-color: rgba(255, 255, 255, 0.8);
  border: 2px solid #ccc;
  border-radius: 10px;
  padding: 20px;
  margin-top: 120px;
  margin-bottom: auto;
  text-align: center;
}

.info-ind
{
  margin-top: 100px;
  width: 500px;
}
.info-enf
{
  margin-top: -230px;
  margin-left: -80px;
  margin-right: 185px;
}
</style>
