/* Adem Avci
/  CS 114 - W01
/  aa2822@njit.edu
*/
import java.io.*;
import java.util.*;
public class Main
{
   private static char[][] maze;        //maze
   private static int rStart, cStart, rEnd, cEnd;   //start and end of the maze
   private static ArrayList<String> mazeB;       //arraylist for maze
   
   public static void Main(String filename)
   {
      rStart = cStart = rEnd = cEnd = -1;
      mazeB = new ArrayList<String>();
      int cols = 0;
      try
      {
         File file = new File(filename);
         Scanner scan = new Scanner(file);   //scans the file
         scan.nextLine();
         while(scan.hasNext())   //checks file for lines
         {
            String nextLine = scan.nextLine();   //reads every line in the maze/file
            mazeB.add(nextLine);   //each line represents a row in the file
            if(nextLine.length() > cols)
               cols = nextLine.length();
         }
      }
      catch(FileNotFoundException e)
      {
         e.printStackTrace();
         System.out.println("Error: " + e.getMessage());
      }
      int rows = mazeB.size();
      maze = new char[rows][cols];
      
      for(int r = 0; r < rows; r++)
      {
         String row = mazeB.get(r);
         for(int c = 0; c < cols; c++)
         {
            if(row.length() >= c)
               maze[r][c] = row.charAt(c);
            else
               maze[r][c] = 'X';
               
            if(maze[r][c] == '+')
            {
               rStart = r;
               cStart = c;
            }
            else if(maze[r][c] == '-')
            {
               rEnd = r;
               cEnd = c;
            }
         }
      }
      System.out.println("Maze Printed!");
   }
         
   public static void printMaze()
   {
      for(char[] r: maze)
      {
         for(char c: r)
         {
            System.out.print(c);
         }
         System.out.println();
      }
   }
   
   public static boolean solve(int r, int c)
   {
      if(r < 0 || c < 0 || r >= maze.length || c >= maze[0].length)
         return false;
      if(maze[r][c] == '-')
         return true;
      if(maze[r][c] != ' ' && maze[r][c] != '+')
         return false;
      maze[r][c] = '.';
      if(solve(r + 1,c))
      {
         maze[r][c] = '+';
         return true;
      }
      if(solve(r - 1,c))
      {
         maze[r][c] = '+';
         return true;
      }
      if(solve(r,c - 1))
      {
         maze[r][c] = '+';
         return true;
      }
      if(solve(r,c + 1))
      {
         maze[r][c] = '+';
         return true;
      } 
      return false;
   }

   public static void main(String[] args)
   {
      Main("maze.dat.txt");
      printMaze();
      System.out.println();
      if(solve(rStart, cStart))
      {
         printMaze();
         System.out.print("Maze Solved!!");
      }
      else
         System.out.println("Unsolvable");
   }
}