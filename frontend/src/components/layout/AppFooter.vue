<script setup lang="ts">
import { useI18n } from 'vue-i18n'

const { t } = useI18n()
const year = new Date().getFullYear()
</script>

<template>
  <footer class="footer">
    <div class="container footer-inner">
      <div class="footer-section">
        <h3 class="footer-logo">MATAR TEXTILES</h3>
        <p>{{ t('app.tagline') }}</p>
      </div>
      <div class="footer-section">
        <h4>{{ t('contact.address') }}</h4>
        <p>7488 St-Hubert</p>
        <p>Montréal QC H2R 2N3</p>
        <p>(514) 277-6462</p>
        <p><a href="mailto:roudainamatar@hotmail.com">roudainamatar@hotmail.com</a></p>
      </div>
      <div class="footer-section">
        <h4>{{ t('contact.hours') }}</h4>
        <p>{{ t('contact.hours_weekday') }}</p>
        <p>{{ t('contact.hours_saturday') }}</p>
        <p>{{ t('contact.hours_sunday') }}</p>
      </div>
      <div class="footer-section">
        <h4>Navigation</h4>
        <RouterLink to="/catalog">{{ t('nav.catalog') }}</RouterLink>
        <RouterLink to="/contact">{{ t('nav.contact') }}</RouterLink>
      </div>
    </div>
    <div class="footer-bottom">
      <div class="container">
        <p>&copy; {{ year }} MATAR TEXTILES. {{ t('footer.rights') }}.</p>
      </div>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background: var(--color-primary-dark);
  color: var(--color-text-inverse);
  margin-top: 3rem;
}
.footer-inner {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 2rem;
  padding: 2.5rem 1rem;
}
.footer-logo {
  font-family: var(--font-family-heading);
  color: #ffffff;
  font-size: 1.2rem;
  letter-spacing: 2px;
  margin-bottom: 0.5rem;
}
.footer-section h4 {
  color: var(--color-primary-light);
  margin-bottom: 0.75rem;
  font-size: 0.95rem;
}
.footer-section p {
  font-size: 0.85rem;
  opacity: 0.85;
  margin-bottom: 0.3rem;
}
.footer-section a {
  display: block;
  color: var(--color-text-inverse);
  opacity: 0.85;
  font-size: 0.85rem;
  margin-bottom: 0.3rem;
}
.footer-section a:hover {
  color: var(--color-primary-light);
  opacity: 1;
}
.footer-bottom {
  border-top: 1px solid rgba(255,255,255,0.1);
  padding: 1rem;
  text-align: center;
  font-size: 0.8rem;
  opacity: 0.7;
}

@media (max-width: 768px) {
  .footer-inner {
    grid-template-columns: repeat(2, 1fr);
  }
}
@media (max-width: 480px) {
  .footer-inner {
    grid-template-columns: 1fr;
  }
}
</style>
