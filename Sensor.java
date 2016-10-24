import java.util.Observable;

public class Sensor extends Observable implements Runnable {
  private double failureRate = 0.1;
  private double probability;
  private Alarm alarm;
  private volatile boolean isBroken;
  private volatile boolean isRunning = true;

  public Sensor(String type) {
    if (type.equals("fire")) {
      alarm = new FireAlarm();
      probability = 0.05;
    } else if (type.equals("burglar")) {
      alarm = new BurglarAlarm();
      probability = 0.1;
    } else if (type.equals("earthquake")) {
      alarm = new EarthquakeAlarm();
      probability = 0.01;
    } else {
      System.out.println("check input!");
    }
  }

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

  public void triggerAlarm() {
    alarm.goOff();
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