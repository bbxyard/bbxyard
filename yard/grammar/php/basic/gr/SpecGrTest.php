<?php
/**
 * Created by PhpStorm.
 * User: bbxyard
 * Date: 19-3-17
 * Time: 下午9:42
 */

class SpecGrTest extends \PHPUnit\Framework\TestCase
{
  public function testCopy() {
    $arr1 = ["hallo", "welt"];
    $arr2 = $arr1;
    $this->assertEquals(count($arr2), count($arr1));
    $this->assertEquals($arr2, $arr1);
    $arr2[1] = "world";
    $this->assertNotEquals($arr2, $arr1); // 改变数组，仅对本数组有效.
  }

  public function triOp() {
    $s1 = 'hallo welt';
    $s2 = $s1 ?: 'hello world';
    var_dump(compact('s1', 's2'));
  }

  public function mathOp()
  {
    var_dump(bcadd(3, 4, 4));
    var_dump(bcmul(3.1415926, 4, 2));
    var_dump(bcadd(time(), bcmul(6, 86400, 0), 0));
    var_dump(floatval("3.14"));
  }
}
