<template>
  <div>
    <v-img
      :src="results.country"
      aspect-ratio="3.0"
      gradient="rgba(255,255,255,.25), rgba(255,255,255,.5)"
    >
      <v-layout align-center justify-center fill-height></v-layout>
    </v-img>

    <v-container grid-list-md>
      <v-layout wrap>
        <v-flex md3 class="hidden-sm-and-down">
          <v-card color="teal accent-4" dark>
            <v-card-title primary class="title">Lorem</v-card-title>
            <v-card-text>{{ lorem }}</v-card-text>
          </v-card>
        </v-flex>
        <v-flex xs12 md9>
          <v-layout wrap>
            <v-flex xs12>
              <v-card color="white" dark>
                <v-card-text>{{ lorem.slice(0, 70) }}</v-card-text>
              </v-card>
            </v-flex>
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
    lorem: `Lorem ipsum dolor sit amet, mel at clita quando. Te sit oratio vituperatoribus, nam ad ipsum posidonium mediocritatem, explicari dissentiunt cu mea. Repudiare disputationi vim in, mollis iriure nec cu, alienum argumentum ius ad. Pri eu justo aeque torquatos.`,
    items: [
      {
        color: 'red lighten-2',
        icon: 'fa-camera'
      },
      {
        color: 'purple darken-1',
        icon: 'fa-utensils'
      },
      {
        color: 'green lighten-1',
        icon: 'fa-camera'
      },
      {
        color: 'indigo',
        icon: 'fa-bed'
      }
    ],
    events: [],
    results: ''
  }),
  created () {
    axios.get('http://192.168.31.114:8399/schedule/selectByNo/1')
      .then(response => {
        var data = JSON.parse(response.data.events)
        var date = ''
        var count = -1
        var results = []
        for (let i = 0; i < data.length; i++) {
          console.log(data[i].start.substring(0, 10))
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
    }
  }
}
</script>
