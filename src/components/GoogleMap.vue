<template>
  <div id="Map" style="display: inline;"></div>
  <!-- <div class="hello" style="display: flex;">
    <div id="directionsPanel" style="float:left;width:30%;height 100%; display: inline;"></div>
    <div>
      <v-btn
        text
        @click="addMarker"
      >
        열기
      </v-btn>
    </div>
  </div> -->
</template>

<script>
/* eslint-disable */
export default {
  name: 'MapTest',
  props: {
    mapEvents: Array
  },
  methods: {
    handleLocationError(browserHasGeolocation, infoWindow, pos) {
      infoWindow.setPosition(pos);
      infoWindow.setContent(browserHasGeolocation ?
                            'Error: The Geolocation service failed.' :
                            'Error: Your browser doesn\'t support geolocation.');
      infoWindow.open(map);
    },
    displayRoute(directionsService, directionsDisplay) {
      directionsService.route({
        origin: 'Jeju+International+Airport',
        destination: 'Hyupjae+Beach',
        travelMode: 'TRANSIT',
        provideRouteAlternatives: true
      }, function(response, status) {
        if (status === 'OK') {
          console.log(response)
          directionsDisplay.setDirections(response);
        } else {
          window.alert('Directions request failed due to ' + status);
        }
      });
    },
    addMarker() {
      console.log(this.mapEvents)
      // var marker = new google.maps.Marker({
      //   position: myLatLng,
      //   map: map,
      //   title: 'Hello World!'
      // });
    }
  },
  mounted: function() {
    var directionsService = new google.maps.DirectionsService;
    var directionsDisplay = new google.maps.DirectionsRenderer;
    var map = new google.maps.Map(document.getElementById('Map'), {
      center: { lat: -34.397, lng: 150.644 },
      zoom: 8,
      gestureHandling: 'cooperative'
    })

    directionsDisplay.setMap(map);
    directionsDisplay.setPanel(document.getElementById('directionsPanel'));
    var infoWindow = new google.maps.InfoWindow;

    // Try HTML5 geolocation.
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function(position) {
        var pos = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        };

        infoWindow.setPosition(pos);
        infoWindow.setContent('Location found.');
        infoWindow.open(map);
        map.setCenter(pos);
      }, function() {
        handleLocationError(true, infoWindow, map.getCenter());
      });
    } else {
      // Browser doesn't support Geolocation
      handleLocationError(false, infoWindow, map.getCenter());
    }
    // this.displayRoute(directionsService, directionsDisplay);
    // this.drawPolyline(map);
    this.$emit('sendMap', map)
  }
}
</script>
<style scoped>
    #Map {
      height: 500px;
      width: 100%;
   }
</style>
