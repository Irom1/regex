import java.util.Scanner;

// clear && javac Main.java && javac RegEx.java && java Main

/*
#, aac, acc, abc, ac, abbc, abbbc, abbbbc, aabc, accb
a.c
a[ab]c
a[^ab]c
ab*c
ab{2,4}c

1. aac, acc, abc
2. aac, abc
3. acc
4. ac, abc, abbc, abbbc, abbbbc
5. abbc, abbbc, abbbbc

#, xx, yz, xz, xy, xyz, xxyz, xyyz, yzz, xxxyz
x[xyz]yz
x.z
x[^y]z
x{1,4}yz
x*yz

1. xxyz, xyyz
2. xyz
3. NONE
4. xyz, xxyz, xxxyz
5. yz, xyz, xxyz, xxxyz
*/

public class Main {
  public static void main(String[] args) {
    System.out.println("\nACSL Reg Exp by Marcus & Mori\n");
    Scanner input = new Scanner(System.in);
    System.out.println("Enter the 10 possible strings (hit enter for defualt set): ");
    String options = input.nextLine();
    if (options.equals("")) {
      System.out.println("\nTesting Strings\n");
      RegEx r = new RegEx("#, aac, acc, abc, ac, abbc, abbbc, abbbbc, aabc, accb");
      System.out.println(r.findAllMatches("a.c"));
      System.out.println(r.findAllMatches("ab*c"));
      System.out.println(r.findAllMatches("a[ab]c"));
      System.out.println(r.findAllMatches("a[^ab]c"));
      System.out.println(r.findAllMatches("ab{2,4}c"));
    } else {
      RegEx r = new RegEx(options);
      System.out.println("\nEnter the expressions to find which match: ");
      for (int i = 0; i < 5; i++) {
        String expression = input.nextLine();
        System.out.println(r.findAllMatches(expression) + "\n");
      }
    }
    input.close();
  }
}