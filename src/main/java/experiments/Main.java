package experiments;

public class Main {
    public static void main(String[] args) {
        double r = 4.51;
        int bound = (int)r;
        int bound2 = Double.valueOf(r).intValue();
        System.out.println(bound);
        System.out.println(bound2);
    }
}
