import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import type { Product } from '@/types/product'

export interface CartItem {
  productId: number
  nameFr: string
  nameEn: string
  slug: string
  price: number
  imageUrl?: string
  quantity: number
}

export const useCartStore = defineStore('cart', () => {
  const items = ref<CartItem[]>(loadCart())

  const totalItems = computed(() => items.value.reduce((sum, item) => sum + item.quantity, 0))
  const totalPrice = computed(() => items.value.reduce((sum, item) => sum + item.price * item.quantity, 0))

  function loadCart(): CartItem[] {
    const stored = localStorage.getItem('cart')
    return stored ? JSON.parse(stored) : []
  }

  function saveCart() {
    localStorage.setItem('cart', JSON.stringify(items.value))
  }

  function addItem(product: Product, quantity: number = 1) {
    const existing = items.value.find(i => i.productId === product.id)
    if (existing) {
      existing.quantity += quantity
    } else {
      items.value.push({
        productId: product.id,
        nameFr: product.nameFr,
        nameEn: product.nameEn,
        slug: product.slug,
        price: product.price,
        imageUrl: product.imageUrl,
        quantity
      })
    }
    saveCart()
  }

  function removeItem(productId: number) {
    items.value = items.value.filter(i => i.productId !== productId)
    saveCart()
  }

  function updateQuantity(productId: number, quantity: number) {
    const item = items.value.find(i => i.productId === productId)
    if (item) {
      if (quantity <= 0) {
        removeItem(productId)
      } else {
        item.quantity = quantity
        saveCart()
      }
    }
  }

  function clear() {
    items.value = []
    saveCart()
  }

  return { items, totalItems, totalPrice, addItem, removeItem, updateQuantity, clear }
})
