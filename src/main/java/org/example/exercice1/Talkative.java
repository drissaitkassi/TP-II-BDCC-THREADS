package org.example.exercice1;

class Talkative implements Runnable {
    private int value;

    public Talkative(int value) {
        this.value = value;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Value: " + value);
        }
    }
}

 class Main {
     public static void main(String[] args) {
         for (int i = 0; i < 10; i++) {
             Talkative talkative = new Talkative(i);
             Thread thread = new Thread(talkative);
             thread.start();
         }
     }
}
