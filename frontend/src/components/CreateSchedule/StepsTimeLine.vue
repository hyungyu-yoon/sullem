<template>
  <div>
    <v-timeline align-top>
      <v-timeline-item color="#F06292" fill-dot right></v-timeline-item>
      <v-timeline-item v-for="step in steps" :key="step" color="#4DB6AC" fill-dot right small>
        <span slot="opposite">
          <v-icon v-if="step.travel_mode == 'TRANSIT'">fa-bus</v-icon>
          <v-icon v-if="step.travel_mode == 'DRIVING'">fa-car</v-icon>
          <v-icon v-if="step.travel_mode == 'WALKING'">fa-walking</v-icon>
        </span>
        <v-card class="elevation-2">
          <v-card-text>
            <p>{{ step.instructions }}</p>
            <p>{{ step.distance }}, {{ step.duration }}</p>
          </v-card-text>
        </v-card>
      </v-timeline-item>
      <v-timeline-item color="#F06292" fill-dot right></v-timeline-item>
    </v-timeline>
  </div>
</template>

<script>
export default {
  name: "StepsTimeLine",
  props: {
    steps: null
  },
  data() {
    return {};
  }
};
</script>