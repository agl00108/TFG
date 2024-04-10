<template>
  <h1>{{ msg }}</h1>
</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      msg: ''
    }
  },
  mounted() {
    fetch("/TFG/provincias/23/municipios/59/zonas/J1/fincas/6/211/2")
        .then((response) => response.text())
        .then((data) => {
          this.msg = data;
        });
  }
}
</script>
