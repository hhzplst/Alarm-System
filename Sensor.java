import java.util.Observable;

public abstract class Sensor extends Observable implements Runnable {
  private double failureRate = 0.01;
  protected volatile boolean isBroken;
  protected volatile boolean isRunning = true;

  public Sensor(string type) {
    if (type.equals("fire")) {
      return new FireSensor();
    } else if (item.equals("burglar")) {
      return new BurglarSensor();
    } else if (item.equals("earthquake")) {
      return new EarthquakeSensor();
    } else return null;
  }

  public void run() {
    while (isRunning) {
      detect();
      try {
        Thread.sleep(100);
      } catch (InterruptedException) {
        System.out.println("Sensor thread got interrupted.");
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

  public abstract void detect();

}