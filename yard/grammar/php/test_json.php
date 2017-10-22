<?php

function testArray2json()
{
    $arr = array("a"=>1, "b"=>2, "c"=>3, "d"=>4);
    $arr["e"] = 5;
    echo json_encode($arr)."\n";
}

function testJson2Array()
{
    $jstr = '{"x":"alpha", "y":"beta", "z":3}';
    $arr  = json_decode($jstr, true);
    var_dump($arr);
}

testArray2json();
testJson2Array();

?>
