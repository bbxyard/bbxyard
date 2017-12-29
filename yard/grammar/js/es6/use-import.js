// ES6
import React from 'react';
import {Route, DefaultRoute, NotFoundRoute} from 'react-router';

// ES5
var React = require('react');
var Router = require('react-router');

var Route = Router.Route;
var DefaultRoute = Router.DefaultRoute;
var NotFoundRoute = Router.NotFoundRoute;


/**
 *

使用ES6中的解构赋值（destructuring assignment），
我们能导入模块的子集，这对于像react-router和underscore这样不止输出一个函数的模块尤其有用。

需要注意的是ES6 import的优先级很高，所有的依赖模块都会在模块代码执行之前加载，
也就是说，你无法像在CommonJS一样有条件的加载模块。
之前我尝试在一个if-else条件里import模块，结果失败了。

 *
**/
