import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Painter extends JPanel {

    private PlayingField playingField;
    private int pixelSize;

    public Painter(PlayingField _playingField, int _pixelSize){
        super();
        playingField = _playingField;
        pixelSize = _pixelSize;
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        Shape tile;
        for(int i = 0; i < playingField.getYsize(); i++){
            for(int j = 0; j < playingField.getXsize(); j++) {
                if (!playingField.get(j, i).getClass().getName().equals("Tile")) {
                    tile = new Rectangle2D.Float(pixelSize * j, pixelSize * i, pixelSize, pixelSize);
                    if (playingField.get(j, i).getClass().getName().equals("Body")) {
                        g2d.setPaint(Color.YELLOW);
                    } else if (playingField.get(j, i).getClass().getName().equals("Head")) {
                        g2d.setPaint(Color.RED);
                    } else if (playingField.get(j, i).getClass().getName().equals("Food")) {
                        g2d.setPaint(Color.GREEN);
                    }
                    g2d.fill(tile);
                    g2d.draw(tile);
                }

            }
        }
    }
}

