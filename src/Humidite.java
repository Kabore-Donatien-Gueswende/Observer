package tp.observateur.com;

public class Humidite extends IObserver{
    
    public Humidite(AbstractSubject abstractSubject) {
        this.abstractSubject = abstractSubject;
        this.abstractSubject.register(this);
    }

    @Override
    public void update() {
        System.out.println("L'humidité est de : " + abstractSubject.getHumidite() +" g/kg (aire sec)");
    }

}
