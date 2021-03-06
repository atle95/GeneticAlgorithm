package jobManager;

/**
 * 
 * @author Chris Sanchez
 * This class is the actual job
 */
public class JobInstance implements Job
{

  String name;
  boolean isCompleted;

  public JobInstance(String name) {
      this.name  = name;
  }



  @Override
  public int getPriority()
  {
    // TODO Auto-generated method stub
    return 0;
  }
  
  
  @Override
  public void start()
  {
    System.out.println("Job " + this.name + " Started");
    try
    {
      Thread.sleep(10000);
      System.out.println("Job " + this.name + " Completed");
      isCompleted = true;
    } catch (InterruptedException e)
    {
      // TODO Auto-generated catch block
      System.out.println("Interuppted.. Mark me finish / dont remove from List");
    }
  }
}
