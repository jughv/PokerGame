import java.util.*;



public class Poker{

    public static HashMap<Character,Integer> values = new HashMap<Character,Integer>();

/*  everything in this comment i made a separate class, id rather the constructor be called player
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

    public static boolean threeOfAKind(int a, int b, int c){ //checks if a 3ofakind
        if (a==b && b ==c){
            return true;
        }
        return false;
    }


    public static boolean pair(int a, int b, int c, Player player){  // checks for pair and whether the pair is high card or low card
        if (a == b && c!=b){ 
            player.pairvalue = a;
            player.pairextra = c;
            return true;
        }
        else if (c == b && a!=b){
            player.pairvalue = c;
            player.pairextra = a;
            return true;
        }
        else if (a == c && c!=b){ 
            player.pairvalue = a;
            player.pairextra = b;
            return true;
        }

        return false;
    }


    public static void lead(Player hand, Player lead, ArrayList<Player> leaders){ //method to determine who is leading rn
        if (hand.flush && hand.straight){ // straight flush logic
            if (!lead.flush || !lead.straight){ //old leader cannot compete
                //have to make a new leader
                leaders.clear();
                leaders.add(hand);
                return; 
            } 
            if (hand.high>lead.high){ // higher straight wins new hand
                //have to make a new leader
                leaders.clear();
                leaders.add(hand);
                return;
            } else if (hand.high<lead.high){ // lower straight wins leading hand
                return;
            } else { // straight flush tie
                //tie must add new leader into arraylist
                leaders.add(hand);
                return;
            }
        }//end straight flush if

        if (hand.threeOfAKind){ //threeofakind logic
            if(!lead.threeOfAKind){//old leader cant compete
                //have to make a new leader
                leaders.clear();
                leaders.add(hand); 
                return;
            } 
            if(hand.high>lead.high){ //higher 3 of a kind
                //have to make a new leader
                leaders.clear();
                leaders.add(hand);
                return;
            } else if (hand.high<lead.high){ //lower 3 of a kind
                return;
            } else {//if same 3 of a kind but this isnt possible soooo should technically throw an error
                //if tie must add extra leader to arraylist
                leaders.add(hand);
                return;
            }
        } //end threeofakind if

        if(hand.straight == true){ //straight logic
            if(!lead.straight){//old leader cant compete
                //have to make a new leader
                leaders.clear();
                leaders.add(hand);
                return;
            } 
            if(hand.high>lead.high){ //higher straight
                //have to make a new leader
                leaders.clear();
                leaders.add(hand);
                return;
            } else if (hand.high<lead.high){ //lower straight
                return;
            } else {//if same straight
                //if tie must add extra leader to arraylist
                leaders.add(hand);
                return;
            }
        }//end straight if

        if(hand.flush == true){ // flush logic
            if(!lead.flush){//old leader cant compete
                //have to make a new leader
                leaders.clear();
                leaders.add(hand);
                return;
            } 
            if(hand.high>lead.high){ //higher flush
                //have to make a new leader
                leaders.clear();
                leaders.add(hand);
                return;
            } else if (hand.high<lead.high){ //lower flush
                return;
            } else {//if same high card move to middle
                if(hand.mid>lead.mid){ //higher middle card
                    //have to make a new leader
                    leaders.clear();
                    leaders.add(hand);
                    return;
                } else if (hand.mid<lead.mid){ //lower middle card
                    return;
                } else { //if same middle card move to low
                    if(hand.low>lead.low){ //higher low card
                        //have to make a new leader
                        leaders.clear();
                        leaders.add(hand);
                        return;
                    } else if (hand.low<lead.low){ //lower low card
                        return;
                    } else { //its a tie
                        //if tie must add extra leader to arraylist
                        leaders.add(hand);
                        return;
                    }
                }//end mid tie else
            }//end high tie else
        }//end flush if

        if(hand.pair == true){ //pair logic
            if(!lead.pair){//old leader cant compete
                //have to make a new leader
                leaders.clear();
                leaders.add(hand);
                return;
            } 

            if(hand.pairvalue>lead.pairvalue){//if pair is greater
                //have to make a new leader
                leaders.clear();
                leaders.add(hand);
                return;
            } else if(hand.pairvalue<lead.pairvalue){//if pair is lower
                return;
            } else{ //if pair is equal
                if(hand.pairextra>lead.pairextra){ //if extra card is greater
                     //have to make a new leader
                leaders.clear();
                leaders.add(hand);
                } else if (hand.pairextra<lead.pairextra){ //if extra card is lower
                    return;
                } else { //tie hands
                    leaders.add(hand);
                    return;
                }
            } //end pair if
           
            /*
            if(hand.mid>lead.mid){ //higher pair middle value will always be part of the pair
                //have to make a new leader
                leaders.clear();
                leaders.add(hand);
                return;
            } else if (hand.mid<lead.mid){ //lower pair
                return;
            } else {//if same pair


                if (hand.highpair){ //hands higher number was used in the pair must use lower number for checks
                    if (lead.highpair){ // if both high numbers have been used for pair only lower numbers remain
                        if (hand.low>lead.low){ //hand has a higher extra card
                            //make new leader
                            leaders.clear();
                            leaders.add(hand);
                        } else if (hand.low<lead.low){ //hand has a lower extra card
                            return;
                        } else { // extra cards are equal
                            //if tie must add extra leader to arraylist
                            leaders.add(hand);
                            return;
                        }
                    } else {// end if for both low numbers now must check low for hand and high for lead
                        if (hand.low>lead.high){ //hand has a higher extra card
                            //make new leader
                            leaders.clear();
                            leaders.add(hand);
                        } else if (hand.low<lead.high){ //hand has a lower extra card
                            return;
                        } else { // extra cards are equal
                            //if tie must add extra leader to arraylist
                            leaders.add(hand);
                            return;
                        }
                    }//end else for low hand and high lead

                } else { //if !hand.highpair now must use the highest number in the hand
                    if (lead.highpair){ //highest number in hand versus lowest number for the leader
                        if (hand.high>lead.low){ //hand has a higher extra card
                            //make new leader
                            leaders.clear();
                            leaders.add(hand);
                        } else if (hand.high<lead.low){ //hand has a lower extra card
                            return;
                        } else { // extra cards are equal
                            //if tie must add extra leader to arraylist
                            leaders.add(hand);
                            return;
                        }
                    } else {// end if for low hand high lead, now checking both highest cards 
                        if (hand.high>lead.high){ //hand has a higher extra card
                            //make new leader
                            leaders.clear();
                            leaders.add(hand);
                        } else if (hand.high<lead.high){ //hand has a lower extra card
                            return;
                        } else { // extra cards are equal
                            //if tie must add extra leader to arraylist
                            leaders.add(hand);
                            return;
                        }
                    } //end else of both highest cards
                } //end !highpair
            } //end else of same pair
            */

        }//end pair if

        if (hand.high > lead.high){ //high card comparison
            leaders.clear();
            leaders.add(hand);
            return;
        } else if (hand.high == lead.high){//tie
            //add leader to arraylist
            leaders.add(hand);
            return;
        } //hand lost :(

        return;
    }

    public static void print(ArrayList<Player> leaders){ // printing id method
        //System.out.print("\n");
        for (int i = 0;i<leaders.size();i++){
            System.out.print(leaders.get(0).id);
            if(i!= leaders.size()-1){
                System.out.print(" ");
            }
        }
        System.out.print("\n");
    }




    public static void main(String agrs[]){ // must use highcard
        //set up my hashmap which defines the characters before
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
        String k = myObj.nextLine();
        int numofplayers = Integer.parseInt(k);
       // int numofplayers = myObj.nextInt(); //reads number of players
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
            Arrays.sort(cardvalues); //sort numbers for me

            if(cardvalues[0] ==2 && cardvalues[1]==3 && cardvalues[2] == 14){ //check if its an Ace-3 straight and resorts
                cardvalues[2]= 1;
                Arrays.sort(cardvalues);
            }

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
            currentplayer.pair = pair(a,b,c, currentplayer);




            if (i==0){ //if first item
                leaders.add(currentplayer);
                continue; //move on skip lead method
            }
            
            lead(currentplayer, leaders.get(0), leaders);

        }//end big for loop
        myObj.close();
        
        print(leaders); // print to stdout
        
    } //end void
}