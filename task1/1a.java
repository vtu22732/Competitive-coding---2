public class SquareRootNewton {
    public static void main(String[] args) {
        int num = 25; 
        double sqrt = num / 2.0; 
        double t;
        double epsilon = 1e-10; 
        
        do {
            t = sqrt;
            sqrt = (t + (num / t)) / 2;
        } while (Math.abs(t - sqrt) > epsilon);

        System.out.println("Square root of " + num + " is: " + sqrt);
    }
}
