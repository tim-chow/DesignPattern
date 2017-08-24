<?php
/**
 * Created by PhpStorm.
 * User: lanjialiang
 * Date: 2017/8/24
 * Time: 下午4:49
 */
interface DrawerInterface{
    public function draw();
}

class CircleDrawer implements DrawerInterface{
    public function draw(){
        return 'circle';
    }
}

class RectangleDrawer implements DrawerInterface{
    public function draw(){
        return 'rectangle';
    }
}
/***
 * bridge
 */
abstract class Shape implements DrawerInterface{
    /**
     * @var DrawInterface $drawer
     */
    protected $drawer = null;
    abstract public  function draw();
}

class CircleShape extends Shape{
    protected  $radius = 0;
    public function __construct($radius){
        $this->radius = $radius;
        $this->drawer = new CircleDrawer();
    }
    public function draw(){
        return $this->drawer->draw();
    }
}

class RectangleShape extends Shape{
    protected  $width=0;
    protected  $height =0;
    public function __construct($width,$height){
        $this->width = $width;
        $this->height = $height;
        $this->drawer = new RectangleDrawer();
    }

    public function draw(){
        return $this->drawer->draw();
    }
}

class Test{
    public function __invoke(){
        $circle = new CircleShape(1);
        assert($circle->draw() === 'circle','circleShape draw fail');
        $rectangle = new RectangleShape(10,10);
        assert($rectangle->draw() === 'rectangle','rectangleShape draw fail');
        print_r('draw success');
    }
}
$test = new Test();
$test();
