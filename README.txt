This is my version of 3 card poker for the second phase of the Mark43 hiring process

In order to run this properly both Player.java and Poker.java classes must be compiled prior to running the tests.
The Poker class is meant to be run as the Player class is supplementary
When I ran the tests provided I used 'python3 ./run_tests "java Poker"' in the terminal after compiling both classes and both test cases passed.

Limitations of my project include if the input format is not exactly as was provided, my code will notice when there is a card duplicated or if the card doesn't exist and will return "Either duplicate, nonexisting card or a misinput".
Therefore in most cases a wrong input will instead return this error message.
My program also does not check for the number of players to be greater than 0 and less than 8 as it was stated that the test cases would be between these numbers.

Last but not least would be my Design decisions and kind of outline of my program
Firstly I chose to use a secondary class as I made a constructor object originaly called Poker(), and it was obnoxious to read when it truthfully represented players.
I then used those objects in order to keep track of their player number, values of their cards, and whether they had a flush, striaght, three of a kind, or a pair.
when there was a pair did I also kept track of what the value of the pair was as well as the value of the extra card. By doing this it saved me from the nightmare of if else statements I was trying to concoct without storing the values.
I made two global hashmaps, one to have a way to the values of 2-A into integers, and the other was to keep track of whether the cards existed or if they had been duplicated.
I used the Scanner to take in each input as a separate string and worked with that.
Each hand is tested to see if it has a straight, flush, pair, threeofakind, and then it checks to see if it is in the lead. Any hand that has lost on the flop is useless and so we discard it.
In case of tie multiple hands must be called, therefore an Arraylist holds the leaders and it is cleared if the latest hand has taken the lead. in case of a tie the new hand is added into the Arraylist. Since the hands are at a stalemate if a new hand becomes the leader the entire Arraylistis cleared, else in case of more stalemates Arraylist simply expands.
after going through all of the hands a method is called in order to print the ids of the players who won the round.




