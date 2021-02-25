import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Project1
{
  static int size;
  static int[][] matches;
  static int[][] menPreferences, womenPreferences;

  private static void setInput() //gets input from txt file
  {
    Scanner sc = null;
    try
    {
      sc = new Scanner(new File("input.txt"));
    } catch (FileNotFoundException e)
    {
      System.out.println("input file not found.");
      System.exit(1);
    }
    size = sc.nextInt();

    matches = new int[size][2];
    womenPreferences = new int[size][size];
    menPreferences = new int[size][size];

    for (int i = 0; i < size; i++)
    {
      for (int j = 0; j < size; j++)
      {
        menPreferences[i][j] = sc.nextInt();
      }
    }

    for (int i = 0; i < size; i++)
    {
      for (int j = 0; j < size; j++)
      {
        womenPreferences[i][j] = sc.nextInt();
      }
    }

    for (int i = 0; i < size; i++)
    {
      for (int j = 0; j < 2; j++)
      {
        matches[i][j] = sc.nextInt();
      }
    }
  }

  public static void main(String[] args)
  {
    setInput();
    Man[] m = new Man[size];
    Woman[] w = new Woman[size];
    //m and w's constructors
    for (int i = 0; i < size; i++)
    {
      m[i] = new Man(size);
      w[i] = new Woman(size);
    }
    //assign men's and women's partners
    for (int i = 0; i < size; i++)
    {
      for (int j = 1; j < 2; j++)
      {
        m[i].partner = matches[i][j];
        w[matches[i][j] - 1].partner = i + 1;
      }
    }
    //assign women's p[3-5] partners
    for (int i = 0; i < size; i++)
    {

    }
    //assign men's and women's preferences
    for (int i = 0; i < size; i++)
    {
      for (int j = 0; j < size; j++)
      {
        m[i].preferences[j] = menPreferences[i][j];
        w[i].preferences[j] = womenPreferences[i][j];
      }
    }
    for (int i = 0; i < size; i++)
    {
      m[i].setPartnersRank();
      w[i].setPartnersRank();
    }

    isStable(m,w);
  }

  public static void isStable(Man[] m, Woman[] w)
  {
    int nextBestWomanIndex,  currGuysRankInNextMatch=0, nextBestWoman, nextBestMan;
    int nextBestManIndex,  currWomansRankInNextMatch=0;
    for (int i = 0; i < size; i++)
    {
      if(m[i].partnersRank > 0)
      {
        nextBestWomanIndex = m[i].partnersRank - 1;
        nextBestWoman = m[i].preferences[nextBestWomanIndex] - 1;
        currGuysRankInNextMatch =  w[nextBestWoman].findRankOf(i+1);
        if(w[nextBestWomanIndex].partnersRank > currGuysRankInNextMatch)
        {
          m[i].partner = m[i].preferences[nextBestWomanIndex];
          System.out.println("Unstable " + (i+1) + " " + m[i].partner);
          return;
        }
      }else if(w[i].partnersRank > 0)
      {
        nextBestManIndex = w[i].partnersRank - 1;
        nextBestMan = m[i].preferences[nextBestManIndex] - 1;
        currWomansRankInNextMatch =  m[nextBestMan].findRankOf(i+1);
        if(m[nextBestManIndex].partnersRank > currWomansRankInNextMatch)
        {
          w[i].partner = nextBestManIndex + 1;
          System.out.println("Unstable at " + (i+1) + " " + w[i].partner);
          return;
        }
      }
    }
    System.out.println("Stable");

  }

}

