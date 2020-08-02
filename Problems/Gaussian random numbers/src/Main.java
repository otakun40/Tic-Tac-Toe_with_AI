import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        double m = scanner.nextDouble();

        Random random;
        boolean seedFound = false;
        boolean lessThanM = true;
        do {
            random = new Random(k);
            for (int i = 0; i < n; i++) {
                double gauss = random.nextGaussian();
                if (gauss > m) {
                    lessThanM = false;
                    break;
                }
            }
            if (lessThanM) {
                System.out.println(k);
                seedFound = true;
            }
            k++;
            lessThanM = true;
        }   while (!seedFound);
    }
}