<template>
  <div>
    <ScheduleImage :coverImage="coverimageUrl" />
    <v-container>
      <v-layout>
        <GoogleMap :mapEvents="events" v-on:sendMap="deliverMap" />
      </v-layout>
      <v-layout>
        <place :map="map" :events="events" :head="head" />
      </v-layout>
      <v-layout>
        <TimeTable v-on:changeHead="deliverHead" :startDay="setStart" :events="events" />
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
      coverimageUrl:
        'http://tourimage.interpark.com/BBS/Tour/FckUpload/201703/discovery_20170323_6362582542356180960.jpg',
      events: [
        // {
        //   name: "Jeju+International+Airport",
        //   details: "제주도의 관문!",
        //   start: "2019-01-08 9:30",
        //   end: "2019-01-08 10:30",
        //   color: "#80CBC4",
        //   latlng: { lat: -25.363, lng: 131.044 },
        //   type: "location"
        // },
        // {
        //   name: "길찾기",
        //   details: "30km",
        //   overview_path:
        //     "a_pkE}i`cWbYaIdElBRVtBzEXX^Jn@I~@[bAi@Vg@Hk@IgAqEyLuAsC}AkC?a@N_@p`@kBzDQ`E@f[^jS`@PTVNDNu@lx@Gx@D|@Nt@hBjE@?z@fBxBdE?PwG~HmLpNYLiE?m@HMPIj@a@hXKzDGrGY~KSxOQhHQnCiApJuAlNgA|JOnBErCT`TPpHv@|Ip@`H`CjOr@zEhA|Ih@tFJrDCbAQzBe@jEqBfK_@lCEjCFrAVlCxAzLr@rEn@zEj@fFPlFTxBPtCPdGBtCHfBNnA`@vBZ|@Zt@lMhUbGnKtSdp@lBxBnBlDzOzYdCtEd@bBHp@`@~H|@rO^lFAbBLnATvAX~Bf@~FrAdMrAdMj@jDr@nC~D`NzApG\\xBvAjN\\xCf@fDr@nDzCnMrDdOpFpTzAzHb@xAbI|WtGxQlAnD~AtGVhBf@dHh@pHPtDrAfURnBd@vBxAdF~CxMXpCXdDD`B_@|D[dCv@pEHfA\\hA\\f@bAp@`BrAx@x@z@dAJVTRd@x@`ApEJ`B?`B{@ve@c@~EOx@g@zDUtHa@|FeAzOQxAqB|KW~BM~BIlCF|ALbAtCpNZ|Ad@pAz@rA|@bAnAbA`B`AzGhDfAZ`IdBxArArBxApBlAxB~@zBl@zANzU|@jK`@jATz@^~@v@p@l@p@z@`DbEtHlHf@r@\\p@X`AhBvIp@`EZzBP|C?hDDnENzG\\vF\\rCb@nClA|F`BrGtCfIVp@\\xAlAhIh@`D|@lE`ApD~@zC~@nCjAzCrA`Dh@fAt@hAdEvEvCvC`BbAdG|CxJdFzEjCnBfAp@j@`@b@pDzFv@~@nIrI~M|MjGxIzHfMf@hAj@`At@v@l@f@`Aj@r@Pl@DzGDz@EdBc@jB]lDa@fE}@hCe@~@?n@HjCn@d@zBt@xC^hA`BtC~BpDnBtC`@VpGlCbI~C|@l@pOlLhBbB\\^~@vApB`H\\|@xCdFl@p@v@n@zIbFj@f@dBlBb@r@Tp@bCpI\\z@\\n@jLtPnAhBn@h@dAp@hA~@~@lAz@tAlCdFf@f@jAz@r@bJ",
        //   start: "2019-01-08 10:30",
        //   end: "2019-01-08 12:30",
        //   color: "transparent",
        //   latlng: null,
        //   type: "route"
        // }
        // {
        //   name: "Hyupjae+Beach",
        //   details: "Going to the beach!",
        //   start: "2019-01-08 12:30",
        //   end: "2019-01-08 13:30",
        //   color: "#80CBC4",
        //   latlng: { lat: 0, lng: 0 },
        //   type: "location"
        // }
      ],
      map: null,
      setStart: '2019-01-08',
      head: this.setStart
    }
  },
  methods: {
    deliverEvents (passed) {
      this.events = passed
    },
    deliverMap (mapPassed) {
      this.map = mapPassed
    },
    deliverHead (value) {
      this.head = value
    }
  }
}
</script>
