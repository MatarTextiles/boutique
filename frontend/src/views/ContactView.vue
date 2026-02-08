<script setup lang="ts">
import { useI18n } from 'vue-i18n'

const { t } = useI18n()
</script>

<template>
  <div class="page container">
    <h1 class="page-title">{{ t('contact.title') }}</h1>

    <div class="contact-grid grid grid-2">
      <div class="contact-info card">
        <div class="contact-info-inner">
          <div class="info-block">
            <h3>{{ t('contact.address') }}</h3>
            <p>MATAR TEXTILES</p>
            <p>7488 St-Hubert</p>
            <p>Montréal QC H2R 2N3</p>
          </div>

          <div class="info-block">
            <h3>{{ t('contact.phone') }}</h3>
            <p><a href="tel:5142776462">(514) 277-6462</a></p>
          </div>

          <div class="info-block">
            <h3>{{ t('contact.email') }}</h3>
            <p><a href="mailto:roudainamatar@hotmail.com">roudainamatar@hotmail.com</a></p>
          </div>

          <div class="info-block">
            <h3>{{ t('contact.hours') }}</h3>
            <p>{{ t('contact.hours_weekday') }}</p>
            <p>{{ t('contact.hours_saturday') }}</p>
            <p>{{ t('contact.hours_sunday') }}</p>
          </div>
        </div>
      </div>

      <div class="contact-map">
        <iframe
          src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2795.5!2d-73.6197!3d45.5337!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x0!2s7488+Rue+Saint-Hubert+Montreal!5e0!3m2!1sfr!2sca!4v1234567890"
          width="100%"
          height="400"
          style="border:0; border-radius: var(--radius-md);"
          allowfullscreen
          loading="lazy"
        ></iframe>
      </div>
    </div>
  </div>
</template>

<style scoped>
.contact-info-inner {
  padding: 2rem;
}
.info-block {
  margin-bottom: 1.5rem;
}
.info-block:last-child {
  margin-bottom: 0;
}
.info-block h3 {
  font-size: 1rem;
  color: var(--color-primary);
  margin-bottom: 0.5rem;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-family: var(--font-family);
}
.info-block p {
  font-size: 0.95rem;
  margin-bottom: 0.2rem;
  color: var(--color-text);
}
.info-block a {
  color: var(--color-primary);
}
</style>
