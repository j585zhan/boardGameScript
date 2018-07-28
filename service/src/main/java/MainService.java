import types.Player;

public class MainService {
    public static void main(String[] args) {
        final Player p = new Player().withAlive(false).withName("Tester").withId(123);
        System.out.println("Hello World, " + p.getName());
    }
}
