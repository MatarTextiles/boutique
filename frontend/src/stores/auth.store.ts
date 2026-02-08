import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { authApi } from '@/api/auth.api'
import type { User, LoginRequest, RegisterRequest, AuthResponse } from '@/types/user'
import api from '@/api/axios'

export const useAuthStore = defineStore('auth', () => {
  const user = ref<Partial<User> | null>(null)
  const token = ref<string | null>(null)

  const isAuthenticated = computed(() => !!token.value)
  const isAdmin = computed(() => user.value?.role === 'ADMIN')

  function loadFromStorage() {
    const storedToken = localStorage.getItem('token')
    const storedUser = localStorage.getItem('user')
    if (storedToken && storedUser) {
      token.value = storedToken
      user.value = JSON.parse(storedUser)
    }
  }

  function setAuth(data: AuthResponse) {
    token.value = data.token
    user.value = {
      email: data.email,
      firstName: data.firstName,
      lastName: data.lastName,
      role: data.role as User['role']
    }
    localStorage.setItem('token', data.token)
    localStorage.setItem('user', JSON.stringify(user.value))
  }

  async function login(request: LoginRequest) {
    const { data } = await authApi.login(request)
    setAuth(data)
    return data
  }

  async function register(request: RegisterRequest) {
    const { data } = await authApi.register(request)
    setAuth(data)
    return data
  }

  async function fetchProfile() {
    const { data } = await api.get<User>('/users/me')
    user.value = data
    localStorage.setItem('user', JSON.stringify(data))
  }

  function logout() {
    token.value = null
    user.value = null
    localStorage.removeItem('token')
    localStorage.removeItem('user')
  }

  return { user, token, isAuthenticated, isAdmin, loadFromStorage, login, register, fetchProfile, logout }
})
