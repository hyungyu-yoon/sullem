<template>
  <div v-if="results !== ''">
    <v-img
      :src="results.country"
      aspect-ratio="3.0"
      gradient="rgba(255,255,255,.25), rgba(255,255,255,.5)"
    >
      <v-layout class="text-center" align-center justify-center fill-height>
        <v-flex xs12>
          <span class="display-3 font-weight-bold" >{{results.title}}</span>
        </v-flex>
      </v-layout>
    </v-img>

    <v-container grid-list-md>
      <v-layout wrap>
        <v-flex md3 class="hidden-sm-and-down">
          <v-card color="teal accent-4" dark height="250">
            <v-card-title primary class="title">{{results.name}}</v-card-title>
            <v-card-text>{{results.createTime.substring(0,16)}}</v-card-text>
            <v-card-text class="title">{{ results.description }}</v-card-text>
          </v-card>
        </v-flex>
        <v-flex xs12 md9>
          <v-layout wrap>
            <v-tabs centered grow icons-and-text>
              <v-tab @click="switchone" style="margin: auto;">tab 1 <i class="material-icons">date_range</i></v-tab>
              <v-tab @click="switchtwo" style="margin: auto;">tab 2 <i class="material-icons">map</i></v-tab>
            </v-tabs>

            <template v-if="this.tab">
              <v-flex xs12 v-for="(event,i) in events" :key=i>
                <v-flex ma-1 xs12>
                  <span class="title font-weight-bold" ma-2>
                    <v-icon>fa-calendar-day</v-icon>&nbsp;Day {{i+1}}
                  </span>
                  <span class="subtitle-2 grey--text">{{event[0].start.substring(0,10)}}</span>
                </v-flex>
                <v-sheet elevation="5">
                  <v-timeline align-top dense>
                    <v-timeline-item
                      v-for="(data, i) in event"
                      v-if="data.type === 'location'"
                      :key="i"
                      :color="getColor(data.category)"
                      :icon="getIcon(data.category)"
                      fill-dot
                    >
                      <v-card class="elevation-2" width="95%">
                        <v-layout pa-3 wrap>
                          <v-flex xs12>
                            <v-icon>fa-clock</v-icon>&nbsp;
                            <span>{{data.start.substring(11)}} ~ {{data.end.substring(11)}}</span>
                          </v-flex>
                          <v-flex xs12 sm6>
                            <v-card elevation="3">
                              <v-img height="200" :src="data.imageUrl"></v-img>
                            </v-card>
                          </v-flex>
                          <v-flex xs12 sm6>
                            <v-card-title class="font-weight-bold">{{data.name}}</v-card-title>
                            <v-card-text>{{data.address}}</v-card-text>
                          </v-flex>
                        </v-layout>
                      </v-card>
                    </v-timeline-item>
                  </v-timeline>
                </v-sheet>
              </v-flex>
            </template>

            <!-- tab2 부분 -->
            <template v-if="!this.tab">

            </template>
            <!--  -->

          </v-layout>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data: () => ({
    no: '',
    events: [],
    results: '',
    tab: true
  }),
  created () {
    this.no = this.$route.params.no
    axios.get('http://192.168.31.114:8399/schedule/selectByNo/' + this.no)
      .then(response => {
        var data = JSON.parse(response.data.events)
        var date = ''
        var count = -1
        var results = []
        for (let i = 0; i < data.length; i++) {
          if (date !== data[i].start.substring(0, 10)) {
            count++
            results.push([])
            date = data[i].start.substring(0, 10)
            results[count].push(data[i])
          } else {
            results[count].push(data[i])
          }
        }
        this.events = results

        this.results = response.data
        console.log(this.results)
      }
      )
      .catch(function (error) {
        console.log(error)
      })
  },
  methods: {
    getIcon (category) {
      if (category[0] === 'lodging') {
        return 'fa-bed'
      } else if (category[0] === 'restaurant') {
        return 'fa-utensils'
      } else {
        return 'fa-camera'
      }
    },
    getColor (category) {
      if (category[0] === 'lodging') {
        return 'red'
      } else if (category[0] === 'restaurant') {
        return 'yellow lighten-3'
      } else {
        return 'teal accent-4'
      }
    },
    switchone () {
      this.tab = true
    },

    switchtwo () {
      this.tab = false
    }
  }
}
</script>

<style>
.v-tabs-slider-wrapper {
  /* width: 30% !important; */
}

</style>
