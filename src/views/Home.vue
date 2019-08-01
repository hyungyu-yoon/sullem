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
    <div class="animate_contentopen">
        <h1>hello I'm main</h1>
        <img src='배경1.jpg'>
    </div>
</template>

<script>
export default {
  mounted: function () {
    // setTimeout(function() {
    //     console.log('open!');
    //     this.openNav()
    // }, 1500);
    this.$store.state.val = true
  },
  methods: {
    // openNav: function() {
    // }
  }
}
</script>
