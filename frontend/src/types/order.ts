export interface OrderItem {
  id?: number
  productId: number
  productNameFr?: string
  productNameEn?: string
  productSlug?: string
  productImageUrl?: string
  quantity: number
  unitPrice?: number
  subtotal?: number
}

export interface Order {
  id: number
  status: 'PENDING' | 'CONFIRMED' | 'SHIPPED' | 'DELIVERED' | 'CANCELLED'
  totalAmount: number
  shippingAddress: string
  notes?: string
  items: OrderItem[]
  createdAt: string
}

export interface CreateOrderRequest {
  shippingAddress: string
  notes?: string
  items: OrderItem[]
}
