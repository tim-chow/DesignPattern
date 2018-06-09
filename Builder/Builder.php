<?php
class Product{
	private $part1;
	private $part2;

	public function getPart1(){
		return $this->part1;
	}

	public function setPart1($part){
		$this->part1 = $part;
	}


	public function getPart2(){
		return $this->part2;
	}	


	public function setPart2($part){
		$this->part2 = $part;
	}

	public function info(){
		return sprintf("part1:%s\npart2:%s",$this->part1, $this->part2);
	}
}

interface  Builder {
    public function buildPart1();

    public function buildPart2();

    public function build();
}

class ConcreteBuilder1 implements Builder {
    /**
     * @var Product $product
     */
    private $product;
    public function __construct()
    {
        $this->product = new Product();
    }

    public function buildPart1()
    {
        // TODO: Implement buildPart1() method.
       $this->product->setPart1('build by ConcreteBuilder1');
    }

    public function buildPart2()
    {
        // TODO: Implement buildPart2() method.
        $this->product->setPart2('build by ConcreteBuilder1');
    }

    public function build(){
        $this->buildPart1();
        $this->buildPart2();
        return $this->product;
    }
}

class Director{
    /**
     * @var Builder $builder
     */
    private  $builder;
    public function __construct(Builder $builder)
    {
        $this->builder = $builder;
    }

    public function getProductor(){
        return $this->builder->build();
    }
}

class test{
    public function __invoke()
    {
        // TODO: Implement __invoke() method.
        $director = new Director(new ConcreteBuilder1());
        $product = $director->getProductor();
        print_r($product->info());
    }
}
$test= new test();
$test();


