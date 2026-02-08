<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
import api from '@/api/axios'
import type { User } from '@/types/user'
import BaseInput from '@/components/ui/BaseInput.vue'
import BaseButton from '@/components/ui/BaseButton.vue'
import LoadingSpinner from '@/components/ui/LoadingSpinner.vue'

const { t } = useI18n()

const profile = ref<User | null>(null)
const loading = ref(true)
const saving = ref(false)
const message = ref('')

onMounted(async () => {
  try {
    const { data } = await api.get<User>('/users/me')
    profile.value = data
  } finally {
    loading.value = false
  }
})

async function updateProfile() {
  if (!profile.value) return
  saving.value = true
  message.value = ''
  try {
    const { data } = await api.put<User>('/users/me', profile.value)
    profile.value = data
    message.value = t('account.updated')
    setTimeout(() => { message.value = '' }, 3000)
  } finally {
    saving.value = false
  }
}
</script>

<template>
  <div class="page container">
    <h1 class="page-title">{{ t('account.title') }}</h1>

    <LoadingSpinner v-if="loading" />
    <div v-else-if="profile" class="account-form">
      <div v-if="message" class="alert alert-success">{{ message }}</div>
      <form @submit.prevent="updateProfile">
        <BaseInput v-model="profile.firstName" :label="t('auth.first_name')" />
        <BaseInput v-model="profile.lastName" :label="t('auth.last_name')" />
        <BaseInput v-model="profile.email" :label="t('auth.email')" type="email" disabled />
        <BaseInput v-model="profile.phone" :label="t('auth.phone')" type="tel" />
        <BaseButton :loading="saving" style="margin-top: 0.5rem;">
          {{ t('account.update') }}
        </BaseButton>
      </form>

      <div class="account-links mt-3">
        <RouterLink to="/account/orders" class="btn btn-outline">{{ t('nav.orders') }}</RouterLink>
      </div>
    </div>
  </div>
</template>

<style scoped>
.account-form {
  max-width: 500px;
  margin: 0 auto;
  padding: 2rem;
  background: var(--color-bg-alt);
  border-radius: var(--radius-md);
  box-shadow: var(--shadow-sm);
}
.account-links {
  display: flex;
  gap: 1rem;
}
</style>
