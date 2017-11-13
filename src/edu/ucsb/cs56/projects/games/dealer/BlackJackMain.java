package edu.ucsb.cs56.projects.games.dealer;

import static java.lang.System.in;
import static java.lang.System.out;

import java.util.Scanner;

/**
 * The console version of the game BlackJack
 * @author Kin Kwan Poon
 * @version cs56 F16
 *
 */
public class BlackJackMain {
    
    public static void main(String[] args) {
        BlackJackGame bjg;
        Scanner stdin=new Scanner(in);
        String prompt;
        boolean shouldRun=true;
        out.println("Welcome to BlackJack!");
        out.println("What is your name?");
        String name = stdin.nextLine();
        out.println("How many decks do you want to play?");
        int numOfDecks=Integer.parseInt(stdin.nextLine());
        out.println("How many points do you want to bet? Current you have 10 points.");
        int points_bet = Integer.parseInt(stdin.nextLine());
        while(points_bet > 10){
            out.println("You do not have enough points.");
            out.println("How many points do you want to bet? Current you have 10 points.");
            points_bet = Integer.parseInt(stdin.nextLine());
        }
        out.println("You are playing with "+numOfDecks+" Decks.");
        bjg=new BlackJackGame(numOfDecks,name);
        
        do{
            if(bjg.remainingCard()<10){
                out.println("There are not enough cards to play!\n"
                            + "The game will now exit");
                break;
            }
            bjg.start();
            while(!bjg.getPlayer().isBusted()){
                out.println("Do you want to hit? (y/n)");
                prompt=stdin.nextLine();
                if(prompt.charAt(0)=='Y'||prompt.charAt(0)=='y')
                    bjg.playerHit();
                else
                    break;
            }
            if(!bjg.getPlayer().isBusted())
                bjg.houseHit();
            bjg.result(points_bet);
            out.println("\nDo you want to continue? (y/n)  " + "Your score = " + bjg.getPlayer().get_score());
            prompt=stdin.nextLine();
            out.println("How many points do you want to bet? Current you have "+ bjg.getPlayer().get_score()+  " points.");
            points_bet = Integer.parseInt(stdin.nextLine());
            while(points_bet > bjg.getPlayer().get_score() && bjg.getPlayer().get_score() > 0){
                out.println("You do not have enough points.");
                out.println("How many points do you want to bet? Current you have "+ bjg.getPlayer().get_score()+  " points.");
                points_bet = Integer.parseInt(stdin.nextLine());
            }
            if(prompt.charAt(0)=='N'||prompt.charAt(0)=='n')
                shouldRun=false;
            bjg.clearHands();
        }while(shouldRun && bjg.getPlayer().get_score() > 0);
        if(bjg.getPlayer().get_score() <= 0)
            out.println("You lost all your points!");
        out.println("Thanks for playing, Bye!");
        stdin.close();
    }
    
}
