### 简介  
client（客户端，<strong>客户端是命令的请求者</strong>）将命令封装成command（命令对象），并给命令对象设置receiver（接收者，<strong>命令对象并不真正的执行命令，而是通过调用接收者的方法来完成命令的执行，因此接收者才是真正执行命令的对象</strong>），然后将命令对象交给invoker（调用者，<strong>调用者可以持有多个命令对象，相当于命令对象的入口</strong>），<strong><em>以后 客户端 通过调用 调用者 来执行命令，而不与 命令对象 直接交互</em></strong>（因此，调用路径为：client =&gt;  invoker =&gt; command =&gt; receiver）<br />

可以看出，命令模式的主要目的是：将命令的请求者（client）和命令的执行者（receiver）进行解耦。解耦之后，可以<em>对请求进行排队</em>、<em>记录请求日志</em>、<em>撤销请求</em>等。<br />

比如，人操作空调的过程是：人在遥控器上输入开机、制冷、关机等命令；遥控器通过红外线把命令发送给空调，然后空调执行命令。很显然，人是client，每个功能都是一个command，遥控器是invoker，空调是receiver。需要额外注意的是，遥控器是命令的唯一入口，人只会与遥控器进行交互，而不会与空调直接交互，这就是命令模式中invoker的作用。

---

### 角色

* Client  
* Command
* ConcreteCommand  
* receiver  
* invoker  

---

### UML类图  

![command.png](http://images.timd.cn/design-pattern/command.png)  
