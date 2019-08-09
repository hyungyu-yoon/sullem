<template>
  <div>
   <v-app-bar app dense elevation="1">
      <v-toolbar-title class=" text-uppercase">
        <a><span class="headline font-weight-bold teal--text text--accent-4" @click="$router.push({name:'home'})">Sullem </span></a>
        <span class="font-weight-bold subtitle-1 teal--text text--accent-4 hidden-xs-only">설렘이 함께하는 당신의 여행</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-title v-if="this.$store.state.user !=null"> {{ this.$store.state.user.name}}님</v-toolbar-title>
        <v-toolbar-items>
          <v-btn text class="font-weight-black">여행일정</v-btn>
          <v-btn text class="font-weight-black">여행후기</v-btn>
          <v-btn class="font-weight-black" v-if="this.$store.state.user == null" @click="showLogin" text>로그인</v-btn>
          <v-btn class="font-weight-black" v-if="this.$store.state.user !=null" @click="logout" text>로그아웃</v-btn>
          <v-btn class="font-weight-black" v-if="this.$store.state.user !=null" text>마이 페이지</v-btn>
          <!-- <v-btn class="font-weight-black" @click="jsonTest" text>Json</v-btn> -->
        </v-toolbar-items>
    </v-app-bar>
    <LoginPage></LoginPage>
    <AccountPage></AccountPage>
    <FindPasswordPage></FindPasswordPage>
  </div>
</template>

<script>
import LoginPage from '@/components/login/LoginPage.vue'
import AccountPage from '@/components/login/AccountPage.vue'
import FindPasswordPage from '@/components/login/FindPasswordPage.vue'

import axios from 'axios'
export default {
  name: 'Header',
  components: {
    LoginPage,
    AccountPage,
    FindPasswordPage
  },
  data () {
    return {
      // data: {
      //   title: "test",
      //   seq: 1,
      //   name: "Smith",
      //   description: "description",
      //   country: "seoul",
      //   startDate: "2019-03-01",
      //   events: [
      //     {
      //       "name": "제주국제공항",
      //       "details": "없음.",
      //       "start": "2019-01-08 00:00",
      //       "end": "2019-01-08 00:30",
      //       "color": "#80CBC4",
      //       "latlng": { "lat": 33.505869, "lng": 126.49278000000004 },
      //       "type": "location"
      //     },
      //     {
      //       "name": "TRANSIT",
      //       "origin": { "lat": 33.505869, "lng": 126.49278000000004 },
      //       "destination": { "lat": 33.45805600000001, "lng": 126.9425 },
      //       "overview_path": "ubokE{r`cWpC|ApC|FdA@`DeBFgBcEoLiE}Izg@eDfu@tABr_@]~ZfD`K|[zo@tV`\\rMhWfDzFlLrG|VrXdJxGdPtP`K~GrHnD|AnPdBxGjNrVxMrRhHlGb\\zRxUhPjTfWvMhMfPlNpR`Zvo@x_AhN|N`LzIlQxJjMbKpo@hgAzPlW|ItRdH`PxIvLpPjM~YdNji@hUbRxCjv@dO~YhFt\\`ChPnDzOpIn\\lSvPbIlq@rOxh@nIhi@dFzWtEfl@|QfGxChRnXxCdDvM~GvZjQ~@pAz@@|b@}PnOaHhQmJdEaFvF_KrGsDto@wHvKwEvQoMhUoOrBoArFmJhGeIrJiHdEuFnHwC|JwIxByHdD{EbLkEjCgCNcIbCgPr@{f@qAua@v@y]vKan@zM_JpFcMfOgp@nCyHjWfJlCrBj@zRBxCj@b@lEZ`FpB~DTvE~DfDb@]qAuBV}CaCgFoAkBa@qCuAFm@`AuBhClBh@mA_AwAg@XkALmAxBuAZiC[HiLdFqAk@g@{DzAOa@U_GsAyCbFmNvAiArJqHvGwCpLyP`AwC`BcV\\aCfSyRbIiTiC}S_FcO_AkClA_BlCwDN_CiB{JuHaDgFgFoB}DaBox@nAkKtAkIq@wI_AoJbAsGhAiLtAsKhHuGrG{R?gG|JaPxDiMzHq\\j@y@xBmMf@kFoAeCmCgLSyPeDoGuAsR}BeMtAaLx@mC?aJQqI[sFqBEoOrD_P~@uQaDsZ}IfDkRpAoIxE{{@hEqPhGw]hDoZkDsMu@aGbAkJ|AkFcB}Go@cJ_EuMoFqKgBmLe@cKh@wOToBuKcKqQcPKaBjW_f@D_ETuEwBaGqIwI{KcRyJ}IyKmf@uHi`@}BaYyB_NcCw^}AwAoHgZsJ_l@r@kHd@gPv@mdAoEez@iEmOcIgPmHgKoR{RaQg|@u@wPwAq[ByM~@yW_AeToE{]jFsu@nA}S_BiaAeAcSwEuXaRgdAgNcn@gQarAgIeXsIiRqFcN}Na\\cMiR}QyYke@kl@_QiW{CkKYc]|Cq{ByBke@cHcSmRwu@aFa`@kDiJ_ZiPoG{@sg@kR_QuO_KkKsH}Fi[}TkU_QkSiQ}_@sh@}WuXkb@m`@{_@qGs]wRiZyNoU}NgVuNeMwEgGcFaIsKaBuFkDwLgPuZs`@yTiV{PsFaBur@qOq`@kNmRgKyFwDyc@eQom@qOoa@_HeS_CyL_G_FgCeEi@gOj@wAeLJiAy@GwE\\wA@~AwD{@sCoBiNi@{FeB{BkFkM{LsFeXyWoFuDcFmF{OsMb\\_z@",
      //       "panel": "[object HTMLDivElement]",
      //       "start": "2019-01-08 00:30",
      //       "end": "2019-01-08 01:00",
      //       "color": "#F48FB1",
      //       "latlng": null,
      //       "type": "route"
      //     },
      //     {
      //       "name": "성산 일출봉",
      //       "details": "없음.",
      //       "start": "2019-01-08 01:00",
      //       "end": "2019-01-08 01:30",
      //       "color": "#80CBC4",
      //       "latlng": { "lat": 33.45805600000001, "lng": 126.9425 },
      //       "type": "location"
      //     },
      //     {
      //       "name": "길찾기",
      //       "origin": { "lat": 33.45805600000001, "lng": 126.9425 },
      //       "destination": { "lat": 33.5162323, "lng": 126.5120637 },
      //       "overview_path": null,
      //       "panel": null,
      //       "start": "2019-01-08 01:30",
      //       "end": "2019-01-08 03:00",
      //       "color": "transparent",
      //       "latlng": null,
      //       "type": "route"
      //     },
      //     {
      //       "name": "용두암",
      //       "details": "없음.",
      //       "start": "2019-01-08 03:00",
      //       "end": "2019-01-08 03:30",
      //       "color": "#80CBC4",
      //       "latlng": { "lat": 33.5162323, "lng": 126.5120637 },
      //       "type": "location"
      //     }
      //   ]
      // }
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
      this.$router.push('/home')
    },
    jsonTest () {
      console.log(this.data)
      axios
        .post('http://192.168.31.114:8399/schedule/insert/', this.data
        )
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
