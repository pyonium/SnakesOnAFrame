import lombok.*;
import java.util.HashMap;

public @Data class PlayingField {

    final private int xsize;
    final private int ysize;
    private HashMap<Coordinate, Tile> tiles;

    public PlayingField(int x, int y){
        xsize = x;
        ysize = y;
        tiles = new HashMap<>();
        Coordinate c;
        for(int i = 0; i < xsize; i++){
            for(int j = 0; j < ysize; j++){
                c = new Coordinate(i,j);
                tiles.put(c, new Tile(c));
            }
        }
        Body thirdBody = new Body(new Coordinate(0,2), null);
        Body secondBody = new Body(new Coordinate(1, 2), thirdBody);
        Body firstBody = new Body(new Coordinate(2, 2), secondBody);
        Tile head = new Head(new Coordinate(3,2), firstBody);
        Food first = new Food(new Coordinate(7,2));
        this.set(first);
        this.set(thirdBody);
        this.set(secondBody);
        this.set(firstBody);
        this.set(head);
    }

    public Tile get(int x, int y){
        return tiles.get(new Coordinate(x, y));
    }

    public void set(Tile t){
        if(t.getClass().getName().equals("Head")){
            if(tiles.get(t.getC()).getClass().getName().equals("Body")){
                System.exit(0);
            }
        }
        tiles.put(t.getC(), t);
    }

    public Tile get(Coordinate c){
        return tiles.get(c);
    }

    public void remove(Coordinate c){
        tiles.remove(c);
    }

    public Head getHead(){
        return tiles.values().stream().
                filter(tile -> tile instanceof Head)
                .map(tile -> (Head)tile)
                .findFirst().get();
    }

}
