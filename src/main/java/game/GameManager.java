package game;

import game.enemy.BossEnemy;
import game.enemy.NormalEnemy;
import game.enemy.SmallEnemy;
import game.enemy.TankerEnemy;
import game.tower.MachineGunTower;
import game.tower.NormalTower;
import game.tower.SniperTower;
import game.tower.SpreadTower;

import java.io.*;
import java.util.Scanner;

/**
 * save and load data
 */
public class GameManager {
    public void saveData(){
        try {
            //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
            FileOutputStream fos = new FileOutputStream("data/game.txt");
            fos.write(32);
            DataOutputStream dos = new DataOutputStream(fos);
            //Bước 2: Ghi dữ liệu
            //mapURL:
            dos.writeUTF(GameField.mapURL + "\n");
            for(AbstractEntity entity : GameField.gameEntities){
                dos.writeUTF(entity.toString() + "\n");
            }
            //Bước 3: Đóng luồng
            fos.close();
            dos.close();
            System.out.println("Done!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static String getMapURL(){
        try{
            Scanner sc = new Scanner(new BufferedReader(new FileReader("data/game.txt")));
            String[] line = sc.nextLine().trim().split(" ");
            if(line[0].contains("assets/tiles/")){
                return line[0].substring(0);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void loadData(){
        try{
            Scanner sc = new Scanner(new BufferedReader(new FileReader("data/game.txt")));
            while(sc.hasNextLine()) {
                String[] line = sc.nextLine().trim().split(" ");
                //Mountain va Road
                if(line[0].contains("Mountain")){
                    double x = Double.parseDouble(line[1]);
                    double y = Double.parseDouble(line[2]);
                    new Mountain(x, y);
                }
                else if(line[0].contains("Road")){
                    double x = Double.parseDouble(line[1]);
                    double y = Double.parseDouble(line[2]);
                    new Road(x, y);
                }
                //Tower
                else if(line[0].contains("NormalTower")){
                    double x = Double.parseDouble(line[1]);
                    double y = Double.parseDouble(line[2]);
                    new NormalTower(x, y);
                }
                else if(line[0].contains("SniperTower")){
                    double x = Double.parseDouble(line[1]);
                    double y = Double.parseDouble(line[2]);
                    new SniperTower(x, y);
                }
                else if(line[0].contains("MachineGunTower")){
                    double x = Double.parseDouble(line[1]);
                    double y = Double.parseDouble(line[2]);
                    new MachineGunTower(x, y);
                }
                else if(line[0].contains("SpreadTower")){
                    double x = Double.parseDouble(line[1]);
                    double y = Double.parseDouble(line[2]);
                    new SpreadTower(x, y);
                }
                //enemy
                else if(line[0].contains("NormalEnemy")){
                    double x = Double.parseDouble(line[1]);
                    double y = Double.parseDouble(line[2]);
                    double vx = Double.parseDouble(line[3]);
                    double vy = Double.parseDouble(line[4]);
                    double hp = Double.parseDouble(line[5]);
                    NormalEnemy enemy = new NormalEnemy(x, y);
                    enemy.setVelocity(vx, vy);
                    enemy.setHp(hp);
                }
                else if(line[0].contains("TankerEnemy")){
                    double x = Double.parseDouble(line[1]);
                    double y = Double.parseDouble(line[2]);
                    double vx = Double.parseDouble(line[3]);
                    double vy = Double.parseDouble(line[4]);
                    TankerEnemy enemy = new TankerEnemy(x, y);
                    double hp = Double.parseDouble(line[5]);
                    enemy.setVelocity(vx, vy);
                    enemy.setHp(hp);
                }
                else if(line[0].contains("SmallEnemy")){
                    double x = Double.parseDouble(line[1]);
                    double y = Double.parseDouble(line[2]);
                    double vx = Double.parseDouble(line[3]);
                    double vy = Double.parseDouble(line[4]);
                    SmallEnemy enemy = new SmallEnemy(x, y);
                    double hp = Double.parseDouble(line[5]);
                    enemy.setVelocity(vx, vy);
                    enemy.setHp(hp);
                }
                else if(line[0].contains("BossEnemy")){
                    double x = Double.parseDouble(line[1]);
                    double y = Double.parseDouble(line[2]);
                    double vx = Double.parseDouble(line[3]);
                    double vy = Double.parseDouble(line[4]);
                    BossEnemy enemy = new BossEnemy(x, y);
                    double hp = Double.parseDouble(line[5]);
                    enemy.setVelocity(vx, vy);
                    enemy.setHp(hp);
                }
                else{
                    //code here
                }
            }
        }catch (Exception e){
            System.err.println(e.toString());
        }
    }
}
