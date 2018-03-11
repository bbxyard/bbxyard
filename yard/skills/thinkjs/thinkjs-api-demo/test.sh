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

testOne "${mod}comment/count?typeId=0&valueId=1006002"
testOne "${mod}comment/list?typeId=0&valueId=1006002&showType=1"

testOne "${mod}goods/index"
testOne "${mod}goods/sku?id=1181000"
testOne "${mod}goods/category?id=1008001"

# SELECT COUNT(`id`) AS think_count FROM `xshop_goods` WHERE ( `is_delete` = 0 ) AND ( `is_on_sale` = 1 ) LIMIT 1
testOne "${mod}goods/count"
testOne "${mod}goods/related?id=1006007"
testOne "${mod}goods/related?id=1181000"
testOne "${mod}goods/new"
testOne "${mod}goods/hot"

testOne "${mod}topic/list"
testOne "${mod}topic/related"
testOne "${mod}topic/detail?id=300"

testOne "${mod}search/index"
testOne "${mod}search/helper?keyword=520"
testOne "${mod}search/clearHistory"

testOne "${mod}region/info?regionId=20"
testOne "${mod}region/list?parentId=20"

testOne "${mod}pay/prepay?orderId=2"
testOne "${mod}pay/notify"

testOne "${mod}index"

testOne "${mod}brand/list?page=5&size=7"
testOne "${mod}brand/detail?id=1024000"

testOne "${mod}admin/category/info?id=1005008"
