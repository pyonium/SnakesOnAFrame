import lombok.Data;

public @Data class Body extends Tile {

    Body prev;

    public Body(Coordinate c, Body _prev){
        super(c);
        prev = _prev;
    }

}
