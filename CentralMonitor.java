import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;

public class CentralMonitor implements Observer, Runnable {
  private volatile boolean isRunning = true;
  private int numberOfAvailableSensors;
  private ArrayList<Sensor> sensorList; 

  public CentralMonitor(ArrayList<Sensor> sensors) {
    this.sensorList = sensors;
    for (Sensor sensor : sensorList) {
      sensor.addObserver(this);
    }
    numberOfAvailableSensors = sensorList.size();
  }

  public void update(Observable obs, Object arg) {
    if (obs instanceof Sensor) {
      //TODO: prioritize the alarm
      Sensor s = (Sensor) obs;
      s.triggerAlarm();      
    }
  }

  public void run() {
    while (isRunning) {
      checkSensorAvailability();
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        System.out.println("Central Monitor thread got interrupted.");
        Thread.currentThread().interrupt();
      }
    }
  }

  private void checkSensorAvailability() {
    for (Sensor sensor : sensorList) {
      if(sensor.check() == true) {
        if (numberOfAvailableSensors == 0) {
          System.out.println("all sensors down.");
          System.exit(1);
        }
        numberOfAvailableSensors--;
      }
    }
    System.out.printf("%d of Sensors are Up and Running...\n", numberOfAvailableSensors);
  }

  public void shutdown() {
    isRunning = false;
  }
}