public class ExampleInstrumentedTest {

    class Employee {

        private String name;
        private double salary;
        private int age;

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

    }


    List<Employee> list = Arrays.asList(
            new Employee("张三", 6666.66, 66),
            new Employee("李四", 5555.55, 55),
            new Employee("王五", 4444.44, 44),
            new Employee("马六", 7777.77, 77),
            new Employee("小明", 3333.33, 33)
    );

    public final String TAG = getClass().getName();

    @Test
    public void test() {
        Collections.sort(list, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getAge() == o2.getAge()) {
                    return o1.getName().compareTo(o2.getName());
                }else {
                    return Integer.compare(o1.getAge(), o2.getAge());
                }
            }
        });

        for (int i= 0 ; i < list.size() ; i++) {
            Log.d(TAG, list.get(i).toString());
        }


        Collections.sort(list, (o1, o2) -> {
            if (o1.getAge() == o2.getAge()) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }else {
                return o1.getName().compareTo(o2.getName());
            }
        });
        list.forEach((e) -> Log.d(TAG, e.toString()));
    }

    @Test
    public void test1() {
        // 四大内置
        Consumer<Integer> con = (t) -> Log.d(TAG, t + "");
        Supplier<String> supplier = () -> "Hello Lambda";
        Function<String, Integer> function = Integer::valueOf;

        // 使用
        use(1000, (t) -> Log.d(TAG, t + "") /*由于 Java8 加强类型推断功能，故这里使用 Lambda 不用声明 Consumer<T> T 的类型*/);
        List<Integer> list = build(10, () -> (int) (Math.random() * 100));
        list.forEach((t) -> Log.d(TAG, t + ""));
        calcute(3, (t) -> ((double) t*t ) / 2.0);
        Log.d(TAG, predicate(99, (f) -> f > 90) + "");
    }

    private void use(Integer i, Consumer<Integer> con) {
        con.accept(i);
    }

    private List<Integer> build(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer integer = sup.get();
            list.add(integer);
        }

        return list;
    }

    private double calcute(int t, Function<Integer, Double> fun) {
        return  fun.apply(t);
    }

    private boolean predicate(float score, Predicate<Float> pre) {
        return pre.test(score);
    }

}
