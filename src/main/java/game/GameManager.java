package game;

import game.enemy.NormalEnemy;
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
    public void loadData(){
//        System.err.println("CUong pro ");
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
//                    double level = Double.parseDouble(line[3]);
                    new SniperTower(x, y);
                }
                else if(line[0].contains("MachineGunTower")){
                    double x = Double.parseDouble(line[1]);
                    double y = Double.parseDouble(line[2]);
//                    double level = Double.parseDouble(line[3]);
                    new MachineGunTower(x, y);
                }
                else if(line[0].contains("SpreadTower")){
                    double x = Double.parseDouble(line[1]);
                    double y = Double.parseDouble(line[2]);
//                    double level = Double.parseDouble(line[3]);
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
                //Bullet
//                else if(line[0].contains("Bullet")){
//                    double x = Double.parseDouble(line[1]);
//                    double y = Double.parseDouble(line[2]);
//                    double target_x = Double.parseDouble(line[3]);
//                    double target_y = Double.parseDouble(line[4]);
//                    Bullet bullet = new Bullet(x, y);
//                    bullet.setTarget(new Vector2D(target_x, target_y));
//                }
                else{
                    //code here
                }
            }
        }catch (Exception e){
            System.err.println(e.toString());
        }
    }
}
