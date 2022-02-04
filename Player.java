public class Player{

    public int id;
    public int high;
    public int mid;
    public int low;
    public int pairvalue;
    public int pairextra;
    public boolean straight;
    public boolean flush;
    public boolean threeOfAKind;
    public boolean pair;
    

    public Player(){
        this.id = 0;
        this.high = 0;
        this.mid = 0; // if need 
        this.low = 0; // if needs
        this.pairvalue = 0;
        this.pairextra = 0;
        this.straight= false;
        this.flush = false;
        this.threeOfAKind = false;
        this.pair = false;
        this.pairvalue = 0;
        this.pairextra = 0;

    }

    public Player(int id, int high, int mid, int low, boolean straight, boolean flush, boolean threeofAKind, boolean pair, int pairvalue, int pairextra){ //trying to make constructor
        this.id = id;
        this.high = high;
        this.mid = mid; // if need 
        this.low = low; // if need
        this.straight= straight;
        this.flush = flush;
        this.threeOfAKind = threeofAKind;
        this.pair = pair;
        this.pairvalue = pairvalue;
        this.pairextra = pairextra;
    }

}