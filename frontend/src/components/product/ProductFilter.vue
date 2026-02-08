<script setup lang="ts">
import { useI18n } from 'vue-i18n'
import type { Category } from '@/types/category'

defineProps<{
  categories: Category[]
  selectedCategory?: string
  searchQuery: string
}>()

const emit = defineEmits<{
  'update:selectedCategory': [value: string | undefined]
  'update:searchQuery': [value: string]
}>()

const { locale, t } = useI18n()
</script>

<template>
  <div class="product-filter">
    <div class="filter-search">
      <input
        class="form-control"
        :placeholder="t('catalog.search_placeholder')"
        :value="searchQuery"
        @input="emit('update:searchQuery', ($event.target as HTMLInputElement).value)"
      />
    </div>
    <div class="filter-categories">
      <button
        class="filter-btn"
        :class="{ active: !selectedCategory }"
        @click="emit('update:selectedCategory', undefined)"
      >
        {{ t('catalog.all_categories') }}
      </button>
      <button
        v-for="cat in categories"
        :key="cat.id"
        class="filter-btn"
        :class="{ active: selectedCategory === cat.slug }"
        @click="emit('update:selectedCategory', cat.slug)"
      >
        {{ locale === 'fr' ? cat.nameFr : cat.nameEn }}
      </button>
    </div>
  </div>
</template>

<style scoped>
.product-filter {
  margin-bottom: 2rem;
}
.filter-search {
  margin-bottom: 1rem;
  max-width: 400px;
}
.filter-categories {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
}
.filter-btn {
  padding: 0.4rem 1rem;
  border: 1px solid var(--color-border);
  background: var(--color-bg-alt);
  border-radius: 20px;
  cursor: pointer;
  font-size: 0.85rem;
  transition: var(--transition);
}
.filter-btn.active, .filter-btn:hover {
  background: var(--color-primary);
  color: var(--color-text-inverse);
  border-color: var(--color-primary);
}
</style>
