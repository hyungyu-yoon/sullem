<template>
  <v-layout wrap>
    <v-flex
      v-for="i in items"
      xs12 sm6 md4
      pa-1
    >
      <v-card>
         <v-img
        class="white--text"
        height="200px"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      >
        <v-card-title class="align-end fill-height text-wrap">I'm a title</v-card-title>
      </v-img>
        <v-card-title>asdasd</v-card-title>
        <v-card-text>asdasdasdasdas</v-card-text>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
import axios from 'axios'
export default {
  props: {
    query: { type: String }
  },
  data () {
    return {
      items: 5
    }
  },
  mounted () {
    axios.get('http://192.168.31.114:8399/post/test')
      .then(function (response) {
        console.log(response.data)
      })
      .catch(function (error) {
        console.log(error)
      })
  },
  methods: {

  }
}
</script>

<style>
.v-text-field__details {
  display: none !important;
}
</style>
