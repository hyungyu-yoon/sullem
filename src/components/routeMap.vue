<template>
  <v-layout justify-space-between>
    <div id="map" style="width: 600px"></div>
    <div>
      <div>
        <v-btn @click="activeBtn = 'TRANSIT'">
          <v-icon :color="activeBtn === 'TRANSIT' ? '#1DE9B6' : ''">fa-bus</v-icon>
        </v-btn>
        <v-btn @click="activeBtn = 'DRIVING'">
          <v-icon :color="activeBtn === 'DRIVING' ? '#1DE9B6' : ''">fa-car</v-icon>
        </v-btn>
        <v-btn @click="activeBtn = 'WALKING'">
          <v-icon :color="activeBtn === 'WALKING' ? '#1DE9B6' : ''">fa-walking</v-icon>
        </v-btn>
      </div>
      <div
        id="directionsPanel"
        style="float:left;width:100%;height: 500px; display: inline; overflow: auto"
      ></div>
    </div>
  </v-layout>
</template>

<script>
export default {
  name: "RouteMap",
  props: {
    mapEvent: null
  },
  data() {
    return {
      map: null,
      infoWindow: null,
      directionsService: null,
      directionsDisplay: null,
      activeBtn: "TRANSIT"
    };
  },
  methods: {
    initMap() {
      this.directionsService = new google.maps.DirectionsService();
      this.directionsDisplay = new google.maps.DirectionsRenderer();
      this.map = new google.maps.Map(document.getElementById("map"), {
        center: this.mapEvent.origin,
        zoom: 10,
        gestureHandling: "cooperative"
      });
      this.$nextTick(() => {
        this.directionsDisplay.setMap(this.map);
      });
      this.infoWindow = new google.maps.InfoWindow();
    },
    displayRoute(directionsService, directionsDisplay) {
      directionsService.route(
        {
          origin: this.mapEvent.origin,
          destination: this.mapEvent.destination,
          travelMode: this.activeBtn,
          provideRouteAlternatives: true
        },
        function(response, status) {
          if (status === "OK") {
            // console.log(response);
            directionsDisplay.setDirections(response);
          } else {
            window.alert("Directions request failed due to " + status);
          }
        }
      );
    }
  },
  mounted() {
    this.initMap();
    this.directionsDisplay.setPanel(document.getElementById("directionsPanel"));
    this.displayRoute(this.directionsService, this.directionsDisplay);
    // this.$emit("sendMap", this.map);
  },
  watch: {
    activeBtn: function() {
      this.displayRoute(this.directionsService, this.directionsDisplay);
    }
  }
};
</script>
<style scoped>
#map {
  height: 500px;
  width: 100%;
}
</style>
