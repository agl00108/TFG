<script setup>
import { RouterLink, RouterView } from 'vue-router'
import Footer from '@/components/Footer.vue'
</script>

<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-dark">
      <div class="container-fluid">
        <router-link class="navbar-brand" to="/"><img src="./assets/img/logo.png" alt="Logo" class="logo"></router-link>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link class="nav-link active" aria-current="page" to="/">Inicio</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/finca">Fincas</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/enfermedad">Enfermedades</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/analisis">Análisis</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/indices">Índices de vegetación</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>

  <main>
    <RouterView />
  </main>
  <Footer />
</template>

<style>
header
{
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background-color: #264d34;
  z-index: 2000;
}

.logo
{
  max-width: 160px;
  height: auto;
}

body
{
  background-image: url('./assets/img/fondo2.png');
  background-size: cover;
  background-position: center;
  background-attachment: fixed;
}
</style>

