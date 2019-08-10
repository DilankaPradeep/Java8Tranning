import static java.lang.Math.sqrt;

public class Basics {
    public static void main(String[] args) {


//    (2) https://www.quora.com/Can-we-create-an-object-of-an-interface-in-Java-If-yes-then-how (Google search)

        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a*100);
            }
        };
        formula.calculate(16); //40.0
        formula.sqrt(49);// 7








    }
    // (1)Java 8 enables us to add non-abstract method implementations to interfaces by utilizing the default keyword.
    // This feature is also known as Extension Methods.
    interface Formula{
        double calculate(int a);

        default double sqrt(int a){
            return Math.sqrt(a);
        }
    }

    }



