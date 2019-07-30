<style>
*{
  box-sizing: border-box;
}

body{
  margin:0;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-size:1rem;
  font-weight:normal;
  line-height:1.5;
  overflow-x:hidden;
}

.v-header{
  height:100vh;
  display:flex;
  align-items:center;
  color: black;
}

.container{
  max-width:960px;
  padding-left:1rem;
  padding-right:1rem;
  margin:auto;
  text-align:center;
}

.fullscreen-video-wrap{
  position:absolute;
  top:0;
  left:0;
  width:100vw;
  height:100vh;
  z-index: 2;
  overflow:hidden;
}

.fullscreen-video-wrap video{
  min-height:100%;
  min-width:100%;
}
/* @media(max-width:1280px){
    .fullscreen-video-wrap{
    position:absolute;
    top:0;
    left:0;
    height:100vh;
    width: 100vw;
    z-index: 2;
    overflow:hidden;
  }

  .fullscreen-video-wrap video{
  min-height:20%;
  min-width:20%;
  }
} */

.header-overlay{
  height:100vh;
  position: absolute;
  top:0;
  left:0;
  width:100vw;
  z-index:1;
  /* background:#225470; */
  opacity:0.85;
}

.header-content{
  z-index:2;
}

.header-content h1{
  font-size:50px;
  margin-bottom:0;
}

.header-content p{
  font-size:1.5rem;
  display:block;
  padding-bottom:2rem;
}

/* @media(max-width:960px){
  .container{
    padding-right:3rem;
    padding-left:3rem;
  }
} */
.v-app-bar{
  /* display: none; */
  /* visibility: hidden; */
}
</style>

<template>
  <header class="v-header container">
      <div @click="vdclick" class="fullscreen-video-wrap">
          <video playsinline autoplay muted loop>
           <source :src='video' type='video/mp4'>
          </video>
      </div>
      <div class="header-overlay"></div>
      <div class="header-content text-md-center">
      </div>
    </header>
</template>

<script>
import router from '@/router'

export default {
  // props: [
  //   'myVal'
  // ],
  mounted: function () {
    this.$store.state.val = false
    console.log(this.video)
  },
  data () {
    return {
      video: `vid${Math.ceil(Math.random() * 4)}_Trim.mp4` // 랜덤비디오 개수
    }
  },
  methods: {
    vdclick: function () {
      router.push({ path: 'about' })
    }
    // hidenav: function () {
    //   console.log(this.myVal)
    //   this.myVal = false;
    //   console.log(this.myVal)
    //   eventBus.$emit('fixVal', this.myVal);
    // }
  }
}
</script>
