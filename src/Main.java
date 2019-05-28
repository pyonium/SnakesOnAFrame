import java.awt.*;

public class Main {

    public static void main(String[] args)
    {
        System.setProperty("sun.java2d.opengl", "true");
        PlayingField p = new PlayingField(20, 20);
        Snake s = new Snake(p, 30);
        s.init();
    }
}
