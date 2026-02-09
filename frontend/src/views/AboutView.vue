<script setup lang="ts">
import { useI18n } from 'vue-i18n'

const { t } = useI18n()
</script>

<template>
  <div class="page container">
    <h1 class="page-title">{{ t('about.title') }}</h1>

    <div class="about-grid">
      <section class="about-card card">
        <h2>{{ t('about.bio_title') }}</h2>
        <p>{{ t('about.bio') }}</p>
      </section>

      <section class="about-card card">
        <h2>{{ t('about.mission_title') }}</h2>
        <p>{{ t('about.mission') }}</p>
      </section>

      <section class="about-card card">
        <h2>{{ t('about.products_title') }}</h2>
        <p>{{ t('about.products') }}</p>
      </section>

      <section class="about-card card team-card">
        <div class="team-content">
          <div class="team-photo">
            <img src="/images/leila-matar.jpg" alt="Leila Matar" />
          </div>
          <div class="team-text">
            <h2>{{ t('about.team_title') }}</h2>
            <p>{{ t('about.team_ceo') }}</p>
            <p>{{ t('about.team_director') }}</p>
          </div>
        </div>
      </section>

      <section class="about-card card">
        <h2>{{ t('about.achievements_title') }}</h2>
        <p>{{ t('about.achievements') }}</p>
      </section>
    </div>
  </div>
</template>

<style scoped>
.about-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 1.5rem;
}

.about-card {
  padding: 2rem;
}

.about-card h2 {
  font-size: 1.15rem;
  color: var(--color-primary);
  margin-bottom: 0.75rem;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-family: var(--font-family);
}

.about-card p {
  font-size: 0.95rem;
  line-height: 1.7;
  color: var(--color-text);
  margin-bottom: 0.5rem;
}

.about-card p:last-child {
  margin-bottom: 0;
}

.about-grid .about-card:first-child {
  grid-column: 1 / -1;
}

.team-card {
  grid-column: 1 / -1;
}

.team-content {
  display: flex;
  gap: 2rem;
  align-items: center;
}

.team-photo {
  flex-shrink: 0;
  width: 180px;
  height: 180px;
  border-radius: 50%;
  overflow: hidden;
}

.team-photo img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.team-text h2 {
  font-size: 1.15rem;
  color: var(--color-primary);
  margin-bottom: 0.75rem;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-family: var(--font-family);
}

@media (max-width: 768px) {
  .about-grid {
    grid-template-columns: 1fr;
  }

  .team-content {
    flex-direction: column;
    text-align: center;
  }

  .team-photo {
    width: 150px;
    height: 150px;
  }
}
</style>
