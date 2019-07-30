<template>
  <div>
    <div v-if="!image">
      <h2>Select an image</h2>
      <input type="file" @change="onFileChange">
    </div>
    <div v-else>
      <img :src="image" />
      <button @click="removeImage">Remove image</button>
      <button @click="uploadImage">Upload image</button>
    </div>
  </div>
</template>



<script>
import axios from 'axios'
export default {
  name: 'postPage',
  data () {
    return {
       image: ''
    }
  },
  methods: {
   onFileChange(e) {
     var files = e.target.files || e.dataTransfer.files;
     if (!files.length)
       return;
     this.createImage(files[0]);
   },
   createImage(file) {
     var image = new Image();
     var reader = new FileReader();
     var vm = this;

     reader.onload = (e) => {
       vm.image = e.target.result;
     };
     reader.readAsDataURL(file);
   },
   removeImage: function (e) {
     this.image = '';
   },
   uploadImage: function (){
     axios
     .put('http://localhost:8399/post/uploadImage', this.image)
     .then(response => (
       console.log(response.data)
     ))
     .catch(error => (
       console.log(error),
       this.errored = true
     ))
     .finally(() => this.loading = false)

   }
  }
}
</script>
