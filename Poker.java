import java.util.*;



public class Poker{

    public static HashMap<Character,Integer> values = new HashMap<Character,Integer>(); //quick way to convert the values of stdin into integers for comparison
    public static HashMap<String,Boolean> deck = new HashMap<String,Boolean>(); //keeps track of deck

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
    }//end straight

    public static boolean flush(char a, char b, char c){ // checks if flush
        if (a==b && b ==c){
            return true;
        }
    return false;
    }//end flush

    public static boolean threeOfAKind(int a, int b, int c){ //checks if a 3ofakind
        if (a==b && b ==c){
            return true;
        }
        return false;
    }//end threeOfAKind


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
    }//end pair


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
    }//end lead

    public static void print(ArrayList<Player> leaders){ // printing id method
        
        for (int i = 0;i<leaders.size();i++){
            System.out.print(leaders.get(0).id);
            if(i!= leaders.size()-1){
                System.out.print(" ");
            }
        }
        System.out.print("\n");
    } //endprint

    public static void createMaps(){
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


        //make my deck of cards to keep track if they have appeared
        //hearts
        Poker.deck.put("Ah", false);
        Poker.deck.put("Kh", false);
        Poker.deck.put("Qh", false);
        Poker.deck.put("Jh", false);
        Poker.deck.put("Th", false);
        Poker.deck.put("9h", false);
        Poker.deck.put("8h", false);
        Poker.deck.put("7h", false);
        Poker.deck.put("6h", false);
        Poker.deck.put("5h", false);
        Poker.deck.put("4h", false);
        Poker.deck.put("3h", false);
        Poker.deck.put("2h", false);

        //diamonds
        Poker.deck.put("Ad", false);
        Poker.deck.put("Kd", false);
        Poker.deck.put("Qd", false);
        Poker.deck.put("Jd", false);
        Poker.deck.put("Td", false);
        Poker.deck.put("9d", false);
        Poker.deck.put("8d", false);
        Poker.deck.put("7d", false);
        Poker.deck.put("6d", false);
        Poker.deck.put("5d", false);
        Poker.deck.put("4d", false);
        Poker.deck.put("3d", false);
        Poker.deck.put("2d", false);

        //clubs
        Poker.deck.put("Ac", false);
        Poker.deck.put("Kc", false);
        Poker.deck.put("Qc", false);
        Poker.deck.put("Jc", false);
        Poker.deck.put("Tc", false);
        Poker.deck.put("9c", false);
        Poker.deck.put("8c", false);
        Poker.deck.put("7c", false);
        Poker.deck.put("6c", false);
        Poker.deck.put("5c", false);
        Poker.deck.put("4c", false);
        Poker.deck.put("3c", false);
        Poker.deck.put("2c", false);

        //spades
        Poker.deck.put("As", false);
        Poker.deck.put("Ks", false);
        Poker.deck.put("Qs", false);
        Poker.deck.put("Js", false);
        Poker.deck.put("Ts", false);
        Poker.deck.put("9s", false);
        Poker.deck.put("8s", false);
        Poker.deck.put("7s", false);
        Poker.deck.put("6s", false);
        Poker.deck.put("5s", false);
        Poker.deck.put("4s", false);
        Poker.deck.put("3s", false);
        Poker.deck.put("2s", false);

    } //end createMaps

    public static void checkCards(String a, String b, String c) throws Exception{
        if(!Poker.deck.containsKey(a)){
            throw new Exception();
        }
        if (Poker.deck.get(a)){
            throw new Exception();
        } else{
            Poker.deck.put(a,true);
        }


        if(!Poker.deck.containsKey(b)){
            throw new Exception();
        }
        if (Poker.deck.get(b)){
            throw new Exception();
        } else{
            Poker.deck.put(b,true);
        }



        if(!Poker.deck.containsKey(c)){
            throw new Exception();
        }
        if (Poker.deck.get(c)){
            throw new Exception();
        } else{
            Poker.deck.put(c,true);
        }
    } //end checkCards




    public static void main(String agrs[]){ // must use highcard
        

        createMaps(); // creates the deck and the values

        Scanner myObj = new Scanner(System.in);

        String k = myObj.nextLine();
        int numofplayers = Integer.parseInt(k);
        ArrayList<Player> leaders = new ArrayList<Player>();//holds leaders

        for (int i = 0; i<numofplayers; i++){
            Player currentplayer = new Player(); //build current player values

            String temp = myObj.nextLine();//takes in each line all together
            //check if cards exists as well as if they are duplicated
            String card1 = temp.substring(2,4);
            String card2 = temp.substring(5,7);
            String card3 = temp.substring(8,10);

            try{
            checkCards(card1, card2, card3);
            } catch (Exception e){
                System.out.print("Either duplicate, nonexisting card or a misinput");
                break;
            }

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
        
    } //end main
}