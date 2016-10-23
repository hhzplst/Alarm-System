public class BurglarSensor extends Sensor {
  private double p = 0.1;

  public void detect() {
    double x = Math.random();
    if (x <= p) {
      setChanged();
      notifyObservers(new BurglarAlarm());
    }
  }

}