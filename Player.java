public class Player{

    public int id;
    public int high;
    public int mid;
    public int low;
    public boolean straight;
    public boolean flush;
    public boolean threeOfAKind;
    public boolean pair;

    public Player(){
        this.id = 0;
        this.high = 0;
        this.mid = 0; // if need 
        this.low = 0; // if needs
        this.straight= false;
        this.flush = false;
        this.threeOfAKind = false;
        this.pair = false;
    }

    public Player(int id, int high, int mid, int low, boolean straight, boolean flush, boolean threeofAKind, boolean pair){ //trying to make constructor
        this.id = id;
        this.high = high;
        this.mid = mid; // if need 
        this.low = low; // if need
        this.straight= straight;
        this.flush = flush;
        this.threeOfAKind = threeofAKind;
        this.pair = pair;
    }

}