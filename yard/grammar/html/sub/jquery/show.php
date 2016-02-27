<?php
    function getRequest($name)
    {
        if (null == $_POST)
            return "";
        echo "GGO";
        $val = ($_POST[$name] != null)? $_POST[$name]: "";
        return $val;
    }
    echo "your name is: #" . getRequest("name") . "#<br />";
    echo "your id is: #" . getRequest("id") . "#<br />";
?>
