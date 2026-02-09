<script setup lang="ts">
import AppHeader from '@/components/layout/AppHeader.vue'
import AppFooter from '@/components/layout/AppFooter.vue'
</script>

<template>
  <div id="app">
    <AppHeader />
    <main class="main-content">
      <RouterView />
    </main>
    <AppFooter />
    <a
      href="https://wa.me/15146044790"
      target="_blank"
      rel="noopener noreferrer"
      class="whatsapp-btn"
      aria-label="WhatsApp"
    >
      <svg viewBox="0 0 32 32" width="32" height="32" fill="#fff"><path d="M16.004 0C7.164 0 .004 7.16.004 16c0 2.822.737 5.576 2.137 8.004L.004 32l8.188-2.102A15.93 15.93 0 0 0 16.004 32C24.844 32 32 24.84 32 16S24.844 0 16.004 0zm0 29.2a13.16 13.16 0 0 1-6.716-1.836l-.48-.288-4.86 1.248 1.296-4.74-.316-.5A13.12 13.12 0 0 1 2.804 16c0-7.28 5.924-13.2 13.2-13.2S29.2 8.72 29.2 16s-5.92 13.2-13.196 13.2zm7.24-9.876c-.396-.2-2.348-1.16-2.712-1.292-.364-.132-.628-.2-.892.2s-1.024 1.292-1.256 1.556c-.232.264-.464.296-.86.1-.396-.2-1.672-.616-3.184-1.964-1.176-1.048-1.972-2.344-2.2-2.74-.232-.396-.024-.608.172-.808.18-.18.396-.464.596-.696.2-.232.264-.396.396-.66.132-.264.068-.496-.032-.696-.1-.2-.892-2.148-1.22-2.94-.32-.776-.648-.668-.892-.68l-.76-.012c-.264 0-.692.1-1.056.496-.364.396-1.388 1.356-1.388 3.308s1.42 3.836 1.62 4.1c.2.264 2.8 4.272 6.784 5.992.948.408 1.688.652 2.264.836.952.3 1.82.26 2.504.156.764-.116 2.348-.96 2.68-1.888.332-.928.332-1.724.232-1.888-.1-.168-.364-.264-.76-.464z"/></svg>
    </a>
  </div>
</template>

<style scoped>
.main-content {
  min-height: calc(100vh - var(--header-height) - 200px);
}
.whatsapp-btn {
  position: fixed;
  bottom: 1.5rem;
  right: 1.5rem;
  z-index: 999;
  width: 60px;
  height: 60px;
  background: #25d366;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.25);
  transition: transform 0.2s, box-shadow 0.2s;
}
.whatsapp-btn:hover {
  transform: scale(1.1);
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.3);
}
</style>
