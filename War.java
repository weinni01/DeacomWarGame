
import java.util.HashMap;
import java.util.Scanner;

public class War extends Deck{
    private Deck warDeck;
    private static Deck player1Deck, player2Deck;
    private static final int GAME_TURN_TOTAL = 52;

    /**
     * Constructor for the War Game class
     * @param currentDeck deck to be used in the war game
     */
    public War(Deck currentDeck){
        warDeck = currentDeck;
        warDeck.shuffle();


        HashMap<Integer, Deck> splitDeck = warDeck.splitDeck();

        player1Deck = splitDeck.get(1);
        player2Deck = splitDeck.get(2);
    }

    /**
     * Run method for the game of War
     */
    public static void main(String[] args){
        Deck warD = new Deck();
        War warGame = new War(warD);
        Scanner scan = new Scanner(System.in);


        System.out.println(player1Deck.toString());
        System.out.println(player2Deck.toString());


        System.out.println("Double tap enter to begin the game!");
        scan.nextLine();

        while(!player1Deck.isEmpty() && !player2Deck.isEmpty()) {
            System.out.println("Press enter for next battle!");
            scan.nextLine();
            Card playerOneCurrCard = (Card) player1Deck.remove(0);
            Card playerTwoCurrCard = (Card) player2Deck.remove(0);

            //display card information
            System.out.println("P1 Card = " + playerOneCurrCard.getSuit() + " : " + playerOneCurrCard.getRank());
            System.out.println("P2 Card = " + playerTwoCurrCard.getSuit() + " : " + playerTwoCurrCard.getRank());

            // player one win case
            if (playerOneCurrCard.getValue() > playerTwoCurrCard.getValue()) {
                System.out.println("Player 1 wins the battle!");
                player1Deck.add(playerTwoCurrCard);
            }
            // player 2 win case
            else if (playerOneCurrCard.getValue() < playerTwoCurrCard.getValue()) {
                System.out.println("Player 2 wins the battle!");
                player2Deck.add(playerOneCurrCard);
            }
            //draw case
            else {

                System.out.println("Draw! Time for a tiebreaker!");

                //As long as both players have a sufficient amount of cards for a tiebreaker
                if (player1Deck.size() >= 4 && player2Deck.size() >= 4) {

                    int tieResult = player1Deck.tieBreaker(player2Deck);

                    if (tieResult == 1) {
                        System.out.println("Player 1 wins the battle!");
                    }
                    if (tieResult == 2) {
                        System.out.println("Player 2 wins the battle!");
                    }

                }else {
                    if (player1Deck.size() < 4) {
                        System.out.println("Player 2 wins");
                    } else if (player2Deck.size() < 4) {
                        System.out.println("Player 1 wins");
                    }
                }
            }
        }

        if(player1Deck.isEmpty()){
            System.out.println("Player 2 wins: P1 deck has been depleted");
        }
        else if(player2Deck.isEmpty()){
            System.out.println("Player 1 wins: P2 deck has been depleted");
        }
    }
}
