package MyPanel;
import Spell.Spell_1;
import Spell.Spell_2;
import Tank.Bomb;
import Tank.Enemy;
import Tank.Hero;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
public class PanelTank extends JPanel implements KeyListener,Runnable {
    Hero hero ;
    Vector<Enemy> enemy = new Vector<>();//创建一个用于储存敌人的线程安全的集合
    Vector<Bomb> bombs = new Vector<>();//创建一个用于储存敌人爆炸效果的集合
    Image image ;
    Image image2 ;
    Image image3 ;
    Image image4 ;
    Thread thread = new Thread(this);//该线程能够一直重绘
    int enemyNum = 10;//设置敌人数量
    public PanelTank(){
        hero = new Hero(800,600);

        for (int i = 0; i < enemyNum; i++) {//将敌人放入集合当中
            enemy.add(new Enemy(i*100,10));
            enemy.get(i).setDirection(1);
        }

        for (Enemy value : enemy) {//将敌人放入集合当中
            value.MoveThread.start();
        }
        thread.start();

    }

    //属于接口KeyListener,有字符输出时调用
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //属于接口KeyListener,当按下键盘按键时调用
    @Override
    public void keyPressed(KeyEvent e) {
        if (hero.isLive){
            if (e.getKeyCode() == KeyEvent.VK_A) {
                hero.moveLeftTankX();
                hero.setDirection(3);
            } else if (e.getKeyCode() == KeyEvent.VK_D) {
                hero.moveRightTankX();
                hero.setDirection(2);
            } else if (e.getKeyCode() == KeyEvent.VK_W) {
                hero.moveUpTankY();
                hero.setDirection(0);
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                hero.moveTankY();
                hero.setDirection(1);
            } else if (e.getKeyCode() == KeyEvent.VK_J) {
                hero.shot();
            }
        }
    }

