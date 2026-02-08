<script setup lang="ts">
import { useI18n } from 'vue-i18n'
import type { Order } from '@/types/order'

defineProps<{ order: Order }>()

const { t } = useI18n()

function statusBadgeClass(status: string) {
  const map: Record<string, string> = {
    PENDING: 'badge-warning',
    CONFIRMED: 'badge-info',
    SHIPPED: 'badge-primary',
    DELIVERED: 'badge-success',
    CANCELLED: 'badge-danger'
  }
  return map[status] || 'badge-info'
}

function formatDate(dateStr: string) {
  return new Date(dateStr).toLocaleDateString()
}
</script>

<template>
  <div class="order-item card">
    <div class="order-item-inner">
      <div class="order-header">
        <h4>{{ t('orders.order_number', { id: order.id }) }}</h4>
        <span class="badge" :class="statusBadgeClass(order.status)">
          {{ t(`orders.statuses.${order.status}`) }}
        </span>
      </div>
      <div class="order-details">
        <p><strong>{{ t('orders.date') }}:</strong> {{ formatDate(order.createdAt) }}</p>
        <p><strong>{{ t('orders.total') }}:</strong> ${{ order.totalAmount.toFixed(2) }}</p>
        <p><strong>Items:</strong> {{ order.items.length }}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.order-item-inner {
  padding: 1.25rem;
}
.order-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 0.75rem;
}
.order-header h4 {
  font-size: 1rem;
}
.order-details p {
  font-size: 0.9rem;
  margin-bottom: 0.25rem;
  color: var(--color-text-light);
}
</style>
