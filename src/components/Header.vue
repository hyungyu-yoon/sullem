<template>
  <v-layout>
     <v-toolbar>
       <v-toolbar-title>Traveler</v-toolbar-title>
       <v-spacer></v-spacer>
       <v-toolbar-title v-if="this.$store.state.user !=null"> {{ this.$store.state.user.name}}</v-toolbar-title>
       <v-toolbar-items>
       <!-- <v-toolbar-items class="hidden-sm-and-down"> -->
       <!-- <v-toolbar-title v-if="this.$store.state.user != null">{{this.$store.state.user}}</v-toolbar-title> -->
         <v-btn v-if="this.$store.state.user == null" @click="showLogin" text>로그인</v-btn>
         <v-btn v-if="this.$store.state.user !=null" @click="logout" text>로그아웃</v-btn>
         <v-btn v-if="this.$store.state.user !=null" text>마이 페이지</v-btn>
         <v-btn @click="jsonTest" text>JSON 등록Test</v-btn>
       </v-toolbar-items>
       <LoginPage></LoginPage>
       <AccountPage></AccountPage>
     </v-toolbar>
  </v-layout>
</template>

<script>
import LoginPage from '@/components/LoginPage.vue'
import AccountPage from '@/components/AccountPage.vue'
import axios from 'axios'
export default {
  name: 'Header',
  components: {
    LoginPage,
    AccountPage
  },
  data () {
    return {
      data:
        {
          title: '신종이 여행',
          date: '2018-10-01~2019-11-03',
          days:
          [
            {
              day: 1,
              route: [
                {
                  origin: 'place_id:ChIJ3S-JXmauEmsRUcIaWtf4MzE',
                  destination: 'place_id:ChIJdT8Y6SlhDDURyJcsX9FENmU',
                  travelMode: 'TRANSIT',
                  start_time: '09:30',
                  end_time: '11:30',
                  comment: '여권 챙기기!'
                },
                {
                  origin: 'place_id:ChIJ3S-JXmauEmsRUcIaWtf4MzE',
                  destination: 'place_id:ChIJdT8Y6SlhDDURyJcsX9FENmU',
                  travelMode: 'TRANSIT',
                  start_time: '09:30',
                  end_time: '11:30',
                  comment: '여권 챙기기!'
                },
                {
                  origin: 'place_id:ChIJ3S-JXmauEmsRUcIaWtf4MzE',
                  destination: 'place_id:ChIJdT8Y6SlhDDURyJcsX9FENmU',
                  travelMode: 'TRANSIT',
                  start_time: '09:30',
                  end_time: '11:30',
                  comment: '여권 챙기기!'
                }
              ]
            },
            {
              day: 2,
              route: [
                {
                  origin: 'place_id:ChIJ3S-JXmauEmsRUcIaWtf4MzE',
                  destination: 'place_id:ChIJdT8Y6SlhDDURyJcsX9FENmU',
                  travelMode: 'TRANSIT',
                  start_time: '09:30',
                  end_time: '11:30',
                  comment: '여권 챙기기!'
                },
                {
                  origin: 'place_id:ChIJ3S-JXmauEmsRUcIaWtf4MzE',
                  destination: 'place_id:ChIJdT8Y6SlhDDURyJcsX9FENmU',
                  travelMode: 'TRANSIT',
                  start_time: '09:30',
                  end_time: '11:30',
                  comment: '여권 챙기기!'
                },
                {
                  origin: 'place_id:ChIJ3S-JXmauEmsRUcIaWtf4MzE',
                  destination: 'place_id:ChIJdT8Y6SlhDDURyJcsX9FENmU',
                  travelMode: 'TRANSIT',
                  start_time: '09:30',
                  end_time: '11:30',
                  comment: '여권 챙기기!'
                }
              ]
            }
          ]
        }

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
    },
    jsonTest () {
      axios
        .post('http://localhost:8399/test/json/', this.data)
        .then(response => (
          console.log(response.data)
        ))
        .catch(error => {
          console.log(error)
          this.errored = true
        })
        .finally(() => this.loading = false)
    }
  }
}
</script>
