public class FireSensor extends Sensor {
  public FireSensor() {
    probability = 0.05;
    alarm = new FireAlarm();
  }
}