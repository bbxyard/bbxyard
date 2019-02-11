# Min Cli

> 令小程序组件的开发和使用变得愉悦

## 介绍

Min资源汇总：[awesome-min](https://github.com/cds803/awesome-min)

Min 是一款微信小程序组件化解决方案，通过预编译的手段让开发者可以选择自己喜欢的开发风格去开发小程序和组件库。而Min与其他组件化框架最大的区别在于是开发真正意义上的小程序组件，通过增强特性能让开发小程序项目变得更加优雅，高效。

同时Min也是一款成长中的小程序组件化方案，大量吸收借鉴了一些优化前端工具以及框架的设计理念和思想。如果Min有不足地方，或者你有更好的想法，欢迎提交ISSUE或者PR。

## 特性

- 提供丰富的[UI组件库](https://github.com/meili/minui)，组件重用方便二次开发
- [单文件](https://meili.github.io/min/docs/features/single-file-mode.html)开发模式，类Vue开发风格
- 支持[自定义组件](https://meili.github.io/min/docs/min-cli/wxc-project/index.html)开发
- 支持[加载海量NPM包](https://meili.github.io/min/docs/features/npm.html)
- 支持[ES2015+特性、Promise](https://meili.github.io/min/docs/features/babel.html)，使用async/await能够有效避免回调地狱
- 支持多种[编译器](https://meili.github.io/min/docs/features/style.html)，Less/PostCss、Babel
- 完善[小程序](https://meili.github.io/min/docs/min-cli/app-project/index.html)和[组件库](https://meili.github.io/min/docs/min-cli/wxc-project/index.html)开发流程，快速建立私有的小程序组件库
- 支持[全局模板](https://meili.github.io/min/docs/advance/global-layout.html)、[可配置化](https://meili.github.io/min/docs/advance/global-setting.html)，减少维护成本和差异化

## 最佳实践

[MinUI](https://github.com/meili/minui)，是基于 Min 产出的一套 UI 组件库，同时也是蘑菇街小程序在应用的 UI 组件库。通过下面的小程序二维码，可以在手机中体验 MinUI（微信基础库版本 1.6.3 以上支持）：

![](http://s3.mogucdn.com/mlcdn/c45406/171103_5l89d0ih87eh9e715065310ekgdea_220x220.png)

## 环境安装

``` bash
$ npm install -g @mindev/min-cli
```

## 组件开发

- **初始化项目**

``` bash
$ min init
```

- **新建组件**

``` bash
$ min new *name
```

- **开发实时编译**

``` bash
$ min dev
```

- **发布组件**

``` bash
$ min publish
```

## 组件应用

- **安装组件**

在小程序项目中安装一个组件，这里用 [MinUI](https://github.com/meili/minui) 的 loading 组件举例：

``` bash
$ min install @minui/wxc-loading
```

- **更新组件**

``` bash
$ min update @minui/wxc-loading
```

## Min 使用交流群

请添加群助手 wUf18018252882 好友或者扫码加好友，并与群助手对话发送验证码 `10088` 按照指引进群。

![微信群](https://s10.mogucdn.com/mlcdn/c45406/180108_888g0d26e23h9j8fc9e3bd7j3e85h_430x430.jpg_220x330.jpg)

## Links

[Documentation](https://meili.github.io/min)

[Changelog](https://meili.github.io/min/docs/changelog/index.html)

[Contributing](https://github.com/meili/min-cli/blob/master/CONTRIBUTING.md)

[License MIT](https://github.com/meili/min-cli/blob/master/LICENSE)

## 开源协议

本项目基于 [MIT](http://opensource.org/licenses/MIT) License，请自由的享受、参与开源。
