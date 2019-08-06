<template>
  <div>
    <ScheduleImage :coverImage="coverimageUrl"/>
    <v-container>
      <v-layout>
        <GoogleMap :mapEvents="events" v-on:sendMap="deliverMap"/>
      </v-layout>
      <v-layout>
        <place :map="map"/>
      </v-layout>
      <v-layout>
        <TimeTable v-on:sendEvents="deliverEvents"/>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import ScheduleImage from '../components/ScheduleImageCover.vue'
import GoogleMap from '../components/GoogleMap.vue'
import TimeTable from '../components/TimeTable.vue'
import Place from '@/components/Place.vue'
export default {
  name: 'CreateSchedulePage',
  components: {
    ScheduleImage,
    GoogleMap,
    TimeTable,
    Place
  },
  data () {
    return {
      coverimageUrl: 'http://tourimage.interpark.com/BBS/Tour/FckUpload/201703/discovery_20170323_6362582542356180960.jpg',
      events: [],
      map: null
    }
  },
  methods: {
    deliverEvents (passed) {
      this.events = passed
    },
    deliverMap (mapPassed) {
      this.map = mapPassed
    }
  }
}
</script>
