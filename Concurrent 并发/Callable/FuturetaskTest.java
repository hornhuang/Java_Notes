package juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class FuturetaskTest {

	@Test
	public void test() throws InterruptedException, ExecutionException {
		FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> {
			int result = (int) ( Math.random() * 100 );
			System.out.println("-->"+result);
			TimeUnit.SECONDS.sleep(3);
			return result;
		});
		new Thread(futureTask, "A").start();
		//System.out.println(futureTask.get() + "");
		System.out.println(66667);
	}
	
}
