<template>
  <div>
    <vue-editor id="editor" useCustomImageHandler @imageAdded="handleImageAdded" v-model="editorData"> </vue-editor>
    <textarea v-model="editorData" style="width:500px; height:300px"></textarea>
    <v-btn @click="postUpload">등록</v-btn>

    <textarea>{{thumbnail}}</textarea>
  </div>
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
      title: 'titleTest',
      editorData: '<p>Hello~</p>',
      thumbnail: ''
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
        url: "http://localhost:8399/post/uploadImage",
        method: "POST",
        data: formData
      })
        .then(result => {
          let url = result.data; // Get url from response
          Editor.insertEmbed(cursorLocation, "image", url)
          resetUploader()
          if(this.thumbnail == ''){
            this.thumbnail = url
          }
          console.log(result)
        })
        .catch(err => {
          console.log(err);
        });
    },
    postUpload : function(){

      axios
        .post('http://localhost:8399/post/uploadPost', {
          title: this.title,
          name: "testAuthor", // this.$session.get("name")
          thumbnail: this.thumbnail,
          content: this.editorData,
          tag: "#중국 #태국"
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
