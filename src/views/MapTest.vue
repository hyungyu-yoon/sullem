<template>
  <div class="hello">
    <div id="Map"></div>
  </div>
</template>

<script>
/* eslint-disable */
export default {
  name: 'MapTest',
  methods: {
    handleLocationError(browserHasGeolocation, infoWindow, pos) {
      infoWindow.setPosition(pos);
      infoWindow.setContent(browserHasGeolocation ?
                            'Error: The Geolocation service failed.' :
                            'Error: Your browser doesn\'t support geolocation.');
      infoWindow.open(map);
    }
  },
  mounted: function() {
    var map = new google.maps.Map(document.getElementById('Map'), {
      center: { lat: -34.397, lng: 150.644 },
      zoom: 8,
      gestureHandling: 'cooperative'
    })
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
  }
}
</script>
<style scoped>
    #Map {
      height:400px;
      width: 400px;
   }
</style>
