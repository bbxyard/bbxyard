<?php

function demoDate()
{
    echo "今天是: " . date("Y/m/d") . "<br>";
    echo "今天是: " . date("Y.m.d") . "<br>";
    echo "今天是: " . date("Y-m-d") . "<br>";
    echo "时间是: " . date("h:i:sa"). "<br>";
    echo "也就是: " . date("Y-m-d h:i:sa") . "<br>" ;
}

demoDate();

?>
