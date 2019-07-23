<template>
  <div>
    <v-app id="inspire">
     <v-toolbar>
       <v-toolbar-side-icon></v-toolbar-side-icon>
       <v-toolbar-title>Traveler</v-toolbar-title>
       <v-spacer></v-spacer>
       <v-toolbar-items class="hidden-sm-and-down">
         <v-btn @click="dialog=true" flat>로그인{{dialog}}</v-btn>
         <v-btn flat>로그아웃</v-btn>
         <v-btn flat>마이 페이지</v-btn>
       </v-toolbar-items>
       <!-- <LoginPage dialog=dialog></LoginPage> -->
     </v-toolbar>
   </v-app>
  </div>
</template>


<script>
import LoginPage from "./LoginPage.vue"
export default {
  name : 'Header',
  components : {
    LoginPage
  },
  data (){
    return {
      user : null,
      dialog : false
    }
  }
}
</script>
