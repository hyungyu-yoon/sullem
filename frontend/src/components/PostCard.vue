<template>
<v-hover v-slot:default="{ hover }">
  <v-card   :elevation="hover ? 5 : 1"
   max-height="330px"
  >
    <v-img
      class="white--text"
      height="200px"
      :src="thumbnail"
    >
    </v-img>
    <v-card-title class="headline font-weight-bold">{{title}}</v-card-title>
    <v-card-text class="subtitle-1 font-weight-bold ma-0">
      {{description}}
    </v-card-text>
  </v-card>
</v-hover>
</template>

<script>
export default {
  props: {
    name: { type: String },
    title: { type: String },
    description: { type: String },
    createDate: { type: String },
    thumbnail: { type: String }
  },
  mounted () {

  }
}
</script>

<style>

</style>
