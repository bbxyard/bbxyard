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

    public function testList()
    {
        $info = array('coffee', 'Brown', 'caffeine');

        // 列出所有变量值
        list($drink, $color, $power) = $info;
        echo "$drink is $color and $power makes it special.\n";

        // 列出他们的其中一个
        list($drink, , $power) = $info;
        echo "$drink has $power.\n";

        // 或者让我们跳到仅第三个
        list( , , $power) = $info;
        echo "I need $power!\n";

        // list() 不能对字符串起作用
        list($bar) = "abcde";
        var_dump($bar); // NULL
    }
}
