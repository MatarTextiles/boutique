<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'

const allSlides = Array.from({ length: 8 }, (_, i) =>
  `/images/carousel/slide-${String(i + 1).padStart(2, '0')}.jpg`
)
const slides = [
  allSlides[0],  // slide-01 (enseigne)
  allSlides[5],  // slide-06 (deuxième position)
  ...allSlides.filter((_, i) => i !== 0 && i !== 5)
]

const current = ref(0)
const total = slides.length
let timer: ReturnType<typeof setInterval> | null = null

function next() {
  current.value = (current.value + 1) % total
}

function prev() {
  current.value = (current.value - 1 + total) % total
}

function goTo(index: number) {
  current.value = index
}

function startAutoPlay() {
  stopAutoPlay()
  timer = setInterval(next, 5000)
}

function stopAutoPlay() {
  if (timer) {
    clearInterval(timer)
    timer = null
  }
}

onMounted(() => {
  startAutoPlay()
})

onUnmounted(() => {
  stopAutoPlay()
})
</script>

<template>
  <div class="carousel" @mouseenter="stopAutoPlay" @mouseleave="startAutoPlay">
    <div class="carousel-track">
      <div
        v-for="(src, index) in slides"
        :key="index"
        class="carousel-slide"
        :class="{ active: index === current }"
      >
        <img :src="src" alt="MATAR TEXTILES" loading="lazy" :class="{ contain: index === 0 }" />
      </div>
    </div>

    <button class="carousel-btn carousel-prev" @click="prev" aria-label="Previous">
      <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"><polyline points="15 18 9 12 15 6"/></svg>
    </button>
    <button class="carousel-btn carousel-next" @click="next" aria-label="Next">
      <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"><polyline points="9 18 15 12 9 6"/></svg>
    </button>

    <div class="carousel-dots">
      <button
        v-for="(_, index) in slides"
        :key="index"
        class="carousel-dot"
        :class="{ active: index === current }"
        @click="goTo(index)"
        :aria-label="`Slide ${index + 1}`"
      />
    </div>
  </div>
</template>

<style scoped>
.carousel {
  position: relative;
  width: 100%;
  height: 70vh;
  min-height: 400px;
  max-height: 650px;
  overflow: hidden;
  background: #111;
}

.carousel-track {
  position: relative;
  width: 100%;
  height: 100%;
}

.carousel-slide {
  position: absolute;
  inset: 0;
  opacity: 0;
  transition: opacity 0.8s ease;
}

.carousel-slide.active {
  opacity: 1;
  z-index: 1;
}

.carousel-slide img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.carousel-slide img.contain {
  object-fit: contain;
  background: #111;
}

.carousel-btn {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  z-index: 10;
  background: rgba(0, 0, 0, 0.4);
  color: #fff;
  border: none;
  width: 48px;
  height: 48px;
  border-radius: 50%;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background 0.2s;
}

.carousel-btn:hover {
  background: rgba(0, 0, 0, 0.7);
}

.carousel-prev {
  left: 1rem;
}

.carousel-next {
  right: 1rem;
}

.carousel-dots {
  position: absolute;
  bottom: 1rem;
  left: 50%;
  transform: translateX(-50%);
  z-index: 10;
  display: flex;
  gap: 6px;
  flex-wrap: wrap;
  justify-content: center;
  max-width: 80%;
}

.carousel-dot {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  border: 2px solid rgba(255, 255, 255, 0.7);
  background: transparent;
  cursor: pointer;
  padding: 0;
  transition: background 0.2s;
}

.carousel-dot.active {
  background: #fff;
}

@media (max-width: 768px) {
  .carousel {
    height: 50vh;
    min-height: 280px;
  }

  .carousel-btn {
    width: 36px;
    height: 36px;
  }

  .carousel-btn svg {
    width: 18px;
    height: 18px;
  }

  .carousel-dots {
    gap: 4px;
  }

  .carousel-dot {
    width: 8px;
    height: 8px;
  }
}
</style>
