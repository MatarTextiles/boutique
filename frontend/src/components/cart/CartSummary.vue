<script setup lang="ts">
import { useI18n } from 'vue-i18n'
import { useCartStore } from '@/stores/cart.store'

const { t } = useI18n()
const cartStore = useCartStore()
</script>

<template>
  <div class="cart-summary card">
    <div class="summary-inner">
      <h3>{{ t('checkout.order_summary') }}</h3>
      <div class="summary-row">
        <span>{{ t('cart.subtotal') }} ({{ cartStore.totalItems }} items)</span>
        <span>${{ cartStore.totalPrice.toFixed(2) }}</span>
      </div>
      <div class="summary-total">
        <span>{{ t('cart.total') }}</span>
        <span>${{ cartStore.totalPrice.toFixed(2) }}</span>
      </div>
      <slot />
    </div>
  </div>
</template>

<style scoped>
.summary-inner {
  padding: 1.5rem;
}
.summary-inner h3 {
  margin-bottom: 1rem;
  font-size: 1.1rem;
}
.summary-row {
  display: flex;
  justify-content: space-between;
  padding: 0.5rem 0;
  color: var(--color-text-light);
  font-size: 0.9rem;
}
.summary-total {
  display: flex;
  justify-content: space-between;
  padding: 0.75rem 0;
  margin-top: 0.5rem;
  border-top: 2px solid var(--color-primary);
  font-weight: 700;
  font-size: 1.1rem;
}
</style>
