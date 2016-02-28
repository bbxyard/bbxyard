<?php

/**
 * AJAX-POST 测试文件
 */

// 引入公共库
include "../../../../../lib/php/form.php";


function main()
{
    $memo = getRequest("memo");
    $uri  = getRequest("uri");
    $outfmt = getRequest("outfmt");
    switch ($outfmt)
    {
    case "xml":
        $res = printf("<root><memo>%s</memo><uri>%s</uri></root>", $memo, $uri);
        echo $res;
        break;
    case "json":
        $res = printf("{\"%s\":\"%s\", \"%s\":\"%s\"}", "memo", $memo, "uri", $uri);
        echo $res;
        break;
    case "html":
    default:
        echo "<p>memo is: #" . $memo . "#</p><br />";
        echo "<p>uri is: #" . $uri . "#</p><br />";
        break;
    }
    return 0;
}


main();

?>
