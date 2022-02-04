import java.util.*;



public class Poker{

    public static HashMap<Character,Integer> values = new HashMap<Character,Integer>();

/*  everything in this comment i made a separate class for to make it easier to follow plus id rather the constructor be called player
    private int id;
    private int high;
    private int mid;
    private int low;
    private boolean straight;
    private boolean flush;
    private boolean threeOfAKind;
    private boolean pair;
    //private boolean lead; // might be unnecessary
    //for pairs i might want to keep track of actual values of cards to make it easier to compare
     
    public Poker(int id, int high, boolean straight, boolean flush, boolean threeofAKind, boolean pair){ //trying to make constructor would prefer to call it player but thats life
        this.id = id;
        this.high = high;
        this.mid = mid; // if need 
        this.low = low; // if need
        this.straight= straight;
        this.flush = flush;
        this.threeOfAKind = threeofAKind;
        this.pair = pair;
       // this.lead = lead; // dont think i need this
    }
    
    
    public static int high(int a, int b, int c){ // dont think i need it
        if (a > b && a > c){ // a is largest
            return a;
        } else if (b > a && b> c){ // b is largest
            return b;
        }
            // if two are equal to each other must make the work for that
        return c;
    }

    public static int mid(int a, int b){
       if (a>b){ //if a is greater
           return a;
       } else { //if b is greater or equal to
        return b;}
    }
    */



    public static boolean straight(int a, int b, int c){ // checks if straight
        int[] arr = {a,b,c};
        Arrays.sort(arr);

            if(a++ != b ){ //if a is not one less
                return false;
            }     
            if(b++ != c ){ // if b is not one less
                return false;
            }
        return true;
    }

    public static boolean flush(char a, char b, char c){ // checks if flush
        if (a==b && b ==c){
            return true;
        }
    return false;
    }

    public static boolean threeOfAKind(int a, int b, int c){
        if (a==b && b ==c){
            return true;
        }
        return false;
    }


    public static boolean pair(int a, int b, int c){  // must find a way to make the pair have a numerical value so it can compare against other pairs
        if (a == b && c!=b){ 
            return true;
        }
        else if (c == b && a!=b){
            return true;
        }
        else if (a == c && c!=b){ 
            return true;
        }

        return false;
    }

    /*
    Have to figure out what to do in terms of ties
    I could have a linked list with all the leaders or something maybe an arraylist and clear it if there is a clearcut winner
    but even then im unsure will have to think about that
    this boolean thing could be a way
    */

    public static boolean lead(Player hand, Player lead){ //will return arraylist of leaders instead
        if (hand.flush && hand.straight){ // straight flush logic
            if (!lead.flush || !lead.straight){ //old leader cannot compete
                //if i am going to make a lead variable will have to change it for the old leader
               return true; 
            } 
            if (hand.high>lead.high){ // higher straight wins
                //if i am going to make a lead variable will have to change it for the old leader
                return true;
            } else if (hand.high<lead.high){ // higher straight wins
                return false;
            } else { // straight flush tie
                //tie must add new leader into arraylist
                return true;
            }
            //return true;
        }//end straight flush if

        if (hand.threeOfAKind){ //threeofakind logic
            if(!lead.threeOfAKind){//old leader cant compete
                //if i am going to make a lead variable will have to change it for the old leader
                return true;
            } 
            if(hand.high>lead.high){ //higher 3 of a kind
                //if i am going to make a lead variable will have to change it for the old leader
                return true;
            } else if (hand.high<lead.high){ //lower 3 of a kind
                return false;
            } else {//if same 3 of a kind but this isnt possible soooo should technically throw an error
                //if tie must add extra leader to arraylist
                return true;
            }
        } //end threeofakind if

        if(hand.straight == true){ //straight logic
            if(!lead.straight){//old leader cant compete
                //if i am going to make a lead variable will have to change it for the old leader
                return true;
            } 
            if(hand.high>lead.high){ //higher straight
                //if i am going to make a lead variable will have to change it for the old leader
                return true;
            } else if (hand.high<lead.high){ //lower straight
                return false;
            } else {//if same straight
                //if tie must add extra leader to arraylist
                return true;
            }
        }//end straight if

        if(hand.flush == true){ // need more logic for cards tbh
            if(!lead.flush){//old leader cant compete
                //if i am going to make a lead variable will have to change it for the old leader
                return true;
            } 
            if(hand.high>lead.high){ //higher flush
                //if i am going to make a lead variable will have to change it for the old leader
                return true;
            } else if (hand.high<lead.high){ //lower flush
                return false;
            } else {//if same flush
                //if tie must add extra leader to arraylist
                return true;
            }
        }//end flush if

        if(hand.pair == true){ //need more logic for pairs tbh what happens if pairs are equal last car becomes high card
            if(!lead.pair){//old leader cant compete
                //if i am going to make a lead variable will have to change it for the old leader
                return true;
            } 
            if(hand.high>lead.high){ //higher pair
                //if i am going to make a lead variable will have to change it for the old leader
                return true;
            } else if (hand.high<lead.high){ //lower pair
                return false;
            } else {//if same pair
                //if tie must add extra leader to arraylist
                return true;
            }
        }//end pair if

        if (hand.high > lead.high){
            //if i am going to make a lead variable will have to change it for the old leader
            return true;
        } else if (hand.high == lead.high){//tie
            //add leader to arraylist
            return true;
        }

        return false;
    }

