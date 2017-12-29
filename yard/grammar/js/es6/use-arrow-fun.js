// ES6
[1, 2, 3].map(n => n * 2); // [2, 4, 6]

// ES5
[1, 2, 3].map(function(n) { return n * 2; }); // [2, 4, 6]


/**
 *
 注意：如果参数只有一个，圆括号是可选的，
 到底是否强制使用取决于你，不过有些人认为去掉括号是坏的实践，
 有些人则无所谓。
 *
**/

// ES6
$.ajax({ type: 'POST', url: '/api/characters', data: { name: name, gender: gender } })
  .done((data) => {
    this.setState({ helpBlock: data.message });
  })
  .fail((jqXhr) => {
    this.setState({ helpBlock: jqXhr.responseJSON.message });
  })
  .always(() => {
    this.setState({ name: '', gender: '' });
  });

// ES5
$.ajax({ type: 'POST', url: '/api/characters', data: { name: name, gender: gender } })
  .done(function(data) {
    this.setState({ helpBlock: data.message });
  }.bind(this))
  .fail(function(jqXhr) {
    this.setState({ helpBlock: jqXhr.responseJSON.message });
  }.bind(this))
  .always(function() {
    this.setState({ name: '', gender: '' });
  }.bind(this));
