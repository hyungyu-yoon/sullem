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
      <v-toolbar-items>
        <v-btn text class="font-weight-black" @click="showCreateSchedule">
          새로운
          <br />여행일정
        </v-btn>
        <v-btn text class="font-weight-black">여행후기</v-btn>
        <v-btn
          class="font-weight-black"
          v-if="this.$store.state.user == null"
          @click="showLogin"
          text
        >로그인</v-btn>
        <v-btn
          class="font-weight-black"
          v-if="this.$store.state.user !=null"
          @click="logout"
          text
        >로그아웃</v-btn>
        <v-btn class="font-weight-black" v-if="this.$store.state.user !=null" text>마이 페이지</v-btn>
      </v-toolbar-items>
    </v-app-bar>
    <LoginPage></LoginPage>
    <AccountPage></AccountPage>
    <CreateScheduleModal
      :isOpenCreateModal="isOpenCreateSchedule"
      @closeCreateScheduleModal="isOpenCreateSchedule=false"
      @createNewSchedule="createNewSchedule"
    />
  </div>
</template>

<script>
import LoginPage from "@/components/LoginPage.vue";
import AccountPage from "@/components/AccountPage.vue";
import CreateScheduleModal from "@/components/CreateSchedule/CreateScheduleModal.vue";
import axios from "axios";
export default {
  name: "Header",
  components: {
    LoginPage,
    AccountPage,
    CreateScheduleModal
  },
  data() {
    return {
      data: "",
      isOpenCreateSchedule: false
    };
  },
  mounted() {
    this.$store.state.user = this.$session.get("user");
  },
  methods: {
    showLogin() {
      this.$store.state.Login = true;
    },
    showCreateSchedule() {
      this.isOpenCreateSchedule = true;
    },
    createNewSchedule(title, startDate) {
      this.$router.push("/createschedule");
    },
    async logout() {
      alert("logout");
      this.$session.destroy();
      this.$store.state.user = null;
      this.$router.push("/home");
    },
    jsonTest() {
      axios
        .post("http://localhost:8399/test/json/", this.data)
        .then(response => console.log(response.data))
        .catch(error => {
          console.log(error);
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    }
  }
};
</script>
