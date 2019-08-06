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
            >
                <v-list dense>
                    <v-list-item @click.stop="left = !left">
                        <v-list-item-action>
                            <v-icon>Member</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>User List</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <br>
                    <v-list-item @click.stop="left = !left">
                        <v-list-item-action>
                            <v-icon>Post</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>Post List</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <br>
                    <v-list-item @click.stop="left = !left">
                        <v-list-item-action>
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
                temporary
            ></v-navigation-drawer>
            <v-container grid-list-xl>
                <v-layout
                wrap
                justify-space-around
                >
                    <v-flex>
                        <v-flex style="display: inline-block;">
                            <v-select
                            style="width: 60px;"
                            v-model="icon"
                            :items="icons"
                            ></v-select>
                        </v-flex>
                        <v-flex xs8 md6 style="display: inline-block;">
                            <v-text-field
                            style="width: 500px;"
                            solo
                            label="검색"
                            prepend-inner-icon="fa-search"
                            color="rgb(29, 233, 182)"
                            placeholder
                            ></v-text-field>
                        </v-flex>
                    </v-flex>
                </v-layout>
            </v-container>

                <!-- <div style="display: inherit;">
                    <v-icon >fas fa-search</v-icon>&nbsp;
                    <v-flex xs12 sm4>
                        <v-text-field
                        placeholder
                        ></v-text-field>
                    </v-flex>
                </div> -->
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
export default {
  props: {
    source: String
  },
  data: () => ({
    drawer: null,
    left: false,
    icons: [
      'name',
      'email',
      'seq'
    ],
    icon: 'name'
  })
}
</script>

<style>

</style>
