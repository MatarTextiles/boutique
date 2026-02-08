<script setup lang="ts">
defineProps<{
  variant?: 'primary' | 'secondary' | 'outline' | 'danger'
  size?: 'sm' | 'md' | 'lg'
  disabled?: boolean
  loading?: boolean
}>()
</script>

<template>
  <button
    class="btn"
    :class="[`btn-${variant || 'primary'}`, size ? `btn-${size}` : '']"
    :disabled="disabled || loading"
  >
    <span v-if="loading" class="btn-spinner"></span>
    <slot />
  </button>
</template>

<style scoped>
button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}
.btn-spinner {
  width: 16px;
  height: 16px;
  border: 2px solid rgba(255,255,255,0.3);
  border-top-color: #fff;
  border-radius: 50%;
  animation: spin 0.6s linear infinite;
}
@keyframes spin {
  to { transform: rotate(360deg); }
}
</style>
