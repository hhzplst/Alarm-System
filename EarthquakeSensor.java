public class EarthquakeSensor extends Sensor {
  private double p = 0.01;

  public void detect() {
    double x = Math.random();
    if (x <= p) {
      setChanged();
      notifyObservers(new EarthquakeAlarm());
    }
  }

}