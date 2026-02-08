<script setup lang="ts">
import { useI18n } from 'vue-i18n'
import type { Order } from '@/types/order'

defineProps<{ order: Order }>()

const { locale, t } = useI18n()

function formatDate(dateStr: string) {
  return new Date(dateStr).toLocaleDateString()
}
</script>

<template>
  <div class="order-summary">
    <h3>{{ t('orders.order_number', { id: order.id }) }}</h3>
    <p class="order-date">{{ t('orders.date') }}: {{ formatDate(order.createdAt) }}</p>
    <p class="order-status">
      {{ t('orders.status') }}:
      <strong>{{ t(`orders.statuses.${order.status}`) }}</strong>
    </p>
    <div class="order-items mt-2">
      <div v-for="item in order.items" :key="item.id" class="order-line">
        <span>{{ locale === 'fr' ? item.productNameFr : item.productNameEn }} x{{ item.quantity }}</span>
        <span>${{ item.subtotal?.toFixed(2) }}</span>
      </div>
    </div>
    <div class="order-total">
      <span>{{ t('cart.total') }}</span>
      <span>${{ order.totalAmount.toFixed(2) }}</span>
    </div>
  </div>
</template>

<style scoped>
.order-summary h3 {
  margin-bottom: 0.5rem;
}
.order-date, .order-status {
  font-size: 0.9rem;
  color: var(--color-text-light);
  margin-bottom: 0.25rem;
}
.order-line {
  display: flex;
  justify-content: space-between;
  padding: 0.4rem 0;
  font-size: 0.9rem;
  border-bottom: 1px solid var(--color-border-light);
}
.order-total {
  display: flex;
  justify-content: space-between;
  padding: 0.75rem 0;
  font-weight: 700;
  font-size: 1.1rem;
  border-top: 2px solid var(--color-primary);
  margin-top: 0.5rem;
}
</style>
