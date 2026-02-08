<script setup lang="ts">
import { ref, watch, onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
import { useRoute } from 'vue-router'
import { useCategoryStore } from '@/stores/category.store'
import { useProductStore } from '@/stores/product.store'
import ProductGrid from '@/components/product/ProductGrid.vue'
import ProductFilter from '@/components/product/ProductFilter.vue'
import LoadingSpinner from '@/components/ui/LoadingSpinner.vue'

const { t } = useI18n()
const route = useRoute()
const categoryStore = useCategoryStore()
const productStore = useProductStore()

const selectedCategory = ref<string | undefined>(route.params.slug as string | undefined)
const searchQuery = ref('')
const currentPage = ref(0)

async function loadProducts() {
  await productStore.fetchProducts({
    page: currentPage.value,
    size: 12,
    category: selectedCategory.value,
    search: searchQuery.value || undefined
  })
}

onMounted(async () => {
  await categoryStore.fetchCategories()
  await loadProducts()
})

watch([selectedCategory, searchQuery], () => {
  currentPage.value = 0
  loadProducts()
})

watch(() => route.params.slug, (newSlug) => {
  selectedCategory.value = newSlug as string | undefined
})

function changePage(page: number) {
  currentPage.value = page
  loadProducts()
}
</script>

<template>
  <div class="page container">
    <h1 class="page-title">{{ t('catalog.title') }}</h1>

    <ProductFilter
      :categories="categoryStore.categories"
      :selected-category="selectedCategory"
      :search-query="searchQuery"
      @update:selected-category="selectedCategory = $event"
      @update:search-query="searchQuery = $event"
    />

    <p class="results-count mb-2" v-if="!productStore.loading">
      {{ t('catalog.showing', { count: productStore.totalElements }) }}
    </p>

    <LoadingSpinner v-if="productStore.loading" />
    <template v-else>
      <ProductGrid v-if="productStore.products.length" :products="productStore.products" />
      <p v-else class="text-center mt-3">{{ t('catalog.no_products') }}</p>
    </template>

    <div v-if="productStore.totalPages > 1" class="pagination mt-2">
      <button
        v-for="page in productStore.totalPages"
        :key="page"
        class="page-btn"
        :class="{ active: page - 1 === currentPage }"
        @click="changePage(page - 1)"
      >
        {{ page }}
      </button>
    </div>
  </div>
</template>

<style scoped>
.results-count {
  color: var(--color-text-light);
  font-size: 0.9rem;
}
.pagination {
  display: flex;
  justify-content: center;
  gap: 0.5rem;
}
.page-btn {
  width: 36px;
  height: 36px;
  border: 1px solid var(--color-border);
  background: var(--color-bg-alt);
  border-radius: var(--radius-sm);
  cursor: pointer;
  transition: var(--transition);
}
.page-btn.active, .page-btn:hover {
  background: var(--color-primary);
  color: var(--color-text-inverse);
  border-color: var(--color-primary);
}
</style>
