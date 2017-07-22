### 简介  
模版方法模式**定义了算法的*框架*，而将算法中的某些步骤延迟到子类中实现。从而使得子类在不改变算法框架的情况下，重定义某些步骤**。  

---

### 角色  

* 抽象类  
其中定义了*实现算法框架*的**模版方法**（应该是final的）  
* 实现类   
***按需***重写抽象类中的某些方法，以完成完整的算法  

---

### UML类图  

![tempaltemethod.png](http://timd.cn/content/images/pictures/templatemethod.png)
 

