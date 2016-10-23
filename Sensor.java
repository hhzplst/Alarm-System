import java.util.Observable;

public abstract class Sensor extends Observable implements Runnable {
  private double failureRate = 0.01;
  protected volatile boolean isBroken;
  protected volatile boolean isRunning = true;

  public Sensor() {};

  public Sensor(String type) {
    createSensor(type);
  }

  public void run() {
    while (isRunning) {
      detect();
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

  private Sensor createSensor(String type) {
    if (type.equals("fire")) {
      return new FireSensor();
    } else if (type.equals("burglar")) {
      return new BurglarSensor();
    } else if (type.equals("earthquake")) {
      return new EarthquakeSensor();
    } else return null;
  }

  public abstract void detect();

}