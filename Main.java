class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    RegEx r = new RegEx("#, aac, acc, abc, ac, abbc, abbbc, abbbbc, aabc, accb");
    System.out.println(r.findAllMatches("a.c"));
    System.out.println(r.findAllMatches("ab*c"));
    System.out.println(r.findAllMatches("a[ab]c"));
    
  }
}
