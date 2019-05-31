<template>
  <div id="app">
    <!-- <img src="./assets/logo.png"> -->
    <h1>{{ msg }}</h1>
    <h2>Essential Links</h2>
    <ul>
      <li v-for="item in links"><a :href="item.url" target="_blank">{{item.name}}</a></li>
    </ul>
    <h2>Ecosystem</h2>
    <ul>
      <li v-for="item in ecosystem"><a :href="item.url" target="_blank">{{item.name}}</a></li>
    </ul>
    <textarea type="text" v-model="msg" />
    <button v-on:click="reverseMessage">Reverse Message</button>
  </div>
</template>

<script>
export default {
  name: 'app',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      links: [
        { url: "https://vuejs.org", name: "Core Docs" },
        { url: "https://forum.vuejs.org", name: "Forum" },
        { url: "https://chat.vuejs.org", name: "Community Chat" },
        { url: "https://twitter.com/vuejs", name: "Twitter" },
      ],
      ecosystem: [
        { url: "http://router.vuejs.org", name: "vue-router" },
        { url: "http://vuex.vuejs.org", name: "vuex" },
        { url: "http://vue-loader.vuejs.org", name: "vue-loader" },
        { url: "https://github.com/vuejs/awesome-vue", name: "awesome-vue" }
      ]
    }
  },
  methods: {
    reverseMessage: function() {
      this.msg = this.msg.split('').reverse().join('');
    }
  }
}
</script>

<style lang="scss">
#app, input, textarea {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  color: #2c3e50;
  margin-top: 60px;
  margin-bottom: 20px;
}

h2 {
  color: orange;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}

</style>
