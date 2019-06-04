package tp.observateur.com;

public class ClaculSomme extends IObserver{
    public ClaculSomme(AbstractSubject abstractSubject) {
        this.abstractSubject = abstractSubject;
        this.abstractSubject.register(this);
    }

    @Override
    public void update() {
        int totale = 3*abstractSubject.getTemperature()+2*abstractSubject.getHumidite();
        System.out.println("Somme de 3 fois la temperature et 2 fois l'humidité : " + totale);
    }
}
