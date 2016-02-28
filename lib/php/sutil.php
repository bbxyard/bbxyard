<? php

/**
 * string 相关操作
 */

// 是否是有效的URL
function isValidUrl($url)
{
    $b = preg_match("/\b(?:(?:https?|ftp):\/\/|www\.)[-a-z0-9+&@#\/%?=~_|!:,.;]*[-a-z0-9+&@#\/%=~_|]/i", $url);
    return $b;
}

// 是否是有效的Email
function isValidEmail($email)
{
    $b = preg_match("/([\w\-]+\@[\w\-]+\.[\w\-]+)/", $email);
    return $b;
}

// 是否仅包含字母
function isLetterSpaceNumberOnly($s)
{
    $b = preg_match("/^[a-zA-Z0-9 ]*$/", $s);
    return $b;
}

?>
