import java.util.*;

public class IntervalListIntersections {

    public static int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> result = new ArrayList<>();

        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            int startMax = Math.max(A[i][0], B[j][0]);
            int endMin = Math.min(A[i][1], B[j][1]);

            if (startMax <= endMin) {
                result.add(new int[]{startMax, endMin});
            }

            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] A = {{0,2},{5,10},{13,23},{24,25}};
        int[][] B = {{1,5},{8,12},{15,24},{25,26}};

        int[][] intersections = intervalIntersection(A, B);

        for (int[] interval : intersections) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
