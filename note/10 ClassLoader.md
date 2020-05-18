# 虚拟机类加载机制

## 1 概述

* **虚拟机类加载机制**

  虚拟机把描述类的数据从**Class文件**加载到**内存**，并对数据进行**校验**、**转换解析**和**初始化**，最终形成可以被虚拟机直接使用的 **Java 类型** 。

* **运行期加载、连接和初始化**

  在Java语言里面，类型的**加载、连接和初始化**过程都是在程序**运行期间**完成的，这种策略虽然会令类加载时稍微增加一些性能开销，但是会为Java应用程序提供高度的**灵活性**，Java里天生可以动态扩展的语言特性就是依 赖运行期动态加载和动态连接这个特点实现的。

## 2 类加载时机

### 2.1 类生命周期

* **生命周期7个阶段**

  * 加载 （Loading）
  * 连接（Linking）
    * 验证（Verification）
    * 准备（Preparation）
    * 解析（Resolution）
  * 初始化 （Initialization）
  * 使用（Using）
  * 卸载（Unloading）

  <img src="images.assets/1589804396203.png" alt="1589804396203" style="zoom:80%;" />

* **交叉混合运行**

  * 加载、验证、准备、初始化和卸载这5个阶段的顺序是确定的，类的加载过程必须按照这种顺序按部就班地**开始**，这些阶段通常都是互相交叉地混合式进行的，通常会在一个阶段执行的过程中调用、激活 

    另外一个阶段；

  * 解析阶段则不一定：它在某些情况下可以在初始化阶段之后再开始，是为了支持Java语言运行时绑定；

### 2.2 **何时触发类加载**

#### 2.3.1 **5种主动引用触发加载**

Java虚拟机规范中并没有进行强制约束，但是对于**初始化**阶段，虚拟机规范则是严格规定了**有且只有5种**情况必须立即对类进行“初始化”（而加载、验证、准备自然需要在此之前开始，即也就是在这5种情况下会触发类加载），即对一个类进行**主动引用**时候：

* **1）**遇到**new**、**getstatic**、**putstatic** 或 **invokestatic **这4条字节码指令时，如果类没有进行过初始化，则需要先触发其初始化。生成这4条指令的最常见的Java代码场景是：
  * 使用 **new** 关键字**实例化对象**的时候；
  * 读取或设置类的**静态字段**（被final修饰、已在编译期把结果放入常量池的静态字段除外）的时候；
  * 调用一个类的**静态方法**的时候；
*  **2）**使用 java.lang.reflect 包的方法对类进行**反射**调用的时候，如果类没有进行过初始化，则需要先触发其初始化； 
* **3）**当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要**先触发其父类**的初始化。 
* **4）**当虚拟机启动时，用户需要指定一个要执行的**主类**（包含main（）方法的那个类），虚拟机会先初始化这个主类。 
* **5）**当使用JDK 1.7的动态语言支持时，如果一个java.lang.invoke.MethodHandle实例最后的解析结果REF_getStatic、REF_putStatic、REF_invokeStatic的方法句柄，并且这个方法句柄所对应的类没有进行过初始化，则需要先触发其初始化。 

#### 2.3.2 **被动加载例子**

```java
public class SuperClass {
    static {
        System.out.println("SuperClass init !");
    }

    public static int value = 123;
}

public class SubClass extends SuperClass{
    static {
        System.out.println("SubClass init !");
    }

    public static final String HELLOWORLD = "hello world";
}

public class ClassLoaderService {
    public static void main(String[] args) {
        // 通过子类引用父类的静态资源，不会导致子类初始化
        System.out.println(SubClass.value);

        // 通过数组定义来引用类，不会触发此类的初始化
        SubClass[] sca = new SubClass[10];
        System.out.println(sca.length);

        // 常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化
        System.out.println(SubClass.HELLOWORLD);
    }
}
```

​	运行结果：<img src="images.assets/1589806172709.png" alt="1589806172709" style="zoom:80%;" />

​	运行结果中没有出现子类 SubClass 初始化打印内容，因为这三种都是被动使用，非主动引用，从而触发不了类加载。

#### 2.3.3 **主动引用触发类加载**

* new 创建对象实例

  ```java
  SubClass subClass = new SubClass();
  ```

  运行结果：

  ![1589806627672](images.assets/1589806627672.png)

* 读取或设置类的静态字段（非final)

  ```java
  public class SubClass extends SuperClass{
      static {
          System.out.println("SubClass init !");
      }
  
      public static  String NAME = "SUB CLASS";
  }
  
  public class ClassLoaderService {
      public static void main(String[] args) {
          // **** 主动引用：触发类加载 ****
          // 读取类的静态字段（非final）
          System.out.println(SubClass.NAME);
      }
  }
  ```

  运行结果：

  ![1589806772457](images.assets/1589806772457.png)

* 调用一个类的静态方法

  ```java
  public class SubClass extends SuperClass{
      static {
          System.out.println("SubClass init !");
      }
  
      public static void info(){
          System.out.println("Hello, I am Subclass !");
      }
  }
  
  public class ClassLoaderService {
      public static void main(String[] args) {
          // **** 主动引用：触发类加载 ****
          SubClass.info();
      }
  }
  ```

  运行结果：

  ![1589806904168](images.assets/1589806904168.png)

* 反射

  ```java
  public class ClassLoaderService {
      public static void main(String[] args) {
          // **** 主动引用：触发类加载 ****
          try {
              Class clazz = Class.forName(SubClass.class.getName());
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          }
      }
  }
  ```

  运行结果：

  ![1589807102185](images.assets/1589807102185.png)

* 先触发其父类

  被动使用例子中子类使用父类的静态常量就触发了父类先初始化；

* 先初始化主类

  这里直接用 main 方法演示：

  ```java
  public class ClassLoaderService {
      static {
          System.out.println("MAIN");
      }
  
      public static void main(String[] args) {
          System.out.println("主类开始调用其它类");
          // **** TODO ****
      }
  }
  ```

  运行结果：

  ![1589807389547](images.assets/1589807389547.png)

#### 2.3.4 **接口的触发加载**

接口与类真正有所区别的是前面讲述的5种“有且仅有”需要开始初始化场景中的第3种：

当一个类在初始化时，要求其父类全部都已经初始化过了，但是一个接口在初始化时，并不要求其父接口全部都完成了初始化，只有在真正**使用到父接口**的时候（如引用接口中定义的常量）**才会初始化** 。