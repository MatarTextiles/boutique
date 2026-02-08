export interface User {
  id: number
  email: string
  firstName: string
  lastName: string
  phone?: string
  role: 'CUSTOMER' | 'ADMIN'
  preferredLocale?: string
}

export interface LoginRequest {
  email: string
  password: string
}

export interface RegisterRequest {
  firstName: string
  lastName: string
  email: string
  password: string
  phone?: string
  preferredLocale?: string
}

export interface AuthResponse {
  token: string
  email: string
  firstName: string
  lastName: string
  role: string
}
