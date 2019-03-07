<?php
/**
 * Created by PhpStorm.
 * User: bbxyard
 * Date: 19-3-7
 * Time: 下午2:21
 */

class DateTest extends \PHPUnit\Framework\TestCase
{
    public function testFmtPrint() {
        $d = date('Y-m-d H:i:s', time());
        $len = strlen($d);
        echo $d . ' ## ' . $len;
        $this->assertGreaterThan(12, strlen($d));
    }
}