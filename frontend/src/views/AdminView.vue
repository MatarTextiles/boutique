<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
import { useAuthStore } from '@/stores/auth.store'
import { useRouter } from 'vue-router'
import { categoryApi } from '@/api/category.api'
import { productApi } from '@/api/product.api'
import { adminApi } from '@/api/admin.api'
import type { Category } from '@/types/category'
import type { Product } from '@/types/product'

const { t, locale } = useI18n()
const authStore = useAuthStore()
const router = useRouter()

const categories = ref<Category[]>([])
const products = ref<Product[]>([])
const loading = ref(true)
const uploadingCategory = ref<number | null>(null)
const uploadingProduct = ref<number | null>(null)
const message = ref('')
const messageType = ref<'success' | 'danger'>('success')

function showMessage(text: string, type: 'success' | 'danger' = 'success') {
  message.value = text
  messageType.value = type
  setTimeout(() => { message.value = '' }, 4000)
}

async function loadData() {
  loading.value = true
  try {
    const [catRes, prodRes] = await Promise.all([
      categoryApi.getAll(),
      productApi.getAll({ page: 0, size: 200 })
    ])
    categories.value = catRes.data
    products.value = prodRes.data.content
  } catch {
    showMessage(t('common.error'), 'danger')
  } finally {
    loading.value = false
  }
}

function triggerUpload(inputId: string) {
  document.getElementById(inputId)?.click()
}

async function handleCategoryUpload(category: Category, event: Event) {
  const input = event.target as HTMLInputElement
  const file = input.files?.[0]
  if (!file) return

  uploadingCategory.value = category.id
  try {
    const { data } = await adminApi.uploadCategoryImage(category.id, file)
    category.imageUrl = data.imageUrl + '?t=' + Date.now()
    showMessage(t('admin.upload_success'))
  } catch {
    showMessage(t('admin.upload_error'), 'danger')
  } finally {
    uploadingCategory.value = null
    input.value = ''
  }
}

async function handleProductUpload(product: Product, event: Event) {
  const input = event.target as HTMLInputElement
  const file = input.files?.[0]
  if (!file) return

  uploadingProduct.value = product.id
  try {
    const { data } = await adminApi.uploadProductImage(product.id, file)
    product.imageUrl = data.imageUrl + '?t=' + Date.now()
    showMessage(t('admin.upload_success'))
  } catch {
    showMessage(t('admin.upload_error'), 'danger')
  } finally {
    uploadingProduct.value = null
    input.value = ''
  }
}

function handleLogout() {
  authStore.logout()
  router.push('/admin/login')
}

onMounted(loadData)
</script>

<template>
  <div class="page container">
    <div class="admin-header">
      <h1 class="page-title">{{ t('admin.title') }}</h1>
      <button class="btn btn-outline btn-sm" @click="handleLogout">{{ t('nav.logout') }}</button>
    </div>

    <div v-if="message" :class="['alert', `alert-${messageType}`]">{{ message }}</div>

    <div v-if="loading" class="text-center mt-2">{{ t('common.loading') }}</div>

    <template v-else>
      <!-- Categories Section -->
      <section class="admin-section">
        <h2>{{ t('admin.categories_title') }}</h2>
        <div class="admin-grid">
          <div v-for="cat in categories" :key="cat.id" class="admin-card card">
            <div class="admin-card-image">
              <img :src="cat.imageUrl || '/images/placeholder.jpg'" :alt="locale === 'fr' ? cat.nameFr : cat.nameEn" />
            </div>
            <div class="admin-card-info">
              <h3>{{ locale === 'fr' ? cat.nameFr : cat.nameEn }}</h3>
              <p class="text-muted">{{ cat.slug }}</p>
              <input
                :id="'cat-upload-' + cat.id"
                type="file"
                accept="image/jpeg,image/png,image/webp"
                style="display: none"
                @change="handleCategoryUpload(cat, $event)"
              />
              <button
                class="btn btn-primary btn-sm"
                :disabled="uploadingCategory === cat.id"
                @click="triggerUpload('cat-upload-' + cat.id)"
              >
                <span v-if="uploadingCategory === cat.id">{{ t('common.loading') }}</span>
                <span v-else>{{ t('admin.change_image') }}</span>
              </button>
            </div>
          </div>
        </div>
      </section>

      <!-- Products Section -->
      <section class="admin-section">
        <h2>{{ t('admin.products_title') }}</h2>
        <div class="admin-grid">
          <div v-for="prod in products" :key="prod.id" class="admin-card card">
            <div class="admin-card-image">
              <img :src="prod.imageUrl || '/images/placeholder.jpg'" :alt="locale === 'fr' ? prod.nameFr : prod.nameEn" />
            </div>
            <div class="admin-card-info">
              <h3>{{ locale === 'fr' ? prod.nameFr : prod.nameEn }}</h3>
              <p class="text-muted">{{ locale === 'fr' ? prod.categoryNameFr : prod.categoryNameEn }}</p>
              <input
                :id="'prod-upload-' + prod.id"
                type="file"
                accept="image/jpeg,image/png,image/webp"
                style="display: none"
                @change="handleProductUpload(prod, $event)"
              />
              <button
                class="btn btn-primary btn-sm"
                :disabled="uploadingProduct === prod.id"
                @click="triggerUpload('prod-upload-' + prod.id)"
              >
                <span v-if="uploadingProduct === prod.id">{{ t('common.loading') }}</span>
                <span v-else>{{ t('admin.change_image') }}</span>
              </button>
            </div>
          </div>
        </div>
      </section>
    </template>
  </div>
</template>

<style scoped>
.admin-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
}

.admin-section {
  margin-bottom: 3rem;
}

.admin-section h2 {
  margin-bottom: 1rem;
  font-family: var(--font-family-heading);
  color: var(--color-primary);
  border-bottom: 2px solid var(--color-border-light);
  padding-bottom: 0.5rem;
}

.admin-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));
  gap: 1.5rem;
}

.admin-card {
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.admin-card-image {
  width: 100%;
  height: 180px;
  overflow: hidden;
  background: var(--color-border-light);
}

.admin-card-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.admin-card-info {
  padding: 1rem;
}

.admin-card-info h3 {
  margin: 0 0 0.25rem;
  font-size: 1rem;
}

.admin-card-info .text-muted {
  font-size: 0.85rem;
  color: var(--color-text-light);
  margin-bottom: 0.75rem;
}

.admin-card-info .btn {
  width: 100%;
}
</style>
