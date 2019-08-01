<template>
  <v-app>
    <Header v-if="this.$store.state.val"/>
    <v-content>
      <router-view/>
    </v-content>
    <Footer v-if="this.$store.state.val"></Footer>
  </v-app>
</template>

<script>

import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
export default {
  name: 'App',
  components: {
    Header,
    Footer
  },
  data: () => ({
    //
  })
}
</script>
