<?php
/**
 * Created by PhpStorm.
 * User: jialiang3
 * Date: 2018/2/5
 * Time: 下午8:36
 */


interface Part
{
    public function cost();
}

abstract class ConcretePart implements Part
{
    protected $part;
    protected $cost;

    public function __construct(Part $part)
    {
        $this->part = $part;
    }

    public function cost()
    {
        return $this->part->cost() + $this->cost;
        // TODO: Implement cost() method.
    }
}

class Screen extends ConcretePart
{
    protected $cost = 100;
}

class Mouse extends ConcretePart
{
    protected $cost = 10;
}

class  Keyboard extends ConcretePart
{
    protected $cost = 1;
}

class Computer implements Part
{
    public function cost()
    {
        return 1000;
        // TODO: Implement cost() method.
    }
}

$computer = new Keyboard(new Screen(new Computer()));
echo $computer->cost();
