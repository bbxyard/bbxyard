<template>
  <div id="app">
    <h1 v-if="showWelcome">{{ msg }}</h1>
    <input v-model="msg">
    <ol>
      <li v-for="todo in todos">{{todo.text}}@{{todo.dateEnd}}</li>
    </ol>
    <ul>
      <li v-for="todo in todos">{{todo.text}}@{{todo.dateEnd}}</li>
    </ul>
    <table>
      <tr><th>content</th><th>date</th></tr>
    </table>
    <button type="button" v-on:click="btnSwitchShow">switch-show</button>
  </div>
</template>

<script>
  export default {
    name: 'app',
    data () {
      return {
        showWelcome: true,
        msg: 'Let us study from w3c school',
        todos: [
          { text: "Study Vue", dateEnd: "2018.01.10" },
          { text: "Study English", dateEnd: "2018.02.14" },
          { text: "Study Management", dateEnd: "2018.03.14" }
        ]
      };
    },
    methods: {
      btnSwitchShow: function() {
        this.showWelcome = !this.showWelcome;
      }
    }
  }
</script>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: yellow;
  margin-top: 30px;
  margin-left: 80px;
  text-shadow: 5px 5px 5px #FF0000;
}

h1 {
  font-weight: bold;
  color: red;
  text-shadow: 5px 5px 5px #FF0000;
}
</styple>
