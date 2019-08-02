<template>
  <v-sheet class="mx-auto ma-10" elevation="5" width="100%">
    <v-btn fab text small @click="send">보내기</v-btn>
    <table>
      <thead>
        <tr>
          <th>ALL</th>
          <th>DAY 1</th>
          <th>DAY 2</th>
          <th>DAY 3</th>
        </tr>
      </thead>
      <tbody>
        <template v-for="(value, index) in 24">
          <tr :key="'1' + value">
            <td id="time" rowspan="2">{{ index }}:00</td>
            <!-- dd-hh-mm -->
            <td :id="'0-' + index + '-0'" @mouseenter="upHere = true" @mouseleave="upHere = false">
              <v-icon v-show="upHere" @click="createEvents">fa-plus-circle</v-icon>
            </td>
            <td :id="'1-' + index + '-0'" @mouseenter="mouseOver" @mouseleave="mouseLeave">
              <!-- <v-icon>fa-plus-circle</v-icon> -->
            </td>
            <td :id="'2-' + index + '-0'" @mouseenter="mouseOver" @mouseleave="mouseLeave">
              <!-- <v-icon>fa-plus-circle</v-icon> -->
            </td>
          </tr>
          <tr :key="'2' + value">
            <td :id="'0-' + index + '-1'" @mouseenter="mouseOver" @mouseleave="mouseLeave">
              <!-- <v-icon>fa-plus-circle</v-icon> -->
            </td>
            <td :id="'1-' + index + '-1'" @mouseenter="mouseOver" @mouseleave="mouseLeave">
              <!-- <v-icon>fa-plus-circle</v-icon> -->
            </td>
            <td :id="'2-' + index + '-1'" @mouseenter="mouseOver" @mouseleave="mouseLeave">
              <!-- <v-icon>fa-plus-circle</v-icon> -->
            </td>
          </tr>
        </template>
      </tbody>
    </table>
  </v-sheet>
</template>

<script>
export default {
  name: 'TimeTable',
  data () {
    return {
      upHere: false,
      events: []
    }
  },
  methods: {
    send () {
      this.$emit('sendEvents', this.events)
    },
    addEvent () {},
    mouseOver (e) {
      // e.target.innerHTML = "<v-icon>fa-plus-circle</v-icon>";
      e.target.innerHTML =
        '<button id="addButton" @click="createEvents"><i class="fas fa-plus-circle"></i></button>'
    },
    mouseLeave (e) {
      e.target.innerHTML = ''
    },
    createEvents () {
      console.log('create')
    }
  },
  mounted () {}
}
</script>

<style>
table {
  width: 100%;
  /* border: 0px !important; */
  border-collapse: collapse;
  color: rgb(117, 117, 117);
}
tr td:nth-child(n + 1) {
  border-left: 1px solid rgb(117, 117, 117);
  border-top: 1px solid rgb(117, 117, 117);
  height: 24px;
}
#time {
  width: 64px;
  vertical-align: top;
  text-align: right;
  border-left: 0px;
  padding-right: 10px;
}
th {
  background-color: #1de9b6;
  height: 44px !important;
}
th:nth-child(n + 2) {
  border-left: 1px solid rgb(117, 117, 117);
}
#addButton:hover {
  color: #1de9b6;
}
</style>
