<template>
  <v-dialog v-model="dialog" persistent max-width="600px">
        <v-card>
          <v-card-title>
            <span class="headline">{{dialog}}</span>
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
          <!-- Google login  -->
          <v-btn round dark v-on:click="loginWithGoogle">
            <v-icon size="25" class="mr-2">fa-google</v-icon>Google 로그인
          </v-btn>
          <!-- Facebook login -->
          <v-btn color="primary" round dark v-on:click="loginWithFacebook">
            <v-icon size="25" class="mr-2">fa-facebook</v-icon>Facebook 로그인
          </v-btn>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" flat @click="dialog = false">Cancel</v-btn>
            <v-btn color="blue darken-1" flat @click="showRegister">Account</v-btn>
            <v-btn
              color="blue darken-1"
              flat
              @click="loginWithMail"
              @keyup.enter="loginWithMail"
            >Login</v-btn>
            <!-- <v-btn color="blue darken-1" flat @click="logout">logout</v-btn> -->
            <!-- <v-btn color="blue darken-1" flat @click="loginCheck">check</v-btn> -->
          </v-card-actions>
        </v-card>
      </v-dialog>
</template>

<script>

export default {
  name : "Login",
  data () {
    return {

    }
  },
  props : {
  	dialog: {type: Boolean, default: false},
  }
}
</script>
