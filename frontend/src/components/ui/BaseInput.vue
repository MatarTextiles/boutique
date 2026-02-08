<script setup lang="ts">
defineProps<{
  label?: string
  type?: string
  placeholder?: string
  error?: string
  modelValue?: string | number
}>()

defineEmits<{
  'update:modelValue': [value: string]
}>()
</script>

<template>
  <div class="form-group">
    <label v-if="label">{{ label }}</label>
    <input
      class="form-control"
      :class="{ 'is-error': error }"
      :type="type || 'text'"
      :placeholder="placeholder"
      :value="modelValue"
      @input="$emit('update:modelValue', ($event.target as HTMLInputElement).value)"
    />
    <span v-if="error" class="error-text">{{ error }}</span>
  </div>
</template>

<style scoped>
.is-error {
  border-color: var(--color-danger);
}
.error-text {
  color: var(--color-danger);
  font-size: 0.8rem;
  margin-top: 0.25rem;
  display: block;
}
</style>
