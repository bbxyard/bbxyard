#!/bin/bash

PROJ_ROOT_HOME=$(pushd `dirname $0` >/dev/null; pwd; popd >/dev/null);

pushd "$PROJ_ROOT_HOME" >/dev/null
find .. -name "*.un~" -exec rm {} \;
popd >/dev/null
