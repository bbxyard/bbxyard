<?php
/**
 * Created by PhpStorm.
 * User: bbxyard
 * Date: 19-3-7
 * Time: 下午2:17
 */

class StrTest extends \PHPUnit\Framework\TestCase
{
    public function testStrReplace() {
        $s1 = ":a:b:c:";
        $s2 = str_replace(':', '/', $s1);
        $this->assertEquals('/a/b/c/', $s2);
    }
}