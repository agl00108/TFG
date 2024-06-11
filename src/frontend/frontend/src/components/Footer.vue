<script setup>
const Footer = {
  name: 'Footer'
};
</script>

<template>
  <footer class="footer mt-auto py-3">
    <div class="container text-center">
      <p class="text-white m-0">Alba Gómez Liébana | Grado en Ingeniería Informática</p>
      <a class="text-white m-0" href="https://www.ujaen.es/" target="_blank" rel="noopener noreferrer">Universidad de Jaén</a>
    </div>
  </footer>
</template>

<style scoped>
.footer
{
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  background-color: #224930;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  z-index: 2000;
}

.footer p
{
  margin: 5px 0;
}

.footer a {
  text-decoration: none;
  color: inherit;
}
</style>
