public class asg1 {

    public static void main(String args[]){
        if(args.length < 3)
            return;
        long a, j, n;
        a = Long.valueOf(args[0]);
        j = Long.valueOf(args[1]);
        n = Long.valueOf(args[2]);
        System.out.println(smart_modulo(a,j,n));
    }

    public static long smart_modulo(long base, long exponent, long modulo){

        /*
        rule: b^c * b^d = b^c+d
        (base 10) b^5 = b^4 * b^1
        (base 2) b^(101) = b^2^3 * 1 * b^2^1

        as a result we can calculate any power by splitting it into easy to calculate powers of two.

        the easiest way to do this is to convert an exponent to binary and for every 1, calculate that power and
        find its product with the remaining ones.

        the modulo operations all work thanks to:
        b^(x+y) = ( b^x %n * b^y %n ) %n
         */

        //error handling
        if(modulo == 0)
            return 0;

        long result = 1;
        base = base % modulo;

        while (exponent > 0){
            //a bitwise and will check the last bit. if it is one the expression is odd.
            if((exponent & 1) == 1) {
                //if there is a one this means we can multiply by a power of two. the first time round we want to
                //multiply our result by base^2^1, the second time base^2^2 then base^2^3 etc
                //we increase the base each iteration to the next base^current_power_of_two
                result *= base;
                //take the modulus to keep our answer reasonable
                result %= modulo;
            }
            //remove the final decimal place by bit shifting it away. this let's us check whether the next bit is a one
            //and therefor require producting with our result.
            exponent = exponent >> 1;
            //base = base * base = base^2^i (1 2 4 8 16...)
            //i.e. increase our base to be base^2^present binary decimal place
            base *= base;
            base %= modulo;

        }
        return result;
    }

}
