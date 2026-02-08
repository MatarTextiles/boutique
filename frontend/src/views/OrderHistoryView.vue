<script setup lang="ts">
import { onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
import { useOrderStore } from '@/stores/order.store'
import OrderHistoryItem from '@/components/order/OrderHistoryItem.vue'
import LoadingSpinner from '@/components/ui/LoadingSpinner.vue'

const { t } = useI18n()
const orderStore = useOrderStore()

onMounted(async () => {
  await orderStore.fetchOrders()
})
</script>

<template>
  <div class="page container">
    <h1 class="page-title">{{ t('orders.title') }}</h1>

    <LoadingSpinner v-if="orderStore.loading" />
    <template v-else>
      <div v-if="orderStore.orders.length" class="orders-list">
        <OrderHistoryItem v-for="order in orderStore.orders" :key="order.id" :order="order" />
      </div>
      <p v-else class="text-center mt-3">{{ t('orders.no_orders') }}</p>
    </template>
  </div>
</template>

<style scoped>
.orders-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
</style>
