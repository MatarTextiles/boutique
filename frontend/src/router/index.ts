import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', name: 'home', component: () => import('@/views/HomeView.vue') },
    { path: '/catalog', name: 'catalog', component: () => import('@/views/CatalogView.vue') },
    { path: '/catalog/:slug', name: 'catalog-category', component: () => import('@/views/CatalogView.vue') },
    { path: '/product/:slug', name: 'product-detail', component: () => import('@/views/ProductDetailView.vue') },
    { path: '/contact', name: 'contact', component: () => import('@/views/ContactView.vue') },
    { path: '/:pathMatch(.*)*', name: 'not-found', component: () => import('@/views/NotFoundView.vue') }
  ],
  scrollBehavior() {
    return { top: 0 }
  }
})

export default router
