import api from './axios'

export const adminApi = {
  uploadCategoryImage(id: number, file: File) {
    const formData = new FormData()
    formData.append('file', file)
    return api.post<{ imageUrl: string }>(`/admin/categories/${id}/image`, formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
  },
  uploadProductImage(id: number, file: File) {
    const formData = new FormData()
    formData.append('file', file)
    return api.post<{ imageUrl: string }>(`/admin/products/${id}/image`, formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
  }
}
