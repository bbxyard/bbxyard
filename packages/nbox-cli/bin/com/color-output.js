/**
 * @file color-output.js
 * @description 彩色输出
 */
import chalk from 'chalk';
import { vsprintf } from 'sprintf-js';


function formatStr(format, argv) {
  const s = vsprintf(format, argv);
  return s;
}

function printCrit(format, ...args) {
  console.log(chalk.bgBlue(formatStr(format, args)));
}

function printWarn(format, ...args) {
  console.warn(chalk.red(formatStr(format, args)));
}

function printError(format, ...args) {
  const s = chalk.bgRed(chalk.bold(formatStr(format, args)));
  console.error(s);
}

function printInfo(format, ...args) {
  console.log(formatStr(format, args));
}

function printDebug(format, ...args) {
  console.log(chalk.cyan(formatStr(format, args)));
}

const ColorOut = {
  Red : chalk.red,
  Green: chalk.green,
  Blue: chalk.blue,
  Yellow: chalk.yellow,
  
  BgRed: chalk.BgRed,
  BgGreen: chalk.bgGreen,
  BgBlue: chalk.bgBlue,
  BgYellow: chalk.bgYellow,

  printCrit,
  printWarn,
  printError,
  printInfo,
  printDebug,

  R : chalk.red,
  G: chalk.green,
  B: chalk.blue,
  Y: chalk.yellow,
  
  BR: chalk.bgRed,
  BG: chalk.bgGreen,
  BB: chalk.bgBlue,
  BY: chalk.bgYellow,

  logCrit: printCrit,
  logWarn: printWarn,
  logError: printError,
  logInfo: printInfo,
  logDebug: printDebug
};

// export default ColorOut;
module.exports = ColorOut;
