import java.awt.*;

public class Main {

    public static void main(String[] args)
    {
        PlayingField p = new PlayingField(20, 20);
        Snake s = new Snake(p, 30);
        s.init();
    }
}
