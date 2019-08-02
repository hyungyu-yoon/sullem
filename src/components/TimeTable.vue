<template>
  <v-layout fill-height>
    <v-flex>
      <v-sheet height="64">
        <v-toolbar flat color="white">
          <v-btn outlined class="mr-4" @click="setToday">First</v-btn>
          <v-btn fab text small @click="prev">이전</v-btn>
          <v-btn fab text small @click="next">이후</v-btn>
          <v-btn fab text small @click="send">보내기</v-btn>
          <!-- <v-toolbar-title>{{ title }}</v-toolbar-title> -->
          <v-spacer></v-spacer>
        </v-toolbar>
      </v-sheet>
      <v-sheet height="600">
        <v-calendar
          interval-minutes="30"
          interval-count="48"
          ref="calendar"
          v-model="focus"
          color="#1DE9B6"
          event-text-color="black"
          :events="events"
          :event-color="getEventColor"
          :event-margin-bottom="3"
          :now="today"
          :type="type"
          :end="end"
          @click:event="showEvent"
          @click:more="viewDay"
          @click:date="viewDay"
        ></v-calendar>
        <v-menu
          v-model="selectedOpen"
          :close-on-content-click="false"
          :activator="selectedElement"
          full-width
          offset-x
        >
          <v-card color="grey lighten-4" min-width="350px" flat>
            <v-toolbar :color="selectedEvent.color" dark>
              <v-btn icon>
                <v-icon>edit</v-icon>
              </v-btn>
              <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon>favorite</v-icon>
              </v-btn>
              <v-btn icon>
                <v-icon>more_vert</v-icon>
              </v-btn>
            </v-toolbar>
            <v-card-text>
              <span v-html="selectedEvent.details"></span>
            </v-card-text>
            <v-card-actions>
              <v-btn text color="secondary" @click="selectedOpen = false">Cancel</v-btn>
            </v-card-actions>
          </v-card>
        </v-menu>
      </v-sheet>
    </v-flex>
  </v-layout>
</template>

<script>
export default {
  data: () => ({
    today: '2019-01-08',
    focus: '2019-01-08',
    type: '4day',
    start: '2019-01-08',
    end: null,
    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
    events: [
      {
        name: 'Jeju+International+Airport',
        details: '제주도의 관문!',
        start: '2019-01-08 9:30',
        end: '2019-01-08 10:30',
        color: 'red',
        latlng: { lat: -25.363, lng: 131.044 },
        type: 'location'
      },
      {
        name: '길찾기',
        details: '30km',
        start: '2019-01-08 10:30',
        end: '2019-01-08 12:30',
        color: 'transparent',
        latlng: null,
        type: 'route'
      },
      {
        name: 'Hyupjae+Beach',
        details: 'Going to the beach!',
        start: '2019-01-08 12:30',
        end: '2019-01-08 13:30',
        color: 'blue',
        latlng: { lat: 0, lng: 0 },
        type: 'location'
      }
    ]
  }),
  computed: {
    // title() {
    //   const { start, end } = this;
    //   if (!start || !end) {
    //     return "";
    //   }

    //   const startMonth = this.monthFormatter(start);
    //   const endMonth = this.monthFormatter(end);
    //   const suffixMonth = startMonth === endMonth ? "" : endMonth;

    //   const startYear = start.year;
    //   const endYear = end.year;
    //   const suffixYear = startYear === endYear ? "" : endYear;

    //   const startDay = start.day + this.nth(start.day);
    //   const endDay = end.day + this.nth(end.day);

    //   return "";
    // },
    monthFormatter () {
      return this.$refs.calendar.getFormatter({
        timeZone: 'UTC',
        month: 'long'
      })
    }
  },
  methods: {
    viewDay ({ date }) {
      this.focus = date
      this.today = date
    },
    getEventColor (event) {
      return event.color
    },
    setToday () {
      this.today = this.start
      this.focus = this.start
    },
    prev () {
      this.$refs.calendar.prev()
    },
    next () {
      // this.$refs.calendar.move();
      var nextDay = new Date(this.today)
      nextDay.setDate(nextDay.getDate() + 1)
      // console.log(nextDay);
      // this.$refs.calendar.next();
    },
    showEvent ({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event
        this.selectedElement = nativeEvent.target
        setTimeout(() => {
          this.selectedOpen = true
        }, 10)
      }

      if (this.selectedOpen) {
        this.selectedOpen = false
        setTimeout(open, 10)
      } else {
        open()
      }

      nativeEvent.stopPropagation()
    },
    // nth(d) {
    //   return d > 3 && d < 21
    //     ? "th"
    //     : ["th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"][d % 10];
    // },
    send () {
      this.$emit('sendEvents', this.events)
    }
  }
}
</script>

<style scoped>
.v-event-timed {
  border: 0px !important;
}
</style>
