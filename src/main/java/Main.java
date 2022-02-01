import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Please input the amount in EUR: ");
        String amount = scan.nextLine();
        Validation.stringValidation(amount);
        double v = Double.parseDouble(amount);

        System.out.println("Please input the shortcut of the currency you want to convert." +
                " Below you have the list of shortcuts: ");
        XmlParse xmlParse = new XmlParse();
        System.out.println();
        xmlParse.getListOfCurrencyShortcut();
        String shortcut = scan.nextLine().toUpperCase();
        Validation.stringValidate(shortcut);

        System.out.println();

        Calculator calculator = new Calculator();
        System.out.println("The exchange value is: "
                + calculator.convert(v, shortcut) + " " + shortcut);
    }
}
