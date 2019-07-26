<template>

  <section class="VideoBg">
    <video autoplay playsinline loop muted ref="video">
      <source src="src/assets/background_video.mp4" type="video/mp4">
    </video>
    <div class="VideoBg__content">
      <slot></slot>
    </div>
  </section>
</template>


<script>
  export default {
    name: 'index',
    data () {
      return {
        videoRatio: null
      }
    }
  }
</script>


<style>
  .VideoBg {
    position: relative;
    background-size: cover;
    background-position: center;
    overflow: hidden;
  }
  .VideoBg video {
    position: absolute;
    top: 50%;
    left: 50%;
    visibility: hidden;
    transform: translate(-50%, -50%);
  }
  .VideoBg__content {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }
</style>
