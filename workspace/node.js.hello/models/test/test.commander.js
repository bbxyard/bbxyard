#!/usr/bin/env node

/**
 * Module dependencies.
 */

var app = require("commander");

/**
 * pizza demo
 */
function demo_pizza() {
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

demo_pizza();
