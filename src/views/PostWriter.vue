<template>
  <v-container grid-list-md>
    <v-layout wrap>
      <v-flex xs12>
        <v-btn @click="postUpload" style="float:right">등록</v-btn>
      </v-flex>
      <v-flex xs12>
        <v-sheet elevation="3" height="745px" v-resize="resize">
        <v-layout pa-5 wrap>
          <v-flex xs12 >
            <v-text-field
            label="Title"
            placeholder="제목을 입력하세요"
            outlined
          ></v-text-field>
          </v-flex>
          <v-flex xs12>
            <v-text-field
            label="description"
            placeholder="한줄 설명"
            outlined
          ></v-text-field>
          </v-flex>
          <v-flex xs12>
          <vue-editor id="editor" :style="{height: height}" useCustomImageHandler @imageAdded="handleImageAdded" v-model="editorData"> </vue-editor>

          </v-flex>
        </v-layout>
        </v-sheet>
      </v-flex>
    </v-layout>
    <!-- <textarea v-model="editorData" style="width:500px; height:300px"></textarea> -->
  </v-container>
</template>

<script>
import { VueEditor } from 'vue2-editor'
import axios from 'axios'
export default {
  components: {
    VueEditor
  },

  data () {
    return {
      title: 'titleTest',
      editorData: '',
      thumbnail: '',
      height: '510px'
    }
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
          if (this.thumbnail === '') {
            this.thumbnail = url
          }
          console.log(result)
        })
        .catch(err => {
          console.log(err)
        })
    },
    postUpload: function () {
      axios
        .post('http://localhost:8399/post/uploadPost', {
          title: this.title,
          name: 'testAuthor', // this.$session.get("name")
          thumbnail: this.thumbnail,
          content: this.editorData,
          tag: '#중국 #태국'
        })
        .then(response => (
          console.log(response)
        )
        )
        .catch(error => {
          console.log(error)
          this.errored = true
        })
        .finally(() => this.loading = false)
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
img {
  cursor:pointer;
}
</style>
