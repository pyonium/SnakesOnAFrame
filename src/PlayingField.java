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
        Body secondBody = new Body(new Coordinate(1, 2), null);
        Body firstBody = new Body(new Coordinate(2, 2), secondBody);
        Tile head = new Head(new Coordinate(3,2), firstBody);
        this.set(secondBody);
        this.set(firstBody);
        this.set(head);
        System.out.println(getHead().toString());
    }

    public Tile get(int x, int y){
        return tiles.get(new Coordinate(x, y));
    }

    public void set(Tile t){
        tiles.put(t.getC(), t);
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
