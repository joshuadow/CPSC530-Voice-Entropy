import java.util.HashMap;

public class Entropy {

    @SuppressWarnings("boxing")
    public static double getShannonEntropy(byte[] b) {
        int n = 0;
        HashMap<Character, Integer> hshmp = new HashMap<>();
        String s = b.toString();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (hshmp.containsKey(ch)) {
                hshmp.put(ch, hshmp.get(ch) + 1);
            } else {
                hshmp.put(ch, 1);
            }
            ++n;
        }
        double result = 0.0;
        for (HashMap.Entry<Character, Integer> entry : hshmp.entrySet()) {
            char ch = entry.getKey();
            double p = (double) entry.getValue() / n;
            result += p * log2(p);
        }
        return -result;
    }

    private static double log2(double a) {
        return Math.log(a) / Math.log(2);
    }
}