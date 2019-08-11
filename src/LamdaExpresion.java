import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LamdaExpresion {

    public static void main(String[] args) {
        /**
         *(1) Each lambda corresponds to a given type, specified by an interface.
         * A so called functional interface must contain exactly one abstract method declaration.
         * Each lambda expression of that type will be matched to this abstract method.
         */
        List<String> names = Arrays.asList("peter", "anna", "mike", "dilanka");
       Collections.sort(names, (a, b) -> b.compareTo(a));
        System.out.println(names);

// (2)Method reference
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);

// (3)Constructor References
        Something something = new Something();
        Converter<String, String> newConverter = something::startsWith;
        String newConvertered = newConverter.convert("Java");
        System.out.println(newConvertered);

// (4) Instead of implementing the factory manually, we glue everything together via constructor references:
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");

//      Lambda Scopes
//    (5)    Accessing local variables
        /**
         * local variables referenced from a lambda expression must be final or effectively final
         */
        final int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);

        stringConverter.convert(2);

        // (6) Accessing fields and static variables
        Lambda4 lambda4 = new Lambda4();


//       (7) Accessing Default Interface Methods
        /**
         * Default methods cannot be accessed from within lambda expressions. The following code does not compile:
         */
        //Formula formula1 = (a) -> sqrt( a * 100);

//      (8)  Built-in Functional Interfaces
//        (8.1)Predicates
        /**
         * Predicates are boolean-valued functions of one argument.
         * The interface contains various default methods for composing predicates to complex logical terms (and, or, negate)
         */
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

//        (8.2) Functions
        /**
         * Functions accept one argument and produce a result.
         * Default methods can be used to chain multiple functions together (compose, andThen).
         */
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");

//        (8.3) Suppliers
        /**
         * Suppliers produce a result of a given generic type.
         * Unlike Functions, Suppliers don't accept arguments.
         */
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person

//        (8.4) Consumers
        /**
         * Consumers represents operations to be performed on a single input argument.
         */
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));

//        (8.5) Comparators
        /**
         * . Java 8 adds various default methods to the interface
         */
//        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
//        or below method
        Comparator<Person> comparator = Comparator.comparing( p -> p.firstName );

        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");

        comparator.compare(p1, p2);             // > 0
        comparator.reversed().compare(p1, p2);  // < 0

//        (8.6) Optionals
        /**
         * Optionals are not functional interfaces, instead it's a nifty utility to prevent NullPointerException.
         * Optional is a simple container for a value which may be null or non-null.
         * Think of a method which may return a non-null result but sometimes return nothing.
         * Instead of returning null you return an Optional in Java 8.
         */
        Optional<String> optional = Optional.of("bam");

        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"


//        (9) Streams
        /**
         * A java.util.Stream represents a sequence of elements on which one or more operations can be performed.
         * Stream operations are either intermediate or terminal.
         * While terminal operations return a result of a certain type, intermediate operations return the stream itself so you can chain multiple method calls in a row.
         * Streams are created on a source, e.g. a java.util.Collection like lists or sets (maps are not supported).
         * Stream operations can either be executed sequential or parallel.
         */
        System.out.println("################## Streams ###################");

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        //        (9.1) Filter
        /**Filter accepts a predicate to filter all elements of the stream.
         * This operation is intermediate which enables us to call another stream operation (forEach) on the result.
         * ForEach accepts a consumer to be executed for each element in the filtered stream.
         * ForEach is a terminal operation. It's void, so we cannot call another stream operation.         *
         */
        System.out.println("************* Filter **************");

        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
        //************* Filter **************
        //aaa2
        //aaa1

        //        (9.2) Sorted
        /**
         * Sorted is an intermediate operation which returns a sorted view of the stream.
         * The elements are sorted in natural order unless you pass a custom Comparator
         * Keep in mind that sorted does only create a sorted view of the stream
         * without manipulating the ordering of the backed collection.
         * The ordering of stringCollection is untouched.
         */
        System.out.println("************* Sorted **************");

        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
        //************* Sorted **************
        //aaa1
        //aaa2

        //        (9.3) Map
        /**
         * The intermediate operation map converts each element into another object via the given function.
         * The following example converts each string into an upper-cased string.
         * But you can also use map to transform each object into another type.
         * The generic type of the resulting stream depends on the generic type of the function you pass to map
         */
        System.out.println("************* Map **************");

        stringCollection
                .stream()
                .map(String::toUpperCase)
//                .sorted((a, b) -> b.compareTo(a))
                .sorted( (a,b) -> a.compareTo( b ) )
                .forEach(System.out::println);
        //************* Map **************
        //AAA1
        //AAA2
        //BBB1
        //BBB2
        //BBB3
        //CCC
        //DDD1
        //DDD2

        //        (9.4) Match
        /**
         *Various matching operations can be used to check whether a certain predicate matches the stream.
         * All of those operations are terminal and return a boolean result.
         */
        System.out.println("************* Match **************");
        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);

        //************* Match **************
        //true
        //false
        //true

        //        (9.5) Count
        /**
         *Count is a terminal operation returning the number of elements in the stream as a long.
         */
        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();

        System.out.println("************* Count **************");
        System.out.println(startsWithB);    // 3

        //************* Count **************
        //3

        //        (9.6) Reduce
        /**
         *This terminal operation performs a reduction on the elements of the stream with the given function.
         * The result is an Optional holding the reduced value.
         */
        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);

        System.out.println("************* Reduce **************");
        reduced.ifPresent(System.out::println);

        //************* Reduce **************
        //aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2


//        (10) Parallel Streams
//          (10.1) Sequential Sort
//          (10.2) Parallel Sort

//        (11) Map

//        (12) Date API
//        (12.1) Clock
//        (12.2) Timezones
//        (12.3) LocalTime
//        (12.4) LocalDate
//        (12.5) LocalDateTime

//        (13) Annotations

    }






    //(3)Constructor References class
    static class Something {
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }

//(2)
@FunctionalInterface
    interface Converter<F,T>{
        T convert(F form);
    }



}
