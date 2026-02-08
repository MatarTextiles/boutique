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

const firstName = ref('')
const lastName = ref('')
const email = ref('')
const password = ref('')
const phone = ref('')
const error = ref('')
const loading = ref(false)

async function handleRegister() {
  error.value = ''
  loading.value = true
  try {
    await authStore.register({
      firstName: firstName.value,
      lastName: lastName.value,
      email: email.value,
      password: password.value,
      phone: phone.value || undefined
    })
    router.push('/')
  } catch {
    error.value = t('auth.register_error')
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="page container">
    <div class="auth-form">
      <h1 class="page-title">{{ t('auth.register') }}</h1>
      <div v-if="error" class="alert alert-danger">{{ error }}</div>
      <form @submit.prevent="handleRegister">
        <BaseInput v-model="firstName" :label="t('auth.first_name')" />
        <BaseInput v-model="lastName" :label="t('auth.last_name')" />
        <BaseInput v-model="email" :label="t('auth.email')" type="email" />
        <BaseInput v-model="password" :label="t('auth.password')" type="password" />
        <BaseInput v-model="phone" :label="t('auth.phone')" type="tel" />
        <BaseButton :loading="loading" class="btn-lg" style="width: 100%; margin-top: 0.5rem;">
          {{ t('auth.submit_register') }}
        </BaseButton>
      </form>
      <p class="auth-link mt-2">
        {{ t('auth.has_account') }}
        <RouterLink to="/login">{{ t('auth.login_link') }}</RouterLink>
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
