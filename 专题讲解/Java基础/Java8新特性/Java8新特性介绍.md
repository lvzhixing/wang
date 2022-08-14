# JDK8新特性

Java 是第一大编程语言和开发平台。它有助于企业降低成本、缩短开发周期、推动创新以及改善应用服务。如今全球有数百万开发人员运行着超过 51 亿个 Java 虚拟机，Java 仍是企业和开发人员的首选开发平台

课程内容的介绍
1.	了解Java发展史
2.	Lambda表达式
3.	接口的增强
4.	函数式接口
5.	方法引用
6.	Stream API
7.	Optional
8.	新时间日期API
9.	其他新特性

# 一、Java发展历史

## 1. Java的发展历史

Sun公司在1991年成立了一个称为绿色计划( Green Project )的项目，由James Gosling(高斯林)博土领导，绿色计划的目的是开发一种能够在各种消费性电子产品(机顶盒、冰箱、收音机等)上运行的程序架构。这个项目的 产品就是Java语言的前身: Oak(橡树)。Oak当时在消费品市场上并不算成功，但随着1995年互联网潮流的兴起， Oak迅速找到了最适合自己发展的市场定位。

- JDK Beta - 1995
- JDK 1.0 - 1996年1月 (真正第一个稳定的版本JDK 1.0.2，被称作 Java 1 ) JDK 1.1 - 1997年2月
- J2SE 1.2 - 1998年12月
- J2ME（Java 2 Micro Edition，Java 2平台的微型版），应用于移动、无线及有限资源的环境。J2SE（Java 2 Standard Edition，Java 2平台的标准版），应用于桌面环境。
- J2EE（Java 2 Enterprise Edition，Java 2平台的企业版），应用于基于Java的应用服务器。J2SE 1.3 - 2000年5月
- J2SE 1.4 - 2002年2月J2SE 5.0 - 2004年9月
- Java SE 6 - 2006年12月Java SE 7 - 2011年7月
- Java SE 8（LTS） - 2014年3月Java SE 9 - 2017年9月
- Java SE 10（18.3） - 2018年3月
- Java SE 11（18.9 LTS） - 2018年9月
- Java SE 12（19.3） - 2019年3月Java SE 13（19.9) - 2019年9月Java SE 14（20.3) - 2020年3月Java SE 15（20.9) - 2020年9月

我们可以看到Java SE的主要版本大约每两年发布一次，直到Java SE 6到Java SE 7开始花了五年时间， 之后又花了三年时间到达Java SE 8。
## 2. OpenJDK和OracleJDK	

### 2.1	Open JDK来源

- Java 由 Sun 公司发明，Open JDK是Sun在2006年末把Java开源而形成的项目。
- 也就是说Open JDK是Java SE平台版的开源和免费实现，它由 SUN 和 Java 社区提供支持
- 2009年 Oracle 收购了 Sun 公司，自此 Java 的维护方之一的SUN 也变成了 Oracle。

### 2.2	Open JDK 和 Oracle JDK的关系

- 大多数 JDK 都是在 Open JDK 的基础上进一步编写实现的，比如 IBM J9, Oracle JDK 和 Azul Zulu, Azul Zing。
- Oracle JDK完全由 Oracle 公司开发，Oracle JDK是基于Open JDK源代码的商业版本。此外，它包含闭源组件。
- Oracle JDK根据二进制代码许可协议获得许可，在没有商业许可的情况下，在2019年1月之后发布的Oracle Java SE 8的公开更新将无法用于商业或生产用途。但是 Open JDK是完全开源的，可以自由使用。

### 2.3	Open JDK 官网介绍

- Open JDK 官网： http://openjdk.java.net
- JDK Enhancement Proposals(JDK增强建议)。通俗的讲JEP就是JDK的新特性

小结：

- Oracle JDK是基于Open JDK源代码的商业版本。我们要学习Java新技术可以去Open JDK 官网学习。

# 二、Lambda表达式	

## 1. 需求分析	

创建一个新的线程，指定线程要执行的任务

代码分析：

```java
```

分析：

