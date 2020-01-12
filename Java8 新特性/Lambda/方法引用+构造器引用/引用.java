public class ExampleInstrumentedTest {

    public final String TAG = getClass().getName();

    class Employee {

        private String name;
        private double salary;
        private int age;

        public Employee(double salary, int age) {
            this.salary = salary;
            this.age = age;
        }

        public Employee(String name, double salary, int age) {
            super();
            this.name = name;
            this.salary = salary;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Employee [name=" + name + ", salary=" + salary + ", age=" + age + "]";
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public double getSalary() {
            return salary;
        }
        public void setSalary(double salary) {
            this.salary = salary;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }

        public String joint(String s1, String s2){
            return s1 + s2;
        }

    }

    @Test
    public void test5() {
        Function<Integer, Employee[]> pre1 = (i) -> new Employee[i];
        pre1.apply(10);

        Function<Integer, Employee[]> pre2 = Employee[]::new;
        pre2.apply(10);
    }

    @Test
    public void test4() {
        BiFunction<Double, Integer, Employee> pre1 = (s1, s2) -> new Employee(s1, s2);
        pre1.apply(1000.00, 20);

        BiFunction<Double, Integer, Employee> pre2 = Employee::new;
        pre2.apply(1000.00, 20);
    }

    @Test
    public void test3() {
        BiFunction<String, String, Boolean> pre1 = (s1, s2) -> s1.equals(s2);
        pre1.apply("参数1", "参数2");

        BiFunction<String, String, Boolean> pre2 = String::equals;
        pre2.apply("参数1", "参数2");
    }

    @Test
    public void test2() {
        Comparator<Integer> com1 = (o1, o2) -> Integer.compare(o1, o2);
        com1.compare(5, 6);

        Comparator<Integer> com2 = Integer::compare;
        com2.compare(5, 6);
    }

    @Test
    public void test1() {
        Consumer<String> con1 = (s) -> System.out.println(s);
        con1.accept(" 对象构造器 ");

        Consumer<String> con2 = System.out::println;
        con2.accept(" 对象构造器 ");
    }

}