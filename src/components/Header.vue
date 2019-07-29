<template>
  <v-layout>
   <v-app-bar app>
      <v-toolbar-title class="headline text-uppercase">
        <span>Sullem</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
        <v-toolbar-items>
          <!-- <v-toolbar-items class="hidden-sm-and-down"> -->
          <!-- <v-toolbar-title v-if="this.$store.state.user != null">{{this.$store.state.user}}</v-toolbar-title> -->
          <v-btn v-if="this.$store.state.user == null" @click="showLogin" text>로그인</v-btn>
          <v-btn v-if="this.$store.state.user !=null" @click="logout" text>로그아웃</v-btn>
          <v-btn v-if="this.$store.state.user !=null" text>마이 페이지</v-btn>
        </v-toolbar-items>
        <v-toolbar-title v-if="this.$store.state.user !=null"> {{ this.$store.state.user.name}}</v-toolbar-title>
    </v-app-bar>
    <LoginPage></LoginPage>
    <AccountPage></AccountPage>
  </v-layout>
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
