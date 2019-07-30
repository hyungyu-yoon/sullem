<template>
  <table>
    <thead>
      <tr>
        <th>ALL</th>
        <th>DAY 1</th>
        <th>DAY 2</th>
        <th>DAY 3</th>
      </tr>
    </thead>
    <tbody>
      <template v-for="(value, index) in 24">
        <tr :key="value">
          <td id='time' rowspan="2" >{{ index }}:00</td>
          <!-- dd-hh-mm -->
          <td :id="'0-' + index + '-0'">1</td>
          <td :id="'1-' + index + '-0'">2</td>
          <td :id="'2-' + index + '-0'">3</td>
        </tr>
        <tr :key="value">
          <td :id="'0-' + index + '-1'">1</td>
          <td :id="'1-' + index + '-1'">2</td>
          <td :id="'2-' + index + '-1'">3</td>
        </tr>
      </template>
    </tbody>
  </table>
</template>

<script>
export default {
  data () {
    return {
      1: {},
      2: {},
      3: {}
    }
  }
}
</script>

<style>
  table {
    width: 100%;
    border: 1px solid;
    border-collapse: collapse;
    table-layout: fixed;
  }
  th, td {
    border: 1px solid;
  }
  tbody {
    display: block;
    overflow: auto;
    height: 400px;
    width: 100%;
  }
  #time {
    vertical-align: top;
    width: 90px;
    text-align: right;
  }
</style>
