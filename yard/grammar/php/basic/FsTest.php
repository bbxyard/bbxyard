<?php
/**
 * Created by PhpStorm.
 * User: bbxyard
 * Date: 19-3-7
 * Time: 下午2:17
 */



class FsTest extends \PHPUnit\Framework\TestCase {
  public function testInfo() {
    var_dump(self::list_info('/etc/passwd'));
  }

  // utils
  /**
   * 返回指定文件和目录的信息
   * @param string $file
   * @return ArrayObject
   */
  public static function list_info($file) {
    $dir = array();
    $dir['filename']   = basename($file);//返回路径中的文件名部分。
    $dir['pathname']   = realpath($file);//返回绝对路径名。
    $dir['owner']      = fileowner($file);//文件的 user ID （所有者）。
    $dir['perms']      = fileperms($file);//返回文件的 inode 编号。
    $dir['inode']      = fileinode($file);//返回文件的 inode 编号。
    $dir['group']      = filegroup($file);//返回文件的组 ID。
    $dir['path']       = dirname($file);//返回路径中的目录名称部分。
    $dir['atime']      = fileatime($file);//返回文件的上次访问时间。
    $dir['ctime']      = filectime($file);//返回文件的上次改变时间。
    $dir['perms']      = fileperms($file);//返回文件的权限。
    // $dir['size']       = self::byte_format(filesize($file),2);//返回文件大小。
    $dir['type']       = filetype($file);//返回文件类型。
    $dir['ext']        = is_file($file) ? pathinfo($file,PATHINFO_EXTENSION) : '';//返回文件后缀名
    $dir['mtime']      = filemtime($file);//返回文件的上次修改时间。
    $dir['isDir']      = is_dir($file);//判断指定的文件名是否是一个目录。
    $dir['isFile']     = is_file($file);//判断指定文件是否为常规的文件。
    $dir['isLink']     = is_link($file);//判断指定的文件是否是连接。
    $dir['isReadable'] = is_readable($file);//判断文件是否可读。
    $dir['isWritable'] = is_writable($file);//判断文件是否可写。
    $dir['isUpload']   = is_uploaded_file($file);//判断文件是否是通过 HTTP POST 上传的。
    return $dir;
  }
}