<template>
  <v-container>
    <div id='map'></div>
      <v-sheet class="mx-auto ma-10" elevation="5">
<v-container grid-list-md text-center>
    <v-layout wrap>
      <v-flex xs12>
        <v-tooltip top>
      <template v-slot:activator="{ on }">
        <v-btn v-on="on" @click="activeBtn = 'attraction'"><v-icon :color="activeBtn === 'attraction' ? '#1DE9B6' : ''">fa-camera</v-icon></v-btn>
      </template>
      <span>관광 명소</span>
    </v-tooltip>
    &nbsp;
    <v-tooltip top>
      <template v-slot:activator="{ on }">
        <v-btn v-on="on" @click="activeBtn = 'loddging'"><v-icon :color="activeBtn === 'loddging' ? '#1DE9B6' : ''">fa-bed</v-icon></v-btn>
      </template>
      <span>숙박</span>
    </v-tooltip>
    &nbsp;
    <v-tooltip top>
      <template v-slot:activator="{ on }">
        <v-btn v-on="on" @click="activeBtn = 'restaurant'"><v-icon :color="activeBtn === 'restaurant' ? '#1DE9B6' : ''">fa-utensils</v-icon></v-btn>
      </template>
      <span>음식점</span>
    </v-tooltip>
    &nbsp;
      </v-flex>
      <v-flex xs0 sm2></v-flex>
      <v-flex xs12 sm4>
          <v-text-field
            solo
            label="지역 입력"
            prepend-inner-icon="fa-globe"
            color="rgb(29, 233, 182)"
            id="location"
            placeholder=""
          ></v-text-field>
      </v-flex>
      <v-flex xs12 sm4>
          <v-text-field
            solo
            label="장소 입력"
            prepend-inner-icon="fa-search"
            color="rgb(29, 233, 182)"
            id="place"
            placeholder=""
          ></v-text-field>
      </v-flex>
      <v-flex xs0 sm2></v-flex>

    </v-layout>
<v-divider></v-divider>
  </v-container>
      <v-slide-group
        v-model="model"
        class="pa-0"
        prev-icon="fa-angle-left"
        next-icon="fa-angle-right"
        show-arrows
        center-active
      >
        <!-- <v-slide-item v-for="result in results"
          v-if="result.photos"
          v-slot:default="{ toggle }"
          :key="result.place_id"
        > -->
        <v-slide-item v-for="result in results"
          v-slot:default="{ toggle }"
          :key="result.place_id"
        >

          <v-card
          draggable
          class="ml-2 mr-2 mb-2"
          width="250"
          elevation="3"
          @click="toggle"
          >
            <v-img
              class="white--text"
              height="150px"
              :src="result.photos[0].getUrl(100,100)"
            >
              <v-card-title class="align-end fill-height text-wrap" style="text-shadow: 2px 2px 8px black;">{{result.name}}</v-card-title>
            </v-img>

            <!-- <v-card-text>
                <div class="text-wrap">
                {{result.name}}
              </div>
            </v-card-text> -->
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>

  </v-container>
</template>

<script>
export default {
  name: 'place',
  props: {
    map: null
  },
  data () {
    return {
      locationAutoComplete: null,
      placeAutoComplete: null,
      results: [],
      model: false,
      activeBtn: 'attraction'
    }
  },
  mounted () {
    this.initMap()
  },
  methods: {
    initMap () {
      this.locationAutoComplete = new google.maps.places.Autocomplete(
        /** @type {!HTMLInputElement} */ (
          document.getElementById('location')), {
          types: ['(cities)']
        })
      this.placeAutoComplete = new google.maps.places.Autocomplete(document.getElementById('place'))

      this.locationAutoComplete.addListener('place_changed', this.locationSearch)
      this.placeAutoComplete.addListener('place_changed', this.placeSearch)
    },
    locationSearch () {
      this.model = false
      var place = this.locationAutoComplete.getPlace()
      if (!place.geometry) {
        window.alert("No details available for input: '" + place.name + "'")
        return
      }
      this.map.fitBounds(place.geometry.viewport)

      var query = ''
      if (this.activeBtn === 'attraction') {
        query = 'tourlist attraction in ' + place.name
      } else if (this.activeBtn === 'loddging') {
        query = 'best hotel in ' + place.name
      } else {
        query = 'best restaurant in ' + place.name
      }

      var request = {
        location: place.geometry.location,
        radius: '3000',
        query: query,
        language: 'ko'
      }

      var service = new google.maps.places.PlacesService(this.map)
      service.textSearch(request, this.callback)
    },
    placeSearch () {
      this.model = false

      var place = this.placeAutoComplete.getPlace()
      if (!place.geometry) {
        window.alert("No details available for input: '" + place.name + "'")
        return
      }

      var request = {
        location: place.geometry.location,
        radius: '3000',
        query: place.name,
        language: 'ko'
      }

      var service = new google.maps.places.PlacesService(this.map)
      service.textSearch(request, this.callback)
    },
    callback (results, status) {
      if (status === google.maps.places.PlacesServiceStatus.OK) {
        this.results = results
        console.log(results)
        this.model = true
      }
    }
  }
}
</script>

<style>
.v-text-field__details{
  display:none;
}
</style>