1.	Thread类需要一个Runnable接口作为参数，其中的抽象方法run方法是用来指定线程任务内容的核心
2.	为了指定run方法体，不得不需要Runnable的实现类
3.	为了省去定义一个Runnable    的实现类，不得不使用匿名内部类

4.	必须覆盖重写抽象的run方法，所有的方法名称，方法参数，方法返回值不得不都重写一遍，而且 不能出错，
5.	而实际上，我们只在乎方法体中的代码

2.	Lambda表达式初体验	
Lambda表达式是一个匿名函数，可以理解为一段可以传递的代码

Lambda表达式的优点：简化了匿名内部类的使用，语法更加简单。
匿名内部类语法冗余，体验了Lambda表达式后，发现Lambda表达式是简化匿名内部类的一种方式。

3.	Lambda的语法规则	
Lambda省去了面向对象的条条框框，Lambda的标准格式由3个部分组成：


格式说明：
(参数类型 参数名称):参数列表
{代码体;} :方法体
-> : 箭头，分割参数列表和方法体

3.1	Lambda练习1
练习无参无返回值的Lambda 定义一个接口

然后创建主方法使用

 


输出：



3.2	Lambda练习2
完成一个有参且有返回值得Lambda表达式案例创建一个Person对象

然后我们在List集合中保存多个Person对象，然后对这些对象做根据age排序操作

 


我们发现在sort方法的第二个参数是一个Comparator接口的匿名内部类，且执行的方法有参数和返回值，那么我们可以改写为Lambda表达式


输出结果



4.	@FunctionalInterface注解

5.	Lambda表达式的原理	
匿名内部类的本质是在编译时生成一个Class  文件。XXXXX$1.class



还可以通过反编译工具来查看生成的代码 XJad 工具来查看



那么Lambda表达式的原理是什么呢？我们也通过反编译工具来查看


写的有Lambda表达式的class文件，我们通过XJad查看报错。这时我们可以通过JDK自带的一个工具：
javap 对字节码进行反汇编操作。


反汇编的结果：

 



在这个反编译的源码中我们看到了一个静态方法   lambda$main$0()，这个方法里面做了什么事情呢？我们通过debug的方式来查看下：

上面的效果可以理解为如下：


为了更加直观的理解这个内容，我们可以在运行的时候添加   -
Djdk.internal.lambda.dumpProxyClasses, 加上这个参数会将内部class码输出到一个文件中


命令执行

 

反编译后的内容：
可以看到这个匿名的内部类实现了UserService接口，并重写了show()方法。在show方法中调用了
Demo03Lambda.lambda$main$0(),也就是调用了Lambda中的内容。

 


小结：
匿名内部类在编译的时候会产生一个class文件。
Lambda表达式在程序运行的时候会形成一个类。
1.	在类中新增了一个方法，这个方法的方法体就是Lambda表达式中的代码
2.	还会形成一个匿名内部类，实现接口，重写抽象方法
3.	在接口中重写方法会调用新生成的方法

6.	Lambda表达式的省略写法	
在lambda表达式的标准写法基础上，可以使用省略写法的规则为：
1.	小括号内的参数类型可以省略
2.	如果小括号内有且仅有一个参数，则小括号可以省略
3.	如果大括号内有且仅有一个语句，可以同时省略大括号，return   关键字及语句分号。



 

7.	Lambda表达式的使用前提
Lambda表达式的语法是非常简洁的，但是Lambda表达式不是随便使用的，使用时有几个条件要特别注意
1.	方法的参数或局部变量类型必须为接口才能使用Lambda
2.	接口中有且仅有一个抽象方法(@FunctionalInterface)


8.	Lambda和匿名内部类的对比
Lambda和匿名内部类的对比
1.	所需类型不一样
匿名内部类的类型可以是 类，抽象类，接口
Lambda表达式需要的类型必须是接口
2.	抽象方法的数量不一样
匿名内部类所需的接口中的抽象方法的数量是随意的
Lambda表达式所需的接口中只能有一个抽象方法
3.	实现原理不一样
匿名内部类是在编译后形成一个class
Lambda表达式是在程序运行的时候动态生成class



# 三、JDK8接口中新增的方法

1.	JDK8中接口的新增
在JDK8中针对接口有做增强，在JDK8之前


