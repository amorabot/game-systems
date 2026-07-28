package turnsystem;

import turnsystem.components.quirks.Quirks;
import turnsystem.data.BaseStats;
import turnsystem.entities.Hero;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int frame = 0;
        Queue<String> commandQueue = generateInputHandler();
        boolean running = true;
        Game gameWorld = Game.gameInstance;

        Hero archer = new Hero(BaseStats.ARCHER);
        archer.addQuirk(Quirks.QUICKDRAW);

        Hero bard = new Hero(BaseStats.BARD);
        Hero templar = new Hero(BaseStats.TEMPLAR);
        templar.addQuirk(Quirks.SLOW_STARTER);

        gameWorld.addHero(archer);
        gameWorld.addHero(bard);
        gameWorld.addHero(templar);

        while (running){
            String cmd = commandQueue.poll();
            if (cmd != null) {gameWorld.getCurrentScene().handleInput(cmd);}



            frame++;
            Thread.sleep(1000);
        }
    }

    public static Queue<String> generateInputHandler(){
        Queue<String> inputQueue = new ConcurrentLinkedQueue<>();
        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) inputQueue.offer(scanner.nextLine());
        });
        inputThread.setDaemon(true);
        inputThread.start();

        return inputQueue;
    }
}
