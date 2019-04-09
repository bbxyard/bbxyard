<?php

$lnSep = '<br />';

echo '__FILE__ => ' . __FILE__ . $lnSep;
echo '__LINE__ => ' . __LINE__ . $lnSep;
echo '__DIR__  => ' . __DIR__ . $lnSep;

// $fp = fopen('/etc/passwd', 'r');
// $content = fgetcsv($fp, 0, ':');
// fclose($fp);
$content = file_get_contents('/etc/passwd');

var_dump($content);
