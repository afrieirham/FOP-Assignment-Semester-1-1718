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
        int endRound = 2;
        int playerBust = 2;
        int dealerBust = 2;


        while(playerMoney>0){
            System.out.printf("You have RM%.2f, how much would you like to bet?\n", playerMoney);
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
                        System.out.printf("You lost RM%.2f\n", playerBet);
                        playerMoney -= playerBet;
                        endRound = 1;
                        playerBust = 1;
                        break;
                    }
                    endRound = 2;
                    playerBust = 2;
                }
                if(response == 2){
                    endRound = 2;
                    playerBust = 2;
                    break;
                }
            }

            //if(playerBust == 1){break;}

            System.out.println("Dealer cards: " + dealerDeck.toString());

            while( dealerDeck.cardsValue() <= 17 && playerBust == 2 && endRound == 2){
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());

                if(dealerDeck.cardsValue() > 21) {
                    System.out.println("Dealer: Bust!");
                    System.out.println("Player: WIN!");
                    System.out.println("Dealer: LOSE!");
                    System.out.printf("You win RM%.2f\n", playerBet);
                    playerMoney += playerBet;
                    endRound = 1;
                    dealerBust = 1;
                    break;
                }
            }

            int playerHand = playerDeck.cardsValue();
            int dealerHand = dealerDeck.cardsValue();
            System.out.println("Dealer's hand is valued at: " + dealerDeck.cardsValue());

            //if(playerBust == 1){playerMoney -= playerBet;}

            if( dealerHand > playerHand && playerBust == 2 && dealerBust == 2 && endRound == 2){
                System.out.println("Player: LOSE!");
                System.out.println("Dealer: WIN!");
                System.out.printf("You lost RM%.2f\n", playerBet);
                playerMoney -= playerBet;
                endRound = 1;
            }

            else if(playerHand == dealerHand && playerBust == 2 && dealerBust == 2 && endRound == 2){
                System.out.println("Push");
                endRound = 1;
            }

            else if (playerHand > dealerHand && playerBust == 2 && dealerBust == 2 && endRound == 2 ) {
                System.out.println("Player: WIN!");
                System.out.println("Dealer: LOSE!");
                System.out.printf("You win RM%.2f\n", playerBet);
                playerMoney += playerBet;
                endRound = 1;
            }

            // else if( (endRound == 2) && (playerBust == 2) && (dealerBust == 2)){
            //     System.out.println("You lose");
            //     playerMoney -= playerBet;
            //     endRound = 1;
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
