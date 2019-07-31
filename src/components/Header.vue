<template>
  <div>
   <v-app-bar app dense elevation="1">
      <v-toolbar-title class=" text-uppercase">
        <span class="headline font-weight-bold teal--text text--accent-4" >Sullem </span>
        <span class="font-weight-bold subtitle-1 teal--text text--accent-4 hidden-xs-only">설렘이 함께하는 당신의 여행</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
        <v-toolbar-items>
          <!-- <v-toolbar-items class="hidden-sm-and-down"> -->
          <!-- <v-toolbar-title v-if="this.$store.state.user != null">{{this.$store.state.user}}</v-toolbar-title> -->
          <v-btn text class="font-weight-black">여행일정</v-btn>
          <v-btn text class="font-weight-black">여행후기</v-btn>
          <v-btn class="font-weight-black" v-if="this.$store.state.user == null" @click="showLogin" text>로그인</v-btn>
          <v-btn class="font-weight-black" v-if="this.$store.state.user !=null" @click="logout" text>로그아웃</v-btn>
          <v-btn class="font-weight-black" v-if="this.$store.state.user !=null" text>마이 페이지</v-btn>
        </v-toolbar-items>
        <v-toolbar-title v-if="this.$store.state.user !=null"> {{ this.$store.state.user.name}}</v-toolbar-title>
    </v-app-bar>
    <LoginPage></LoginPage>
    <AccountPage></AccountPage>
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
