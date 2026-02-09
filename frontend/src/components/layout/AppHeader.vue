<script setup lang="ts">
import { ref } from 'vue'
import { useI18n } from 'vue-i18n'
import LanguageSwitcher from './LanguageSwitcher.vue'

const { t } = useI18n()
const mobileMenuOpen = ref(false)
</script>

<template>
  <header class="header">
    <div class="container header-inner">
      <RouterLink to="/" class="logo">MATAR TEXTILES</RouterLink>

      <button class="mobile-toggle" @click="mobileMenuOpen = !mobileMenuOpen">
        <span></span><span></span><span></span>
      </button>

      <nav class="nav" :class="{ open: mobileMenuOpen }">
        <RouterLink to="/" @click="mobileMenuOpen = false">{{ t('nav.home') }}</RouterLink>
        <RouterLink to="/catalog" @click="mobileMenuOpen = false">{{ t('nav.catalog') }}</RouterLink>
        <RouterLink to="/about" @click="mobileMenuOpen = false">{{ t('nav.about') }}</RouterLink>
        <RouterLink to="/contact" @click="mobileMenuOpen = false">{{ t('nav.contact') }}</RouterLink>
        <LanguageSwitcher />
      </nav>
    </div>
  </header>
</template>

<style scoped>
.header {
  background: #ffffff;
  height: var(--header-height);
  position: sticky;
  top: 0;
  z-index: 100;
  box-shadow: var(--shadow-md);
}
.header-inner {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 100%;
}
.logo {
  font-family: var(--font-family-heading);
  font-size: 1.4rem;
  font-weight: 700;
  color: var(--color-primary-dark) !important;
  letter-spacing: 2px;
}
.nav {
  display: flex;
  align-items: center;
  gap: 1.25rem;
}
.nav a {
  color: var(--color-primary-dark);
  font-size: 0.9rem;
  font-weight: 400;
  opacity: 0.85;
}
.nav a:hover, .nav a.router-link-active {
  color: var(--color-primary);
  opacity: 1;
}
.mobile-toggle {
  display: none;
  flex-direction: column;
  gap: 4px;
  background: none;
  border: none;
  cursor: pointer;
  padding: 4px;
}
.mobile-toggle span {
  display: block;
  width: 24px;
  height: 2px;
  background: var(--color-primary-dark);
  transition: var(--transition);
}

@media (max-width: 768px) {
  .mobile-toggle {
    display: flex;
  }
  .nav {
    display: none;
    position: absolute;
    top: var(--header-height);
    left: 0;
    right: 0;
    background: #ffffff;
    flex-direction: column;
    padding: 1rem;
    gap: 0.75rem;
    box-shadow: var(--shadow-lg);
  }
  .nav.open {
    display: flex;
  }
}
</style>
