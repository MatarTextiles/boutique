<script setup lang="ts">
import { ref } from 'vue'
import { useI18n } from 'vue-i18n'
import { useRouter } from 'vue-router'
import { useCartStore } from '@/stores/cart.store'
import { useOrderStore } from '@/stores/order.store'
import CartSummary from '@/components/cart/CartSummary.vue'
import BaseInput from '@/components/ui/BaseInput.vue'
import BaseButton from '@/components/ui/BaseButton.vue'

const { t } = useI18n()
const router = useRouter()
const cartStore = useCartStore()
const orderStore = useOrderStore()

const shippingAddress = ref('')
const notes = ref('')
const error = ref('')
const success = ref(false)

async function placeOrder() {
  if (!shippingAddress.value.trim()) {
    error.value = 'Shipping address is required'
    return
  }
  error.value = ''
  try {
    await orderStore.placeOrder({
      shippingAddress: shippingAddress.value,
      notes: notes.value || undefined,
      items: cartStore.items.map((item: { productId: number; quantity: number }) => ({
        productId: item.productId,
        quantity: item.quantity
      }))
    })
    cartStore.clear()
    success.value = true
    setTimeout(() => router.push('/account/orders'), 2000)
  } catch {
    error.value = 'Failed to place order'
  }
}
</script>

<template>
  <div class="page container">
    <h1 class="page-title">{{ t('checkout.title') }}</h1>

    <div v-if="success" class="alert alert-success text-center">
      {{ t('checkout.order_success') }}
    </div>

    <div v-else-if="cartStore.items.length === 0" class="text-center mt-3">
      <p>{{ t('cart.empty') }}</p>
      <RouterLink to="/catalog" class="btn btn-primary mt-2">{{ t('cart.continue_shopping') }}</RouterLink>
    </div>

    <div v-else class="checkout-layout">
      <div class="checkout-form">
        <div v-if="error" class="alert alert-danger">{{ error }}</div>
        <form @submit.prevent="placeOrder">
          <div class="form-group">
            <label>{{ t('checkout.shipping_address') }} *</label>
            <textarea v-model="shippingAddress" class="form-control" rows="3" required></textarea>
          </div>
          <BaseInput v-model="notes" :label="t('checkout.notes')" />
          <BaseButton :loading="orderStore.loading" class="btn-lg" style="width: 100%; margin-top: 1rem;">
            {{ t('checkout.place_order') }}
          </BaseButton>
        </form>
      </div>
      <CartSummary />
    </div>
  </div>
</template>

<style scoped>
.checkout-layout {
  display: grid;
  grid-template-columns: 1fr 350px;
  gap: 2rem;
  align-items: start;
}
.checkout-form {
  background: var(--color-bg-alt);
  padding: 1.5rem;
  border-radius: var(--radius-md);
  box-shadow: var(--shadow-sm);
}
textarea.form-control {
  resize: vertical;
  font-family: var(--font-family);
}

@media (max-width: 768px) {
  .checkout-layout {
    grid-template-columns: 1fr;
  }
}
</style>
