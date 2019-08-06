<template>
  <v-dialog v-model="this.$store.state.Account" persistent max-width="600px">
        <v-card>
          <v-card-title>
            <span class="headline">Account</span>
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

                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="name" label="name*" required></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="age" label="age*" type="Number" required></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="gender" label="gender*" required></v-text-field>
                <v-flex xs12>

                  <v-text-field v-model="phone" label="phone(010-0000-0000)*" required></v-text-field>
                </v-flex>
                </v-flex>
              </v-layout>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="cancel">Close</v-btn>
            <v-btn color="blue darken-1" text @click="signUp">Register</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
</template>

<script>
import axios from 'axios'
export default {
  name: 'AccountPage',
  data () {
    return {
      email: null,
      password: null,
      name: null,
      age: null,
      gender: null,
      phone: null
    }
  },
  methods: {
    cancel () {
      this.$store.state.Account = false
    },
    signUp () {
      axios
        .post('http://localhost:8399/member/insert/', {
          email: this.email,
          password: this.password,
          name: this.name,
          age: this.age,
          gender: this.gender,
          phone: this.phone
        })
        .then(response => (
          alert('회원가입을 축하합니다 ^^'),
          this.cancel()
        )
        )
        .catch(error => {
          console.log(error)
          this.errored = true
        })
        .finally(() => this.loading = false)
    }
  }
}
</script>
