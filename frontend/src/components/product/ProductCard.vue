<script setup lang="ts">
import { useI18n } from 'vue-i18n'
import type { Product } from '@/types/product'

defineProps<{ product: Product }>()

const { locale } = useI18n()
</script>

<template>
  <RouterLink :to="`/product/${product.slug}`" class="product-card card">
    <div class="product-image">
      <img :src="product.imageUrl || '/images/placeholder.jpg'" :alt="locale === 'fr' ? product.nameFr : product.nameEn" />
    </div>
    <div class="product-info">
      <h3 class="product-name">{{ locale === 'fr' ? product.nameFr : product.nameEn }}</h3>
      <p class="product-category">{{ locale === 'fr' ? product.categoryNameFr : product.categoryNameEn }}</p>
    </div>
  </RouterLink>
</template>

<style scoped>
.product-card {
  display: block;
  text-decoration: none;
  color: inherit;
}
.product-image {
  position: relative;
  aspect-ratio: 1;
  overflow: hidden;
  background: var(--color-border-light);
}
.product-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s ease;
}
.product-card:hover .product-image img {
  transform: scale(1.05);
}
.product-info {
  padding: 0.85rem;
}
.product-name {
  font-size: 0.95rem;
  font-family: var(--font-family);
  color: var(--color-text);
  margin-bottom: 0.25rem;
  font-weight: 600;
}
.product-category {
  font-size: 0.8rem;
  color: var(--color-text-light);
  margin-bottom: 0.5rem;
}
</style>
