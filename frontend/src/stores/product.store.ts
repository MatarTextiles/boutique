import { defineStore } from 'pinia'
import { ref } from 'vue'
import { productApi } from '@/api/product.api'
import type { Product } from '@/types/product'

export const useProductStore = defineStore('product', () => {
  const products = ref<Product[]>([])
  const currentProduct = ref<Product | null>(null)
  const totalPages = ref(0)
  const totalElements = ref(0)
  const currentPage = ref(0)
  const loading = ref(false)

  async function fetchProducts(params?: { page?: number; size?: number; category?: string; search?: string }) {
    loading.value = true
    try {
      const { data } = await productApi.getAll(params)
      products.value = data.content
      totalPages.value = data.totalPages
      totalElements.value = data.totalElements
      currentPage.value = data.number
    } finally {
      loading.value = false
    }
  }

  async function fetchBySlug(slug: string) {
    loading.value = true
    try {
      const { data } = await productApi.getBySlug(slug)
      currentProduct.value = data
    } finally {
      loading.value = false
    }
  }

  return { products, currentProduct, totalPages, totalElements, currentPage, loading, fetchProducts, fetchBySlug }
})
