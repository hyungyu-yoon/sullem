<template>
  <div>
    <v-img
      :src="coverImage"
      alt="coverImage"
      height="300"
      gradient="to top right, rgba(0,0,0,.2), rgba(0,0,0,.4)"
    >
      <v-layout fill-height justify-center align-center>
        <strong class="white--text text-center">
          {{ title }}
          <br />볼리비아, 23일, 나홀로 여행
        </strong>
      </v-layout>
    </v-img>
  </div>
</template>

<script>
export default {
  name: "ScheduleImage",
  props: {
    coverImage: String
  },
  data() {
    return {
      title: this.$store.state.scheduleTitle
    };
  }
};
</script>

<style>
</style>
