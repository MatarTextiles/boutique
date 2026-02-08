import api from './axios'
import type { Product } from '@/types/product'
import type { PageResponse } from '@/types/api'

export const productApi = {
  getAll(params?: { page?: number; size?: number; category?: string; search?: string }) {
    return api.get<PageResponse<Product>>('/products', { params })
  },
  getBySlug(slug: string) {
    return api.get<Product>(`/products/${slug}`)
  }
}
