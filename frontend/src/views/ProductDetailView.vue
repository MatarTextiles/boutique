<script setup lang="ts">
import { onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
import { useRoute } from 'vue-router'
import { useProductStore } from '@/stores/product.store'
import LoadingSpinner from '@/components/ui/LoadingSpinner.vue'

const { locale, t } = useI18n()
const route = useRoute()
const productStore = useProductStore()

onMounted(async () => {
  await productStore.fetchBySlug(route.params.slug as string)
})
</script>

<template>
  <div class="page container">
    <LoadingSpinner v-if="productStore.loading" />
    <div v-else-if="productStore.currentProduct" class="product-detail">
      <div class="product-detail-image">
        <img :src="productStore.currentProduct.imageUrl || '/images/placeholder.jpg'" :alt="locale === 'fr' ? productStore.currentProduct.nameFr : productStore.currentProduct.nameEn" />
      </div>
      <div class="product-detail-info">
        <p class="product-category-label">
          <RouterLink :to="`/catalog/${productStore.currentProduct.categorySlug}`">
            {{ locale === 'fr' ? productStore.currentProduct.categoryNameFr : productStore.currentProduct.categoryNameEn }}
          </RouterLink>
        </p>
        <h1>{{ locale === 'fr' ? productStore.currentProduct.nameFr : productStore.currentProduct.nameEn }}</h1>

        <div class="product-description mt-2">
          <h3>{{ t('product.description') }}</h3>
          <p>{{ locale === 'fr' ? productStore.currentProduct.descriptionFr : productStore.currentProduct.descriptionEn }}</p>
        </div>

        <div v-if="productStore.currentProduct.sku" class="product-sku mt-1">
          <small>{{ t('product.sku') }}: {{ productStore.currentProduct.sku }}</small>
        </div>

        <div class="product-cta mt-2">
          <RouterLink to="/contact" class="btn btn-primary btn-lg">
            {{ t('product.contact_us') }}
          </RouterLink>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.product-detail {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 3rem;
  align-items: start;
}
.product-detail-image {
  border-radius: var(--radius-md);
  overflow: hidden;
  background: var(--color-border-light);
}
.product-detail-image img {
  width: 100%;
  aspect-ratio: 1;
  object-fit: cover;
}
.product-category-label {
  margin-bottom: 0.5rem;
}
.product-category-label a {
  color: var(--color-primary-dark);
  font-size: 0.9rem;
  text-transform: uppercase;
  letter-spacing: 1px;
}
.product-detail-info h1 {
  font-size: 1.8rem;
  margin-bottom: 0.75rem;
}
.product-description h3 {
  font-size: 1rem;
  margin-bottom: 0.5rem;
}
.product-description p {
  color: var(--color-text-light);
  line-height: 1.7;
}
.product-sku {
  color: var(--color-text-light);
}

@media (max-width: 768px) {
  .product-detail {
    grid-template-columns: 1fr;
    gap: 1.5rem;
  }
}
</style>
