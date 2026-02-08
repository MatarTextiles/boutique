import api from './axios'
import type { LoginRequest, RegisterRequest, AuthResponse } from '@/types/user'

export const authApi = {
  login(data: LoginRequest) {
    return api.post<AuthResponse>('/auth/login', data)
  },
  register(data: RegisterRequest) {
    return api.post<AuthResponse>('/auth/register', data)
  }
}
