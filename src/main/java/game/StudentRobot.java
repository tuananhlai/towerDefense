package game;

import game.screen.PlayScreen;
import game.tower.MachineGunTower;
import game.tower.NormalTower;
import game.tower.SniperTower;
import game.tower.SpreadTower;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class StudentRobot {
    /**
     * find all points which are conner
     */
    public static void findPositionsAdvantage(){
        GameField.listPoint.clear();
        for(int i = 10; i > 0; i--){
            for(int j = 18; j > 0; j--){
                int numOfNeighborsRoad = 0;
                if(GameField.map[i][j+1] == 0){
                    numOfNeighborsRoad++;
                }
                if(GameField.map[i][j-1] == 0){
                    numOfNeighborsRoad++;
                } if(GameField.map[i-1][j] == 0){
                    numOfNeighborsRoad++;
                } if(GameField.map[i+1][j] == 0){
                    numOfNeighborsRoad++;
                }
                if(numOfNeighborsRoad >= 2 && GameField.map[i][j] == 1){
                    GameField.listPoint.add(new Vector2D(i, j));
                    //
//                    System.err.println(i + " " + j + " map " + GameField.map[i][j]);
                }
            }
        }
    }
    public static void readTranningResult(String url){
        try{
            Scanner sc = new Scanner(new BufferedReader(new FileReader(url)));
            while(sc.hasNextLine()) {
                for (int i=1; i<100; i++) {
                    String[] line = sc.nextLine().trim().split(" ");
                    for (int j=1; j<22; j++) {
                        GameField.trainning_result[i][j] = Integer.parseInt(line[j - 1]);
                    }
                }
            }
        }catch (Exception e){
            System.err.println(e.toString());
        }
    }

    /**
     * dua vao so dich va tien de quyet dinh mua thap
     * @param numOfEnemy so luong dich hien tai
     * @param money so tien hien tai
     */
    public static void putTowerGenius(int numOfEnemy, int money){
        if(money < 22 || numOfEnemy < 5){ //chi xet khi lon hon 21 vang va hon 4 con enemy
            return;
        }else{
            int predict = GameField.trainning_result[money - 21][numOfEnemy - 4];
            switch (predict){
                case 0:{
                    break;
                } case 1:{ //thap loai re tien nhat
                    Vector2D position = GameField.listPoint.get(0); //vi tri dat thap
                    GameField.listPoint.remove(0);
                    new NormalTower(position.y*Settings.TILE_HEIGHT, position.x*Settings.TILE_WIDTH); // xay thap tai do
                    PlayScreen.spendMoney(Settings.NORMAL_TOWER_PRICE);
                    break;
                } case 2:{ //thap loai binh thuong
                    Vector2D position = GameField.listPoint.get(0); //vi tri dat thap
                    GameField.listPoint.remove(0);
                    new SpreadTower(position.y*Settings.TILE_HEIGHT, position.x*Settings.TILE_WIDTH); // xay thap tai do
                    PlayScreen.spendMoney(Settings.SPREAD_TOWER_PRICE);
                    break;
                } case 3:{ //thap dat thu 2
                    Vector2D position = GameField.listPoint.get(0); //vi tri dat thap
                    GameField.listPoint.remove(0);
                    new MachineGunTower(position.y*Settings.TILE_HEIGHT, position.x*Settings.TILE_WIDTH); // xay thap tai do
                    PlayScreen.spendMoney(Settings.MACHINE_GUN_TOWER_PRICE);
                    break;
                } case 4:{ //thap dat nhat
                    Vector2D position = GameField.listPoint.get(0); //vi tri dat thap
                    GameField.listPoint.remove(0);
                    new SniperTower(position.y*Settings.TILE_HEIGHT, position.x*Settings.TILE_WIDTH); // xay thap tai do
                    PlayScreen.spendMoney(Settings.SNIPER_TOWER_PRICE);
                    break;
                }
                default: break;
            }
        }
    }

}
