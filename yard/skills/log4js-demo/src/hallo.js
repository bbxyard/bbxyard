const log4js = require('log4js');
const logger = log4js.getLogger();
logger.level = 'debug';
logger.debug('Das ist a debug txt');
logger.info('this is a info level txt');
logger.error('this is a error level txt');
