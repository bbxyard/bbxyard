<?php
$res[] = setcookie('name', 'foo.bar.add', 3600, 'foo/bar');
$res[] = setcookie('year', 2019, 600, 'bar');
$res[] = setcookie('author', 'Brian', 86400, '/');
var_dump($res);