    public static void print(ArrayList<Player> leaders){ // printing id method
        System.out.print("\n");
        for (int i = 0;i<leaders.size();i++){
            System.out.print(leaders.get(0).id);
            if(i!= leaders.size()-1){
                System.out.print(" ");
            }
        }
        //System.out.print("\n");
    }




    public static void main(String agrs[]){ // must use highcard
        //set up my hashmap which defines the character before 
        Poker.values.put('2',2);
        Poker.values.put('3',3);
        Poker.values.put('4',4);
        Poker.values.put('5',5);
        Poker.values.put('6',6);
        Poker.values.put('7',7);
        Poker.values.put('8',8);
        Poker.values.put('9',9);
        Poker.values.put('T',10);
        Poker.values.put('J',11);
        Poker.values.put('Q',12);
        Poker.values.put('K',13);
        Poker.values.put('A',14);

        Scanner myObj = new Scanner(System.in);
        int numofplayers = myObj.nextInt(); //reads number of players
        ArrayList<Player> leaders = new ArrayList<Player>();//holds leaders

        for (int i = 0; i<numofplayers; i++){
            Player currentplayer = new Player(); //build current player values

            String temp = myObj.nextLine();//takes in each line all together
            //manipulate strings to parse playerID, and then each of their hands

            currentplayer.id = Character.getNumericValue(temp.charAt(0)); // place player id
            
            //now find the 3 values of cards
            int a = Poker.values.get(temp.charAt(2));
            int b = Poker.values.get(temp.charAt(5));
            int c = Poker.values.get(temp.charAt(8));
            int cardvalues[] = new int[3];
            cardvalues[0] = a;
            cardvalues[1] = b;
            cardvalues[2] = c;
            Arrays.sort(cardvalues); //sort array for me
            currentplayer.low = cardvalues[0];
            currentplayer.mid = cardvalues[1];
            currentplayer.high = cardvalues[2];


            //assign temporary suit values
            char d = temp.charAt(3);
            char e = temp.charAt(6);
            char f = temp.charAt(9);

            //find out their hand
            currentplayer.flush = flush(d,e,f);
            currentplayer.straight = straight(a,b,c);
            currentplayer.threeOfAKind = threeOfAKind(a,b,c);
            currentplayer.pair = pair(a,b,c);




            if (i==0){ //if first item
                leaders.add(currentplayer);
                continue;
            }
            
            boolean timebeing = lead(currentplayer, leaders.get(0));
        }//end big for loop
        myObj.close();
        
        print(leaders); // print to stdout
        
    } //end void
}