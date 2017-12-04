package blackjack.pkg2;

import java.util.Scanner;
import java.util.Random;

public class Blackjack2 {

    public static void main(String[] args) {
        System.out.println("Welcome to Blackjack!");

        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double playerMoney = 1000.00;

        Scanner userInput = new Scanner(System.in);


        while(playerMoney>0){
            System.out.println("You have RM" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if(playerBet > playerMoney){
                System.out.println("You have less money than that.");
                break;
            }

            boolean endRound = false;

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
                        System.out.println("Bust!");
                        System.out.println("Currently valued at: " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                if(response == 2){
                    break;
                }
            }

            System.out.println("Dealer cards: " + dealerDeck.toString());

            if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false){
                System.out.println("Dealer Wins!");
                playerMoney -= playerBet;
                endRound = true;
            }

            while( (dealerDeck.cardsValue() < 17) && (endRound = false)){
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());

            }
            System.out.println("Dealer's hand is valued at: " + dealerDeck.cardsValue());

            if((dealerDeck.cardsValue() > 21) && (endRound = false)){
                System.out.println("Dealer Bust!");
                System.out.println("You Wins!");
                playerMoney += playerBet;
                endRound = true;
            }


            if(((playerDeck.cardsValue()) == dealerDeck.cardsValue()) && (endRound == false)){
                System.out.println("Push");
                endRound = true;
            }

            if (((playerDeck.cardsValue()) > (dealerDeck.cardsValue())) && (endRound == false)){
                System.out.println("You win!");
                playerMoney += playerBet;
                endRound = true;
            }
            else if(endRound == false){
                System.out.println("You lose");
                playerMoney -= playerBet;
                endRound = true;
            }

            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);

            System.out.println("End of round!");


        }

        System.out.println("Game Over! You have no money.");


    }

}
