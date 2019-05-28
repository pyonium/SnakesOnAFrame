import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public @RequiredArgsConstructor class Snake {

    final private PlayingField playingField;
    final private int pixelSize;
    private Frame frame;
    private char direction;

    public void init(){
        direction = 'd';
        frame = new Frame();
        frame.setBackground(Color.BLACK);
        frame.add(new Painter(playingField, pixelSize));
        frame.setSize(playingField.getXsize() * pixelSize,playingField.getYsize() * pixelSize);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                direction = e.getKeyChar();
            }
        });
        frame.setVisible(true);
        while(true){
            updateGameState("");
            try {
                TimeUnit.MILLISECONDS.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateGameState(String message){
        System.out.println("Updating Game State...");
        Head current = playingField.getHead();
        Head newHead;
        Coordinate newHeadCoord = new Coordinate(0, 0);
        switch(direction) {
            case 'a':
                newHeadCoord = new Coordinate(current.getC().getX() - 1, current.getC().getY());
                break;
            case 'd':
                newHeadCoord = new Coordinate(current.getC().getX() + 1, current.getC().getY());
                break;
            case 's':
                newHeadCoord = new Coordinate(current.getC().getX(), current.getC().getY() + 1);
                break;
            case 'w':
                newHeadCoord = new Coordinate(current.getC().getX(), current.getC().getY() - 1);
                break;
        }
        Body newBody = new Body(current.getC(), current.getPrev());
        newHead = new Head(newHeadCoord, newBody);
        playingField.set(newBody);
        playingField.set(newHead);
        if(!message.equals("FOOD")){
            Body b = newHead.getPrev();
            Body temp = null;
            do{
                b = b.getPrev();

                if(b.getPrev() == null){
                    playingField.set(new Tile(b.getC()));
                    temp.setPrev(null);
                    break;
                }
                if(b.getPrev().getPrev() == null){
                    temp = b;
                }

            } while(b.getPrev() != null);
        }
        frame.getComponent(0).repaint();
    }


}
