<template>
  <v-dialog v-model="this.$store.state.Login" persistent max-width="600px">
        <v-card>
          <v-card-title>
            <span class="headline">Login</span>
          </v-card-title>
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12>
                  <v-text-field v-model="email" label="Email*" required></v-text-field>
                </v-flex>
                <v-flex xs12>
                  <v-text-field v-model="password" label="Password*" type="password" required></v-text-field>
                </v-flex>
              </v-layout>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <!-- <v-btn round dark v-on:click="loginWithGoogle">
            <v-icon size="25" class="mr-2">fa-google</v-icon>Google 로그인
          </v-btn>
          <v-btn color="primary" round dark v-on:click="loginWithFacebook">
            <v-icon size="25" class="mr-2">fa-facebook</v-icon>Facebook 로그인
          </v-btn> -->
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn  flat @click="cancel">Cancel</v-btn>
            <v-btn  flat @click="showAccount">Account</v-btn>
            <v-btn  flat @click="login">Login</v-btn>
          </v-card-actions>
        </v-card>

      </v-dialog>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Login',
  data () {
    return {
      email: null,
      password: null
    }
  },
  methods: {
    async showAccount () {
      this.$store.state.Login = false
      this.$store.state.Account = true
    },
    cancel () {
      this.$store.state.Login = false
    },
    async login () {
      var user = await this.loginWithEmail();
      // console.log(user)
      if(user != null && user.data != "") {
        this.$store.state.user = user.data;
        this.$session.start();
        this.$session.set('user', this.$store.state.user);
        alert(this.$session.get('user').name + '님 로그인에 성공하셨습니다.');
        this.cancel();
      } else {
        alert("회원정보가 일치하지 않습니다.");
      }
    },
    async loginWithEmail () {
      return axios
        .post('http://localhost:8399/member/login/' + this.email + '/' + this.password)
        .catch(error => {
          console.log(error)
          this.errored = true
        })
        .finally(() => this.loading = false)
    }
  }
}
</script>