JDK8之后对接口做了增加，接口中可以有默认方法和静态方法


2.	默认方法	
2.1	为什么要增加默认方法
在JDK8以前接口中只能有抽象方法和静态常量，会存在以下的问题：
如果接口中新增抽象方法，那么实现类都必须要抽象这个抽象方法，非常不利于接口的扩展的


2.2	接口默认方法的格式
接口中默认方法的语法格式是


package com.bobo.jdk.inter;


public class Demo01Interface {


public static void main(String[] args) { A a = new B();
a.test3();
A c = new C(); c.test3();

}
}

interface A{
void test1();
// 接口中新增抽象方法，所有实现类都需要重写这个方法，不利于接口的扩展
void test2();


/**
*	接口中定义的默认方法
*	@return
*/
public default String test3(){ System.out.println("接口中的默认方法执行了..."); return "hello";
}
}

class B implements A{ @Override
public void test1() {


}


@Override
public void test2() {


}


@Override
public String test3() {
System.out.println("B 实现类中重写了默认方法..."); return "ok ...";
}
}

 



2.3	接口中默认方法的使用
接口中的默认方法有两种使用方式
1.	实现类直接调用接口的默认方法
2.	实现类重写接口的默认方法



3.	静态方法	
JDK8中为接口新增了静态方法，作用也是为了接口的扩展
3.1	语法规则



 

/**
*	接口中定义的默认方法
*	@return
*/
public default String test3(){ System.out.println("接口中的默认方法执行了..."); return "hello";
}


/**
*	接口中的静态方法
*	@return
*/
public static String test4(){ System.out.println("接口中的静态方法	");
return "Hello";
}
}

class B implements A{ @Override
public void test1() {


}


@Override
public void test2() {


}


@Override
public String test3() {
System.out.println("B 实现类中重写了默认方法..."); return "ok ...";
}

}


class C implements A{ @Override
public void test1() {


}


@Override
public void test2() {

}
}

3.2	静态方法的使用
接口中的静态方法在实现类中是不能被重写的，调用的话只能通过接口类型来实现:    接口名.静态方法名
();


4.	两者的区别介绍
1.	默认方法通过实例调用，静态方法通过接口名调用
2.	默认方法可以被继承，实现类可以直接调用接口默认方法，也可以重写接口默认方法
3.	静态方法不能被继承，实现类不能重写接口的静态方法，只能使用接口名调用

# 四、函数式接口

1.	函数式接口的由来
我们知道使用Lambda表达式的前提是需要有函数式接口，而Lambda表达式使用时不关心接口名， 抽象方法名。只关心抽象方法的参数列表和返回值类型。因此为了让我们使用Lambda表达式更加的方 法，在JDK中提供了大量常用的函数式接口

 



2.	函数式接口介绍	
在JDK中帮我们提供的有函数式接口，主要是在 java.util.function 包中。
2.1	Supplier
无参有返回值的接口，对于的Lambda表达式需要提供一个返回数据的类型。


使用：

 



2.2	Consumer
有参无返回值得接口,前面介绍的Supplier接口是用来生产数据的，而Consumer接口是用来消费数据的，使用的时候需要指定一个泛型来定义参数类型


使用：将输入的数据统一转换为小写输出



默认方法:andThen
如果一个方法的参数和返回值全部是Consumer类型，那么就可以实现效果，消费一个数据的时候， 首先做一个操作，然后再做一个操作，实现组合，而这个方法就是Consumer接口中的default方法andThen方法


具体的操作

 





2.3	Function
有参有返回值的接口，Function接口是根据一个类型的数据得到另一个类型的数据，前者称为前置条件，后者称为后置条件。有参数有返回值。


使用：传递进入一个字符串返回一个数字

 

默认方法：andThen，也是用来进行组合操作，



默认的compose方法的作用顺序和andThen方法刚好相反而静态方法identity则是，输入什么参数就返回什么参数

2.4	Predicate
有参且返回值为Boolean的接口

 

使用：


在Predicate中的默认方法提供了逻辑关系操作 and or negate isEquals方法

 





# 五、方法引用	

