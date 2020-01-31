# 类装载器

- 如 ClassLoader 负责将编译后的 .class 文件，加载到方法区中（编程 Class 类的模板 ）
- 只负责加载，能否执行由 Execution Engine 决定
- 注 class 文件开头有特殊标识，保证 JVM 载入正确的 class 文件 （避免你建了个 txt 文件，手动改成 .class 这类情况）



## 类型

- Bootstrap 启动类加载器 C++
  - 加载 Java1.0 中的 object、String、Arraylist 等基类
  - (级别太高所以 java 控制台输出时 null)
- Extension 拓展类加载器 java
  - 加载 Java 后续版本添加类 
  - 加载 /jre/lib/ext 文件夹下 jar 包中的类
- AppClassLoader 应用程序类加载器 （自定义的类使用这个）

- 用户自定义类加载器 java.lang.ClassLoader 子类 



#### 继承关系

- AppClassLoader -继承自-> Extension  -继承自-> Extension   -继承自-> Bootstrap 

#### 

#### stackOverFlowError

- 是一个错误，是栈溢出的意思

- 原因几乎都是由 **递归**或者其相互调用引发的 **死循环 **导致