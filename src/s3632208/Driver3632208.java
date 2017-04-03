
//Written by Subhashini Naresh
//Student ID: s3632208

package s3632208;
import s3632205.*;
import java.util.*;


public class Driver3632208
{
	public Game selectAGame()
	{
		Scanner s = new Scanner(System.in);
		String option=null;
	  
		do{
			   System.out.println("\nChoose 1-3 options for: \n 1)Swimming\n 2)Running\n 3)Cycling\n");
			    option=s.next();
			    if(option.equals("1") || option.equals("2") || option.equals("3"))
			    	break;
			    else  {
			    System.out.println("Please choose the right option or choose 5 to exit");
			    option=s.next();
			    }
			    if (option.equals("5"))
			    	return null;
	    }while(!(option.equals("1")) || !(option.equals("2"))|| !(option.equals("3")));
		
		
	    String choose="";
	    Game game=new Game();
	    game=game.createGameId(game, option);
		if(option.equals("1"))
			choose="SW";
		else if(option.equals("2"))
			choose="RU";
		else
			choose="CY";
		game=game.chooseAthletes(game,choose);
		game.referee= Official.chooseReferee();
		System.out.println("Participants of "+game.gameId+" are:");
		for(int i=0;i<game.participants.length;i++)
			System.out.println((i+1)+")"+game.participants[i].name+" "+game.participants[i].athleteId);	
		return game;
		}

	public Game prediction(Game game)
	{
		
		System.out.println("Participants of "+game.gameId+" are:");
		for(int i=0;i<game.participants.length;i++)
		{
			System.out.println((i+1)+")"+game.participants[i].name+" "+game.participants[i].athleteId);	
		}
		
		System.out.println("\nChoose the ID of the athlete you wish to predict as the winner\n");
		int j=1;
		while(j==1)
		{
			Scanner scan=new Scanner(System.in);
			game.userPrediction=scan.next();
			if(game.userPrediction.equals("*"))
			{
				game.userPrediction=null;
				return game;
			}
			int flag=0;
			game.userPrediction=game.userPrediction.toUpperCase();
			for(int i=0;i<game.participants.length;i++)
			{
				if(game.userPrediction.equals(game.participants[i].athleteId))
					flag=1;
			
			}
			if(flag==0)
				System.out.println("\nPlease enter a valid ID\n else press * to return to main menu");
			else if(flag==1)
				return game;
		}
		return game;
	}
	
	public Game startGame(Game game)
	{
		int minTime1=999,minTime2=999,minTime3=999;
		int firstWinner=0,secondWinner=0,thirdWinner=0;
		game.winners=new Athlete[3];
		for(int i=0;i<game.participants.length;i++)
		{
			game.participants[i].compete(game.gameId);
		}
		for(int i=0;i<game.participants.length;i++)
		{
			if(game.participants[i].time<minTime1)
			{
				minTime1=game.participants[i].time;
				firstWinner=i;	
			}
			
		}
		game.participants[firstWinner].points+=5;
		
		for(int i=0;i<game.participants.length;i++)
		{
			if(minTime1 <=game.participants[i].time && !(game.participants[i].athleteId.equals(game.participants[firstWinner].athleteId)) && game.participants[i].time<minTime2)
			{
				minTime2=game.participants[i].time;
				secondWinner=i;	
			}
			
		}
		game.participants[secondWinner].points+=2;
		for(int i=0;i<game.participants.length;i++)
		{
			if(game.participants[i].time>minTime2 && !(game.participants[i].athleteId.equals(game.participants[secondWinner].athleteId)) && game.participants[i].time<minTime3)
			{	minTime3=game.participants[i].time;
				thirdWinner=i;}
		}
		game.participants[thirdWinner].points+=1;
		game.winners[0]=game.participants[firstWinner];
		game.winners[1]=game.participants[secondWinner];
		game.winners[2]=game.participants[thirdWinner];
		int flag=0;
		if(game.userPrediction!=null)
		{
			for(int i=0;i<3;i++){
			if(game.userPrediction.equals(game.winners[i].athleteId))
			{
				flag=1;
				System.out.println("Congratulations!! Your prediction turned out to be right");
			}
		}
			if(flag!=1)
				System.out.println("Sorry.Hope you predict correctly next time.Enjoy!");
		}
		game.userPrediction=null;
		return game;
	}
	
	public void displayResults(Game[] games,int count)
	{
		String[] titles=new String[3];
		titles[0]="First";
		titles[1]="Second";
		titles[2]="Third";
		
		for(int i=0;i<count;i++){
			if(games[i].winners==null)
				System.out.println("\nThe game "+games[i].gameId+" was aborted due to various reasons!\n");
			else
			{
				System.out.println("\nResults of the Game "+games[i].gameId+" with Referee "+games[i].referee.name+","+games[i].referee.age+" ID: "+games[i].referee.officialId+" from "+games[i].referee.state);
				System.out.println("\nWinners:\n========================================================================\nTITLE\t\tATLHLETE\tID\tAGE\tPOINTS\tSTATE");
				for(int j=0;j<games[i].winners.length;j++)
        		System.out.println(titles[j]+"\t\t"+games[i].winners[j].name+"\t\t"+games[i].winners[j].athleteId+"\t"+games[i].winners[j].age+"\t"+games[i].winners[j].points+"\t"+games[i].winners[j].state+"\n");
				
			}
		} 
	}
	public void displayPoints()
	{
		System.out.println("\nPoints of all Athletes are displayed as below:\n");
		System.out.println("\nID      ATHLETE NAME\t\tPOINTS\n");
		if(AthleteDetails.listofAthletes==null)
			AthleteDetails.storeAthletes();
		for(int i=0;i<32;i++)
			System.out.println(AthleteDetails.listofAthletes[i].athleteId+"\t  "+AthleteDetails.listofAthletes[i].name+"   \t\t"+AthleteDetails.listofAthletes[i].points);
	}
	}
