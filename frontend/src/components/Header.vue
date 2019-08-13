<template>
  <div>
    <v-app-bar app dense elevation="1">
      <v-toolbar-title class="text-uppercase">
        <a>
          <span
            class="headline font-weight-bold teal--text text--accent-4"
            @click="$router.push({name:'home'})"
          >Sullem</span>
        </a>
        <span
          class="font-weight-bold subtitle-1 teal--text text--accent-4 hidden-xs-only"
        >설렘이 함께하는 당신의 여행</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-title v-if="this.$store.state.user !=null">{{ this.$store.state.user.name}}님</v-toolbar-title>
      <v-toolbar-items class="hidden-sm-and-down">
        <v-btn v-if="this.$store.state.user !=null" text class="font-weight-black" @click="showCreateSchedule">
          일정등록
        </v-btn>
        <v-btn v-if="this.$store.state.user !=null" text class="font-weight-black" @click="$router.push({name:'postWriter'})">후기등록</v-btn>
        <v-btn
          class="font-weight-black"
          v-if="this.$store.state.user == null"
          @click="showLogin"
          text
        >로그인</v-btn>
        <v-btn
          class="font-weight-black"
          @click="mypage"
          v-if="this.$store.state.user !=null"
          text
        >MyPage</v-btn>
        <v-btn
          class="font-weight-black"
          v-if="this.$store.state.user !=null"
          @click="logout"
          text
        >로그아웃</v-btn>
        <!-- <v-btn class="font-weight-black" @click="jsonTest" text>Json</v-btn> -->
      </v-toolbar-items>
      <v-app-bar-nav-icon class="hidden-md-and-up" @click.stop="drawer = !drawer">
    </v-app-bar-nav-icon>

    </v-app-bar>
<v-navigation-drawer
    v-model="drawer"
    fixed
    temporary
    right
  >
   <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="title" >
            <span class="teal--text text--accent-4 font-weight-bold">SULLEM</span>
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>

  <v-divider></v-divider>

  <v-list
        dense
        nav
      >
      <v-list-item v-if="this.$store.state.user ==null" @click="showLogin">
          <v-list-item-content>
            <v-list-item-title>Login</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item v-if="this.$store.state.user !=null" @click="logout">
          <v-list-item-content>
            <v-list-item-title>Logout</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item  v-if="this.$store.state.user !=null" @click="showCreateSchedule" >
          <v-list-item-content>
            <v-list-item-title>일정등록</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item v-if="this.$store.state.user !=null" @click="$router.push({name:'postWriter'})">
          <v-list-item-content>
            <v-list-item-title>후기등록</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item v-if="this.$store.state.user !=null" @click="mypage">
          <v-list-item-content>
            <v-list-item-title>MyPage</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

      </v-list>
  </v-navigation-drawer>

    <LoginPage></LoginPage>
    <AccountPage></AccountPage>
    <FindPasswordPage></FindPasswordPage>
    <CreateScheduleModal
      :isOpenCreateModal="isOpenCreateSchedule"
      @closeCreateScheduleModal="isOpenCreateSchedule=false"
      @createNewSchedule="createNewSchedule"
    />
  </div>
</template>

<script>
import LoginPage from '@/components/login/LoginPage.vue'
import AccountPage from '@/components/login/AccountPage.vue'
import FindPasswordPage from '@/components/login/FindPasswordPage.vue'
import CreateScheduleModal from '@/components/CreateSchedule/CreateScheduleModal.vue'

import axios from 'axios'

export default {
  name: 'Header',
  components: {
    LoginPage,
    AccountPage,
    FindPasswordPage,
    CreateScheduleModal
  },
  data () {
    return {
      data: '',
      isOpenCreateSchedule: false,
      drawer: null
    }
  },
  mounted () {
    this.$store.state.user = this.$session.get('user')
  },
  methods: {
    showLogin () {
      this.$store.state.Login = true
    },

    showCreateSchedule () {
      if (this.$store.state.user != null) {
        this.isOpenCreateSchedule = true
      } else {
        alert('로그인 해야 이용가능합니다.')
      }
    },
    createNewSchedule (title, startDate) {
      this.isOpenCreateSchedule = false
      this.$router.push('/createschedule')
    },
    async logout () {
      alert('logout')
      this.$session.destroy()
      this.$store.state.user = null
      // this.$router.push('/home')
      this.$router.go(0)
    },
    jsonTest () {
      console.log(this.data)
      axios
        .post('http://192.168.31.114:8399/schedule/insert/', this.data)
        .then(response => console.log(response.data))
        .catch(error => {
          console.log(error)
          this.errored = true
        })

        .finally(() => (this.loading = false))
    },
    mypage () {
      this.$router.push('/mypage')
    }
  }
}
</script>
