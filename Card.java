public class Card {
    private String rank;
    private String suit;
    private String[] ranks =  {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};
    private String[] suits = {"clubs", "diamonds", "hearts", "spades"};

    public Card(){
        rank = "2";
        suit = null;
    }
    public Card(String suit, String val){
        rank = val;
        this.suit = suit;
    }

    /**
     * Return the walue of the card rank based on where it is placed in the rank array
     * Rank array has been setup to have weakest rank value first and strongest rank value last
     *
     * @return game value of the card rank
     */
    public int getValue(){
        int cardValue = 0;

        for(int i = 0; i < ranks.length; i++){
            if(this.rank == ranks[i]){
                cardValue = i;
            }
        }

        // Add one to returned cardValue to ensure if 2 is top card it has a value other than 0
        return cardValue + 1;
    }

    /**
     * Return suit of this card
     * @return suit of this card
     */
    public String getSuit(){
        return this.suit;
    }

    /**
     * Return rank of this card
     * @return rank of this card
     */
    public String getRank(){
        return this.rank;
    }

    /**
     * Setter for rank variable
     * @param val new rank for card
     */
    public void setRank(String val){
        rank = val;
    }

    /**
     * Setter for suit variable
     * @param suitName new suit to be applied to card
     */
    public void setSuit(String suitName){
        suit = suitName;
    }

    /**
     * ToString used to easily print information about the cards during the game of war
     * @return toString of this card
     */
    public String toString(){
        String str = this.suit + ":" + this.rank;
        return str;
    }


}
