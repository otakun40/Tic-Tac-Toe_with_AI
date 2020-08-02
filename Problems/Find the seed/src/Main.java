import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int max = 0;
        int minMax = Integer.MAX_VALUE;
        int minMaxSeed = 0;
        Random random;
        for (int seed = a; seed <= b; seed++) {
            random = new Random(seed);
            int current;
            for (int j = 0; j < n; j++) {
                current = random.nextInt(k);
                if (max < current) {
                    max = current;
                }
            }
            if (minMax > max) {
                minMax = max;
                minMaxSeed = seed;
            }
            max = 0;
        }
        System.out.println(minMaxSeed);
        System.out.println(minMax);
    }
}