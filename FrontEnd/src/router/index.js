import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'non-profits',
      component: () => import('../views/NonProfitsView.vue')
    },
    {
      path: '/cryptocurrency-donations',
      name: 'cryptocurrency-donations',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/CryptocurrencyDonationsView.vue')
    }
  ]
})

export default router
