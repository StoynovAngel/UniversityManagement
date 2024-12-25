import config.DatabaseInitializer;
import config.DependencyFactory;
import services.InitialMenu;

public class Main {
    public static void main(String[] args) {
        DependencyFactory factory = new DependencyFactory();
        InitialMenu initialMenu = new InitialMenu(factory.createInteraction());
        initialMenu.userChoice();
        DatabaseInitializer initializer = new DatabaseInitializer();
        initializer.initializeDatabase();
    }
}