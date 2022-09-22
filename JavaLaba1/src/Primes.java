public class Primes {
    public static void main(String[] args) throws Exception {
        // the limit to which the numbers are searched for
        final int limit = 100;
        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                System.out.print(i);
                System.out.print(' ');
            }
        }
    }

    // isPrime method return true if number is prime
    // else return false
    private static boolean isPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
