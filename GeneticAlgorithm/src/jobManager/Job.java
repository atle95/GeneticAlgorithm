package jobManager;

/**
 * 
 * @author Chris Sanchez
 * This interface Actually defines the job
 *
 */
public interface Job
{
  void start();
  
  int getPriority();
}
