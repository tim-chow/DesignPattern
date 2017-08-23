<?php
/**
 * Created by PhpStorm.
 * User: lanjialiang
 * Date: 2017/8/23
 * Time: 下午4:02
 */


interface CarInterface{
    public function desc();
}
abstract class AudiCar implements CarInterface{}
abstract class BMWCar implements CarInterface{}

class AudiA4 extends AudiCar{
    const AUDI_A4 = "this is AudiA4";

    public function desc(){
        return self::AUDI_A4;
    }
}


class BMWx1 extends BMwCar{
    const BMW_X1 = "this is BMWx1";

    public function desc(){
        return self::BMW_X1;
    }
}
/**
 * car factory
 */
abstract class CarFactory{
    public  function createAudi(){
        return $this->createAudiCar();
    }

    public function createAudiCar(){
    }

    public function createBMW(){
        return $this->createBMWCar();
    }

    public function createBMWCar(){
    }
}
class AudiA4Factory extends CarFactory{
    public function createAudiCar(){
        return new AudiA4();
    }
}
class BMWx1Factory extends  CarFactory{
    public function createBMWCar(){
        return new BMWx1();
    }
}

class Test{
    public function __invoke(){
        $audiA4 = new AudiA4Factory();
        $bmwX1 = new BMWx1Factory();
        assert(AudiA4::AUDI_A4 === $audiA4->createAudi()->desc(), "audiA4 fail");
        assert(BMWx1::BMW_X1 === $bmwX1->createBMW()->desc(),"bmwX1 fail");
    }
}
$test = new Test();
$test();
