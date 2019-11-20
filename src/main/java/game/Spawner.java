package game;

import game.enemy.BossEnemy;
import game.enemy.NormalEnemy;
import game.enemy.SmallEnemy;
import game.enemy.TankerEnemy;

import java.io.*;
import java.util.*;

class List2D{
    public String name;
    public int number;

    public List2D(String name, int number){
        this.name = name;
        this.number = number;
    }
    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
public class Spawner extends AbstractTile {
    private int timeToLoadNewWave = 600;
    private double spawnRate = 1;
    List<List<List2D>> listWaveTurn = new ArrayList<>(); //list chua so turn enemy ra, Map chua so luong cac enemy trong 1 turn
    List<Integer> timeWait = new ArrayList<>();
    private int betweenSpawnCount = 0;

    /**
     * xem có load game cũ k
     * @param loadOlderGame
     */
    public Spawner(boolean loadOlderGame){
        readWaveData(loadOlderGame);
    }
    @Override
    public void run() {
        betweenSpawnCount++;
        for(List<List2D> waveTurn : listWaveTurn){
            if(waveTurn.size() > 0){
                if(waveTurn.get(0)!=null){
                    List2D enemyList = waveTurn.get(0);
                    if(enemyList.getNumber() == 0){
                        waveTurn.remove(enemyList);
                    }
                    else if(enemyList.getName().contains("NormalEnemy") && enemyList.getNumber() > 0 && betweenSpawnCount > 40/spawnRate){
                        new NormalEnemy();
                        betweenSpawnCount = 0;
                        enemyList.setNumber(enemyList.getNumber() - 1);
                        GameField.numberOfEnemy++;
                        return;
                    }
                    else if(enemyList.getName().contains("TankerEnemy") && enemyList.getNumber() > 0 && betweenSpawnCount > 50/spawnRate){
                        new TankerEnemy();
                        betweenSpawnCount = 0;
                        enemyList.setNumber(enemyList.getNumber() - 1);
                        GameField.numberOfEnemy++;
                        return;
                    }
                    else if(enemyList.getName().contains("SmallEnemy") && enemyList.getNumber() > 0 && betweenSpawnCount > 20/spawnRate){
                        new SmallEnemy();
                        betweenSpawnCount = 0;
                        enemyList.setNumber(enemyList.getNumber() - 1);
                        GameField.numberOfEnemy++;
                        return;
                    }
                    else if(enemyList.getName().contains("BossEnemy") && enemyList.getNumber() > 0 && betweenSpawnCount > 60/spawnRate){
                        new BossEnemy();
                        betweenSpawnCount = 0;
                        enemyList.setNumber(enemyList.getNumber() - 1);
                        GameField.numberOfEnemy++;
                        return;
                    }
                }
            }
            else{
                timeToLoadNewWave--;
                if(timeToLoadNewWave <= 0){
                    listWaveTurn.remove(waveTurn);
                    timeToLoadNewWave = 400;
                }
                break;
            }
        }


    }
    //read file wave.txt in /data
    public void readWaveData(boolean loadOderGame){
        try{
            Scanner sc = null;
            if(loadOderGame){
                sc = new Scanner(new BufferedReader(new FileReader("data/currentWave.txt")));
            }else{
                sc = new Scanner(new BufferedReader(new FileReader("data/wave.txt")));
            }
            while(sc.hasNextLine()) {
                //map nay chua thong tin tung turn, sau do add vao list
                String[] line = sc.nextLine().trim().split(" ");
                int size = Integer.parseInt(line[0]);
                List<List2D> waveTurn = new ArrayList<>();
                for(int i = 0; i <size; i++){
                    String[] wave = sc.nextLine().trim().split(" ");
                    String name = wave[0];
                    int number = Integer.parseInt(wave[1]);
                    waveTurn.add(new List2D(name, number));
                }
                listWaveTurn.add(waveTurn);
            }
        }catch (Exception e){
            System.err.println(e.toString());
        }
    }
    //save current wave turn
    public void saveCurrentWaveTurn(){
        try {
            FileWriter fw = new FileWriter("data/currentWave.txt");
            for(List<List2D> waveTurn : listWaveTurn){
                fw.write(String.valueOf(waveTurn.size()) + "\n");
                for(List2D wave : waveTurn){
                    fw.write(wave.getName() + " " + wave.getNumber() + "\n");
                }
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}