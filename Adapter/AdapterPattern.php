<?php
/**
 * Created by PhpStorm.
 * User: lanjialiang
 * Date: 2017/8/23
 * Time: 下午5:59
 */
interface Target{
    public function targetMethod();
}

class Adapter implements Target{
    /**
     * @var Adaptee $adaptee
     */
    protected $adaptee = null;

    public function __construct(){
        $this->adaptee = new Adaptee();
    }

    public function targetMethod(){
        return 'target'.$this->adaptee->specMethod();
    }
}
class Adaptee{
    public function specMethod(){
        return 'Spec';
    }
}

class Test{
    public function __invoke(){
        $adapter = new Adapter();
        assert($adapter->targetMethod()==='targetSpec','adapter fail');
        print_r("adapter success");
    }
}

$test = new Test();
$test();