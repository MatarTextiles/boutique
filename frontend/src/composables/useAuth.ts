import { useAuthStore } from '@/stores/auth.store'
import { useRouter } from 'vue-router'

export function useAuth() {
  const authStore = useAuthStore()
  const router = useRouter()

  async function logout() {
    authStore.logout()
    router.push({ name: 'home' })
  }

  return {
    user: authStore.user,
    isAuthenticated: authStore.isAuthenticated,
    isAdmin: authStore.isAdmin,
    logout
  }
}
