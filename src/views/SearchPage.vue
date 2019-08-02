<template>
<div>
    <v-img
      src="../assets/banner2.jpg"
      aspect-ratio="3.0"
      gradient="rgba(255,255,255,.25), rgba(255,255,255,.5)"
    >
      <v-layout align-center justify-center fill-height></v-layout>
    </v-img>

    <v-container grid-list-md>
      <v-layout wrap>
        <v-flex md3 class="hidden-sm-and-down">
          <v-card color="purple" dark>
            <v-card-title primary class="title">Lorem</v-card-title>
            <v-card-text>{{ lorem }}</v-card-text>
          </v-card>
        </v-flex>
        <v-flex xs12 md9>
          <v-layout wrap>
            <v-flex xs12>
              <v-sheet elevation="5">
                  <span>게시물 등록 장소</span>
              </v-sheet>
            </v-flex>

          </v-layout>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
export default {
  data: () => ({
    lorem: `Lorem ipsum dolor sit amet, mel at clita quando. Te sit oratio vituperatoribus, nam ad ipsum posidonium mediocritatem, explicari dissentiunt cu mea. Repudiare disputationi vim in, mollis iriure nec cu, alienum argumentum ius ad. Pri eu justo aeque torquatos.`,
    items: [
      {
        color: 'red lighten-2',
        icon: 'fa-camera'
      },
      {
        color: 'purple darken-1',
        icon: 'fa-utensils'
      },
      {
        color: 'green lighten-1',
        icon: 'fa-camera'
      },
      {
        color: 'indigo',
        icon: 'fa-bed'
      }
    ]
  })
}
</script>
