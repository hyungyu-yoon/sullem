<template>
  <div>
    <v-app id="inspire">
     <v-toolbar>
       <v-toolbar-side-icon></v-toolbar-side-icon>
       <v-toolbar-title>Traveler</v-toolbar-title>
       <v-spacer></v-spacer>
       <v-toolbar-title v-if="this.$store.state.user !=null"> {{ this.$store.state.user.name}}</v-toolbar-title>
       <v-toolbar-items>
       <!-- <v-toolbar-items class="hidden-sm-and-down"> -->
         <v-btn v-if="this.$store.state.user == null" @click="showLogin" flat>로그인{{this.$store.state.Login}}</v-btn>
         <v-btn v-if="this.$store.state.user !=null" @click="logout" flat>로그아웃</v-btn>
         <v-btn flat>마이 페이지</v-btn>
       </v-toolbar-items>
       <LoginPage></LoginPage>
       <AccountPage></AccountPage>
     </v-toolbar>
   </v-app>
  </div>
</template>

<script>
import LoginPage from '@/components/LoginPage.vue'
import AccountPage from '@/components/AccountPage.vue'

export default {
  name: 'Header',
  components: {
    LoginPage,
    AccountPage
  },
  data () {
    return {

    }
  },
  mounted () {
    this.$store.state.user = this.$session.get('user')
  },
  methods: {
    showLogin () {
      this.$store.state.Login = true
    },
    async logout () {
      alert('logout')
      this.$session.destroy()
      this.$store.state.user = null
      this.$router.push('/')
    }

  }
}
</script>
