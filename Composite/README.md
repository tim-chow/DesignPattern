### 简介  

  组合模式是**使用树形结构组织对象，以达成“部分-整体”的层次关系，使得客户端可以以一致性的方式对待单个对象和组合对象**。  

---

### 角色 

* component  
为leaf 和 composite定义公共的接口 和 默认的实现  
* leaf  
实现component。叶子节点对象，没有子节点  
* composite  
实现component。组合节点对象，需要保存子节点，并且实现增加和删除子节点的方法  

---

### UML类图  

![composite.png](http://timd.cn/content/images/pictures/composite.png)   


