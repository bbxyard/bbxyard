#!/bin/bash
# test server

# config
[ -f ~/.bash_profile ] && . ~/.bash_profile
prefix="http://127.0.0.1:8360"

function testOne()
{
    cmd="curl $prefix/$1"
    red $cmd
    $cmd
    echo ""
}

mod=

testOne "${mod}goods/sku?id=1006007"
testOne "${mod}goods/category?id=1008001"

# SELECT COUNT(`id`) AS think_count FROM `xshop_goods` WHERE ( `is_delete` = 0 ) AND ( `is_on_sale` = 1 ) LIMIT 1
testOne "${mod}goods/count"
testOne "${mod}goods/related?id=1006007"
testOne "${mod}goods/related?id=1181000"
testOne "${mod}goods/new"
testOne "${mod}goods/hot"
