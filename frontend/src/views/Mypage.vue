<template>
  <div class="animate-contentopen">
    <v-img
      src="../assets/mypage.jpg"
      aspect-ratio="3.0"
      gradient="rgba(255,255,255,.25), rgba(255,255,255,.5)"
      v-bind:style="{height:newHeight}"
    >
      <v-layout align-center justify-center fill-height>
        <v-flex xs4 md2>
            <h1 style="margin: auto;">MyPage</h1>

        </v-flex>
        &nbsp;
        <v-flex xs6 md4 lg3>

        </v-flex>
      </v-layout>
    </v-img>

    <v-container class="animate-contentopen">
      <v-layout mt-5 mb-5>

      </v-layout>
      <v-layout wrap>

      </v-layout>
    </v-container>
  </div>
</template>

<script>
import router from '@/router'

export default {
  data () {
    return {
      newHeight: null,
      category: '여행일정',
      items: ['여행일정', '여행후기'],
      text: ''
    }
  },
  components: {},
  created () {
    console.log(this.$session.get('user'))
    if (this.$session.get('user') === undefined) {
      router.push({ path: 'home' })
      alert('로그인 해야 이용 가능한 서비스입니다.')
    }
  },
  mounted () {
  },
  methods: {

  }
}
</script>
<style>

</style>
