import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import OlivoView from "../views/OlivoView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/enfermedad',
      name: 'enfermedad',
      component: () => import('../views/EnfermedadView.vue')
    },
    {
      path: '/finca',
      name: 'finca',
      component: () => import('../views/FincaView.vue')
    },
    {
      path: '/indices',
      name: 'indice',
      component: () => import('../views/IndicesVegetacionView.vue')
    },
    {
      path: '/fincaEsp',
      name: 'fincaEsp',
      component: () => import('../views/FincaEspecifica.vue')
    },
    {
      path: '/OlivoView/:idObjeto',
      name: 'OlivoView',
      component: OlivoView,
      props: true
    }
  ]
})

export default router
