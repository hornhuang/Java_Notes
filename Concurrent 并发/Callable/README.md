# Callable

- 结合 FutureTask 使用



## 与 Runnable、Thread 区别

- 抽象方法名不同
- Callable 抛出异常而 Runnable、Thread 不抛出 

- Callable 有返回值而 Runnable、Thread 没有



## FutureTask

- 实现了 RunnableTask 接口
- RunnableTask 接口继承了 Callable 和 Runnable 接口，从而提高了程序的扩展性

