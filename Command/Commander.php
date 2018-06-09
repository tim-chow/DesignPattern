<?php
/**
 * Created by PhpStorm.
 * User: jialiang3
 * Date: 2018/1/31
 * Time: 下午4:03
 */

abstract  class Command{
    public function powerOn(){

    }

    public function powerOff(){

    }

    public function getCommand(){

    }
}

class ConcreteCommand extends Command {
    private $command;
    public function powerOn(){
        $this->command = 'powerOn';
    }

    public function powerOff(){
        $this->command = 'powerOff';
    }

    public function getCommand(){
        return $this->command;
    }
}

class Receive {
    private  $command;
    public function __construct(Command $command)
    {
        $this->command = $command;
    }
    public function doCommand(){
        print("execute: ".$this->command->getCommand());
    }
}

class Invoker{
    protected $receive;
    protected $command;
    public function __construct()
    {
        $this->command = new ConcreteCommand();
        $this->receive = new Receive($this->command);
    }

    public  function powerOn(){
        $this->command->powerOn();
        $this->receive->doCommand();
    }

    public function powerOff(){
        $this->command->powerOff();
        $this->receive->doCommand();
    }
}

class Client{
    public function __invoke()
    {
        // TODO: Implement __invoke() method.
        $invoker = new Invoker();
        $invoker->powerOn();
        $invoker->powerOff();
    }
}

$client = new Client();
$client();

