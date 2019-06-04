package tp.observateur.com;

public class Demo {
    public static void main(String[] args) {
        AbstractSubject subject = new AbstractSubject();
        new Temperature(subject);
        new Humidite(subject);
        new ClaculSomme(subject);
        
        //first update()
        subject.setTemperatureAndHumidite(15,12);
        
        System.out.println("*********************");
        
        //second update()
        subject.setTemperatureAndHumidite(18,11);
        
    }
}
