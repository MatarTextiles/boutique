<script setup lang="ts">
import { useI18n } from 'vue-i18n'

const { locale } = useI18n()

function switchLocale(lang: string) {
  locale.value = lang
  localStorage.setItem('locale', lang)
}
</script>

<template>
  <div class="lang-switcher">
    <button
      :class="{ active: locale === 'fr' }"
      @click="switchLocale('fr')"
    >FR</button>
    <span class="separator">|</span>
    <button
      :class="{ active: locale === 'en' }"
      @click="switchLocale('en')"
    >EN</button>
  </div>
</template>

<style scoped>
.lang-switcher {
  display: flex;
  align-items: center;
  gap: 0.25rem;
}
.lang-switcher button {
  background: none;
  border: none;
  color: var(--color-text-light);
  cursor: pointer;
  font-size: 0.85rem;
  font-weight: 500;
  padding: 0.2rem 0.4rem;
  border-radius: var(--radius-sm);
  transition: var(--transition);
}
.lang-switcher button.active {
  color: var(--color-primary);
  font-weight: 700;
}
.lang-switcher button:hover {
  color: var(--color-primary);
}
.separator {
  color: var(--color-text-light);
  font-size: 0.8rem;
}
</style>
