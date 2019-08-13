<template>
  <v-container grid-list-md>
    <v-layout wrap>
      <v-flex xs12>
        <v-btn @click="postUpload" style="float:right" v-if="$route.params.post === undifiend">등록</v-btn>
        <v-btn @click="updatePost" style="float:right" v-else>수정</v-btn>
      </v-flex>
      <v-flex xs12>
        <v-sheet elevation="3" height="745px" v-resize="resize">
        <v-layout pa-5 wrap>
          <v-flex xs12 >
            <v-text-field
            label="Title"
            placeholder="제목을 입력하세요"
            outlined
            v-model="post.title"
          ></v-text-field>
          </v-flex>
          <v-flex xs12>
            <v-text-field
            label="description"
            placeholder="한줄 설명"
            outlined
            v-model="post.description"
          ></v-text-field>
          </v-flex>
          <v-flex xs12>
          <vue-editor id="editor" :style="{height: height}" useCustomImageHandler @imageAdded="handleImageAdded" v-model="post.content"> </vue-editor>

          </v-flex>
        </v-layout>
        </v-sheet>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { VueEditor } from 'vue2-editor'
import axios from 'axios'
import router from '@/router'

export default {
  components: {
    VueEditor
  },

  data () {
    return {
      post: {
        title: '',
        content: '',
        thumbnail: '',
        description: ''
      },
      height: '510px'
    }
  },
  mounted () {
    console.log(this.$store.state.Login)
    if (this.$route.params.post !== undefined) {
      this.post = this.$route.params.post
    }
    console.log(this.$route.params.post)
  },
  methods: {
    handleImageAdded: function (file, Editor, cursorLocation, resetUploader) {
      // An example of using FormData
      // NOTE: Your key could be different such as:
      // formData.append('file', file)

      var formData = new FormData()
      formData.append('image', file)
      console.log(file)

      axios({
        url: 'http://192.168.31.114:8399/post/uploadImage',
        method: 'POST',
        data: formData
      })
        .then(result => {
          let url = result.data // Get url from response
          Editor.insertEmbed(cursorLocation, 'image', url)

          resetUploader()

          if (this.post.thumbnail === '') {
            this.post.thumbnail = url
          }
          console.log(result)
        })
        .catch(err => {
          console.log(err)
        })
    },
    postUpload: async function () {
      console.log(this.post.title)
      await axios
        .post('http://192.168.31.114:8399/post/uploadPost', {
          title: this.post.title,
          seq: this.$session.get('user')['seq'],
          name: this.$session.get('user')['name'],
          description: this.post.description,
          thumbnail: this.post.thumbnail,
          content: this.post.content
        })
        .then(response => (
          console.log(response),
          router.push({ path: 'home' })
        )
        )
        .catch(error => {
          console.log(error)
          this.errored = true
        })
        .finally(() => this.loading = false)
    },
    updatePost () {
      alert('test')
    },
    resize () {
      if (window.innerWidth < 920 && window.innerWidth >= 538) {
        this.height = '480px'
      } else if (window.innerWidth < 538 && window.innerWidth >= 380) {
        this.height = '440px'
      } else if (window.innerWidth < 380) {
        this.height = '420px'
      } else {
        this.height = '510px'
      }
    }
  }
}
</script>

<style>
img:hover {
  cursor:pointer;
}
</style>
