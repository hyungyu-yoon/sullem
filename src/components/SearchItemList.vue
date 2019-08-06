<template>
  <v-layout wrap>
    <v-flex
      v-for="i in items"
      :key="i"
      xs12 sm6 md4
      pa-1
    >
      <Schedule></Schedule>
    </v-flex>
  </v-layout>
</template>

<script>
import Schedule from './Schedule.vue'
import axios from 'axios'
export default {
  components: {
    Schedule
  },
  props: {
    query: { type: String },
    category: { type: String }
  },
  data () {
    return {
      items: 5
    }
  },
  mounted () {
    // axios.get('http://192.168.31.114:8399/post/test')
    //   .then(function (response) {
    //     console.log(response.data)
    //   })
    //   .catch(function (error) {
    //     console.log(error)
    //   })
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
