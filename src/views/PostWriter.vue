<template>
  <div>
    <h1>Post</h1>

    <vue-ckeditor
           type="classic"
           v-model="editorData"
           :upload-adapter="UploadAdapter"
           ></vue-ckeditor><br /><br />
    <h2>Live editor data</h2>
    <textarea v-model="editorData" style="width:500px; height:500px"></textarea>
  </div>
</template>

<script>
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import VueCkeditor from 'vue-ckeditor5'

export default {
  name: 'postWriter',
  components: {
    'vue-ckeditor': VueCkeditor.component
  },
  data () {
    return {
      editorData: '<p>Hello world!</p>',
      editorDisabled: false
    }
  },
  methods: {
    UploadAdapter (loader) {
      console.log(loader.file)
          // this.loader = loader
          this.upload = () => {
            const body = new FormData();
            body.append('file', loader.file);
            console.log("body")
            console.log(body);
            return fetch('http://192.168.31.114:8399/post/upload', {
              file: body,
              method: 'POST'
            })
            .then(response => (
              console.log("data"),
              console.log(response)
            ))
            .then(downloadUrl => {
              return {
                default: downloadUrl
              }
            })
            .catch(error => {
              console.log(error);
            });
          }
        //   this.abort = () => {
        //     console.log('Abort upload.')
        // }
    }

    // addEventLog (eventName, ...args) {
    //      const eventValue = `Event: '${eventName}'.`
    //      this.eventsLog += `<p>${eventValue} Check debugger console.</p>`
    //      console.log(eventValue)
    //      for (let index = 0, length = args.length; index < length; ++index) {
    //        console.log(args[index])
    //      }
    //      console.log('\n')
    //    }
  }
}
</script>

<style>
.ck-content{
  height:500px;
}
</style>
