public class BurglarSensor extends Sensor {
  public BurglarSensor() {
    probability = 0.1;
    alarm = new BurglarAlarm();
  }
}