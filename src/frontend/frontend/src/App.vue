<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div class="container">
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
              <router-link class="nav-link active" aria-current="page" to="/">Home</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/finca">Finca</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/enfermedad">Enfermedad</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/analisis">Analisis</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
  </div>

  <main>
    <RouterView />
  </main>

</template>

<style>
header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background-color: #22472e;
}
.logo {
  max-width: 160px;
  height: auto;
}
body {
  background-image: url('../src/assets/img/fondo2.png');
  background-size: cover;
  background-repeat: no-repeat;
}
</style>
