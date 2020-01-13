# 八锁

- 这里介绍 synchronized 锁住代码还是对象



## 总结

- static synchronized 锁类的 Class 对象（ Class.forName 的 Class ） 
- synchronized 锁对象
- 普通方法
- 三者之间独立，互不干扰，三者各自锁住各自



# 八锁试练

- 八种所得情况，进行练习



##  1.两个普通同步方法，两个线程标准访问

- 打印 Email, SMS



## 2.暂停三秒再访问邮件

- 打印 Email, SMS



## 3.新增普通方法 sayHello

- 打印 Hello, Email



## 4.两部手机先邮件还是先短信

- 打印 SMS, Email



## 5.两个静态同步方法，同一个手机

- 打印 Email, SMS



## 6.两个静态同步方法，两部手机

- 打印 Email, SMS



## 7.一个静态同步方法，一个普通方法，一部手机

- 打印 SMS, Email



## 8.一个静态同步方法，一个普通方法，两部手机

- 打印 SMS, Email