package Threads;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PoolThread {
    private final int nbThread; //Le nombre de thread

    private final PoolWorker[] threads; //Le tableau de threads


    private final LinkedList queue; //La queue qui contient les tâches à traiter

    //Constructeur
    public PoolThread(int nb) {
        this.nbThread = nb; //on attribue le nombre de thread
        queue = new LinkedList(); //on crée une nouvelle queue
        threads = new PoolWorker[this.nbThread]; //on crée x threads

        for(int i=0; i<this.nbThread; i++){ //On instancie PoolWorker dans chaque thread
            threads[i] = new PoolWorker();
            threads[i].start();
            System.out.println("Thread " + i + " en attente.");
        }
    }

    //Methode qui permet de mettre une nouvelle tâche dans la queue
    public void assign(Runnable traitementPacket) {
        synchronized(queue){
            queue.add(traitementPacket); //on rajoute la tâche dans la queue
            queue.notify(); //On réveille un des threads en attente pour traiter la tâche
        }
    }
    //La classe PoolWorker qui s'occupe d'attribuer les tâche aux threads
    private class PoolWorker extends Thread{
        public void run() { //run, méthode appelle en faisant start ligne 23
            Runnable r;
            while(true){
                synchronized(queue){
                    //Tant que la queue ne contient pas de tâche, on attend.
                    while(queue.isEmpty()){
                        try {
                            queue.wait(); //On attend un notify (ligne 32)
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PoolThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //Une fois qu'un notify est reçu, on récupère la 1ere tache dans la queue
                    r = (Runnable) queue.removeFirst();
                }
                try{
                    //On démarre la tâche qu'on à récupérer dans la queue
                    r.run();
                }catch(RuntimeException ex){
                    System.out.println("Erreur de pool: " + ex.getMessage());
                }
                //Et on se remet en attente d'une tâche.
            }
        }
    }
}

