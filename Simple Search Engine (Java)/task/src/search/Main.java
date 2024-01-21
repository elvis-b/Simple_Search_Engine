package search;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--data")) {
            if (args.length > 1) {
                String fileName = args[1];
                MenuController menuController = new MenuController(fileName);
                menuController.menu();
            }
        } else {
            System.out.println("Usage: java Main --data <file_name>");
        }
    }
}