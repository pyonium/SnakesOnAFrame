import lombok.Data;

public @Data class Head extends Tile {

    Body prev;

    public Head(Coordinate c, Body _prev){
        super(c);
        prev = _prev;
    }

    public void printList(){
        Body previous = prev;
        while(previous != null){
            System.out.println(previous.toString());
            previous = previous.getPrev();
        }
    }

}
