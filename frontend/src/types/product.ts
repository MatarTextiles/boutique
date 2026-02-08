export interface Product {
  id: number
  nameFr: string
  nameEn: string
  slug: string
  descriptionFr?: string
  descriptionEn?: string
  price: number
  imageUrl?: string
  sku?: string
  stockQuantity: number
  active: boolean
  categoryId: number
  categorySlug: string
  categoryNameFr: string
  categoryNameEn: string
}
