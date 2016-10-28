import java.util.Observable;

public abstract class Sensor extends Observable implements Runnable {
  private double failureRate = 0.1;
  protected double probability;
  private volatile boolean isBroken;
  private volatile boolean isRunning = true;

  public void run() {
    while (isRunning) {
      double x = Math.random();
      if (x <= probability) {
        setChanged();
        notifyObservers();
      }
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        System.out.println("Sensor thread got interrupted.");
        Thread.currentThread().interrupt();
      }
    }
  }

  public boolean check() {
    double x = Math.random();
    if (x <= failureRate)
      isBroken = true;
    return isBroken;
  }

  public void shutdown() {
    isRunning = false;
  }

}