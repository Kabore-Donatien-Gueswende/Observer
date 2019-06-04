package tp.observateur.com;

public class Temperature extends IObserver {
    
    public Temperature(AbstractSubject abstractSubject) {
        this.abstractSubject = abstractSubject;
        this.abstractSubject.register(this);
    }
    
    @Override
    public void update() {
        System.out.println("La temperature est de : " + abstractSubject.getTemperature() +"°");
    }
}
