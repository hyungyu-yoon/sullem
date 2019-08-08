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
              <v-card color="white" dark>
                <v-card-text>{{ lorem.slice(0, 70) }}</v-card-text>
              </v-card>
            </v-flex>
            <v-flex xs12>
              <v-flex ma-1 xs12>
                <span class="title font-weight-bold" ma-2>
                  <v-icon>fa-calendar-day</v-icon>&nbsp;Day 1
                </span>
                <span class="subtitle-2 grey--text">2019-08-01</span>
              </v-flex>
              <v-sheet elevation="5">
                <v-timeline align-top dense>
                  <v-timeline-item
                    v-for="(item, i) in items"
                    :key="i"
                    :color="item.color"
                    :icon="item.icon"
                    fill-dot
                  >
                    <v-card class="elevation-2" width="95%">
                      <v-layout pa-3 wrap>
                        <v-flex xs12>
                          <v-icon>fa-clock</v-icon>&nbsp;
                          <span>09:00~10:00</span>
                        </v-flex>
                        <v-flex xs12 sm6>
                          <v-card elevation="3">
                            <v-img height="200" src="../assets/banner.jpg"></v-img>
                          </v-card>
                        </v-flex>
                        <v-flex xs12 sm6>
                          <v-card-title class="font-weight-bold">장소 이름</v-card-title>
                          <v-card-text>부가 설명란</v-card-text>
                        </v-flex>
                      </v-layout>
                      <v-card-text>
                        메모란
                        <br />Lorem ipsum dolor sit amet, no nam oblique veritus. Commune scaevola imperdiet nec ut, sed euismod convenire principes at. Est et nobis iisque percipit, an vim zril disputando voluptatibus, vix an salutandi sententiae.
                      </v-card-text>
                    </v-card>
                  </v-timeline-item>
                </v-timeline>
              </v-sheet>
            </v-flex>
            <v-flex xs12>
              <v-flex ma-1 xs12>
                <span class="title font-weight-bold" ma-2>
                  <v-icon>fa-calendar-day</v-icon>&nbsp;Day 2
                </span>
                <span class="subtitle-2 grey--text">2019-08-02</span>
              </v-flex>
              <v-sheet elevation="5">
                <v-timeline dense>
                  <v-timeline-item
                    v-for="(item, i) in items"
                    :key="i"
                    :color="item.color"
                    :icon="item.icon"
                    fill-dot
                  >
                    <v-card class="elevation-2" width="99%">
                      <v-card-title class="headline">Lorem ipsum</v-card-title>
                      <v-card-text>Lorem ipsum dolor sit amet, no nam oblique veritus. Commune scaevola imperdiet nec ut, sed euismod convenire principes at. Est et nobis iisque percipit, an vim zril disputando voluptatibus, vix an salutandi sententiae.</v-card-text>
                    </v-card>
                  </v-timeline-item>
                </v-timeline>
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
