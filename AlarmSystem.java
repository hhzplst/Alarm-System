import java.util.ArrayList;
import java.util.Scanner;

public class AlarmSystem {

  public static void main(String[] args) {

    int i;
    Sensor s;
    ArrayList<Sensor> mySensors = new ArrayList<Sensor>();
    ArrayList<Thread> myThreads = new ArrayList<Thread>();

    for (i = 0; i < 5; i++) {
      s = new Sensor("burglar");
      mySensors.add(s);
      myThreads.add(new Thread(s));
    }

    for (i = 0; i < 5; i++){
      s = new Sensor("fire");
      mySensors.add(s);
      myThreads.add(new Thread(s));
    }

    for (i = 0; i < 5; i++) {
      s = new Sensor("earthquake");
      mySensors.add(s);
      myThreads.add(new Thread(s));
    }

    CentralMonitor myMonitor = new CentralMonitor(mySensors);
    myThreads.add(new Thread(myMonitor));

    for (Thread r : myThreads)
      r.start();

    System.out.println("Press return to terminate...");
    new Scanner(System.in).nextLine();

    for (Sensor sensor : mySensors)
      sensor.shutdown();

    myMonitor.shutdown();
  }

}