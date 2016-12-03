package avoronin;

public class Calculate {

   public static void main(String[] args) {
      Calculate calc = new Calculate();
      System.out.println(calc.showText("World"));
   }

   public String showText(String text) {
      return String.format("%s %s %s", text, text, text);
   }
}