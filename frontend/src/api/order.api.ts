import api from './axios'
import type { Order, CreateOrderRequest } from '@/types/order'
import type { PageResponse } from '@/types/api'

export const orderApi = {
  create(data: CreateOrderRequest) {
    return api.post<Order>('/orders', data)
  },
  getAll(params?: { page?: number; size?: number }) {
    return api.get<PageResponse<Order>>('/orders', { params })
  },
  getById(id: number) {
    return api.get<Order>(`/orders/${id}`)
  }
}
