import { useCartStore } from '@/stores/cart.store'
import type { Product } from '@/types/product'
import { ref } from 'vue'

export function useCart() {
  const cartStore = useCartStore()
  const addedMessage = ref('')

  function addToCart(product: Product, quantity: number = 1) {
    cartStore.addItem(product, quantity)
    addedMessage.value = product.nameFr
    setTimeout(() => { addedMessage.value = '' }, 2000)
  }

  return {
    items: cartStore.items,
    totalItems: cartStore.totalItems,
    totalPrice: cartStore.totalPrice,
    addToCart,
    addedMessage,
    removeItem: cartStore.removeItem,
    updateQuantity: cartStore.updateQuantity,
    clear: cartStore.clear
  }
}
