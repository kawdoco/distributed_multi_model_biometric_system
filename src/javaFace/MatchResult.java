package javaFace;

public class MatchResult
{
  private String matchFnm;
  private double matchDist;
  
  public MatchResult(String fnm, double dist)
  {
    matchFnm = fnm;
    matchDist = dist;
  }
  
  public String getMatchFileName() {
    return matchFnm;
  }
  
  public void setMatchFileName(String fnm) { matchFnm = fnm; }
  
  public double getMatchDistance() {
    return matchDist;
  }
  
  public void setMatchDistance(double dist) { matchDist = dist; }
  


  public String getName()
  {
    int slashPos = matchFnm.lastIndexOf('\\');
    int extPos = matchFnm.lastIndexOf(".png");
    String name = slashPos == -1 ? matchFnm.substring(0, extPos) : 
      matchFnm.substring(slashPos + 1, extPos);
    
    name = name.replaceAll("[-_0-9]*$", "");
    return name;
  }
}
