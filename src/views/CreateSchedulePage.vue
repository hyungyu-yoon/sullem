<template>
  <div>
    <ScheduleImage :coverImage="coverimageUrl"/>
    <h1>This is create page.</h1>
  </div>
</template>

<script>
import ScheduleImage from '../components/ScheduleImageCover.vue'
export default {
  name: 'CreateSchedulePage',
  components: {
    ScheduleImage
  },
  data () {
    return {
      coverimageUrl: 'http://tourimage.interpark.com/BBS/Tour/FckUpload/201703/discovery_20170323_6362582542356180960.jpg'
    }
  }
}
</script>
