# 四大内置接口



#### 消费者

```java
Consumer<Integer> con = (t) -> Log.d(TAG, t + "");
```

###### 使用

```java
use(1000, (t) -> Log.d(TAG, t + "") /*由于 Java8 加强类型推断功能，故这里使用 Lambda 不用声明 Consumer<T> T 的类型*/);
```

```java
private void use(Integer i, Consumer<Integer> con) {    
    con.accept(i);
}
```



#### 构造者

```java
Supplier<String> supplier = () -> "Hello Lambda";
```

###### 使用

```java
List<Integer> list = build(10, () -> (int) (Math.random() * 100));list.forEach((t) -> Log.d(TAG, t + ""));
```

```java
private List<Integer> build(int num, Supplier<Integer> sup) {    
    List<Integer> list = new ArrayList<>();    
    for (int i = 0; i < num; i++) {        
        Integer integer = sup.get();        
        list.add(integer);    
    }    
    return list;
}
```



#### 函数接口

```java
Function<String, Integer> function = Integer::valueOf;
```

###### 使用

```java
calcute(3, (t) -> ((double) t*t ) / 2.0);
```

```java
private double calcute(int t, Function<Integer, Double> fun) {    
    return  fun.apply(t);
}
```



#### 断言接口

```java
Predicate<Integer> predicate = (o1) -> o1 > 1000;
```

###### 使用

```java
Log.d(TAG, predicate(99, (f) -> f > 90) + "");
```

```java
private boolean predicate(float score, Predicate<Float> pre) {    
    return pre.test(score);
}
```





## 子接口

