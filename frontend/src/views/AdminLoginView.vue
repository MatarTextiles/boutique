<script setup lang="ts">
import { ref } from 'vue'
import { useI18n } from 'vue-i18n'
import { useAuthStore } from '@/stores/auth.store'
import { useRouter } from 'vue-router'
import BaseInput from '@/components/ui/BaseInput.vue'
import BaseButton from '@/components/ui/BaseButton.vue'

const { t } = useI18n()
const authStore = useAuthStore()
const router = useRouter()

const email = ref('')
const password = ref('')
const error = ref('')
const loading = ref(false)

async function handleLogin() {
  error.value = ''
  loading.value = true
  try {
    await authStore.login({ email: email.value, password: password.value })
    if (authStore.isAdmin) {
      router.push('/admin')
    } else {
      authStore.logout()
      error.value = t('admin.not_admin')
    }
  } catch {
    error.value = t('auth.login_error')
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="page container">
    <div class="auth-form">
      <h1 class="page-title">{{ t('admin.login_title') }}</h1>
      <div v-if="error" class="alert alert-danger">{{ error }}</div>
      <form @submit.prevent="handleLogin">
        <BaseInput v-model="email" :label="t('auth.email')" type="email" />
        <BaseInput v-model="password" :label="t('auth.password')" type="password" />
        <BaseButton :loading="loading" class="btn-lg" style="width: 100%; margin-top: 0.5rem;">
          {{ t('auth.submit_login') }}
        </BaseButton>
      </form>
    </div>
  </div>
</template>

<style scoped>
.auth-form {
  max-width: 420px;
  margin: 2rem auto;
  padding: 2rem;
  background: var(--color-bg-alt);
  border-radius: var(--radius-md);
  box-shadow: var(--shadow-md);
}
</style>
