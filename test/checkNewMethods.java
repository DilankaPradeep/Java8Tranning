import org.junit.Test;

import java.util.function.Function;
import java.util.function.Predicate;

public class checkNewMethods
{
	@Test
	public void builtInFunctionalInterfaces(){

//		predicates
		/**
		 * Boolean valued function of one argument
		 */
		Predicate<String> predicate = (s -> s.length()>0);
		predicate.test( "abc" );
		predicate.negate().test( "pqr" );

//		function
		/**
		 * Accept one argument and produce a result
		 * Can be chain multiple functions together (compose, andThen)
		 */
		Function<String,Integer> toInteger = Integer::valueOf;
		Function<String ,String > backToString = toInteger.andThen( String::valueOf );
		backToString.apply( "123" );


	}

}
