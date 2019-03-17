<?php
/**
 * Created by PhpStorm.
 * User: bbxyard
 * Date: 19-3-17
 * Time: 下午10:32
 */

namespace type;


use PHPUnit\Framework\TestCase;

class ConvTest extends TestCase {
  public function testToNum() {
    $this->assertEquals(3, floor(3.14));
    $this->assertEquals(3, round(3.14));
    $this->assertEquals(3, intval(3.14));

    $this->assertEquals(3, floor(3.86));
    $this->assertEquals(4, round(3.86));
    $this->assertEquals(3, intval(3.86));
  }
}