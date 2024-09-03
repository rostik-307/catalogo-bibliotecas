import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/components/Home.vue'
import ListadoItem from '@/components/ListadoItem.vue'
import InsertarItem from '@/components/InsertarItem.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/listado',
      name: 'listado',
     component: ListadoItem
    },
    {
      path: '/insertar',
      name: 'insertar',
      component: InsertarItem
    }
  ]
})

export default router
