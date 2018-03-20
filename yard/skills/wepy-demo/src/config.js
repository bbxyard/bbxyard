const xshopPages = [
  '../pages-xshop/index',
  '../pages-xshop/catalog'
];

const index = {
  list: [
    { id: 'fun', name: '好玩', open: false, pages: ['../pages/fun/wheel'].concat(xshopPages) },
    { id: 'grammar', name: '基础语法', open: false, pages: ['../pages/gr/for', '../pages/gr/if'] },
    { id: 'form', name: '表单', open: false, pages: ['../pages/index', '../pages/list', 'input', 'list', 'uploader'] },
    { id: 'widget', name: '基础组件', open: false, pages: ['article', 'badge', 'button', 'flex', 'grid', 'icons', 'loadmore', 'panel', 'preview', 'progress', 'slider'] },
    { id: 'feedback', name: '操作反馈', open: false, pages: ['actionsheet', 'dialog', 'msg', 'picker'] },
    { id: 'nav', name: '导航相关', open: false, pages: ['navbar', 'footer'] },
    { id: 'search', name: '搜索相关', open: false, pages: ['searchbar'] }
  ]
};

const user = require('./config.user.js'); // 自定义配置文件
const api = user.api;

module.exports = {
  index: index,
  api: api
};
