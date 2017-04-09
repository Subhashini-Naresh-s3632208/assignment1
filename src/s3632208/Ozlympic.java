
import java.util.*;
import java.util.Scanner;
import java.util.Random;
 
 
 
 class Ozlympic {
	 
	 //Written by Subhashini Naresh
	//Student ID: s3632208


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Driver3632208 driver2=new Driver3632208();
		Game game=null;
		Game[] games=new Game[100];
		int i=1;
		games[0]=null;
		int count=0;
		System.out.println("Welcome to Ozlympic Sports!");
		
		while(i==1)
		{
			System.out.println("\nPlease choose the options 1-6 from the menu below:\n1)Select a game to run\n2)Predict the winner of the game\n3)Start the game\n4)Display the final result of all games\n5)Display the points of all Athletes\n6)Exit\n");
			Scanner s=new Scanner(System.in);
			String option=s.next();
		
			if(option.equals("1"))
			{
				game=driver2.selectAGame();
				if(game==null)
				{
					System.out.println("if you wish to exit,Please press 6\n");
					String option1=s.next();
					if(option1.equals("6"))
						System.exit(0);
				}
				games[count]=game;
				count++;
			}
		
			else if(option.equals("2"))
			{
				if(game==null)
					System.out.println("Please select a game to run");
				else
				{
					game=driver2.prediction(game);
					if(game==null)
						System.out.println("if you wish to exit,Please press 6\n");
				}
			}
			else if(option.equals("3"))
			{
				if(game==null)
					System.out.println("Please select a game to run");
				else
				{
					game=driver2.startGame(game);
					if(game==null)
						System.out.println("if you wish to exit,Please press 6\n");
					System.out.println("The game "+game.gameId+" has succesfully been played by all participants!\nChoose 4 to view results");
				}
			}
			else if(option.equals("4"))
			{
				if(games[0]==null)
					System.out.println("Please select a game to run.There are no games to display");
				else
					driver2.displayResults(games,count);	
			}
			else if(option.equals("5"))
			{
				driver2.displayPoints();
			}
			else if(option.equals("6"))
			{
				System.exit(0);
			}
			else
			{
				System.out.println("please choose the right option or press \"6\" to exit");
			}
		}
	}
}

 
