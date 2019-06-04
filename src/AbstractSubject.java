package tp.observateur.com;

import java.util.ArrayList;
import java.util.List;

public class AbstractSubject {
    
    private  List<IObserver> observer = new ArrayList<IObserver>();
    
    private int temperature;
    private int humidite;
    
    public int getTemperature() {
        return temperature;
     }

     public void setTemperatureAndHumidite(int temperature, int humidite) {
        this.temperature = temperature;
        this.humidite = humidite;
        notifyAllObserver();
     }
     
     public int getHumidite() {
         return humidite;
     }

    
    public void register(IObserver iobserver) {
        this.observer.add(iobserver);
    }
    
    public void unregister(IObserver iobserver) {
        this.observer.remove(iobserver);
    }
    
    public void notifyAllObserver() {
        for (IObserver iObserver : observer) {
            iObserver.update();
        }
    }
         
}
