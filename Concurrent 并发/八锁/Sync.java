package lock;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 1.两个普通同步方法，两个线程标准访问								打印 Email, SMS
 * 2.暂停三秒再访问邮件												打印 Email, SMS
 * 3.新增普通方法 sayHello											打印 Hello, Email
 * 4.两部手机先邮件还是先短信										打印 SMS, Email
 * 5.两个静态同步方法，同一个手机									打印 Email, SMS
 * 6.两个静态同步方法，两部手机										打印 Email, SMS
 * 7.一个静态同步方法，一个普通方法，一部手机							打印 SMS, Email
 * 8.一个静态同步方法，一个普通方法，两部手机							打印 SMS, Email
 * 
 * @author FishInWater-1999
 */
public class Sync {

	@Test
	public void test() throws InterruptedException {
		Phone phone = new Phone();
		
		new Thread(() -> {
			try {
				phone.sendEmail();
			} catch (Exception e) {
				e.printStackTrace();
			}}, "A").start();
		
		Thread.sleep(100);
		
		new Thread(() -> phone.sendSMS(), "B").start();
		
		
	}
	
}

class Phone {
	
	public synchronized void sendEmail() throws InterruptedException {
		TimeUnit.SECONDS.sleep(3);
		System.out.println("---- send Email");
	}
	
	public synchronized void sendSMS() {
		System.out.println("---- send SMS");
	}
	
	public void sayHello() {
		System.out.println("---- say hello");
	}
}