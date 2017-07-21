### 简介

为了降低系统的*复杂度*，通常会将系统拆分成若干个*子系统*。此时就需要给所有子系统提供一个一致性的*界面*---facade。客户端只需要访问facade，facade负责将客户端的请求代理给适当的子系统。  

---

### 角色

* facade 
    * **持有子系统对象的引用**
    * 需要知道将哪些请求转发给哪些子系统对象
* subsystem classes
    * **没有facade的任何信息** 
    * 负责处理来自facade的请求 

---

### UML类图

![facade.jpg](http://timd.cn/content/images/2017/07/facade-2.png)

