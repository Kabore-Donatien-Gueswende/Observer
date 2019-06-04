# Patron_Observateur
Nous allons dans ce projet implementer le patron observateur dans le cas de la mis a jour automatique des interface client sur le temps,
l'humidité ainsi qu'une fonction qui gère la somme des elements énoncés précédemment.
Un schéma simplifié de notre projet se présente commme :

![alt tag](https://github.com/Kabore-Donatien-Gueswende/Patron_Observateur/blob/master/img/UML.PNG)


Il sagit d'une architecture simplifiée, certaine methode non énuméré ci-dessus apparaitrons par la suite.

Nous allons commencer l'implementation par la mis en place de l'interface __IObserver__ : 
elle a une methode __update()__ à implementer par les classes l'heritants, et un objet __AbstractSubject__ qui permet au classes l'héritant
de s'enregistrer ou de se désabonner des eventuels mis-à-jour de leur inetrface (une classe que l'on vera plus tard).

    public abstract class IObserver {
        public AbstractSubject abstractSubject;
        public abstract void update();
    }

Maintenant créons les observateurs concrets:
On en a trois, une classe __Temperature__, une classe __Humidite__ et en fin une classe qui fera la somme de __3*temperature + 2*humidite__

Il implementerons la methode __update__, et grace à l'objet __AbstractSubject__, chaque classe poura se désabonner ou abonner.

__classe Temperature__ :


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

__classe Humidite__


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
    
__classe calculSomme__


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
 
Les classes concretes etant crée, passons maintenant à la création de la classe __AbstractSubject__ qui est la classe maitresse de
ce patron. Elle contient toute les methodes (désabonnement, abonnement, notification ainsi que la liste des observateurs abonnés) qui
permet aux classes concretes de fonctionner.

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
    
 
Cette classe permettra aux observateurs concrets d'etre mis-à-jour à chaque changement effectué. Ainsi creons la classe Demo, qui servira
de classe test.

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
    
Ce qui donne l'image suivante:

![alt tag](https://github.com/Kabore-Donatien-Gueswende/Patron_Observateur/blob/master/img/Capture.PNG)

__Conclusion :__ le patron observateur permet à des classes intermediaires d'etre notifié des changements de paramètres ou arguments
d'un module, qui provoquera à ce effet des changements au sein des classes intermediaires.
