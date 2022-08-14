# Spring的Controller是单例还是多例，怎么保证并发的安全

首先说答案：

controller默认是单例的，不要使用非静态的成员变量，否则会发生数据逻辑混乱。正因为单例所以是线程不安全的

# 1、案例测试

看下面代码，我们简单测试下：

```java
package cn.itsource.pethome.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestControler {

    private int num = 0;

    @GetMapping("/test1")
    public void testScope() {
        System.out.println(++num);
    }

    @GetMapping("/test2")
    public void testScope2() {
        System.out.println(++num);
    }
}
```

用浏览器分别访问：

http://localhost/test/test1   得到结果：1

http://localhost/test/test2   得到结果：2

得到的不同的值，说明这是线程不安全的



接下来我们再来给controller增加一个注解，如下：

```java
package cn.itsource.pethome.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Scope("prototype")
public class TestControler {

    private int num = 0;

    @GetMapping("/test1")
    public void testScope() {
        System.out.println(++num);
    }

    @GetMapping("/test2")
    public void testScope2() {
        System.out.println(++num);
    }
}
```

用浏览器分别访问：

http://localhost/test/test1   得到结果：1

http://localhost/test/test2   得到结果：1

得到的值都是1，说明这是线程安全的



相信大家不难发现 ：单例是不安全的，会导致属性重复使用

那么我们如何解决这个问题呢？

# 2、解决方案

解决方案如下：

1. 不要在controller中定义成员变量
2. 万一必须要定义一个非静态成员变量时候，则通过注解@Scope(“prototype”)，将其设置为多例模式
3. 在Controller中使用ThreadLocal变量

# 3、补充

spring bean作用域有以下5个：

**singleton:** 单例模式，当spring创建applicationContext容器的时候，spring会欲初始化所有的该作用域实例，加上lazy-init就可以避免预处理；

**prototype：** 原型模式，每次通过getBean获取该bean就会新产生一个实例，创建后spring将不再对其管理；

（下面是在web项目下才用到的）

**request：** 搞web的大家都应该明白request的域了吧，就是每次请求都新产生一个实例，和prototype不同就是创建后，接下来的管理，spring依然在监听；

**session:** 每次会话，同上；

**global session:** 全局的web域，类似于servlet中的application。































