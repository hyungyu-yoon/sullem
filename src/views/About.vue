<template>
  <div class="about">
    <h1>This is an about page</h1>
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
