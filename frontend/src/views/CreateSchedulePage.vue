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
    <v-btn @click="save">보내기</v-btn>
  </div>
</template>

<script>
import ScheduleImage from "../components/CreateSchedule/ScheduleImageCover.vue";
import GoogleMap from "../components/CreateSchedule/GoogleMap.vue";
import TimeTable from "../components/CreateSchedule/TimeTable.vue";
import Place from "../components/CreateSchedule/Place.vue";
import axios from "axios";
export default {
  name: "CreateSchedulePage",
  components: {
    ScheduleImage,
    GoogleMap,
    TimeTable,
    Place
  },
  data() {
    return {
      title: this.$store.state.scheduleTitle,
      description: this.$store.state.scheduleDescription,
      coverimageUrl:
        "http://tourimage.interpark.com/BBS/Tour/FckUpload/201703/discovery_20170323_6362582542356180960.jpg",
      map: null,
      events: [],
      setStart: this.$store.state.scheduleStart,
      // route: {
      //   title: this.title,
      //   description: this.description,
      //   seq: this.$session.get("user")["seq"],
      //   name: this.$session.get("user")["name"],
      //   startDate: "2019-01-08",
      //   country:
      //     "https://maps.googleapis.com/maps/api/place/js/PhotoService.GetPhoto?1sCmRaAAAA4lTkI0ZVvUhM0nGxquReFenMPoHZjeCqrQzQNT7cRO9qRiJcl8WnaznfoyUk0fCz-uL6qx2dfJ_bse0argQqeAyaQ850_f6UnJwrK0bauebBhYrmJVuknR3Gal_Hpju4EhBnD1xB912ziLSHhJp-cmPkGhSI1cgueRwS-DtTPRt9Y37FMyHSvQ&3u4128&5m1&2e1&callback=none&key=AIzaSyAuEYSl_vfcCZK2DiwF94DY8S5EkbAWRyY&token=23627",
      //   events: [
      //     {
      //       name: "제주국제공항",
      //       details: "없음.",
      //       start: "2019-01-08 00:00",
      //       end: "2019-01-08 00:30",
      //       color: "#80CBC4",
      //       latlng: { lat: 33.51041350000001, lng: 126.49135339999998 },
      //       imageUrl:
      //         "https://maps.googleapis.com/maps/api/place/js/PhotoService.GetPhoto?1sCmRaAAAA4lTkI0ZVvUhM0nGxquReFenMPoHZjeCqrQzQNT7cRO9qRiJcl8WnaznfoyUk0fCz-uL6qx2dfJ_bse0argQqeAyaQ850_f6UnJwrK0bauebBhYrmJVuknR3Gal_Hpju4EhBnD1xB912ziLSHhJp-cmPkGhSI1cgueRwS-DtTPRt9Y37FMyHSvQ&3u4128&5m1&2e1&callback=none&key=AIzaSyAuEYSl_vfcCZK2DiwF94DY8S5EkbAWRyY&token=23627",
      //       category: ["airport", "point_of_interest", "establishment"],
      //       rating: 3.9,
      //       address: "대한민국 제주특별자치도 제주시 공항로 2",
      //       type: "location"
      //     },
      //     {
      //       name: "TRANSIT",
      //       origin: { lat: 33.51041350000001, lng: 126.49135339999998 },
      //       destination: { lat: 33.5162323, lng: 126.5120637 },
      //       overview_path:
      //         "a_pkE}i`cW|b@o_@gBJOEC[`BeRzDa`@JcCI_AOo@w@mBm@iA{AaCiA{As@g@gQiIo@k@Yc@Sk@a@cDe@_CyC}JaCeIsa@~U",
      //       panel: "[object HTMLDivElement]",
      //       start: "2019-01-08 00:30",
      //       end: "2019-01-08 02:00",
      //       color: "#F48FB1",
      //       latlng: null,
      //       type: "route"
      //     },
      //     {
      //       name: "용두암",
      //       details: "없음.",
      //       start: "2019-01-08 02:00",
      //       end: "2019-01-08 02:30",
      //       color: "#80CBC4",
      //       latlng: { lat: 33.5162323, lng: 126.5120637 },
      //       imageUrl:
      //         "https://maps.googleapis.com/maps/api/place/js/PhotoService.GetPhoto?1sCmRaAAAAvOjE80kTiGSx-k9-OkxUxWdBw84epXkaePsDJ6VeusqdxX8IJ5b1GTvGqfAYBiSUmEOpFttzLlRdIUNX7YlJps1z9Rd_SONUtwE2PNgAuQM1QaxFD2xSkzHsDd4-kyDGEhBOz6_HDN2WSYkWKcq0kG26GhQa2SzPchO702_QiStzL3gFNUViDw&3u5312&5m1&2e1&callback=none&key=AIzaSyAuEYSl_vfcCZK2DiwF94DY8S5EkbAWRyY&token=124145",
      //       category: ["point_of_interest", "establishment"],
      //       rating: 3.8,
      //       address: "대한민국 제주특별자치도 제주시 용담2동 용두암길 15",
      //       type: "location"
      //     },
      //     {
      //       name: "길찾기",
      //       origin: { lat: 33.5162323, lng: 126.5120637 },
      //       destination: { lat: 33.4394527, lng: 126.62946420000003 },
      //       overview_path: null,
      //       panel: null,
      //       start: "2019-01-08 02:30",
      //       end: "2019-01-09 00:30",
      //       color: "transparent",
      //       latlng: null,
      //       type: "route"
      //     },
      //     {
      //       name: "제주절물자연휴양림",
      //       details: "없음.",
      //       start: "2019-01-09 00:30",
      //       end: "2019-01-09 01:00",
      //       color: "#80CBC4",
      //       latlng: { lat: 33.4394527, lng: 126.62946420000003 },
      //       imageUrl:
      //         "https://maps.googleapis.com/maps/api/place/js/PhotoService.GetPhoto?1sCmRaAAAAurHE_L0kwVggAUnT47Y0yBE81ipEBtZiyHgqvdT7vsPo3z4clcVVoWJdY4ZzkztS6mJRkxNhwgqkLOz8KFBF10yFm82WolOtTj8yxX1wXZXaKpQQo0k6bWBoiCmxtUshEhDKEh2i8mqR4-Hfu71M2EwlGhRslr93TJlxm8dfKWQtSZj9CSW6DQ&3u3024&5m1&2e1&callback=none&key=AIzaSyAuEYSl_vfcCZK2DiwF94DY8S5EkbAWRyY&token=74124",
      //       category: ["park", "point_of_interest", "establishment"],
      //       rating: 4.5,
      //       address: "대한민국 제주특별자치도 제주시 봉개동 명림로 584",
      //       type: "location"
      //     },
      //     {
      //       name: "TRANSIT",
      //       origin: { lat: 33.4394527, lng: 126.62946420000003 },
      //       destination: { lat: 33.451638, lng: 126.49000000000001 },
      //       overview_path:
      //         "qcbkEci{cWoH{D}@FcA\\_Ad@kFzCgA~@ya@r`@g@~@iJjGuIzFw@p@{IzHqDlDsAdAeAj@sBv@mAt@wF~DcAz@iArAoFbHw@j@eOpIeA\\eANmE`@gDVy@R_Bn@g@DyDBQEq@u@c@Mq@@m@HiNbAiATuC|@yEnAwAt@oGpGc@`@o@^u@NgE\\s@T_Aj@oGzEm@PyAFm@NoAj@g@NWLk@f@cAnAs@l@cGvCq@NgGPkAIiB[}@M]D[NS\\qBrKk@vBy@tBwI`QiHtMy@~A{@nBeD|IiAbDyA`FwC~HqGvQaCjGsHpR}EvLq@xB_AdEuHb]oBxG}CrJmGlOkAvDD|@Np@rJxTtJtT`LrVrBlDrGrLlDbGhCvEH^o@n@g@\\_D|B_GdEmGjEIE_FsKqOs]WQYCYHm@f@wBtAcBv@uAb@YVU^]~A[t@U\\s@n@O^Af@Dd@rA|C~N`]xTbh@p@rApHlMtE~H?l@oEfEcHzFoGxDaLxGsYrQmDtBiFhCgKbFe@?o@ScK}D]Ie@EoEISDMLKPSnK@x@l@tGXjCNx@zE`MbBnEpEfOnC`Jv@~DPvAZtAh@~@h@d@rJrEnFbCZZhAzAhAdB|@dBp@`BNx@Bt@E|@qAnMgFjg@SjBaChDm@tAEX?jDJpAT\\hAr@xBx@f@TVTtBbFVTZL\\?b@Or@Ev@g@`@o@Hk@@k@Q{@uEaMwAoCeAeBGKA[FWHO`Pw@dVgA`B@nKLzNPxNXrEHLRXPVDXIPONQ`GJlMPHHSrVMfMGJsV[Q@QLK\\GfHGhHAnAH`BpBvEjD`HxDxGxArCfCnFjA~BZZf@?b@KrIsCfd@cMvJgCzRcEnAW`AKvE_@hFk@|AM|CIbIL~KT`CKfAU`G_A~CI`NEf@G`@OdBqA|@k@VUrFuB|@SfL_AzDW^DlCn@lDjAh@L~D^fEAiKiU",
      //       panel: "[object HTMLDivElement]",
      //       start: "2019-01-09 01:00",
      //       end: "2019-01-09 04:30",
      //       color: "#F48FB1",
      //       latlng: null,
      //       type: "route"
      //     },
      //     {
      //       name: "제주 러브랜드",
      //       details: "없음.",
      //       start: "2019-01-09 04:30",
      //       end: "2019-01-09 05:00",
      //       color: "#80CBC4",
      //       latlng: { lat: 33.451638, lng: 126.49000000000001 },
      //       imageUrl:
      //         "https://maps.googleapis.com/maps/api/place/js/PhotoService.GetPhoto?1sCmRaAAAAkyHKGJC8q2dMwGGPf7Ajz8_C-CUeGUbcEbu32AmioiRSZyE7dDG-XB1DrkYMx76qHIyK_edDbM48frjywUuelc4j9q6MHQFWfqJMObRn-oDwGEyhvlBzcBMBpL82ofS4EhBXfXQDnPRP74ZgYlO_yRCbGhQzIPPqptYgn4cN5ctG3anDQhadMw&3u3024&5m1&2e1&callback=none&key=AIzaSyAuEYSl_vfcCZK2DiwF94DY8S5EkbAWRyY&token=67490",
      //       category: ["amusement_park", "point_of_interest", "establishment"],
      //       rating: 3.8,
      //       address: "대한민국 제주특별자치도 제주시 연동 1100로 2894-72",
      //       type: "location"
      //     },
      //     {
      //       name: "TRANSIT",
      //       origin: { lat: 33.451638, lng: 126.49000000000001 },
      //       destination: { lat: 33.4847577, lng: 126.4887056 },
      //       overview_path:
      //         "wodkEoa`cWsF`KKD@JJHMnAwCT_@FoDTeK|@}@R}F|B[RgD`Cq@L_RFiGz@cBTc@@mEEkR]aCB_AFwAJ}Ef@gE\\gBVaLvBeEx@cFlAsJdCKG?oFN}c@GWKKUMe@@oHCaECc@FSNM^[pg@ItJKToFzAqBj@mExAaDfAMAWm@?YTqTRkWvEqF",
      //       panel: "[object HTMLDivElement]",
      //       start: "2019-01-09 05:00",
      //       end: "2019-01-09 08:00",
      //       color: "#F48FB1",
      //       latlng: null,
      //       type: "route"
      //     },
      //     {
      //       name: "메종 글래드 제주",
      //       details: "없음.",
      //       start: "2019-01-09 08:00",
      //       end: "2019-01-09 08:30",
      //       color: "#80CBC4",
      //       latlng: { lat: 33.4847577, lng: 126.4887056 },
      //       imageUrl:
      //         "https://maps.googleapis.com/maps/api/place/js/PhotoService.GetPhoto?1sCmRaAAAAvn0WBAsp7df5leG97-GX114gdWYaAgmE1McmIWupg0o5aV8Vfr9_EPtXe0FNvMY6xUjrdVd8BxhZ6gjz8TjkNXaV-m54ZxKbzGUapnbtrmKzLV6kpm3U3yzfBP4eBCE3EhDgqjhdKykZgd9kgll8SJRXGhQYj-lQT5r2HPetQwN7-UuUuzqnhA&3u3264&5m1&2e1&callback=none&key=AIzaSyAuEYSl_vfcCZK2DiwF94DY8S5EkbAWRyY&token=80513",
      //       category: ["lodging", "point_of_interest", "establishment"],
      //       rating: 4.2,
      //       address: "대한민국 제주시 연동 KR특별자치도 제주시 연동 노연로 80",
      //       type: "location"
      //     },
      //     {
      //       name: "TRANSIT",
      //       origin: { lat: 33.4847577, lng: 126.4887056 },
      //       destination: { lat: 33.51041350000001, lng: 126.49135339999998 },
      //       overview_path:
      //         "w~jkEmy_cWk]|OkDqI_BiDqBsDuKsPIe@BqCXgTCi@W]]UkTWeEEeEPma@nB}b@n_@",
      //       panel: "[object HTMLDivElement]",
      //       start: "2019-01-09 08:30",
      //       end: "2019-01-10 02:00",
      //       color: "#F48FB1",
      //       latlng: null,
      //       type: "route"
      //     },
      //     {
      //       name: "제주국제공항",
      //       details: "없음.",
      //       start: "2019-01-10 02:00",
      //       end: "2019-01-10 02:30",
      //       color: "#80CBC4",
      //       latlng: { lat: 33.51041350000001, lng: 126.49135339999998 },
      //       imageUrl:
      //         "https://maps.googleapis.com/maps/api/place/js/PhotoService.GetPhoto?1sCmRaAAAA4lTkI0ZVvUhM0nGxquReFenMPoHZjeCqrQzQNT7cRO9qRiJcl8WnaznfoyUk0fCz-uL6qx2dfJ_bse0argQqeAyaQ850_f6UnJwrK0bauebBhYrmJVuknR3Gal_Hpju4EhBnD1xB912ziLSHhJp-cmPkGhSI1cgueRwS-DtTPRt9Y37FMyHSvQ&3u4128&5m1&2e1&callback=none&key=AIzaSyAuEYSl_vfcCZK2DiwF94DY8S5EkbAWRyY&token=23627",
      //       category: ["airport", "point_of_interest", "establishment"],
      //       rating: 3.9,
      //       address: "대한민국 제주특별자치도 제주시 공항로 2",
      //       type: "location"
      //     }
      //   ]
      // },
      head: this.$store.state.scheduleStart
    };
  },
  methods: {
    deliverEvents(passed) {
      this.events = passed;
    },
    deliverMap(mapPassed) {
      this.map = mapPassed;
    },
    deliverHead(value) {
      this.head = value;
    },
    save: function() {
      var imageIdx = Math.floor(Math.random() * this.events.length);
      imageIdx += imageIdx % 2 == 0 ? 0 : 1;

      this.coverimageUrl = this.events[imageIdx].imageUrl;

      var route = {
        title: this.title,
        description: this.description,
        seq: this.$session.get("user")["seq"],
        name: this.$session.get("user")["name"],
        startDate: this.setStart,
        country: this.coverimageUrl,
        events: this.events
      };

      axios
        .post("http://192.168.31.114:8399/schedule/insert", route)
        .then(response => (console.log(response.date), this.cancel()))
        .catch(error => {
          console.log(error);
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    }
  }
};
</script>
