<?php
/**
 * Created by PhpStorm.
 * User: bbxyard
 * Date: 19-3-17
 * Time: 下午9:42
 */

class ArrTest extends \PHPUnit\Framework\TestCase
{
    public function testCopy() {
        $arr1 = ["hallo", "welt"];
        $arr2 = $arr1;
        $this->assertEquals(count($arr2), count($arr1));
        $this->assertEquals($arr2, $arr1);
        $arr2[1] = "world";
        $this->assertNotEquals($arr2, $arr1); // 改变数组，仅对本数组有效.
    }
}