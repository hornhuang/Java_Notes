# Callable

- 结合 FutureTask 使用



## 与 Runnable、Thread 区别

- 抽象方法名不同
- Callable 抛出异常而 Runnable、Thread 不抛出 

- Callable 有返回值而 Runnable、Thread 没有



## FutureTask

- 实现了 RunnableTask 接口
- RunnableTask 接口继承了 Callable 和 Runnable 接口，从而提高了程序的扩展性

```java
	public void test() throws InterruptedException, ExecutionException {
		FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> {
			int result = (int) ( Math.random() * 100 );
			System.out.println(result);
			return result;
		});
		new Thread(futureTask, "A").start();
		System.out.println(futureTask.get() + "");
	}
```

- futureTask.get() 获得线程运行后 return 的返回值

- 注：遇到 get() 方法时，futureTask.get() 所在线程阻塞，后面语句不再执行，等 Callable 方法执行结束得到返回值后接着运行后面语句
- 但如果不写 futureTask.get() 方法，则不影响后面语句执行