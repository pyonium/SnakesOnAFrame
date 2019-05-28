import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public @RequiredArgsConstructor class Snake {

    final private PlayingField playingField;
    final private int pixelSize;
    private JFrame frame;
    private char direction;

    public void init(){
        createAndShowGui();
        while(true){
            updateGameState("");
            try {
                TimeUnit.MILLISECONDS.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void createAndShowGui(){
        direction = 'd';
        frame = new JFrame();
        Painter p = new Painter(playingField, pixelSize);
        p.setBackground(Color.BLACK);
        frame.setContentPane(p);
        frame.pack();
        frame.setBackground(Color.BLACK);
        frame.setSize(playingField.getXsize() * pixelSize,playingField.getYsize() * pixelSize);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(direction == e.getKeyChar()){
                }
                else if(direction == 'w' && e.getKeyChar() == 's' || direction == 's' && e.getKeyChar() == 'w' || direction == 'a' && e.getKeyChar() == 'd' || direction == 'd' && e.getKeyChar() == 'a'){
                }
                else {
                    direction = e.getKeyChar();
                }

            }
        });
        frame.setVisible(true);
    }

    public void updateGameState(String message){
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

        boolean isFood = false;
        if(playingField.get(newHeadCoord).getClass().getName().equals("Food")){
            isFood = true;
        }
        playingField.set(newHead);
        playingField.set(newBody);
        if(!isFood) {
            Body b = newHead.getPrev();
            Body temp = null;
            do {
                b = b.getPrev();

                if (b.getPrev() == null) {
                    playingField.set(new Tile(b.getC()));
                    temp.setPrev(null);
                    break;
                }
                if (b.getPrev().getPrev() == null) {
                    temp = b;
                }

            } while (b.getPrev() != null);
        }

        if(isFood) {
            boolean done = false;
            int randomX;
            int randomY;
            while (!done) {
                randomX = ThreadLocalRandom.current().nextInt(0, playingField.getXsize());
                randomY = ThreadLocalRandom.current().nextInt(0, playingField.getYsize());
                    if (playingField.get(randomX, randomY).getClass().getName().equals("Tile")) {
                        playingField.set(new Food(new Coordinate(randomX, randomY)));
                        System.out.println(randomX + " " + randomY);
                        done = true;
                    }
            }
        }

        if(newHead.getC().getX() >= playingField.getXsize() || newHead.getC().getY() >= playingField.getYsize() || newHead.getC().getX() < 0 || newHead.getC().getY() < 0){
            System.exit(0);
        }

        frame.repaint(10);
    }


}
