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
        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

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

//        (9.1) Filter
//        (9.2) Sorted
//        (9.3) Map
//        (9.4) Match
//        (9.5) Count
//        (9.6) Reduce


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
