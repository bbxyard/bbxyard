<template>
  <div id="app">
    <h1>{{ msg }}</h1>
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
  </div>
</template>

<script>
  export default {
    name: 'app',
    data () {
      return {
        msg: 'Let us study from w3c school',
        todos: [
          { text: "Study Vue", dateEnd: "2018.01.10" },
          { text: "Study English", dateEnd: "2018.02.14" },
          { text: "Study Management", dateEnd: "2018.03.14" }
        ]
      };
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

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}
</styple>
