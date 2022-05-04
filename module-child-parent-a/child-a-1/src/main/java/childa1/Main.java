package childa1;

public class Main {
    public static void main(String[] args) {
        System.out.println("no-core-child-a-1" + " -> consume -> " + childb2.Main.soyCoreChildB2());
    }

    public static String soyCoreChildA1(){
        return "no-core-child-a-1" + " -> consume -> " + childb2.Main.soyCoreChildB2();
    }
}
