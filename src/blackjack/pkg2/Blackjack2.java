package blackjack.pkg2;

import java.util.Scanner;
import java.util.Random;

public class Blackjack2 {

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        System.out.println("Welcome to Blackjack!");

        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double playerMoney = 1000.00;

        Scanner userInput = new Scanner(System.in);
        boolean endRound = false;
        boolean playerBust = false;
        boolean dealerBust = false;


        while(playerMoney>0){
            System.out.println("You have RM" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if(playerBet > playerMoney){
                System.out.println("You have less money than that.");
                break;
            }


            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while(true){
                System.out.println("Your hand: ");
                System.out.print(playerDeck.toString());
                System.out.println("");
                System.out.println("Your hand is valued at: " + playerDeck.cardsValue());

                System.out.println("Dealer's hand: " + dealerDeck.getCard(0).toString() + " and [HIDDEN]") ;

                System.out.println("1-Hit or 2-Stand");
                int response = userInput.nextInt();

                if(response == 1){
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw a " + playerDeck.getCard(playerDeck.deckSize()-1).toString());

                    if(playerDeck.cardsValue() > 21){
                        System.out.println("Player: Bust!");
                        System.out.println("Player: LOSE!");
                        System.out.println("Dealer: WIN!");
                        System.out.println("Currently valued at: " + playerDeck.cardsValue());
                        System.out.println("You lost RM" + playerBet);
                        playerMoney -= playerBet;
                        endRound = true;
                        playerBust = true;
                        break;
                    }
                }
                if(response == 2){
                    endRound = true;
                    break;
                }
            }

            //if(playerBust == true){break;}

            System.out.println("Dealer cards: " + dealerDeck.toString());

            while( dealerDeck.cardsValue() <= 17 && playerBust == false){
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());

                if(dealerDeck.cardsValue() > 21) {
                    System.out.println("Dealer: Bust!");
                    System.out.println("Player: WIN!");
                    System.out.println("Dealer: LOSE!");
                    System.out.println("You win RM" + playerBet);
                    playerMoney += playerBet;
                    endRound = true;
                    dealerBust = true;
                    break;
                }
            }


            System.out.println("Dealer's hand is valued at: " + dealerDeck.cardsValue());

            if(playerBust == true){playerMoney -= playerBet;}

            if( dealerDeck.cardsValue() > playerDeck.cardsValue() && playerBust == false && dealerBust == false){
                System.out.println("Player: LOSE!");
                System.out.println("Dealer: WIN!");
                System.out.println("You lost RM" + playerBet);
                playerMoney += playerBet;
                endRound = true;
            }

            else if(playerDeck.cardsValue() == dealerDeck.cardsValue()){
                System.out.println("Push");
                endRound = true;
            }

            else if (playerDeck.cardsValue() > dealerDeck.cardsValue() && playerBust == false ) {
                System.out.println("Player: WIN!");
                System.out.println("Dealer: LOSE!");
                System.out.println("You win RM" + playerBet);
                playerMoney += playerBet;
                endRound = true;
            }

            // else if( (endRound == false) && (playerBust == false) && (dealerBust == false)){
            //     System.out.println("You lose");
            //     playerMoney -= playerBet;
            //     endRound = true;
            // }

            //if( playerDeck.cardsValue()  >  dealerDeck.cardsValue()) {playerMoney += playerBet;}

            System.out.println("Player = " + playerDeck.cardsValue());
            System.out.println("Dealer = " + dealerDeck.cardsValue());

            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);

            System.out.println("End of round!");

    }


        System.out.println("Game Over!");


    }

}
