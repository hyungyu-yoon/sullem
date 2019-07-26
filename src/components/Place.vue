<template>
    <div class='place'>
        <div id='map'></div>

        <v-sheet
    class="mx-auto"
    elevation="8"

  >
    <v-slide-group
      v-model="model"
      class="pa-4"
      prev-icon="fa-angle-left"
      next-icon="fa-angle-right"
      show-arrows
    >
      <v-slide-item
        v-for="n in 10"
        :key="n"
        v-slot:default="{ active, toggle }"
      >

      <v-card
    class="ma-6"
    width="400"

  >
    <v-img
      class="white--text"
      height="200px"
      src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
    >
      <v-card-title class="align-end fill-height">장소 이름</v-card-title>
    </v-img>

    <v-card-text>
      <span>요약</span><br>
      <span class="text--primary">
        <span>Whitehaven Beach</span><br>
        <span>Whitsunday Island, Whitsunday Islands</span>
      </span>
    </v-card-text>

  </v-card>

      </v-slide-item>
    </v-slide-group>
  </v-sheet>
    </div>
</template>

<script>
export default {
  name: 'place',
  data () {
    return {
      map: null,
      result: null,
      model: null
    }
  },
  mounted () {
    this.initMap()
  },
  methods: {
    initMap () {
      this.map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: -33.8666, lng: 151.1958 },
        zoom: 15
      })

      // Search for Google's office in Australia.
      var request = {
        location: this.map.getCenter(),
        radius: '500',
        query: '123+main+street'
      }

      var service = new google.maps.places.PlacesService(this.map)
      service.textSearch(request, this.callback)
    },
    callback (results, status) {
      if (status == google.maps.places.PlacesServiceStatus.OK) {
        this.result = results
        for (var i = 0; i < results.length; i++) {
          var x = results[i].photos[0].getUrl(300, 300)
          console.log(x)
          var marker = new google.maps.Marker({
            map: this.map,
            place: {
              placeId: results[i].place_id,
              location: results[i].geometry.location
            }
          })
        }
      }
    }
  }
}
</script>
