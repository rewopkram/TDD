import java.util.StringTokenizer;


public class StringCalculator {

    public int add(String val) {
        if (val != null && val.trim().length() == 0) {
            return 0;
        }
        else if (val.indexOf(",") != -1) {
            int sum = 0;
            StringTokenizer st = new StringTokenizer(val, ",");
            while (st.hasMoreTokens()) {
                sum += Integer.parseInt(st.nextToken());
            }
            return sum;
        }
        return Integer.parseInt(val);
    }
    
    public int multiply(String nums) {
        if (nums != null && nums.trim().length() == 0) {
            return 0;
        }
        else {
            StringTokenizer st = new StringTokenizer(nums, ",");
            int product = 1;
            while (st.hasMoreTokens()) {
                int n1 = Integer.parseInt(st.nextToken());
                product *= n1;
            }
            return product;
        }
    }
}
