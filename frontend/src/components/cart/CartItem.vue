<script setup lang="ts">
import { useI18n } from 'vue-i18n'
import type { CartItem } from '@/stores/cart.store'

defineProps<{ item: CartItem }>()

const emit = defineEmits<{
  'update-quantity': [productId: number, quantity: number]
  'remove': [productId: number]
}>()

const { locale, t } = useI18n()
</script>

<template>
  <div class="cart-item">
    <div class="cart-item-image">
      <img :src="item.imageUrl || '/images/placeholder.jpg'" :alt="locale === 'fr' ? item.nameFr : item.nameEn" />
    </div>
    <div class="cart-item-info">
      <RouterLink :to="`/product/${item.slug}`" class="cart-item-name">
        {{ locale === 'fr' ? item.nameFr : item.nameEn }}
      </RouterLink>
      <p class="cart-item-price">${{ item.price.toFixed(2) }}</p>
    </div>
    <div class="cart-item-quantity">
      <button class="qty-btn" @click="emit('update-quantity', item.productId, item.quantity - 1)">-</button>
      <span>{{ item.quantity }}</span>
      <button class="qty-btn" @click="emit('update-quantity', item.productId, item.quantity + 1)">+</button>
    </div>
    <div class="cart-item-subtotal">
      ${{ (item.price * item.quantity).toFixed(2) }}
    </div>
    <button class="cart-item-remove" @click="emit('remove', item.productId)">{{ t('cart.remove') }}</button>
  </div>
</template>

<style scoped>
.cart-item {
  display: flex;
  align-items: center;
  gap: 1rem;
  padding: 1rem;
  border-bottom: 1px solid var(--color-border-light);
}
.cart-item-image {
  width: 80px;
  height: 80px;
  flex-shrink: 0;
  border-radius: var(--radius-sm);
  overflow: hidden;
  background: var(--color-border-light);
}
.cart-item-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.cart-item-info {
  flex: 1;
}
.cart-item-name {
  font-weight: 600;
  font-size: 0.95rem;
}
.cart-item-price {
  color: var(--color-text-light);
  font-size: 0.85rem;
  margin-top: 0.25rem;
}
.cart-item-quantity {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}
.qty-btn {
  width: 28px;
  height: 28px;
  border: 1px solid var(--color-border);
  background: var(--color-bg-alt);
  border-radius: var(--radius-sm);
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1rem;
}
.cart-item-subtotal {
  font-weight: 700;
  min-width: 80px;
  text-align: right;
}
.cart-item-remove {
  background: none;
  border: none;
  color: var(--color-danger);
  cursor: pointer;
  font-size: 0.8rem;
}

@media (max-width: 768px) {
  .cart-item {
    flex-wrap: wrap;
  }
}
</style>
