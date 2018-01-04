<template>
  <div id="app">
    <h1 v-if="showWelcome">{{ msg }}</h1>
    <input v-model="msg" @keyup.enter.space.k="submit" placeholder="edit me">
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
    <button type="button" @click="say('Hi')">Say Hi</button>
    <button type="button" @click="say('Hallo', $event)">Say Hallo</button>
    <div id="range" class="demo">
      <span v-for="n in 10">{{ n }} </span>
    </div>
    <div v-for="(key, val) in me"> {{key}}-{{val}}</div>
    <hr>
    <h2>Form Study</h2>
    <div id="checkbox-demo">
      <input type="checkbox" id="checkbox" v-model="checked" />
      <label for="checkbox">{{checked}}</label>
    </div>
    <div id="checkbox-mult-demo">
      <input type="checkbox" id="name" :value="me.name" v-model="checkedme">
      <label for="name">{{me.name}}</label>
      <input type="checkbox" id="age" :value="me.age" v-model="checkedme">
      <label for="age">{{me.age}}</label>
      <input type="checkbox" id="gender" :value="me.gender" v-model="checkedme">
      <label for="gender">{{me.gender}}</label>
      <br />
      <span>Your info is: {{checkedme}}</span>
    </div>
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
        ],
        me: { name: "boxu", age: 32, gender: "male" },
        checked: true,
        checkedme: []
      };
    },
    methods: {
      btnSwitchShow: function(event) {
        this.showWelcome = !this.showWelcome;
        alert("Hello " + this.name + "!");
        alert("event.target.tagName: " + event.target.tagName);
      },
      say: function(msg, event) {
        alert(msg);
        if (event) {
          event.preventDefault();
          console.log("Nice you invoke the event: ", event);
        }
      },
      submit: function(event) {
        alert("done");
        console.log("submit: " + event);
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
