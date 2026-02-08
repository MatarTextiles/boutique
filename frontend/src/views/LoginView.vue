<script setup lang="ts">
import { ref } from 'vue'
import { useI18n } from 'vue-i18n'
import { useAuthStore } from '@/stores/auth.store'
import { useRouter, useRoute } from 'vue-router'
import BaseInput from '@/components/ui/BaseInput.vue'
import BaseButton from '@/components/ui/BaseButton.vue'

const { t } = useI18n()
const authStore = useAuthStore()
const router = useRouter()
const route = useRoute()

const email = ref('')
const password = ref('')
const error = ref('')
const loading = ref(false)

async function handleLogin() {
  error.value = ''
  loading.value = true
  try {
    await authStore.login({ email: email.value, password: password.value })
    const redirect = (route.query.redirect as string) || '/'
    router.push(redirect)
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
      <h1 class="page-title">{{ t('auth.login') }}</h1>
      <div v-if="error" class="alert alert-danger">{{ error }}</div>
      <form @submit.prevent="handleLogin">
        <BaseInput v-model="email" :label="t('auth.email')" type="email" />
        <BaseInput v-model="password" :label="t('auth.password')" type="password" />
        <BaseButton :loading="loading" class="btn-lg" style="width: 100%; margin-top: 0.5rem;">
          {{ t('auth.submit_login') }}
        </BaseButton>
      </form>
      <p class="auth-link mt-2">
        {{ t('auth.no_account') }}
        <RouterLink to="/register">{{ t('auth.register_link') }}</RouterLink>
      </p>
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
.auth-link {
  text-align: center;
  font-size: 0.9rem;
  color: var(--color-text-light);
}
</style>
