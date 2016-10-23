public class FireSensor extends Sensor {
  private double p = 0.05;

  public FireSensor() {
    super();
  }

  public void detect() {
    double x = Math.random();
    if (x <= p) {
      setChanged();
      notifyObservers(new FireAlarm());
    }
  }

}