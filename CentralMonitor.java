import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;

public class CentralMonitor implements Observer, Runnable {
  private volatile boolean isRunning = true;
  private int numberOfAvailableSensors;
  ArrayList<Sensor> sensorList; 

  public CentralMonitor(ArrayList<Sensor> sensors) {
    this.sensorList = sensors;
    for (Sensor sensor : sensorList) {
      sensor.addObserver(this);
    }
    numberOfAvailableSensors = sensorList.size();
  }

  public void update(Observable obs, Object arg) {
    if (obs instanceof Sensor) {
      //prioritize the alarm
      //set off alarm
    }
  }

  public void run() {
    while (isRunning) {
      checkSensorAvailability();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        System.out.println("Central Monitor thread got interrupted.");
        Thread.currentThread().interrupt();
      }
    }
  }

  private void checkSensorAvailability() {
    for (Sensor sensor : sensorList) {
      if(sensor.check() == false) {
        numberOfAvailableSensors--;
      }
    }
    System.out.printf("{0} of Sensors are Up and Running...", numberOfAvailableSensors);
  }

  public void shutdown() {
    isRunning = false;
  }
}