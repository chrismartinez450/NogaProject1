public class Woman
{
  public int partner, partnersRank;
  public int[] preferences;

  public Woman(int size)
  {
    preferences = new int[size];
  }
  public void setPartnersRank()
  {
    int rank=0;
    for(int i = 0; i < preferences.length; i++)
    {
      if(preferences[i] == partner) rank = i;
    }
    this.partnersRank = rank;
  }
  public int findRankOf(int num)
  {
    int rank = 0;
    for(int i = 0; i < preferences.length; i++)
    {
      if(preferences[i] == num) rank = i;
    }
    return rank;
  }
}
