<template>
  <div class="animate-contentopen">
    <v-img
      src="../assets/mypage.jpg"
      aspect-ratio="3.0"
      gradient="rgba(255,255,255,.25), rgba(255,255,255,.5)"
    >
      <v-layout align-center justify-center fill-height>
        <v-flex xs4 md2>
            <h1 style="margin: auto;">MyPage</h1>

        </v-flex>
        &nbsp;
        <v-flex xs6 md4 lg3>

        </v-flex>
      </v-layout>
    </v-img>

    <v-container>
                <v-card>
                    <v-card-title>
                    내 포스트
                    <v-spacer></v-spacer>
                    <v-text-field
                        v-model="search"
                        append-icon="fas fa-search"
                        label="Search"
                        single-line
                        hide-details
                    ></v-text-field>
                    </v-card-title>
                    <v-data-table
                    :headers="headers"
                    :search="search"
                    :items="posts"
                    :page.sync="page"
                    :items-per-page="itemsPerPage"
                    hide-default-footer
                    class="elevation-1"
                    @page-count="pageCount = $event"
                    >
                      <template v-slot:item.action="{ item }">
                        <v-icon
                          small
                          @click="deleteItem(item)"
                        >
                          fas fa-trash
                        </v-icon>
                      </template>

                    </v-data-table>
                    <div class="text-center pt-2">
                    <v-pagination v-model="page" :length="pageCount"></v-pagination>
                    <v-text-field
                        :value="itemsPerPage"
                        label="Items per page"
                        type="number"
                        min="-1"
                        max="15"
                        @input="itemsPerPage = parseInt($event, 10)"
                    ></v-text-field>
                    </div>
                </v-card>
            </v-container>
  </div>
</template>

<script>
import router from '@/router'
import axios from 'axios'
import { async } from 'q';

export default {
  data () {
    return {
      posts: [],
      post: {
      createDate: null,
      description: null,
      likes: null,
      name: null,
      postNo: null,
      thumbnail: null,
      title: null,
      views: null,
    },
    dialog: false,
    drawer: false,
    left: false,
    page: 1,
    pageCount: 0,
    itemsPerPage: 10,
    search: '',
    headers: [
      { text: 'seq', value: 'seq'},
      { text: 'PostNo', value: 'postNo' },
      { text: 'Title', value: 'title' },
      { text: 'Name', value: 'name' },
      { text: 'Description', value: 'description' },
      { text: 'CreateDate', value: 'createDate'},
      { text: 'Likes', value: 'likes' },
      { text: 'Views', value: 'views'},
      { text: 'Actions', value: 'action', sortable: false },
    ],
    }
  },
  components: {},
  created () {
    console.log(this.$session.get('user'))
    if (this.$session.get('user') === undefined) {
      router.push({ path: 'home' })
      alert('로그인 해야 이용 가능한 서비스입니다.')
    }
  },
  mounted () {
    this.selectAll()
  },
  methods: {
    selectAll: function () {
      axios({
        method: 'get',
        url: `//192.168.31.114:8399/post/selectBySeq/${this.$session.get('user')['seq']}`
      })
        .then(response => {
          console.log(response['data'])
          this.posts = response['data']
          // this.posts.push(response['data'][0])
        })
    },
  }
}
</script>
<style>

</style>
