<template>
    <div>
        <v-app id="inspire">
            <v-app-bar
            app
            clipped-right
            color="blue-grey"
            dark
            >
                <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
                <v-toolbar-title>Sullem Administration</v-toolbar-title>
                <v-spacer></v-spacer>
            </v-app-bar>

            <v-navigation-drawer
            v-model="drawer"
            app
            temporary
            disable-resize-watcher
            >
                <v-list dense>
                    <v-list-item @click.stop="left = !left">
                        <v-list-item-action @click="memclick">
                            <v-icon>Member</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>User List</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <br>
                    <v-list-item @click.stop="left = !left">
                        <v-list-item-action @click="postclick">
                            <v-icon>Post</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>Post List</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <br>
                    <v-list-item @click.stop="left = !left">
                        <v-list-item-action @click="scheclick">
                            <v-icon>Schedule</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>Schedule List</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-navigation-drawer>

            <v-navigation-drawer
                v-model="left"
                fixed

            ></v-navigation-drawer>

            <v-container>
                <v-card>
                    <v-card-title>
                    Members
                    <v-spacer></v-spacer>
                    <v-text-field
                        v-model="search"
                        append-icon="fas fa-search"
                        label="Search"
                        single-line
                        hide-details
                    ></v-text-field>
                    </v-card-title>
                    <v-data-table
                    :headers="headers"
                    :search="search"
                    :items="members"
                    :page.sync="page"
                    :items-per-page="itemsPerPage"
                    hide-default-footer
                    class="elevation-1"
                    @page-count="pageCount = $event"
                    >
                      <template v-slot:top>
                        <!-- <v-toolbar flat color="white"> -->
                          <v-divider
                            class="mx-4"
                            inset
                            vertical
                          ></v-divider>
                          <v-spacer></v-spacer>
                          <v-dialog v-model="dialog" max-width="500px">
                            <!-- <template v-slot:activator="{ on }">
                              <v-btn color="primary" dark class="mb-2" v-on="on">New Item</v-btn>
                            </template> -->
                            <v-card>
                              <!-- <v-card-title>
                                <span class="headline">{{ formTitle }}</span>
                              </v-card-title> -->

                              <!-- <v-card-text>
                                <v-container grid-list-md>
                                  <v-layout wrap>
                                    <v-flex xs12 sm6 md4>
                                      <v-text-field v-model="editedItem.name" label="Names"></v-text-field>
                                    </v-flex>
                                    <v-flex xs12 sm6 md4>
                                      <v-text-field v-model="editedItem.email" label="Emails"></v-text-field>
                                    </v-flex>
                                    <v-flex xs12 sm6 md4>
                                      <v-text-field v-model="editedItem.age" label="Ages"></v-text-field>
                                    </v-flex>
                                  </v-layout>
                                </v-container>
                              </v-card-text> -->

                              <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                                <v-btn color="blue darken-1" text @click="save">Save</v-btn>
                              </v-card-actions>
                            </v-card>
                          </v-dialog>
                        <!-- </v-toolbar> -->
                      </template>
                      <template v-slot:item.action="{ item }">
                        <v-icon
                          small
                          class="mr-2"
                          @click="editItem"
                        >
                          fas fa-edit
                        </v-icon>
                        <v-icon
                          small
                          @click="deleteItem"
                        >
                          fas fa-trash
                        </v-icon>
                      </template>

                    </v-data-table>
                    <div class="text-center pt-2">
                    <v-pagination v-model="page" :length="pageCount"></v-pagination>
                    <v-text-field
                        :value="itemsPerPage"
                        label="Items per page"
                        type="number"
                        min="-1"
                        max="15"
                        @input="itemsPerPage = parseInt($event, 10)"
                    ></v-text-field>
                    </div>
                </v-card>
            </v-container>
            <v-footer
            app
            color="blue-grey"
            class="white--text"
            style="height: 70px;"
            >

                <v-spacer></v-spacer>
                <span>&copy; 2019</span>
            </v-footer>
        </v-app>
    </div>
</template>

<script>
import axios from 'axios'
import router from '@/router'

export default {
  props: {
    source: String
  },
  mounted () {
    this.selectAll()

    // axios
    //   .get('192.168.31.114:8399/member/selectAll/')
    //   .then(response => (this.info = response))
    // //   console.log(this.info)
  },
  data: () => ({
    dialog: false,
    drawer: false,
    left: false,
    page: 1,
    pageCount: 0,
    itemsPerPage: 10,
    search: '',
    headers: [
      { text: 'Seq', value: 'seq' },
      { text: 'Name', value: 'name' },
      { text: 'Email', value: 'email' },
      { text: 'Age', value: 'age' },
      { text: 'Actions', value: 'action', sortable: false }
    ],
    members: [],
    editedindex: -1,
    editeditem: {
      name: '',
      email: '',
      age: 0
    },
    defaultitem: {
      name: '',
      email: '',
      age: 0
    }
  }),
  // computed: {
  //     formTitle () {
  //       return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
  //     },
  //   },

  watch: {
    dialog (val) {
      val || this.close()
    }
  },

  // created () {
  //   this.initialize()
  // },

  methods: {
    selectAll: function () {
      axios({
        method: 'get',
        url: '//192.168.31.114:8399/member/selectAll/'
      })
        .then(response => {
          console.log(response)
          console.log(response['data'])
          console.log(response['data'][0])
          this.members = response['data']
          // this.members.push(response['data'][0])
        })
    },
    memclick: function () {
      router.push({ path: 'home' })
    },
    postclick: function () {
      router.push({ path: 'home' })
    },
    scheclick: function () {
      router.push({ path: 'home' })
    },
    deleteBySeq: async function () {
      await axios({
        method: 'delete',
        url: '//192.168.31.114:8399/member/delete/5' })
        .then(response => {
          console.log('delete!')
          console.log(response)
        })
      this.selectAll()
    },
    editItem (item) {
      this.editedIndex = this.members.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem (item) {
      const index = this.members.indexOf(item)
      confirm('Are you sure you want to delete this item?') && this.members.splice(index, 1)
    },

    close () {
      this.dialog = false
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      }, 300)
    },

    save () {
      if (this.editedIndex > -1) {
        Object.assign(this.members[this.editedIndex], this.editedItem)
      } else {
        this.members.push(this.editedItem)
      }
      this.close()
    },
    enterkey: function () {
      if (window.event.keyCode === 13) {
        // 엔터키가 눌렸을 때 실행할 내용
        console.log('enter!')
      }
    }
  }
}
</script>

<style>
@media (max-width: 640px) {
  .search {
    width: 230px !important;
  }
}

</style>
