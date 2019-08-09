<template>
      <v-container grid-list-md>
          <v-layout wrap>
            <v-flex v-for="(result,i) in results" :key="i" xs12 sm6 md4>
              <!-- <home-schedule-card
                v-if="no == 1"
                :result="result"
              ></home-schedule-card> -->
              <home-post-card v-if="no === '1'" :result="result"></home-post-card>

              <home-post-card v-if="no === '3'" :result="result"></home-post-card>
            </v-flex>
          </v-layout>
        </v-container>
</template>

<script>
import axios from 'axios'
import HomePostCard from './HomePostCard.vue'
// import HomeScheduleCard from './HomeScheduleCard.vue'
export default {
  components: {
    HomePostCard
    // HomeScheduleCard
  },
  props: {
    no: { type: String }
  },
  data () {
    return {
      results: null
    }
  },
  created () {
    if (this.no === '1') {
      axios.get('http://192.168.31.114:8399/post/selectTopPost')
        .then(response => {
          this.results = response.data
        }
        )
        .catch(function (error) {
          console.log(error)
        })
    } else if (this.no === '2') {
      axios.get('http://192.168.31.114:8399/schedule/selectByViewsTop')
        .then(response => {
          this.results = response.data
          console.log(this.results)
        }
        )
        .catch(function (error) {
          console.log(error)
        })
    } else if (this.no === '3') {
      axios.get('http://192.168.31.114:8399/post/selectPage/ /1')
        .then(response => {
          this.results = response.data.postList
        }
        )
        .catch(function (error) {
          console.log(error)
        })
    }
  }
}
</script>

<style>

</style>
