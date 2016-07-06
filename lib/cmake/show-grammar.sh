#!/bin/bash
# show grammar

cd $(dirname "$0")
# cmake -P grammar.cmake

tmp_build_dir=".build"
[ -d "$tmp_build_dir" ] && rm -rvf "$tmp_build_dir"
mkdir -p "$tmp_build_dir"

[ -d "$tmp_build_dir" ] && {
  pushd "$tmp_build_dir" &>/dev/null
  cmake ../
  popd &>/dev/null
  rm -rvf "$tmp_build_dir" >/dev/null
}

