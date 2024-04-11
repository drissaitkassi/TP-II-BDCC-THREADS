package org.example.exercice2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Sommeur implements Runnable {
    private int[] tableau;
    private int debut;
    private int fin;
    private int somme;

    public Sommeur(int[] tableau, int debut, int fin) {
        this.tableau = tableau;
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public void run() {
        for (int i = debut; i <= fin; i++) {
            somme += tableau[i];
        }
    }

    public int getSomme() {
        return somme;
    }
}

 class Main {
    public static void main(String[] args) {
        int[] tableau = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int nombreThreads = 4; // Nombre de threads à utiliser
        int taillePlage = tableau.length / nombreThreads;

        ExecutorService pool = Executors.newFixedThreadPool(nombreThreads);

        int debut = 0;
        int fin = taillePlage - 1;

        for (int i = 0; i < nombreThreads; i++) {
            if (i == nombreThreads - 1) {
                fin = tableau.length - 1;
            }
            Sommeur sommeur = new Sommeur(tableau, debut, fin);
            pool.execute(sommeur);
            debut = fin + 1;
            fin += taillePlage;
        }

        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int sommeTotale = 0;
        for (int i = 0; i < nombreThreads; i++) {
            sommeTotale += ((Sommeur) pool).getSomme();
        }

        System.out.println("Somme totale du tableau : " + sommeTotale);
    }
}

