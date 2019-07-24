<template>
  <v-dialog v-model="this.$store.state.Account" persistent max-width="600px">
        <v-card>
          <v-card-title>
            <span class="headline">Account</span>
          </v-card-title>
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="name" label="your name" required></v-text-field>
                </v-flex>
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
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" flat @click="cancel">Close</v-btn>
            <!-- <v-btn color="blue darken-1" flat @click="signUp">register</v-btn> -->
          </v-card-actions>
        </v-card>
      </v-dialog>
</template>

<script>
export default {
  name: 'AccountPage',
  data (){
    return {
      name:null,
      email:null,
      password:null
    }
  },
  methods : {
    cancel () {
      this.$store.state.Account=false;
    }
  }
}
</script>
