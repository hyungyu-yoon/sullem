<style>
.animate_contentopen {
  animation: animateopen 1.5s ease;
}

@keyframes animateopen {
  0% {
    transform: scale(0.2, 0.002);
  }
  35% {
    transform: scale(0.2, 0.002);
    opacity: 1;
  }
  /* 30% {
    transform: scale(0.2, 0.002);
    opacity: 1;
  } */
  70% {
    transform: scale(1, 0.002);
    opacity: 1;
  }
  100% {
    transform: scale(1, 1);
  }
}

</style>

<template>

<div class="animate-contentopen">
<div>
    <v-img src="../assets/banner.jpg"
           aspect-ratio="3.0"  gradient="rgba(255,255,255,.25), rgba(255,255,255,.5)"
            v-resize="resize"  v-bind:style="{height:newHeight}"
           >
      <v-layout align-center justify-center fill-height>
      <v-flex xs6 sm4 >
          <v-text-field
            solo
            label="장소 입력"
            clearable
            color="teal accent-4"
            class="font-weight-bold"
          ></v-text-field>
      </v-flex>

      </v-layout>
    </v-img>
  </div>

  <v-container class="animate-contentopen">
    <v-layout mt-5 mb-5>
    <v-sheet
      width="100%"
      elevation="5"
      color="white"
    >
    <v-flex pa-5>
          <div data-skyscanner-widget="FlightSearchWidget" data-locale="ko-KR"></div>
    </v-flex>
    </v-sheet>
    </v-layout>
    <v-layout wrap>
      <v-flex xs12>
        <span class="font-weight-bold headline teal--text text--accent-4">베스트<br/> 여행후기</span>
        <v-divider class="teal accent-4"></v-divider>
        <v-divider class="teal accent-4"></v-divider>
      </v-flex>
      <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
      <v-flex xs12>
        <span class="font-weight-bold headline teal--text text--accent-4">베스트<br/> 여행일정</span>
        <v-divider class="teal accent-4"></v-divider>
        <v-divider class="teal accent-4"></v-divider>
      </v-flex>
      <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
      <v-flex xs12>
        <span class="font-weight-bold headline teal--text text--accent-4">새로운<br/> 여행후기</span>
        <v-divider class="teal accent-4"></v-divider>
        <v-divider class="teal accent-4"></v-divider>
      </v-flex>
      <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
      <v-flex xs12>
        <span class="font-weight-bold headline teal--text text--accent-4">새로운<br/> 여행일정</span>
        <v-divider class="teal accent-4"></v-divider>
        <v-divider class="teal accent-4"></v-divider>
      </v-flex>
      <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

    </v-layout>
  </v-container>
  </div>
</template>

<script>

export default {
  data () {
    return {
      newHeight: null
    }
  },
  components: {

  },
  mounted () {
    let recaptchaScript = document.createElement('script')
    recaptchaScript.setAttribute('src', 'https://widgets.skyscanner.net/widget-server/js/loader.js')
    document.head.appendChild(recaptchaScript)
    this.$store.state.val = true
  },
  methods: {
    resize () {
      this.newHeight = window.innerWidth < 600 ? window.innerHeight - 56 + 'px' : null
    }
  }
}
</script>
<style>
.v-text-field__details{
  display:none!important;
}
</style>