1.	为什么要用方法引用	
1.1	lambda表达式冗余
在使用Lambda表达式的时候，也会出现代码冗余的情况，比如：用Lambda表达式求一个数组的和

 


1.2	解决方案
因为在Lambda表达式中要执行的代码和我们另一个方法中的代码是一样的，这时就没有必要重写一 份逻辑了，这时我们就可以“引用”重复代码



:: 方法引用 也是JDK8中的新的语法

2.	方法引用的格式	
符号表示： ::
符号说明：双冒号为方法引用运算符，而它所在的表达式被称为方法引用
应用场景：如果Lambda表达式所要实现的方案，已经有其他方法存在相同的方案，那么则可以使用方 法引用。
常见的引用方式：
方法引用在JDK8中使用是相当灵活的，有以下几种形式：
1.	instanceName::methodName 对象::方法名
2.	ClassName::staticMethodName 类名::静态方法

3.	ClassName::methodName 类名::普通方法
4.	ClassName::new 类名::new 调用的构造器
5.	TypeName[]::new String[]::new 调用数组的构造器

2.1	对象名::方法名
这是最常见的一种用法。如果一个类中的已经存在了一个成员方法，则可以通过对象名引用成员方法


方法引用的注意事项：
1.	被引用的方法，参数要和接口中的抽象方法的参数一样
2.	当接口抽象方法有返回值时，被引用的方法也必须有返回值


2.2	类名::静态方法名
也是比较常用的方式：




2.3	类名::引用实例方法
Java面向对象中，类名只能调用静态方法，类名引用实例方法是用前提的，实际上是拿第一个参数作 为方法的调用者

 




2.4	类名::构造器
由于构造器的名称和类名完全一致，所以构造器引用使用::new 的格式使用，




2.5	数组::构造器
数组是怎么构造出来的呢？

 


小结：方法引用是对Lambda表达式符合特定情况下的一种缩写方式，它使得我们的Lambda表达式更加的精简，也可以理解为lambda表达式的缩写形式，不过要注意的是方法引用只能引用已经存在的方法。



# 六、Stream API	

1.	集合处理数据的弊端	
当我们在需要对集合中的元素进行操作的时候，除了必需的添加，删除，获取外，最典型的操作就是 集合遍历，

 


上面的代码针对与我们不同的需求总是一次次的循环循环循环.这时我们希望有更加高效的处理方式，这 时我们就可以通过JDK8中提供的Stream API来解决这个问题了。
Stream更加优雅的解决方案：


上面的SteamAPI代码的含义：获取流，过滤张，过滤长度，逐一打印。代码相比于上面的案例更加的简洁直观

2.	Steam流式思想概述	
注意：Stream和IO流(InputStream/OutputStream)没有任何关系，请暂时忘记对传统IO流的固有印 象！
Stream流式思想类似于工厂车间的“生产流水线”，Stream流不是一种数据结构，不保存数据，而是对数据进行加工
处理。Stream可以看作是流水线上的一个工序。在流水线上，通过多个工序让一个原材料加工成一个商品。

 

 



Stream API能让我们快速完成许多复杂的操作，如筛选、切片、映射、查找、去除重复，统计，匹配和归约。

3.	Stream流的获取方式	
3.1	根据Collection获取
首先，java.util.Collection 接口中加入了default方法 stream，也就是说Collection接口下的所有的实现都可以通过steam方法来获取Stream流。


但是Map接口别没有实现Collection接口，那这时怎么办呢？这时我们可以根据Map获取对应的key value的集合。


3.1 通过Stream的of方法
在实际开发中我们不可避免的还是会操作到数组中的数据，由于数组对象不可能添加默认方法，所 有Stream接口中提供了静态方法of



4.	Stream常用方法介绍	
Stream常用方法
Stream流模型的操作很丰富，这里介绍一些常用的API。这些方法可以被分成两种：

