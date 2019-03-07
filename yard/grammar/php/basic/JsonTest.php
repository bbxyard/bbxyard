<?php
/**
 * Created by PhpStorm.
 * User: bbxyard
 * Date: 19-3-7
 * Time: 下午2:05
 */

use PHPUnit\Framework\TestCase;

class JsonTest extends TestCase {
    public function testPushAndPop()
    {
        $stack = [];
        $this->assertEquals(0, count($stack));
        array_push($stack, 'foo');
        $this->assertEquals('foo', $stack[count($stack)-1]);
        $this->assertEquals(1, count($stack));
        $this->assertEquals('foo', array_pop($stack));
        $this->assertEquals(0, count($stack));
    }

    public function testArray2json()
    {
        $arr = array("a"=>1, "b"=>2, "c"=>3, "d"=>4);
        $arr["e"] = 5;
        $this->assertEquals(5, count($arr));
        echo json_encode($arr)."\n";
    }

    public function testJson2Array()
    {
        $jstr = '{"x":"alpha", "y":"beta", "z":3}';
        $arr  = json_decode($jstr, true);
        $this->assertEquals(3, count($arr));
        var_dump($arr);
    }
}