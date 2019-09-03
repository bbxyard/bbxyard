const log4js = require('log4js');
log4js.configure({
  appenders: { cheese: {type: 'file', filename: 'cheese.log'} },
  categories: { default: {appenders: ['cheese'], level: 'error'} }
});

const logger = log4js.getLogger('cheese');
logger.trace('Entering cheese testing');
logger.debug('Got cheese');
logger.info('This is INFO');
logger.warn('This is WARN');
logger.error('This is ERROR');
logger.fatal('This is FATAL');