方法名	方法作用	返回值类型	方法种类
count	统计个数	long	终结
forEach	逐一处理	void	终结
filter	过滤	Stream	函数拼接
limit	取用前几个	Stream	函数拼接
skip	跳过前几个	Stream	函数拼接
map	映射	Stream	函数拼接
concat	组合	Stream	函数拼接
终结方法：返回值类型不再是   Stream   类型的方法，不再支持链式调用。本小节中，终结方法包括
count 和
forEach 方法。
非终结方法：返回值类型仍然是 Stream 类型的方法，支持链式调用。（除了终结方法外，其余方法均为非终结
方法。）
Stream注意事项(重要)
1.	Stream只能操作一次
2.	Stream方法返回的是新的流
3.	Stream不调用终结方法，中间的操作不会执行

4.1	forEach
forEach用来遍历流中的数据的


该方法接受一个Consumer接口，会将每一个流元素交给函数处理


4.2	count
Stream流中的count方法用来统计其中的元素个数的


该方法返回一个long值，代表元素的个数。



4.3	filter
filter方法的作用是用来过滤数据的。返回符合条件的数据

可以通过filter方法将一个流转换成另一个子集流


该接口接收一个Predicate函数式接口参数作为筛选条件

 

输出：


4.4	limit

limit方法可以对流进行截取处理，支取前n个数据，


参数是一个long类型的数值，如果集合当前长度大于参数就进行截取，否则不操作：


输出：


4.5	skip



如果希望跳过前面几个元素，可以使用skip方法获取一个截取之后的新流：


操作：


输出：



4.6	map
如果我们需要将流中的元素映射到另一个流中，可以使用map方法：

 


该接口需要一个Function函数式接口参数，可以将当前流中的T类型数据转换为另一种R类型的数据




4.7	sorted
如果需要将数据排序，可以使用sorted方法：


在使用的时候可以根据自然规则排序，也可以通过比较强来指定对应的排序规则



4.8	distinct
如果要去掉重复数据，可以使用distinct方法：

 


使用：


Stream流中的distinct方法对于基本数据类型是可以直接出重的，但是对于自定义类型，我们是需要重写hashCode和equals方法来移除重复元素。

4.9	match
如果需要判断数据是否匹配指定的条件，可以使用match相关的方法


使用

 

注意match是一个终结方法
4.10	find
如果我们需要找到某些数据，可以使用find方法来实现




使用：


4.11	max和min






如果我们想要获取最大值和最小值，那么可以使用max和min方法


使用




4.12	reduce方法


如果需要将所有数据归纳得到一个数据，可以使用reduce方法


使用：



4.13	map和reduce的组合
在实际开发中我们经常会将map和reduce一块来使用












,	new Person("李四",	22)
,	new Person("张三",	13)
,	new Person("王五",	15)
,	new Person("张三",	19)

 

输出结果




4.14	mapToInt
如果需要将Stream中的Integer类型转换成int类型，可以使用mapToInt方法来实现




使用



4.15	concat
如果有两个流，希望合并成为一个流，那么可以使用Stream接口的静态方法concat

 

使用:



4.16	综合案例
定义两个集合，然后在集合中存储多个用户名称。然后完成如下的操作：
1.	第一个队伍只保留姓名长度为3的成员
2.	第一个队伍筛选之后只要前3个人
3.	第二个队伍只要姓张的成员
4.	第二个队伍筛选之后不要前两个人
5.	将两个队伍合并为一个队伍
6.	根据姓名创建Person对象
7.	打印整个队伍的Person信息



 


输出结果：



5.	Stream结果收集
5.1	结果收集到集合中



 

输出：



5.2	结果收集到数组中
Stream中提供了toArray方法来将结果放到一个数组中，返回值类型是Object[],如果我们要指定返回的 类型，那么可以使用另一个重载的toArray(IntFunction f)方法



5.3	对流中的数据做聚合计算
当我们使用Stream流处理数据后，可以像数据库的聚合函数一样对某个字段进行操作，比如获得最大 值，最小值，求和，平均值，统计数量。


