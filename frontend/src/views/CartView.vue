<script setup lang="ts">
import { useI18n } from 'vue-i18n'
import { useCartStore } from '@/stores/cart.store'
import { useAuthStore } from '@/stores/auth.store'
import CartItem from '@/components/cart/CartItem.vue'
import CartSummary from '@/components/cart/CartSummary.vue'

const { t } = useI18n()
const cartStore = useCartStore()
const authStore = useAuthStore()
</script>

<template>
  <div class="page container">
    <h1 class="page-title">{{ t('cart.title') }}</h1>

    <div v-if="cartStore.items.length === 0" class="empty-cart text-center mt-3">
      <p>{{ t('cart.empty') }}</p>
      <RouterLink to="/catalog" class="btn btn-primary mt-2">{{ t('cart.continue_shopping') }}</RouterLink>
    </div>

    <div v-else class="cart-layout">
      <div class="cart-items">
        <CartItem
          v-for="item in cartStore.items"
          :key="item.productId"
          :item="item"
          @update-quantity="cartStore.updateQuantity"
          @remove="cartStore.removeItem"
        />
        <div class="cart-actions mt-2">
          <button class="btn btn-outline btn-sm" @click="cartStore.clear()">{{ t('cart.clear') }}</button>
        </div>
      </div>
      <CartSummary>
        <RouterLink
          v-if="authStore.isAuthenticated"
          to="/checkout"
          class="btn btn-primary btn-lg"
          style="width: 100%; margin-top: 1rem;"
        >
          {{ t('cart.checkout') }}
        </RouterLink>
        <RouterLink
          v-else
          to="/login?redirect=/checkout"
          class="btn btn-primary btn-lg"
          style="width: 100%; margin-top: 1rem;"
        >
          {{ t('nav.login') }} & {{ t('cart.checkout') }}
        </RouterLink>
      </CartSummary>
    </div>
  </div>
</template>

<style scoped>
.cart-layout {
  display: grid;
  grid-template-columns: 1fr 350px;
  gap: 2rem;
  align-items: start;
}
.empty-cart p {
  font-size: 1.1rem;
  color: var(--color-text-light);
}

@media (max-width: 768px) {
  .cart-layout {
    grid-template-columns: 1fr;
  }
}
</style>
