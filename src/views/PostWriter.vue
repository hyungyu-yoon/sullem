<template>
  <v-container style="height:1000px">
    <v-layout wrap>
      <v-flex xs12>
        <v-btn @click="postUpload" style="float:right">등록</v-btn>
      </v-flex>
      <v-flex xs12>
        <v-sheet height="800">
        <vue-editor id="editor" useCustomImageHandler @imageAdded="handleImageAdded" v-model="post.editorData"> </vue-editor>
      </v-sheet>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { VueEditor } from "vue2-editor";
import axios from "axios";
export default {
  components: {
    VueEditor
  },

  data() {
    return {
      post: {
        title: 'ttt',
        editorData: '<p>Hello~</p>',
        thumbnail: '',
        description:  "it's description"
      }
    };
  },

  methods: {
    handleImageAdded: function(file, Editor, cursorLocation, resetUploader) {
      // An example of using FormData
      // NOTE: Your key could be different such as:
      // formData.append('file', file)

      var formData = new FormData()
      formData.append("image", file)
      console.log(file)

      axios({
        url: "http://192.168.31.114:8399/post/uploadImage",
        method: "POST",
        data: formData
      })
        .then(result => {
          let url = result.data; // Get url from response
            Editor.insertEmbed(cursorLocation, "image", url)

          resetUploader()
          if(this.post.thumbnail == ''){
            this.post.thumbnail = url
          }
          console.log(result)
        })
        .catch(err => {
          console.log(err);
        });
    },
    postUpload : function(){
      console.log(this.post.title)
      axios
        .post('http://localhost:8399/post/uploadPost', {
          title: this.post.title,
          seq: 1,
          name: "shinjong", // this.$session.get("name")
          description: this.post.description,
          thumbnail: this.post.thumbnail,
          content: this.post.editorData,
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
    }
  }
};
</script>

<style>
img:hover {
  cursor:pointer;
}
</style>
