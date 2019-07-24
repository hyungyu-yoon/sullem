<template>
  <div class="hello" style="display: flex;">
    <div id="Map" style="display: inline;"></div>
    <div id="directionsPanel" style="float:left;width:30%;height 100%; display: inline;"></div>
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
    },
    // drawPolyline (map) {
    //   var bounds = new google.maps.LatLngBounds();
    //   var jsonData = {
    //     "overview_polyline": {
    //       "points": "a_pkE}i`cWj[}GNRhBr@VTtBbFZX^Hd@GZM|@Md@W`@q@Jo@Cw@Mk@uDcK_AwBcAiBaA}AC]FULQ~Ou@dVgAb]^tVd@Z^RJOpN[x^IzHEnAL`BpBvExBnEz@|A?PeUpXYLaA?gC?[BQDMPIj@SzMYhOGrGMpE_@fVQhHQnCiApJuAlNgA|JOnBErCLtJFjHPpHhB~RtDfVdBxNTtD?zC_@xEWlBo@vCsAzHM`AC`AAhAFrAVlCz@lH\\lCr@rEn@zEj@fFPlFTxBPtCPdGBtCHfB\\jCn@xBpAjCjRz\\n@fANd@hOte@zBhHlBxBtLpTdIbOn@bBTzA~ArY^lFAbBLnATvA`A~JlB~Qx@jHj@jDr@nC~D`NzApG\\xBxArNZpCf@fDr@nDfApElClLrFhTvB|IzAzHb@zAbIzWbDdJpBrFn@pB\\|@v@zCf@xBVhBpAvQtA~VN|BRnBd@vBdAtDRn@~CxMZjD\\~D?l@Gz@e@vDMnAv@pEHfA\\hA\\f@bAp@`BrAx@x@z@dAJVTRd@x@`ApEJ`B?`BSnLg@fXc@~Ek@hDKjAUtHgBxWQxAmA`Hc@zBW~BM`CIjCF|ALbAvAbHxAjHd@pAz@rA|@bAnAbA`B`AzGhDfAZ`IdBxArArBxApBlAxB~@zBl@zANfb@~AjATz@^pBdB|C~Dt@~@dAfAnFdFf@r@\\p@X`AhBvIp@`EZzBP|CDxJVpJT`D\\rCfAlGrA~Fv@rCdBbFfAtCn@zCz@fGh@`D|@lE`ApD~BjH~C|Hh@fAt@hAfHzHt@r@`BbA~RbKjIrErAnApDzFv@~@`UdUlBjBfA|AbEzFzDlG~BxDf@hAj@`At@v@l@f@`Aj@r@Pl@DzGDz@EdBc@jB]lDa@fE}@hCe@~@?n@HjCn@d@zBt@xC^hAVh@hE|GjAfBb@l@`@VlOlGfA^|@l@pOlLfB`B^`@~@vA`BzFl@bBxCdFl@p@v@n@fAl@rGtDj@f@dBlBb@r@xCbKz@jBzN~Sn@h@dAp@hA~@~@lAz@tAnBxD\\j@f@f@jAz@r@bJ"
    //     }
    //   };
    //   var path = google.maps.geometry.encoding.decodePath(jsonData.overview_polyline.points)
    //   console.log(path);
    //   for (var i = 0; i < path.length; i++) {
    //     bounds.extend(path[i]);
    //   }
    //   var polyline = new google.maps.Polyline({
    //     path: path,
    //     strokeColor: '#FF0000',
    //     strokeOpacity: 0.8,
    //     strokeWeight: 2,
    //     fillColor: '#FF0000',
    //     fillOpacity: 0.35,
    //     map: map
    //       // strokeColor: "#0000FF",
    //       // strokeOpacity: 1.0,
    //       // strokeWeight: 2
    //   });
    //   polyline.setMap(map);
    //   map.fitBounds(bounds);
    // },
    // https://developers.google.com/maps/documentation/javascript/directions
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
    this.displayRoute(directionsService, directionsDisplay);
    // this.drawPolyline(map);
  }
}
</script>
<style scoped>
    #Map {
      height:400px;
      width: 400px;
   }
</style>
