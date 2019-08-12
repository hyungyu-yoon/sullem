<template>
  <div>
    <v-timeline align-top>
      <v-timeline-item v-for="n in 3" :key="n" color="#4DB6AC" fill-dot right>
        <span slot="opposite">Tus eu perfecto</span>
        <v-card class="elevation-2">
          <v-card-text>
            <span></span>
            <span></span>
          </v-card-text>
        </v-card>
      </v-timeline-item>
    </v-timeline>
  </div>
</template>

<script>
export default {
  data: () => ({})
};
</script>