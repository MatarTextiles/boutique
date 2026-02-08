import api from './axios'
import type { Category } from '@/types/category'

export const categoryApi = {
  getAll() {
    return api.get<Category[]>('/categories')
  },
  getBySlug(slug: string) {
    return api.get<Category>(`/categories/${slug}`)
  }
}
