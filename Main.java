import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
    public static void main(String[] args) {
        String[] fileNames = {"input1.json", "input2.json"};
        for (String file : fileNames) {
            try {
                JSONObject json = (JSONObject) new JSONParser().parse(new FileReader(file));
                JSONObject keys = (JSONObject) json.get("keys");
                int k = ((Long) keys.get("k")).intValue();

                List<BigInteger> xList = new ArrayList<>();
                List<BigInteger> yList = new ArrayList<>();

                for (Object keyObj : json.keySet()) {
                    String keyStr = (String) keyObj;
                    if (keyStr.equals("keys")) continue;

                    int x = Integer.parseInt(keyStr);
                    JSONObject point = (JSONObject) json.get(keyStr);
                    int base = Integer.parseInt((String) point.get("base"));
                    String val = (String) point.get("value");

                    BigInteger y = new BigInteger(val, base);
                    xList.add(BigInteger.valueOf(x));
                    yList.add(y);

                    if (xList.size() == k) break; // Only take first k points
                }

                BigInteger secret = lagrangeInterpolation(BigInteger.ZERO, xList, yList);
                System.out.println("Secret (constant term c) from " + file + ": " + secret);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static BigInteger lagrangeInterpolation(BigInteger x, List<BigInteger> xList, List<BigInteger> yList) {
        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < xList.size(); i++) {
            BigInteger xi = xList.get(i);
            BigInteger yi = yList.get(i);
            BigInteger term = yi;

            for (int j = 0; j < xList.size(); j++) {
                if (i == j) continue;
                BigInteger xj = xList.get(j);
                BigInteger numerator = x.subtract(xj);
                BigInteger denominator = xi.subtract(xj);
                term = term.multiply(numerator).divide(denominator);
            }

            result = result.add(term);
        }

        return result;
    }
}
