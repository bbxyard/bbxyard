#!/bin/bash

for x in $(thinkjs list | grep "-" | awk '{print $2}'); do thinkjs new thinkjs-$x-demo $x; done;

