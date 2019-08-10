class Lambda4 {

	/**
	 * we have both read and write access to instance fields and static variables from within lambda expressions
	 */
	static int outerStaticNum;
	int outerNum;

	void testScopes() {
		LamdaExpresion.Converter<Integer, String> stringConverter1 = (from) -> {
			outerNum = 23;
			return String.valueOf(from);
		};

		LamdaExpresion.Converter<Integer, String> stringConverter2 = (from) -> {
			outerStaticNum = 72;
			return String.valueOf(from);
		};
	}
}
