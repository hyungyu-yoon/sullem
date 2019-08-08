<template>
  <div>
    <div id="map" style="width: 1240px"></div>
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
    mapEvents: null
  },
  data() {
    return {
      map: null,
      infoWindow: null,
      directionsService: null,
      directionsDisplay: null,
      markers: []
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
          this.markers.push(marker);
        }
      }
    },
    deleteMarker() {
      this.setMapOnAll(null);
      this.markers = [];
    },
    setMapOnAll(map) {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(map);
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
    },
    drawRoute() {
      for (var i in this.mapEvents) {
        if (
          this.mapEvents[i]["type"] == "route" &&
          this.mapEvents[i]["overview_path"]
        ) {
          var path = google.maps.geometry.encoding.decodePath(
            this.mapEvents[i]["overview_path"]
          );

          var polyline = new google.maps.Polyline({
            path: path,
            strokeColor: "#F06292",
            strokeOpacity: 1,
            strokeWeight: 3,
            fillColor: "#F06292",
            fillOpacity: 1,
            map: this.map
          });
          polyline.setMap(this.map);
        }
      }
    }
  },
  mounted() {
    this.initMap();
    // this.geolocation();
    // this.$nextTick(() => {
    //   this.geolocation();
    // });
    // directionsDisplay.setMap(this.map);
    // 경로 그려주기
    // this.directionsDisplay.setPanel(document.getElementById("directionsPanel"));
    // this.displayRoute(this.directionsService, this.directionsDisplay);
    // 여기까지
    // this.drawPolyline(map)
    // var path =
    //   "a_pkE}i`cWbYaIdElBRVtBzEXX^Jn@I~@[bAi@Vg@Hk@IgAqEyLuAsC}AkC?a@N_@p`@kBzDQ`E@f[^jS`@PTVNDNu@lx@Gx@D|@Nt@hBjE@?z@fBxBdE?PwG~HmLpNYLiE?m@HMPIj@a@hXKzDGrGY~KSxOQhHQnCiApJuAlNgA|JOnBErCT`TPpHv@|Ip@`H`CjOr@zEhA|Ih@tFJrDCbAQzBe@jEqBfK_@lCEjCFrAVlCxAzLr@rEn@zEj@fFPlFTxBPtCPdGBtCHfBNnA`@vBZ|@Zt@lMhUbGnKtSdp@lBxBnBlDzOzYdCtEd@bBHp@`@~H|@rO^lFAbBLnATvAX~Bf@~FrAdMrAdMj@jDr@nC~D`NzApG\\xBvAjN\\xCf@fDr@nDzCnMrDdOpFpTzAzHb@xAbI|WtGxQlAnD~AtGVhBf@dHh@pHPtDrAfURnBd@vBxAdF~CxMXpCXdDD`B_@|D[dCv@pEHfA\\hA\\f@bAp@`BrAx@x@z@dAJVTRd@x@`ApEJ`B?`B{@ve@c@~EOx@g@zDUtHa@|FeAzOQxAqB|KW~BM~BIlCF|ALbAtCpNZ|Ad@pAz@rA|@bAnAbA`B`AzGhDfAZ`IdBxArArBxApBlAxB~@zBl@zANzU|@jK`@jATz@^~@v@p@l@p@z@`DbEtHlHf@r@\\p@X`AhBvIp@`EZzBP|C?hDDnENzG\\vF\\rCb@nClA|F`BrGtCfIVp@\\xAlAhIh@`D|@lE`ApD~@zC~@nCjAzCrA`Dh@fAt@hAdEvEvCvC`BbAdG|CxJdFzEjCnBfAp@j@`@b@pDzFv@~@nIrI~M|MjGxIzHfMf@hAj@`At@v@l@f@`Aj@r@Pl@DzGDz@EdBc@jB]lDa@fE}@hCe@~@?n@HjCn@d@zBt@xC^hA`BtC~BpDnBtC`@VpGlCbI~C|@l@pOlLhBbB\\^~@vApB`H\\|@xCdFl@p@v@n@zIbFj@f@dBlBb@r@Tp@bCpI\\z@\\n@jLtPnAhBn@h@dAp@hA~@~@lAz@tAlCdFf@f@jAz@r@bJ";

    // var path = [
    //   { lat: 37.772, lng: -122.214 },
    //   { lat: 21.291, lng: -157.821 },
    //   { lat: -18.142, lng: 178.431 },
    //   { lat: -27.467, lng: 153.027 }
    // ];

    // var jsonData = {
    //   overview_polyline: {
    //     points:
    //       "a_pkE}i`cWbYaIdElBRVtBzEXX^Jn@I~@[bAi@Vg@Hk@IgAqEyLuAsC}AkC?a@N_@p`@kBzDQ`E@f[^jS`@PTVNDNu@lx@Gx@D|@Nt@hBjE@?z@fBxBdE?PwG~HmLpNYLiE?m@HMPIj@a@hXKzDGrGY~KSxOQhHQnCiApJuAlNgA|JOnBErCT`TPpHv@|Ip@`H`CjOr@zEhA|Ih@tFJrDCbAQzBe@jEqBfK_@lCEjCFrAVlCxAzLr@rEn@zEj@fFPlFTxBPtCPdGBtCHfBNnA`@vBZ|@Zt@lMhUbGnKtSdp@lBxBnBlDzOzYdCtEd@bBHp@`@~H|@rO^lFAbBLnATvAX~Bf@~FrAdMrAdMj@jDr@nC~D`NzApG\\xBvAjN\\xCf@fDr@nDzCnMrDdOpFpTzAzHb@xAbI|WtGxQlAnD~AtGVhBf@dHh@pHPtDrAfURnBd@vBxAdF~CxMXpCXdDD`B_@|D[dCv@pEHfA\\hA\\f@bAp@`BrAx@x@z@dAJVTRd@x@`ApEJ`B?`B{@ve@c@~EOx@g@zDUtHa@|FeAzOQxAqB|KW~BM~BIlCF|ALbAtCpNZ|Ad@pAz@rA|@bAnAbA`B`AzGhDfAZ`IdBxArArBxApBlAxB~@zBl@zANzU|@jK`@jATz@^~@v@p@l@p@z@`DbEtHlHf@r@\\p@X`AhBvIp@`EZzBP|C?hDDnENzG\\vF\\rCb@nClA|F`BrGtCfIVp@\\xAlAhIh@`D|@lE`ApD~@zC~@nCjAzCrA`Dh@fAt@hAdEvEvCvC`BbAdG|CxJdFzEjCnBfAp@j@`@b@pDzFv@~@nIrI~M|MjGxIzHfMf@hAj@`At@v@l@f@`Aj@r@Pl@DzGDz@EdBc@jB]lDa@fE}@hCe@~@?n@HjCn@d@zBt@xC^hA`BtC~BpDnBtC`@VpGlCbI~C|@l@pOlLhBbB\\^~@vApB`H\\|@xCdFl@p@v@n@zIbFj@f@dBlBb@r@Tp@bCpI\\z@\\n@jLtPnAhBn@h@dAp@hA~@~@lAz@tAlCdFf@f@jAz@r@bJ"
    //   }
    // };
    // var path = google.maps.geometry.encoding.decodePath(
    //   jsonData.overview_polyline.points
    // );

    // var polyline = new google.maps.Polyline({
    //   path: path,
    //   strokeColor: "#FF0000",
    //   strokeOpacity: 0.8,
    //   strokeWeight: 2,
    //   fillColor: "#FF0000",
    //   fillOpacity: 0.35,
    //   map: this.map
    // });

    // polyline.setMap(this.map);
    this.$emit("sendMap", this.map);
    this.drawRoute();
  },
  watch: {
    mapEvents: function() {
      this.deleteMarker();
      this.addMarker();
      this.drawRoute();
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
