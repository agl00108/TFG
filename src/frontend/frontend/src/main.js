import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './assets/css/bootstrap.min.css'
import './assets/js/bootstrap.bundle.min.js'
import store from './store';

const app = createApp(App).use(store).use(router).mount('#app');
