<template>
  <v-layout justify-center>
    <v-dialog v-model="dialog" persistent max-width="350">
      <template v-slot:activator="{ on }">
        <v-icon color="#4DB6AC" v-on="on">fa-plus-circle</v-icon>
      </template>
      <v-card>
        <v-card-title class="headline">{{ SpecificLocation.name }}</v-card-title>
        <v-divider />
        <!-- <v-radio-group v-model="amPm" :mandatory="false">
          <v-radio label="AM" value="am"></v-radio>
          <v-radio label="PM" value="pm"></v-radio>
        </v-radio-group>-->
        <v-card-text>
          <v-layout align-center justify-space-between>
            <v-icon @click="duration > 30 ? duration -= 30 : duration">fa-minus</v-icon>
            <p color="#4DB6AC">{{ duration }}분</p>
            <p>동안</p>
            <v-icon @click="duration += 30">fa-plus</v-icon>
          </v-layout>
          <v-layout>
            <v-select :items="hour" placeholder="시"></v-select>
            <v-select :items="min" placeholder="분"></v-select>
          </v-layout>
        </v-card-text>
        <!-- <v-card-text>{{ SpecificLocation.place_id }}</v-card-text>
        <v-card-text>lat: {{ SpecificLocation.geometry.location.lat() }}</v-card-text>
        <v-card-text>lng: {{ SpecificLocation.geometry.location.lng() }}</v-card-text>-->
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="#EF5350" text @click="dialog = false">취소</v-btn>
          <v-btn color="#26A69A" text @click="addEvents">추가</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
</template>

<script>
export default {
  props: {
    SpecificLocation: null,
    events: null
  },
  data () {
    return {
      dialog: false,
      amPm: 'am',
      hour: [...Array(24).keys()],
      min: ['00', '30'],
      duration: 30
    }
  },
  methods: {
    addEvents () {
      var newEvent = {
        name: this.SpecificLocation.name,
        details: '없음.',
        start: '2019-01-08 15:30',
        end: '2019-01-08 16:30',
        color: '#80CBC4',
        latlng: {
          lat: this.SpecificLocation.geometry.location.lat(),
          lng: this.SpecificLocation.geometry.location.lng()
        },
        type: 'location'
      }
      this.events.push(newEvent)
      this.dialog = false
    }
  }
}
</script>
