package a.b.c;

import sun.misc.Unsafe;

public class TestMeJRE {
  public static void main(String[] args) {
    System.out.println("Hi!");
  }

  public static void TestMeJRE8(){
    Unsafe demos = null;
    System.out.println("Unsafe: " + demos);
    System.out.println("I am part of JRE *** 11");
  }
}
