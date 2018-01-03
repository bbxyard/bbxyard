<template>
  <div id="diy">
    <h1>hello slot demo</h1>
    <h2> {{msg}} </h2>
    <children>
      <span slot="1stx" @click="tobeknow">12345</span>
      <span slot="2nd">67890</span>
    </children>
    <ul>
      <li><a href="https://vuejs.org" target="_blank">Core Docs</a></li>
      <li><a href="https://forum.vuejs.org" target="_blank">Forum</a></li>
      <li><a href="https://chat.vuejs.org" target="_blank">Community Chat</a></li>
      <li><a href="https://twitter.com/vuejs" target="_blank">Twitter</a></li>
    </ul>
  </div>
</template>

<script>
  export default {
    name: 'diy',
    data () {
      return {
        msg: 'Welcome to Your Vue.js SLOT DEMO'
      }
    }
  }
</script>

<style lang="scss">
#diy {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: green;
  margin-top: 30px;
}

h1, h2 {
  font-weight: bold;
}
</styple>
