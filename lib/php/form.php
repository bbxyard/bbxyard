<?php

/**
 *
 * request 类型数组
 *
 */
function getRequestArray()
{
    $req = null;
    if ($_SERVER["REQUEST_METHOD"] == "POST")
    {
        $req = $_POST;
    }
    else if ($_SERVER["REQUEST_METHOD"] == "GET")
    {
        $req = $_GET;
    }
    return $req;
}

function getRequest($name)
{
    $req = getRequestArray();
    if (null == $req)
        return "";
    $val = empty($req[$name])? "": $req[$name];
    $val = trim($val);
    return $val;
}

?>
