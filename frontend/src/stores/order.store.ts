import { defineStore } from 'pinia'
import { ref } from 'vue'
import { orderApi } from '@/api/order.api'
import type { Order, CreateOrderRequest } from '@/types/order'

export const useOrderStore = defineStore('order', () => {
  const orders = ref<Order[]>([])
  const currentOrder = ref<Order | null>(null)
  const totalPages = ref(0)
  const loading = ref(false)

  async function placeOrder(request: CreateOrderRequest) {
    loading.value = true
    try {
      const { data } = await orderApi.create(request)
      currentOrder.value = data
      return data
    } finally {
      loading.value = false
    }
  }

  async function fetchOrders(params?: { page?: number; size?: number }) {
    loading.value = true
    try {
      const { data } = await orderApi.getAll(params)
      orders.value = data.content
      totalPages.value = data.totalPages
    } finally {
      loading.value = false
    }
  }

  async function fetchOrderById(id: number) {
    loading.value = true
    try {
      const { data } = await orderApi.getById(id)
      currentOrder.value = data
    } finally {
      loading.value = false
    }
  }

  return { orders, currentOrder, totalPages, loading, placeOrder, fetchOrders, fetchOrderById }
})
