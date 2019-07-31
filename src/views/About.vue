<template>
  <div class="about">
    <h1>This is an about page</h1>
    <p>구글 맵 테스트 중이에여</p>
    <Place></Place>
  </div>
</template>

<script>
import Place from '@/components/Place.vue'
export default {
  name: 'about',
  components: {
    Place
  }
}
</script>

<style>
  #map {
    height: 500px;
  }
</style>
