<template>
    <div class='place'>
        <div id='map'></div>
    </div>
</template>

<script>
export default {
  name: 'place',
  data () {
    return {
      map: null
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
        query: 'tourlist attraction in Sydney'
      }

      var service = new google.maps.places.PlacesService(this.map)
      service.textSearch(request, this.callback)
    },
    callback (results, status) {
      if (status == google.maps.places.PlacesServiceStatus.OK) {
        for (var i = 0; i < results.length; i++) {
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
