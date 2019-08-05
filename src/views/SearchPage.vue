<template>
<div>
    <v-sheet class="teal accent-4" height="150" tile>
      <v-layout align-center justify-center fill-height>
        <v-flex xs6 sm4>
          <v-text-field
            solo
            label="장소 입력"
            clearable
            color="teal accent-4"
            class="font-weight-bold"
            append-icon="fa-search"
            @click:append="search"
            v-model="text"
          ></v-text-field>
        </v-flex>
      </v-layout>
    </v-sheet>

    <v-container grid-list-md mt-3>
      <v-layout wrap>
        <v-flex md3 class="hidden-sm-and-down">
          <v-card
            class="mx-auto"
            max-width="300"
            tile
          >
    <v-list rounded>
      <v-subheader>Category</v-subheader>
      <v-list-item-group v-model="item" color="teal accent-4">
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
        >
          <!-- <v-list-item-icon>
            <v-icon v-text="item.icon"></v-icon>
          </v-list-item-icon> -->
          <v-list-item-content>
            <v-list-item-title v-text="item.text" class="font-weight-bold"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-card>
        </v-flex>
        <v-flex xs12 md9>
          <v-layout wrap>
            <v-flex xs12>
                <schedule-list :query="this.$route.params.query"></schedule-list>
            </v-flex>

          </v-layout>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import ScheduleList from '../components/ScheduleList.vue'
export default {
  components: {
    ScheduleList
  },
  data: () => ({
    lorem: `Lorem ipsum dolor sit amet, mel at clita quando. Te sit oratio vituperatoribus, nam ad ipsum posidonium mediocritatem, explicari dissentiunt cu mea. Repudiare disputationi vim in, mollis iriure nec cu, alienum argumentum ius ad. Pri eu justo aeque torquatos.`,
    item: 0,
    items: [
      { text: '여행일정' },
      { text: '여행후기' }

    ],
    text: ''
  }),
  mounted () {
    // alert(this.$route.params.query)
  },
  methods: {
    search () {
      alert(this.text)
    }
  }
}
</script>
