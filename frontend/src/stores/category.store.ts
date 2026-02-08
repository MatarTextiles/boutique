import { defineStore } from 'pinia'
import { ref } from 'vue'
import { categoryApi } from '@/api/category.api'
import type { Category } from '@/types/category'

export const useCategoryStore = defineStore('category', () => {
  const categories = ref<Category[]>([])
  const loading = ref(false)

  async function fetchCategories() {
    if (categories.value.length > 0) return
    loading.value = true
    try {
      const { data } = await categoryApi.getAll()
      categories.value = data
    } finally {
      loading.value = false
    }
  }

  return { categories, loading, fetchCategories }
})
