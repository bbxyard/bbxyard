import Vue from 'vue'
import App from './App.vue'
import Diy from './diy.vue'

var diy = new Vue({
  el: '#diy',
  methods: {
    tobeknow: function() {
      console.log("It is the parent's method");
      alert("haha");
    }
  },
  components: {
    children: {
      template: "<button><slot name='1st'>1st</slot>为了明确作用范围，<slot name='2nd'>2nd</slot>所以使用button标签</button>"
    }
  },
  render: h => h(Diy)
});

var app = new Vue({
  el: '#app',
  render: h => h(App)
});
