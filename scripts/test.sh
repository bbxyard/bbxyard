#!/bin/bash


PROJ_ROOT_HOME=$(pushd `dirname $0` >/dev/null; pwd; popd >/dev/null);
# MOCHA_OPTS="--compilers js:babel-core/register --recursive -t 50000"

export PATH=$PROJ_ROOT_HOME/../node_modules/.bin:$PATH

# npm run test-all

lerna run test
