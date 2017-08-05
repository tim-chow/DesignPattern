### 简介  

备忘录模式用于**在不破坏封闭的情况下，获取对象（*发起者*）的内部状态，并且保存在对象之外（*备忘录*），以便以后将对象恢复到原先备份的状态**。  

---

### 角色  

* 发起者（originator）  
创建备忘录，用以保存当前时刻originator对象的内部状态；  
可以从备忘录对象恢复内部状态；
决定将哪些状态保存到备忘录对象中  
* 备忘录（memento）  
用于保存originator对象的内部的状态。memento的接口分为：  

  * 为originator提供**宽接口**，可以访问和操作memento对象的内容
  * 为caretaker提供**窄接口**，只能传递memento对象的引用
* 管理者（caretaker）  
负责管理memento对象。但是不能访问和操作memento对象的内容  

---

### UML类图  

![memento](http://timd.cn/content/images/pictures/memento.png)  