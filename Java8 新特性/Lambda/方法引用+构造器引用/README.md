# 1、方法引用

- 函数接口的抽象方法参数类型、数量、返回值，必须和对象对应方法一致
- 函数接口的抽象方法参数顺序，必须和对象对应方法一致



## 1.1、对象 :: 实体方法

#### 原先

```
Consumer<String> con1 = (t) -> System.out.println(t);
con1.accept(" 对象构造器 ");
```

#### 引用

```java
Consumer<String> con = System.out::println;
con.accept(" 对象构造器 ");
```



## 1.2、类 :: 静态方法

#### 原先

```java
Comparator<Integer> com1 = (o1, o2) -> Integer.compare(o1, o2);
com1.compare(5, 6);
```

#### 引用

```java
Comparator<Integer> com2 = Integer::compare;
com2.compare(5, 6);
```



## 1.3、类 :: 实体方法

- 第一个参数是实例方法的调用者
- 其他参数是调用者方法的参数

#### 原先

```java
BiFunction<String, String, Boolean> pre1 = (s1, s2) -> s1.equals(s2);
pre1.apply("参数1", "参数2");
```

#### 引用

```java
BiFunction<String, String, Boolean> pre2 = String::equals;
pre2.apply("参数1", "参数2");
```



# 2、构造器引用



## 2.1、类构造器引用

#### 原先

```java
BiFunction<Double, Integer, Employee> pre1 = (s1, s2) -> new Employee(s1, s2);
pre1.apply(1000.00, 20);
```

#### 引用

```java
BiFunction<Double, Integer, Employee> pre2 = Employee::new;
pre2.apply(1000.00, 20);
```



## 2.2、数组引用

#### 原先

```java
Function<Integer, Employee[]> pre1 = (i) -> new Employee[i];
pre1.apply(10);
```

#### 引用

```java
Function<Integer, Employee[]> pre2 = Employee[]::new;
pre2.apply(10);
```

