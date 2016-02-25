<?php

//重新定向浏览器指向
function downloadByRedirect($url)
{
    Header("Location: " . $url);
    exit;
}

/**
  * gen new html A
  */
function downloadByGenA($url)
{
    $FMT='<a href="%s">点击下载文件</a>';
    $result = sprintf($FMT, $url);
    echo $result;
}

/**
 * reopen
 */
function download($file, $new_fname)
{
    //检查文件是否存在
    if (!isset($file) || trim($file) == '')
    {
        return '500';
    }
    else if ( ! file_exists($file))
    {
        //echo "文件找不到";
        return '404';
    }
    else
    {
        // 生成下载文件名
        $file_name = basename($file);   //下载文件名
        if (isset($new_fname) && trim($new_fname) != "")
        {
            $file_name = trim($new_fname);
        }
        $url_file_name = urlencode($file_name);

        // 打开文件
        $fh = fopen($file, "r");
        //输入文件标签
        Header ("Content-type: application/octet-stream");
        Header ("Accept-Ranges: bytes");
        Header ("Accept-Length: " . filesize($file));
        Header ("Content-Disposition: attachment; filename=" . $url_file_name);
        //输出文件内容
        //读取文件内容并直接输出到浏览器
        //echo fread($fh, filesize($file));
        while (!feof($fh))
        {
            //设置文件最长执行时间
            set_time_limit(0);
            print(fread($fh, 8 * 1024));
            fflush($fh);
            ob_flush();
        }
        fclose($fh);
        return 0;
    }
}

//download("http://mirrors.ustc.edu.cn/gnu/glibc/glibc-2.23.tar.bz2");
//download("/etc/passwd", "");
download("/home/bbxyard/var/download/wps-office_10.1.0.5460~a20p1_amd64.deb", "wps_copyed.deb");

?>
