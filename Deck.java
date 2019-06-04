
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;



public class Deck extends ArrayList {
    private int deckLength = 52;
    private ArrayList<Card> deck;
    private String[] ranks =  {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};
    private String[] suits = {"clubs", "diamonds", "hearts", "spades"};

    public Deck(){
        deck = new ArrayList<Card>();

        // Populate the deck of cards. Run through suits and add each rank to that suit
        for(int i = 0; i < suits.length; i++){
            for(int j = 0; j < ranks.length; j++){
                Card newCard = new Card(suits[i], ranks[j]);
                deck.add(newCard);
            }
        }
    }

    public Deck(int initLength){
        deck = new ArrayList<Card>(initLength);
    }

    public ArrayList<Card> getDeck(){
        return this.deck;
    }

    /**
     * Shuffle the deck to allow for random player hands
     */
    public void shuffle(){

        for(int i = deck.size() - 1; i >= 0; i--){
            //Choose a random card to swap
            int rand = (int) (Math.random()* i);

            //swap cards
            Card temp = deck.get(i);
            deck.set(i, deck.get(rand));
            deck.set(rand, temp);

        }
    }

    /**
     *  Splits the deck of cards to be used by the players
     * @return An ArrayList of two decks with length 26
     */
    public HashMap<Integer, Deck> splitDeck(){

        HashMap<Integer, Deck> spDeck = new HashMap<Integer, Deck>();
        Deck deckHalf1 = new Deck(deckLength);
        Deck deckHalf2 = new Deck(deckLength);

        for(int i = 0; i < this.deck.size(); i++){
            if(i < this.deck.size()/ 2){
                deckHalf1.add(this.deck.get(i));
            }
            else{
                deckHalf2.add(this.deck.get(i));
            }
        }

        spDeck.put(1, deckHalf1);
        spDeck.put(2, deckHalf2);
        return spDeck;
    }

    /**
     * Method used to calculate which player wins a draw
     * @param p2 other players deck
     * @return 1 if player 1 wins the draw, 2 if player 2 wins the draw,
     *         3 if both players have same final card (tie again)
     */
    public int tieBreaker( Deck p2){
        int breakerScore = -1;
        int initIndex = 3;
        Card p1TieCard = (Card) this.get(initIndex);
        Card p2TieCard = (Card) p2.get(initIndex);

        Collection<Card> p1TieBreakerSet = new Deck(4);
        Collection<Card> p2TieBreakerSet = new Deck(4);

        for(int i  = 0; i < 4; i++){
            p1TieBreakerSet.add((Card) this.get(i));
            p2TieBreakerSet.add((Card) p2.get(i));
        }

        System.out.println("Player 1 tie deck = " + p1TieBreakerSet);
        System.out.println("Player 1 tie card = " + p1TieCard);

        System.out.println("Player 2 tie deck = " + p2TieBreakerSet);
        System.out.println("Player 2 tie card = " + p2TieCard);

        this.removeAll(p1TieBreakerSet);
        p2.removeAll(p2TieBreakerSet);


        if (p1TieCard.getValue() > p2TieCard.getValue()) {
            this.addAll(p2TieBreakerSet);
            breakerScore = 1;
            return breakerScore;
        } else if (p1TieCard.getValue() < p2TieCard.getValue()) {
            p2.addAll(p1TieBreakerSet);
            breakerScore = 2;
            return breakerScore;

        }
        // back to back draws case
        else{
            breakerScore = 0;
            while(breakerScore == 0) {
                int oldIndex = initIndex;
                int newInitIndex = initIndex + 3;

                if (this.size() > newInitIndex && p2.size() > newInitIndex) {
                    System.out.println("Still a draw! Time for another tiebreaker!!!");
                    p1TieCard = (Card) this.get(newInitIndex);
                    p2TieCard = (Card) p2.get(newInitIndex);

                    for (int i = oldIndex; i < oldIndex + 4; i++) {
                        p1TieBreakerSet.add((Card) this.get(i));
                        p2TieBreakerSet.add((Card) p2.get(i));
                    }

                    System.out.println("Player 1 tie deck = " + p1TieBreakerSet);
                    System.out.println("Player 1 tie card = " + p1TieCard);

                    System.out.println("Player 2 tie deck = " + p2TieBreakerSet);
                    System.out.println("Player 2 tie card = " + p2TieCard);

                    this.removeAll(p1TieBreakerSet);
                    p2.removeAll(p2TieBreakerSet);

                    if (p1TieCard.getValue() > p2TieCard.getValue()) {
                        this.addAll(p2TieBreakerSet);
                        breakerScore = 1;
                    } else if (p1TieCard.getValue() < p2TieCard.getValue()) {
                        p2.addAll(p1TieBreakerSet);
                        breakerScore = 2;

                    }
                }
                else{
                    if(this.size() < 4){
                        System.out.println("P2 wins, P1 did not have enough cards to fight the draw!");
                        break;
                    }
                    else if(p2.size() < 4){
                        System.out.println("P1 wins, P2 did not have enough cards to fight the draw!");
                        break;
                    }
                }

            }
            return breakerScore;
        }
    }

    //Testing and Debugging
    public static void main(String[] args){
        Deck deck = new Deck();
        for(int i = 0; i < deck.size(); i++){
            System.out.println(deck.deck.get(i).toString());

        }

        System.out.println(deck.deck.size());
        deck.shuffle();

        for(int i = 0; i < deck.deck.size(); i++){
            System.out.println(deck.deck.get(i).toString());

        }

        System.out.println("Splitting deck...");
        HashMap<Integer, Deck> splitDeck = new HashMap<Integer, Deck>();
        splitDeck = deck.splitDeck();

        System.out.println(splitDeck.toString());
        System.out.println(splitDeck.get(1).size());
        System.out.println(splitDeck.get(2).size());

        System.out.println(splitDeck.get(1).get(0));
        System.out.println(splitDeck.get(1).get(0) + " " + splitDeck.get(2));
    }

}
