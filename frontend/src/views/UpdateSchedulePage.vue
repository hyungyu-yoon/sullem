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
  name: "UpdateSchedulePage",
  components: {
    ScheduleImage,
    GoogleMap,
    TimeTable,
    Place
  },
  data() {
    return {
      no: "",
      results: "",
      seq: "",
      title: "",
      description: "",
      coverimageUrl: "",
      map: null,
      events: [],
      setStart: "",
      head: ""
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
    save() {
      var imageIdx = Math.floor(Math.random() * this.events.length);
      imageIdx += imageIdx % 2 == 0 ? 0 : 1;

      this.coverimageUrl = this.events[imageIdx].imageUrl;

      var route = {
        title: this.title,
        description: this.description,
        seq: this.seq,
        name: this.$session.get("user")["name"],
        startDate: this.setStart,
        country: this.coverimageUrl,
        events: this.events
      };

      axios
        .post("http://192.168.31.114:8399/schedule/update", route)
        .then(response => (console.log(response.date), this.cancel()))
        .catch(error => {
          console.log(error);
          this.errored = true;
        })
        .finally(() => (this.loading = false));

      this.$router.push("/home");
    }
  },
  created() {
    if (this.$session.get("user") !== undefined) {
      this.seq = this.$session.get("user")["seq"];
    }
    this.no = this.$route.params.no;
    axios
      .get("http://192.168.31.114:8399/schedule/selectByNo/" + this.no)
      .then(response => {
        var data = JSON.parse(response.data.events);
        var date = "";
        var count = -1;
        var results = [];
        for (let i = 0; i < data.length; i++) {
          if (date !== data[i].start.substring(0, 10)) {
            count++;
            results.push([]);
            date = data[i].start.substring(0, 10);
            results[count].push(data[i]);
          } else {
            results[count].push(data[i]);
          }
        }
        this.events = results;

        this.results = response.data;
        this.events = results;
        this.results = response.data;
        this.title = this.results.title;
        this.description = this.results.description;
        this.name = this.results.name;
        this.startDate = this.results.setStart;
        this.head = this.results.setStart;
        this.coverimageUrl = this.results.country;
        console.log(this.results);
      })
      .catch(function(error) {
        console.log(error);
      });

    // this.disqus_config = function() {
    //   this.page.url = "http://192.168.31.129:8080/schedule/" + this.no; // Replace PAGE_URL with your page's canonical URL variable
    //   this.page.identifier = this.no; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
    // };

    // let recaptchaScript = document.createElement("script");
    // recaptchaScript.setAttribute(
    //   "src",
    //   "https://happyhacking-1.disqus.com/embed.js"
    // );
    // document.head.appendChild(recaptchaScript);
  },
  beforeDestroy() {
    this.save();
  }
};
</script>
