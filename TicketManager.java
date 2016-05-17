//**********************************************************************************************************************
// Name: Amit Tallapragada
// Title: TicketManager.java
// Description: Manages all of the calculations and printing of data for Assignment 8.
// Time spent: 3 DAYS
// Date:4/25/16
//**********************************************************************************************************************

import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class TicketManager {
	static final int NUMROWS = 15;
	static final int NUMCOLUMNS = 30;
	char[][] seats = new char[NUMROWS][NUMCOLUMNS];
	double[] price = new double[NUMROWS];
	int seatsSold =0;
	double totalRevenue;
	
	public TicketManager()
	{
		File inputFile = new File("seatPrices.txt");
		File seatAvail = new File("seatAvailability.txt");
		int x = 0;
		try{
			Scanner in = new Scanner(inputFile);		
			while(in.hasNextLine())
			{
				double y = Double.parseDouble(in.nextLine());
				price[x] = y;
				x++;
			}
		}
		catch (FileNotFoundException e){
			System.out.println("in the catch");
			System.out.println(e);
		}
		try{
			Scanner inn = new Scanner(seatAvail);
			String test = "";
			int t,y =0;
			while(inn.hasNext())
			{
				test += inn.nextLine();
			}
			for(int z=0; z<seats.length; z++)
			{
				for(int w =0; w<seats[z].length; w++)
				{
					seats[z][w] = test.charAt(z);
				}
			}
			
		}
		catch (FileNotFoundException e){
			System.out.println("in the catch");
			System.out.println(e);
		}		
	}
	
	public boolean requestTickets(int rows, int cols, int numofseats)
	{
		int Row = rows;
		int Col = cols;
		int num = numofseats;
		int t =0;
		for(int x=0; x<numofseats; x++)
		{
			if(seats[Row][Col+x] == '#')
			{
				t = 1;
			}
		}
		if(t == 1)
		{
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean purchaseTicket(int rows, int cols, int numofseats)
	{
		int Row = rows-1;
		int Col = cols-1;
		int num = numofseats;
		int t =0;
		if(requestTickets(Row, Col, num))
		{
			for(int x =0; x<num; x++)
			{
				seats[Row][Col+t] = '*';
				t++;
			}
			seatsSold +=num;
			totalRevenue += (num*getPrice(rows));
			return true;

		}
		return false;
	}
	
	public int getSeatsSold()
	{
		return seatsSold;
	}
	
	public double getTotalRevenue()
	{
		return totalRevenue;
	}
	
	public void displaySeats()
	{
		int iterator =0;
		int rowcounter = 0;
		System.out.println("Seats \n" + "123456789012345678901234567890");
		System.out.print("Row 1 ");
		for(int rows = 0; rows<seats.length; rows++)
		{
			rowcounter ++;
			for(int cols = 0; cols<seats[rows].length; cols++)
			{
				if(iterator>=NUMCOLUMNS)
				{
					System.out.println();
					System.out.print("Row " + rowcounter + " ");
					iterator = 0;
				}
				System.out.print(seats[rows][cols]);					
				iterator++;
			}
		}
		System.out.println("\nLegend: * = Sold \n" + "# = Available");
	}
	
	public void printTickets(int row, int col, int numberofseats)
	{
		String intro = "***********************************************\n* Gammage Theater *";

		for(int x =0; x<numberofseats; x++)
		{
			System.out.print(intro + "\n");
			System.out.println("* Row " + row + " Seat " + (col+x) + " *" );
			System.out.println("* Price: $ " + getPrice(row) +" *");
			System.out.println("***********************************************");
			System.out.println();
		}
		
	}
	
	public void displaySalesReport()
	{
		System.out.print("Gammage Sales Report\n" + "_____________________________\n");
		System.out.println("Seats Sold: " + getSeatsSold());
		System.out.println("Seats Available: " + (450-getSeatsSold()));
		System.out.println("Total revenue to date: " + getTotalRevenue());
	}
	
	public double getPrice(int rownum)
	{
		for(int x =0; x<NUMROWS; x++)
		{
			if(x == rownum)
			{
				return price[x];
			}
		}
		return 0;
	}
	

}