    //属于接口KeyListener,当松开键盘按键时调用
    @Override
    public void keyReleased(KeyEvent e) {

    }
    //--------------------判断敌方坦克是否被击中（通过控制isLive来控制坦克是否消失）
    public void Check(){
        for (Spell_1 spell1 : hero.spells) {
            for (Enemy enemy1 : enemy) {
                switch (enemy1.getDirection()){
                    case 0://敌人坦克朝上
                    case 1://敌人坦克朝下
                        if (spell1.getX() < enemy1.getTankX() + 70 && spell1.getX() > enemy1.getTankX()-10 &&
                            spell1.getY() < enemy1.getTankY() + 100 && spell1.getY() > enemy1.getTankY() + 20) {
                            enemy1.isLive = false;
                            spell1.isLive = false;
                            bombs.add(new Bomb(enemy1.getTankX(), enemy1.getTankY()));//创建爆炸效果
                        }
                        break;
                    case 2://敌人坦克朝右
                    case 3://敌人坦克朝左
                        if (spell1.getX() < enemy1.getTankX() + 60 && spell1.getX() > enemy1.getTankX() - 40 &&
                                spell1.getY() < enemy1.getTankY() + 100 && spell1.getY() > enemy1.getTankY() +30) {
                            enemy1.isLive = false;
                            spell1.isLive = false;
                            bombs.add(new Bomb(enemy1.getTankX(), enemy1.getTankY()));//创建爆炸效果
                        }
                        break;
                }
            }
        }

        for (Enemy enemy1: enemy) {
            for (Spell_2 spell2: enemy1.enemySpells){
                switch (enemy1.getDirection()){
                    case 0://坦克朝上
                    case 1://坦克朝下
                        if (spell2.getX() < hero.getTankX() + 70 && spell2.getX() > hero.getTankX()-10 &&
                                spell2.getY() < hero.getTankY() + 100 && spell2.getY() > hero.getTankY() + 20) {
                            hero.isLive = false;
                            spell2.isLive = false;
                            hero.ResurgenceWait();//等待复活
                            bombs.add(new Bomb(hero.getTankX(), hero.getTankY()));//创建爆炸效果
                        }
                        break;
                    case 2://坦克朝右
                    case 3://坦克朝左
                        if (spell2.getX() < hero.getTankX() + 60 && spell2.getX() > hero.getTankX() - 40 &&
                                spell2.getY() < hero.getTankY() + 100 && spell2.getY() > hero.getTankY() +30) {
                            hero.isLive = false;
                            spell2.isLive = false;
                            hero.ResurgenceWait();//等待复活
                            bombs.add(new Bomb(hero.getTankX(), hero.getTankY()));//创建爆炸效果
                        }
                        break;
                }
            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        image = Toolkit.getDefaultToolkit().getImage(PanelTank.class.getResource("/Bomb1.png"));//初始化图片
        image2 = Toolkit.getDefaultToolkit().getImage(PanelTank.class.getResource("/Bomb2.png"));//初始化图片2
        image3 = Toolkit.getDefaultToolkit().getImage(PanelTank.class.getResource("/Bomb3.png"));//初始化图片3
        image4 = Toolkit.getDefaultToolkit().getImage(PanelTank.class.getResource("/Bomb4.png"));//初始化图片3

        //--------------------绘制坦克爆炸效果
        for (Bomb bomb:bombs) {
            if(bomb.life >= 500){
                graphics.drawImage(image,bomb.X-30,bomb.Y+10,130,130,this);
                System.out.println("爆炸绘制成功！");
                bomb.loseLife();
            }
            else if (bomb.life >= 400){
                graphics.drawImage(image2, bomb.X-30, bomb.Y+10, 130, 130, this);
                System.out.println("爆炸绘制成功！2");
                bomb.loseLife();
            }
            else  if (bomb.life >= 300) {
                graphics.drawImage(image4, bomb.X-30, bomb.Y+10, 130, 130, this);
                graphics.drawImage(image3, bomb.X-30, bomb.Y+10, 130, 130, this);
                System.out.println("爆炸绘制成功！3");
                bomb.loseLife();
            }
            else if ((bomb.life >= 200)){
                graphics.drawImage(image4, bomb.X-30, bomb.Y+10, 130, 130, this);
                System.out.println("爆炸绘制成功！4");
                bomb.loseLife();
            }
            else {
                bombs.remove(bomb);
            }
        }

        //--------------------绘制玩家坦克
        if (hero.isLive){
            DrawTank(graphics, hero.getTankX(), hero.getTankY(), hero.getDirection(), hero.getTeam());
        }
        else {
            hero.setTankX(100000);
            System.out.println("英雄去世了");
        }

        //--------------------绘制敌人坦克
        for (int i = 0;i < enemy.size();i++) {
            Enemy enemy1 = enemy.get(i);
            if(enemy1.isLive) {
                DrawTank(graphics, enemy1.getTankX(), enemy1.getTankY(), enemy1.getDirection(), enemy1.getTeam());
            }
            else {
                enemy.remove(enemy1);
            }
            synchronized (this){//绘制敌方子弹
                for (Spell_2 spell2 : enemy1.enemySpells) {
                    if (spell2.isLive){
                        DrawSpell(graphics, spell2.getX(), spell2.getY());
                    }
                }
            }
        }

        //--------------------绘制友方坦克子弹
        for (int i = 0;i < hero.spells.size();i++) {
            Spell_1 playerSpell = hero.spells.get(i);
            if (hero.thread != null && playerSpell.isLive){
                //判断发射子弹时坦克的方向
                DrawSpell(graphics, playerSpell.getX(), playerSpell.getY());
            }
            else {
                hero.spells.remove(playerSpell);
            }
        }
        Check();
    }

    /**
     * @param tankX tank的X坐标
     * @param tankY tank的Y坐标
     * @param direction tank的朝向
     * @param team tank的队伍
     */

    public void DrawTank(Graphics graphics,int tankX,int tankY,int direction,int team){

        switch (direction){
            case 0://坦克朝上时

                //坦克本体
                graphics.setColor(new Color(team,100,50));//color
                graphics.fill3DRect(tankX,tankY+25,15,80,true);//履带1
                graphics.fill3DRect(tankX+55,tankY+25,15,80,true);//履带2
                graphics.setColor(new Color(team,75,50));//BaseColor
                graphics.fill3DRect(tankX+15,tankY+35,40,60,true);//车体

                //炮塔与炮管
                graphics.setColor(new Color(team,100,50));
                graphics.fill3DRect(tankX+34,tankY+10,4,40,true);
                graphics.setColor(new Color(team,110,50));
                graphics.fillOval(tankX+20,tankY+50,30,30);
                break;
            case 1://坦克朝下时

                //坦克本体
                graphics.setColor(new Color(team,100,50));//color
                graphics.fill3DRect(tankX,tankY+25,15,80,true);//履带1
                graphics.fill3DRect(tankX+55,tankY+25,15,80,true);//履带2
                graphics.setColor(new Color(team,75,50));//BaseColor
                graphics.fill3DRect(tankX+15,tankY+35,40,60,true);//车体

                //炮塔与炮管
                graphics.setColor(new Color(team,100,50));
                graphics.fill3DRect(tankX+34,tankY+80,4,40,true);
                graphics.setColor(new Color(team,110,50));
                graphics.fillOval(tankX+20,tankY+50,30,30);
                break;
            case 2://坦克朝右时
                graphics.setColor(new Color(team,100,50));//color
                graphics.fill3DRect(tankX-5,tankY+30,80,15,true);//履带1
                graphics.fill3DRect(tankX-5,tankY+85,80,15,true);//履带2
                graphics.setColor(new Color(team,75,50));//BaseColor
                graphics.fill3DRect(tankX+5,tankY+45,60,40,true);//车体

                //炮塔与炮管
                graphics.setColor(new Color(team,100,50));
                graphics.fill3DRect(tankX+50,tankY+63,40,4,true);
                graphics.setColor(new Color(team,110,50));
                graphics.fillOval(tankX+20,tankY+50,30,30);
                break;

            case 3://坦克朝左时
                graphics.setColor(new Color(team,100,50));//color
                graphics.fill3DRect(tankX-5,tankY+30,80,15,true);//履带1
                graphics.fill3DRect(tankX-5,tankY+85,80,15,true);//履带2
                graphics.setColor(new Color(team,75,50));//BaseColor
                graphics.fill3DRect(tankX+5,tankY+45,60,40,true);//车体

                //炮塔与炮管
                graphics.setColor(new Color(team,100,50));
                graphics.fill3DRect(tankX-15,tankY+63,40,4,true);
                graphics.setColor(new Color(team,110,50));
                graphics.fillOval(tankX+20,tankY+50,30,30);
                break;
        }
    }
    public void DrawSpell(Graphics graphics,int tankX,int tankY){
        graphics.fill3DRect(tankX,tankY,6,6,true);
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repaint();
        }
    }
}

