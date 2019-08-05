<template>
  <v-layout justify-center>
    <v-dialog v-model="dialog" width="600px">
      <template v-slot:activator="{ on }">
        <v-btn v-on="on">{{ SpecificLocation.name }}</v-btn>
      </template>
      <v-card>
        <v-card-title class="headline">{{ SpecificLocation.name }}</v-card-title>
        <v-card-text>{{ SpecificLocation.place_id }}</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="dialog = false">Disagree</v-btn>
          <v-btn color="green darken-1" text @click="dialog = false">Agree</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
</template>

<script>
export default {
  props: {
    SpecificLocation: null
  },
  data () {
    return {
      dialog: false
    }
  }
}
</script>