public void test03(){
// 获取年龄的最大值
Optional<Person> maxAge = Stream.of( new Person("张三", 18)
,	new Person("李四",	22)
,	new Person("张三",	13)
,	new Person("王五",	15)
,	new Person("张三",	19)
).collect(Collectors.maxBy((p1, p2) -> p1.getAge() - p2.getAge())); System.out.println("最大年龄：" + maxAge.get());
// 获取年龄的最小值
Optional<Person> minAge = Stream.of( new Person("张三", 18)
,	new Person("李四",	22)
,	new Person("张三",	13)
,	new Person("王五",	15)
,	new Person("张三",	19)
).collect(Collectors.minBy((p1, p2) -> p1.getAge() - p2.getAge())); System.out.println("最新年龄:" + minAge.get());
// 求所有人的年龄之和
Integer sumAge = Stream.of( new Person("张三", 18)
,	new Person("李四",	22)
,	new Person("张三",	13)
,	new Person("王五",	15)
,	new Person("张三",	19)
)
//.collect(Collectors.summingInt(s -> s.getAge()))
.collect(Collectors.summingInt(Person::getAge))
;
System.out.println("年龄总和：" + sumAge);
// 年龄的平均值
Double avgAge = Stream.of(
new Person("张三", 18)

,	new Person("李四",	22)
,	new Person("张三",	13)
,	new Person("王五",	15)
,	new Person("张三",	19)
).collect(Collectors.averagingInt(Person::getAge)); System.out.println("年龄的平均值：" + avgAge);
// 统计数量
Long count = Stream.of(
new Person("张三", 18)

,	new Person("李四",	22)
,	new Person("张三",	13)
,	new Person("王五",	15)
,	new Person("张三",	19)
).filter(p->p.getAge() > 18)
.collect(Collectors.counting()); System.out.println("满足条件的记录数:" + count);

}

5.4	对流中数据做分组操作
当我们使用Stream流处理数据后，可以根据某个属性将数据分组







,	new Person("李四",	22,	177)
,	new Person("张三",	14,	165)
,	new Person("李四",	15,	166)
,	new Person("张三",	19,	182)





,	new Person("李四",	22,	177)
,	new Person("张三",	14,	165)
,	new Person("李四",	15,	166)
,	new Person("张三",	19,	182)




输出结果：


多级分组: 先根据name分组然后根据年龄分组

 

,	new Person("张三",	14,	165)
,	new Person("李四",	15,	166)
,	new Person("张三",	19,	182)










输出结果：



5.5	对流中的数据做分区操作
Collectors.partitioningBy会根据值是否为true,把集合中的数据分割为两个列表，一个true列表，一个false列表







,	new Person("李四",	22,	177)
,	new Person("张三",	14,	165)
,	new Person("李四",	15,	166)
,	new Person("张三",	19,	182)

 

输出结果：



5.6	对流中的数据做拼接
Collectors.joining会根据指定的连接符，将所有的元素连接成一个字符串






,	new Person("李四",	22,	177)
,	new Person("张三",	14,	165)
,	new Person("李四",	15,	166)
,	new Person("张三",	19,	182)





,	new Person("李四",	22,	177)
,	new Person("张三",	14,	165)
,	new Person("李四",	15,	166)
,	new Person("张三",	19,	182)





,	new Person("李四",	22,	177)
,	new Person("张三",	14,	165)
,	new Person("李四",	15,	166)
,	new Person("张三",	19,	182)

6.	并行的Stream流	
6.1	串行的Stream流
我们前面使用的Stream流都是串行，也就是在一个线程上面执行。


输出:



6.2	并行流
parallelStream其实就是一个并行执行的流，它通过默认的ForkJoinPool，可以提高多线程任务的速 度。
6.2.1	获取并行流
我们可以通过两种方式来获取并行流。
1.	通过List接口中的parallelStream方法来获取
2.	通过已有的串行流转换为并行流(parallel)


实现：


6.2.2	并行流操作


效果



6.3	并行流和串行流对比
我们通过for循环，串行Stream流，并行Stream流来对500000000亿个数字求和。来看消耗时间

 


通过案例我们可以看到parallelStream的效率是最高的。
Stream并行处理的过程会分而治之，也就是将一个大的任务切分成了多个小任务，这表示每个任务都是一个线程操作。

6.4	线程安全问题
在多线程的处理下，肯定会出现数据安全问题。如下：

 


运行效果：


或者直接抛异常


针对这个问题，我们的解决方案有哪些呢？
1.	加同步锁
2.	使用线程安全的容器
3.	通过Stream中的toArray/collect操作

