<template>
  <v-layout justify-center>
    <v-dialog v-model="isOpen" width="800px">
      <v-card>
        <v-card-title class="headline">경로 찾기</v-card-title>
        <v-card-text>
          <RouteMap :mapEvent="event" />
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="sendClose">취소</v-btn>
          <v-btn color="green darken-1" text @click="sendClose">저장</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
</template>

<script>
import RouteMap from './routeMap.vue'
export default {
  name: 'SelectRouteModal',
  components: {
    RouteMap
  },
  props: {
    isOpen: false,
    event: null
  },
  methods: {
    sendClose () {
      this.$emit('close')
    }
  }
}
</script>
