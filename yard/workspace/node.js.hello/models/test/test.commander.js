#!/usr/bin/env node
// demo how to use the commander to
// parse the cmdline options and args


/**
 * demo pizza
 */
function DemoPizza() {
    var app = require("commander");
    app.version("1.0.0")
        .option("-p, --peppers", "Add peppers")
        .option("-P, --pineapple", "Add pineapple")
        .option("-b, --bbq-sauce", "Add bbq sauce")
        .option("-c, --cheese [type]", "Add the specified type of cheese [marble]", "marble")
        .parse(process.argv);
        
    console.log("you ordered a pizza with:");
    if (app.peppers)    console.log("   - peppers");
    if (app.pineapple)  console.log("   - pineapple");
    if (app.bbqSauce)   console.log("   - bbq");
    console.log("   - %s cheese", app.cheese);   
}


/**
 * demo data type
 */
function DemoDataType() {
    function range(val) {
      return val.split('..').map(Number);
    }    
    function list(val) {
      return val.split(',');
    }    
    function collect(val, memo) {
      memo.push(val);
      return memo;
    }    
    function increaseVerbosity(v, total) {
      return total + 1;
    }
    
    var program = require("commander");
    program
      .version('0.0.1')
      .usage('[options] <file ...>')
      .option('-i, --integer <n>', 'An integer argument', parseInt)
      .option('-f, --float <n>', 'A float argument', parseFloat)
      .option('-r, --range <a>..<b>', 'A range', range)
      .option('-l, --list <items>', 'A list', list)
      .option('-o, --optional [value]', 'An optional value')
      .option('-c, --collect [value]', 'A repeatable value', collect, [])
      .option('-v, --verbose', 'A value that can be increased', increaseVerbosity, 0)
      .parse(process.argv);
    
    console.log(' int: %j', program.integer);
    console.log(' float: %j', program.float);
    console.log(' optional: %j', program.optional);
    program.range = program.range || [];
    console.log(' range: %j..%j', program.range[0], program.range[1]);
    console.log(' list: %j', program.list);
    console.log(' collect: %j', program.collect);
    console.log(' verbosity: %j', program.verbose);
    console.log(' args: %j', program.args);       
}


/*
 * svn style command line tools
 */
function DemoSvnStyle() {
    var program = require('commander');
    program
        .version('0.0.1')
        .option('-C, --chdir <path>', 'change the working directory')
        .option('-c, --config <path>', 'set config path. defaults to ./deploy.conf')
        .option('-T, --no-tests', 'ignore test hook');
    program
        .command('setup [env]')
        .description('run setup commands for all envs')
        .option("-s, --setup_mode [mode]", "Which setup mode to use")
        .action(function(env, options){
            var mode = options.setup_mode || "normal";
            env = env || 'all';
            console.log('setup for %s env(s) with %s mode', env, mode);
        });
    program
        .command('exec <cmd>')
        .alias('ex')
        .description('execute the given remote cmd')
        .option("-e, --exec_mode <mode>", "Which exec mode to use")
        .action(function(cmd, options){
            console.log('exec "%s" using %s mode', cmd, options.exec_mode);
        }).on('--help', function() {
            console.log('  Examples:');
            console.log();
            console.log('    $ deploy exec sequential');
            console.log('    $ deploy exec async');
            console.log();
        });
    program
        .command('*')
        .action(function(env){
            console.log('deploying "%s"', env);
        });

    program.parse(process.argv);
}


/**
 * git style command line tools
 */
function DemoGitStyle() {
    var program = require('..');
    program
        .version('0.0.1')
        .command('install [name]', 'install one or more packages')
        .command('search [query]', 'search with optional query')
        .command('list', 'list packages installed', {isDefault: true})
        .parse(process.argv);
}


//DemoPizza();
//DemoDataType();
DemoSvnStyle();
//DemoGitStyle();
