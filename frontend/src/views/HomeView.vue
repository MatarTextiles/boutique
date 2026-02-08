<script setup lang="ts">
import { onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
import { useCategoryStore } from '@/stores/category.store'
import { useProductStore } from '@/stores/product.store'
import ProductGrid from '@/components/product/ProductGrid.vue'
import LoadingSpinner from '@/components/ui/LoadingSpinner.vue'

const { locale, t } = useI18n()
const categoryStore = useCategoryStore()
const productStore = useProductStore()

onMounted(async () => {
  await Promise.all([
    categoryStore.fetchCategories(),
    productStore.fetchProducts({ size: 8 })
  ])
})
</script>

<template>
  <div>
    <section class="hero">
      <div class="container hero-content">
        <h1>{{ t('home.hero_title') }}</h1>
        <p>{{ t('home.hero_subtitle') }}</p>
        <RouterLink to="/catalog" class="btn btn-secondary btn-lg">{{ t('home.shop_now') }}</RouterLink>
      </div>
    </section>

    <section class="container page">
      <h2 class="section-title">{{ t('home.featured_categories') }}</h2>
      <div class="grid grid-3">
        <RouterLink
          v-for="cat in categoryStore.categories"
          :key="cat.id"
          :to="`/catalog/${cat.slug}`"
          class="category-card card"
        >
          <div class="category-image">
            <img :src="cat.imageUrl || '/images/placeholder.jpg'" :alt="locale === 'fr' ? cat.nameFr : cat.nameEn" />
          </div>
          <div class="category-info">
            <h3>{{ locale === 'fr' ? cat.nameFr : cat.nameEn }}</h3>
            <p>{{ locale === 'fr' ? cat.descriptionFr : cat.descriptionEn }}</p>
          </div>
        </RouterLink>
      </div>

      <h2 class="section-title mt-3">{{ t('home.featured_products') }}</h2>
      <LoadingSpinner v-if="productStore.loading" />
      <ProductGrid v-else :products="productStore.products" />
      <div class="text-center mt-2">
        <RouterLink to="/catalog" class="btn btn-outline">{{ t('home.view_all') }}</RouterLink>
      </div>
    </section>
  </div>
</template>

<style scoped>
.hero {
  background: linear-gradient(135deg, var(--color-primary-dark) 0%, var(--color-primary) 100%);
  color: var(--color-text-inverse);
  padding: 4rem 0;
  text-align: center;
}
.hero-content h1 {
  font-size: 2.5rem;
  color: var(--color-text-inverse);
  margin-bottom: 1rem;
}
.hero-content p {
  font-size: 1.15rem;
  opacity: 0.9;
  margin-bottom: 2rem;
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
}
.section-title {
  font-size: 1.75rem;
  margin-bottom: 1.5rem;
  text-align: center;
}
.category-card {
  text-decoration: none;
  color: inherit;
  display: block;
}
.category-image {
  aspect-ratio: 16/9;
  overflow: hidden;
  background: var(--color-border-light);
}
.category-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s ease;
}
.category-card:hover .category-image img {
  transform: scale(1.05);
}
.category-info {
  padding: 1rem;
}
.category-info h3 {
  font-size: 1.1rem;
  margin-bottom: 0.25rem;
}
.category-info p {
  font-size: 0.85rem;
  color: var(--color-text-light);
}

@media (max-width: 768px) {
  .hero-content h1 {
    font-size: 1.75rem;
  }
}
</style>
