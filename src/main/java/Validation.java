public class Validation {

    public static void stringValidation(String name) {
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("No amount has been given");
        }
        try {
            Double.parseDouble(name);
        } catch (NumberFormatException e) {
            System.out.println("Given value is not a numeric");
        }
    }

    public static void stringValidate(String name) {
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("No currency shortcut has been given");
        }
    }

}
