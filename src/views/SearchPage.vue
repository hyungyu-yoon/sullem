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
            @click:append="search()"
            @keyup.enter="search()"
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
            <v-list-item-group v-model="item" color="teal accent-4" mandatory>
              <v-list-item
                v-for="(item, i) in items"
                :key="i"
                 @click="selectCategory(i)"
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
                <search-item-list
                v-if="results"
                 :results="results"
                ></search-item-list>
            </v-flex>

          </v-layout>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import SearchItemList from '../components/SearchItemList.vue'
import axios from 'axios'
export default {
  components: {
    SearchItemList
  },
  data: () => ({
    item: 0,
    items: [
      { text: '여행일정' },
      { text: '여행후기' }

    ],
    text: '',
    results: null,
    page: 1
  }),
  mounted () {
    this.text = this.$route.params.query
    if (this.$route.params.category === '여행일정') {
      this.item = 0
      this.getScheduleList()
    } else if (this.$route.params.category === '여행후기') {
      this.item = 1
      this.getPostList()
    }
  },
  methods: {
    search () {
      console.log(this.item)
      if (this.item === 0) {
        this.getScheduleList()
      } else if (this.item === 1) {
        this.getPostList()
      }
    },
    selectCategory (item) {
      this.text = ''
      this.item = item
      this.search()
    },
    getScheduleList () {
      this.results = null
    },
    getPostList () {
      var query = this.text === '' ? ' ' : this.text
      axios.get('http://192.168.31.114:8399/post/selectPage/' + query + '/' + this.page)
        .then(response => {
          this.results = response.data
          this.results.category = this.item
          console.log(this.results)
        }
        )
        .catch(function (error) {
          console.log(error)
        })
    }
  }
}
</script>
