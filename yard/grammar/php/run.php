<?php

$arr = array(
    "foo" => "bar",
    "bar" => "foo",
);

$cmd = "ls ../..";

//system($cmd);

exec($cmd, $res, $ret);
echo "\$ret" . "=" . $ret . "\n";
print_r($res);

passthru($cmd, $ret);
printf("by passthru! ret=%d\n", $ret);

?>
