<template>
  <v-app>
    <v-content>
      <Header></Header>
      <router-view/>
    </v-content>
  </v-app>
</template>

<script>
import Header from './components/Header.vue'
export default {
  name: 'App',
  components : {
    Header
  },
  data: () => ({
    //
  })
}
</script>
