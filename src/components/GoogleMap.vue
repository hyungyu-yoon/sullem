<template>
  <div>
    <div id="map" style="width: 1240px"></div>
    <v-btn text @click="addMarker">열기</v-btn>
  </div>
  <!-- <div>
    <div id="directionsPanel" style="float:left;width:30%;height 100%; display: inline;"></div>

  </div>-->
</template>

<script>
// import { log } from 'util'
/* eslint-disable */
export default {
  name: "MapTest",
  props: {
    mapEvents: Array
  },
  data() {
    return {
      map: null,
      infoWindow: null,
      directionsService: null,
      directionsDisplay: null
    };
  },
  methods: {
    initMap() {
      this.directionsService = new google.maps.DirectionsService();
      this.directionsDisplay = new google.maps.DirectionsRenderer();
      this.map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 0, lng: 0 },
        zoom: 2,
        gestureHandling: "cooperative"
      });
      this.$nextTick(() => {
        this.directionsDisplay.setMap(this.map);
      });
      this.infoWindow = new google.maps.InfoWindow();
    },
    handleLocationError(browserHasGeolocation, infoWindow, pos) {
      infoWindow.setPosition(pos);
      infoWindow.setContent(
        browserHasGeolocation
          ? "Error: The Geolocation service failed."
          : "Error: Your browser doesn't support geolocation."
      );
      infoWindow.open(this.map);
    },
    displayRoute(directionsService, directionsDisplay) {
      directionsService.route(
        {
          origin: "Jeju+International+Airport",
          destination: "Hyupjae+Beach",
          travelMode: "TRANSIT",
          provideRouteAlternatives: true
        },
        function(response, status) {
          if (status === "OK") {
            console.log(response);
            directionsDisplay.setDirections(response);
          } else {
            window.alert("Directions request failed due to " + status);
          }
        }
      );
    },
    addMarker() {
      var index = 1;
      for (var i in this.mapEvents) {
        if (this.mapEvents[i]["type"] == "location") {
          var marker = new google.maps.Marker({
            position: this.mapEvents[i]["latlng"],
            map: this.map,
            label: String(index++)
          });
        }
      }
    },
    geolocation() {
      // Try HTML5 geolocation.
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          function(position) {
            var pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };
            map.setCenter(pos);
          },
          function() {
            handleLocationError(true, this.infoWindow, this.map.getCenter());
          }
        );
      } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, this.infoWindow, this.map.getCenter());
      }
    }
  },
  mounted() {
    this.initMap();
    // this.addMarker();
    // this.geolocation();
    // this.$nextTick(() => {
    //   this.geolocation();
    // });
    // directionsDisplay.setMap(this.map);
    this.directionsDisplay.setPanel(document.getElementById("directionsPanel"));
    this.displayRoute(this.directionsService, this.directionsDisplay);
    // this.displayRoute(directionsService, directionsDisplay);
    // this.drawPolyline(map);
    this.$emit("sendMap", this.map);
  }
};
</script>
<style scoped>
#map {
  height: 500px;
  width: 100%;
}
</style>