实现：


/**
* 使用线程安全的容器
*/ @Test
public void test03(){ Vector v = new Vector();
Object obj = new Object(); IntStream.rangeClosed(1,1000)
.parallel()
.forEach(i->{
synchronized (obj){ v.add(i);
}

});
System.out.println(v.size());
}

/**
* 将线程不安全的容器转换为线程安全的容器
*/ @Test
public void test04(){
List<Integer> listNew = new ArrayList<>();
// 将线程不安全的容器包装为线程安全的容器
List<Integer> synchronizedList = Collections.synchronizedList(listNew); Object obj = new Object();
IntStream.rangeClosed(1,1000)
.parallel()
.forEach(i->{
synchronizedList.add(i);
});
System.out.println(synchronizedList.size());
}



/**
*	我们还可以通过Stream中的 toArray方法或者 collect方法来操作
*	就是满足线程安全的要求
*/ @Test
public void test05(){
List<Integer> listNew = new ArrayList<>(); Object obj = new Object();
List<Integer> list = IntStream.rangeClosed(1, 1000)
.parallel()
.boxed()
.collect(Collectors.toList()); System.out.println(list.size());
}





# 七、Optional类	

这个Optional类注意是解决空指针的问题
1.	以前对null 的处理	


2.	Optional类	
Optional是一个没有子类的工具类，Optional是一个可以为null的容器对象，它的主要作用就是为了避免Null检查，防止NullpointerException，


3.	Optional的基本使用	
Optional对象的创建方式

 


4.	Optional的常用方法	


/**
* 自定义一个方法，将Person对象中的 name 转换为大写 并返回
*/ @Test
public void test05(){
Person p = new Person("zhangsan",18); Optional<Person> op = Optional.of(p); String name = getNameForOptional(op); System.out.println("name="+name);

}


/**
*	根据Person对象 将name转换为大写并返回
*	通过Optional方式实现
*	@param op
*	@return
*/
public String getNameForOptional(Optional<Person> op){ if(op.isPresent()){
String msg = //op.map(p -> p.getName()) op.map(Person::getName)
//.map(p -> p.toUpperCase())
.map(String::toUpperCase)
.orElse("空值"); return msg;
}
return null;
}

/**
*	根据Person对象 将name转换为大写并返回
*	@param person
*	@return
*/
public String getName(Person person){ if(person != null){
String name = person.getName(); if(name != null){
return name.toUpperCase();
}else{
return null;
}
}else{
return null;
}
}





# 八、新时间日期API	

1.	旧版日期时间的问题	
在旧版本中JDK对于日期和时间这块的时间是非常差的。

 

1.	设计不合理，在java.util和java.sql的包中都有日期类，java.util.Date同时包含日期和时间的，而
java.sql.Date仅仅包含日期，此外用于格式化和解析的类在java.text包下。
2.	非线程安全，java.util.Date是非线程安全的，所有的日期类都是可变的，这是java日期类最大的问题之一。
3.	时区处理麻烦，日期类并不提供国际化，没有时区支持。

2.	新日期时间API介绍	
JDK 8中增加了一套全新的日期时间API，这套API设计合理，是线程安全的。新的日期及时间API位于java.time 包
中，下面是一些关键类。
LocalDate ：表示日期，包含年月日，格式为 2019-10-16
LocalTime ：表示时间，包含时分秒，格式为 16:38:54.158549300
LocalDateTime ：表示日期时间，包含年月日，时分秒，格式为 2018-09-06T15:33:56.750 DateTimeFormatter ：日期时间格式化类。
Instant：时间戳，表示一个特定的时间瞬间。
Duration：用于计算2个时间(LocalTime，时分秒)的距离Period：用于计算2个日期(LocalDate，年月日)的距离ZonedDateTime ：包含时区的时间
Java中使用的历法是ISO   8601日历系统，它是世界民用历法，也就是我们所说的公历。平年有365天， 闰年是366
天。此外Java 8还提供了4套其他历法，分别是：
ThaiBuddhistDate：泰国佛教历MinguoDate：中华民国历JapaneseDate：日本历
HijrahDate：伊斯兰历

