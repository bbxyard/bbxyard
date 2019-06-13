const path = require('path')
const fontelloUpdate = require('fontello-update')
const argv = global.process.argv;
const isEdit = argv[2] === 'edit'
const isUpdate = argv[2] === 'update'

// fontello-update库中config配置相对于npm rum的位置

// edit fontello
if(isEdit) {
  fontelloUpdate({
    config: 'css/lib/fontello/config.json',
    open: true,
  })
}

// update fontello
if(isUpdate) {
  fontelloUpdate({
    config: 'css/lib/fontello/config.json',
    fonts: 'css/lib/fontello/font',
    css: 'css/lib/fontello/css',
  })
}
