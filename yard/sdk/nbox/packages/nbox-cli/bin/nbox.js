#!/usr/bin/env node

const chalk = require('chalk');
const program = require('commander');
const pkg = require('../package.json');
const fs = require('fs');
const path = require('path');
const nbox = require('./com/api-proxy');
const babel = require('babel-core/register');

/**
 * @function main
 * @description 入口函数
 * @param {Array} argv
 */
function main(AppDir, AppName, argv) {
  program
    .version(pkg.version, '-v, --version')
    .usage(chalk.green('<command> [options]'));

  // 枚举所有子命令.
  let env = process.env.NODE_ENV;
  let foo = process.env.foo;

  const subDir = path.join(AppDir, 'sub');
  const handlers = nbox.kit.getFileList(subDir)
    .map(item => require(item.path))
    .map(Handler => new Handler());

  handlers.forEach((handler) => {
    handler.render(program);
    // console.log(handler);
  });

  program
    .command('*')
    .action( function(...args){
      console.log(program.help());
    });

  // console.log(chalk.red('env: (env, foo)=(%s, %s)'), env, foo);
  program.parse(argv);
}

const AppDir = path.dirname(process.argv[1]);
const AppName = path.basename(process.argv[1]);
main(AppDir, AppName, process.argv);
