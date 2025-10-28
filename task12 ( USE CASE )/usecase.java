import java.util.*;

public class ATMDispenser {

    // ---------- GREEDY APPROACH ----------
    public static Map<Integer, Integer> greedyDispense(int amount, int[] denominations) {
        Map<Integer, Integer> result = new LinkedHashMap<>();
        Arrays.sort(denominations); // sort ascending
        for (int i = denominations.length - 1; i >= 0; i--) {
            int note = denominations[i];
            int count = amount / note;
            if (count > 0) {
                result.put(note, count);
                amount -= count * note;
            }
        }
        if (amount != 0) return null; // Greedy failed
        return result;
    }

    // ---------- DYNAMIC PROGRAMMING APPROACH ----------
    public static Map<Integer, Integer> dpDispense(int amount, int[] denominations) {
        int n = denominations.length;
        int[] dp = new int[amount + 1];
        int[] lastUsed = new int[amount + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int note : denominations) {
                if (i >= note && dp[i - note] != Integer.MAX_VALUE && dp[i - note] + 1 < dp[i]) {
                    dp[i] = dp[i - note] + 1;
                    lastUsed[i] = note;
                }
            }
        }

        if (dp[amount] == Integer.MAX_VALUE) return null; // Not possible to form amount

        Map<Integer, Integer> result = new LinkedHashMap<>();
        int temp = amount;
        while (temp > 0) {
            int note = lastUsed[temp];
            result.put(note, result.getOrDefault(note, 0) + 1);
            temp -= note;
        }

        // Sort by descending order for readability
        Map<Integer, Integer> sorted = new TreeMap<>(Collections.reverseOrder());
        sorted.putAll(result);
        return sorted;
    }

    // ---------- MAIN METHOD ----------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== ATM Cash Dispenser ===");
        System.out.print("Enter withdrawal amount: ");
        int amount = sc.nextInt();

        int[] denominations = {2000, 500, 200, 100, 50, 20, 10}; // available notes

        // Try greedy approach first
        Map<Integer, Integer> result = greedyDispense(amount, denominations);

        if (result != null) {
            System.out.println("\nDispensed using GREEDY approach:");
        } else {
            System.out.println("\nGreedy approach failed! Applying DYNAMIC PROGRAMMING...");
            result = dpDispense(amount, denominations);
            if (result == null) {
                System.out.println("Cannot dispense the requested amount with given denominations!");
                sc.close();
                return;
            } else {
                System.out.println("Dispensed using DYNAMIC PROGRAMMING:");
            }
        }

        // Display the notes used
        int totalNotes = 0;
        for (Map.Entry<Integer, Integer> e : result.entrySet()) {
            System.out.println("₹" + e.getKey() + " x " + e.getValue());
            totalNotes += e.getValue();
        }
        System.out.println("Total notes dispensed: " + totalNotes);

        sc.close();
    }
}