2.1	日期时间的常见操作
LocalDate，LocalTime以及LocalDateTime的操作。

/**
* JDK8 日期时间操作
*/ @Test
public void test01(){
// 1.创建指定的日期
LocalDate date1 = LocalDate.of(2021, 05, 06); System.out.println("date1 = "+date1);

// 2.得到当前的日期
LocalDate now = LocalDate.now(); System.out.println("now = "+now);

// 3.根据LocalDate对象获取对应的日期信息System.out.println("年：" + now.getYear()); System.out.println("月：" + now.getMonth().getValue()); System.out.println("日：" + now.getDayOfMonth());
System.out.println("星期：" + now.getDayOfWeek().getValue());
}

/**
* 时间操作
*/ @Test
public void test02(){
// 1.得到指定的时间
LocalTime time = LocalTime.of(5,26,33,23145); System.out.println(time);
// 2.获取当前的时间
LocalTime now = LocalTime.now(); System.out.println(now);
// 3.获取时间信息
System.out.println(now.getHour()); System.out.println(now.getMinute()); System.out.println(now.getSecond()); System.out.println(now.getNano());
}


/**
* 日期时间类型  LocalDateTime
*/ @Test
public void test03(){
// 获取指定的日期时间
LocalDateTime dateTime =
LocalDateTime.of(2020
, 06
, 01
, 12
, 12
, 33

 





2.2	日期时间的修改和比较



 

注意：在进行日期时间修改的时候，原来的LocalDate对象是不会被修改，每次操作都是返回了一个新的
LocalDate对象，所以在多线程场景下是数据安全的。

2.3	格式化和解析操作
在JDK8中我们可以通过 java.time.format.DateTimeFormatter 类可以进行日期的解析和格式化操作


2.4	Instant类
在JDK8中给我们新增一个Instant类(时间戳/时间线)，内部保存了从1970年1月1日   00:00:00以来的秒和纳秒

 




2.5	计算日期时间差
JDK8中提供了两个工具类Duration/Period：计算日期时间差
1.	Duration：用来计算两个时间差(LocalTime)
2.	Period:用来计算两个日期差(LocalDate)





2.6	时间校正器
有时候我们可以需要如下调整：将日期调整到"下个月的第一天"等操作。这时我们通过时间校正器效果 可能会更好。
TemporalAdjuster:时间校正器
TemporalAdjusters:通过该类静态方法提供了大量的常用TemporalAdjuster的实现。

 




2.7	日期时间的时区
Java8 中加入了对时区的支持，LocalDate、LocalTime、LocalDateTime是不带时区的，带时区的日期时间类分别为：ZonedDate、ZonedTime、ZonedDateTime。
其中每个时区都对应着 ID，ID的格式为 “区域/城市” 。例如 ：Asia/Shanghai 等。
ZoneId：该类中包含了所有的时区信息



 


JDK新的日期和时间API的优势：
1.	新版日期时间API中，日期和时间对象是不可变，操作日期不会影响原来的值，而是生成一个新的实例
2.	提供不同的两种方式，有效的区分了人和机器的操作
3.	TemporalAdjuster可以更精确的操作日期，还可以自定义日期调整期
4.	线程安全



# 九、其他新特性	

1.	重复注解	
自从Java 5中引入 注解 以来，注解开始变得非常流行，并在各个框架和项目中被广泛使用。不过注解有一个很大的限
制是：在同一个地方不能多次使用同一个注解。JDK 8引入了重复注解的概念，允许在同一个地方多次使用同一个注
解。在JDK 8中使用@Repeatable注解定义重复注解。
1.1	定义一个重复注解的容器



1.2	定义一个可以重复的注解



1.3	配置多个重复的注解

 


1.4	解析得到指定的注解







2.	类型注解	
JDK 8为@Target元注解新增了两种类型： TYPE_PARAMETER ， TYPE_USE 。
TYPE_PARAMETER ：表示该注解能写在类型参数的声明语句中。 类型参数声明如： 、
TYPE_USE ：表示注解可以再任何用到类型的地方使用。

TYPE_PARAMETER

 


使用：



TYPE_USE


使用