public class EarthquakeSensor extends Sensor {
  public EarthquakeSensor() {
    probability = 0.01;
    alarm = new EarthquakeAlarm();
  }
}