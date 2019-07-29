<template>
  <div>
    <ScheduleImage :coverImage="coverimageUrl"/>
    <v-container>
      <v-layout>
        <GoogleMap/>
      </v-layout>
      <v-layout>
        <TimeTable/>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import ScheduleImage from '../components/ScheduleImageCover.vue'
import GoogleMap from '../components/GoogleMap.vue'
import TimeTable from '../components/TimeTable.vue'
export default {
  name: 'CreateSchedulePage',
  components: {
    ScheduleImage,
    GoogleMap,
    TimeTable
  },
  data () {
    return {
      coverimageUrl: 'http://tourimage.interpark.com/BBS/Tour/FckUpload/201703/discovery_20170323_6362582542356180960.jpg'
    }
  }
}
</script>
