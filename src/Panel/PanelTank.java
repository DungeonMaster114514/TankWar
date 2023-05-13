package Panel;

import Spell.Spell_1;
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
    Thread thread = new Thread(this);//该线程能够一直重绘
    int enemyNum = 5;
    public PanelTank(){
        hero = new Hero(0,300);

        for (int i = 0; i < enemyNum; i++) {//将敌人放入集合当中
            enemy.add(new Enemy(i*100,10));
            enemy.get(i).setDirection(3);
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
        if(e.getKeyCode() == KeyEvent.VK_A){
            hero.moveTankX();
            hero.setDirection(3);
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            hero.moveUpTankX();
            hero.setDirection(2);
        }
        else if(e.getKeyCode() == KeyEvent.VK_W){
            hero.moveUpTankY();
            hero.setDirection(0);
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            hero.moveTankY();
            hero.setDirection(1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_J){
            hero.shot();
        }

    }

    //属于接口KeyListener,当松开键盘按键时调用
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        DrawTank(graphics, hero.getTankX(), hero.getTankY(), hero.getDirection(), hero.getTeam());//绘制玩家坦克

        for (Enemy enemy1 : enemy) {//绘制敌人坦克
            DrawTank(graphics, enemy1.getTankX(), enemy1.getTankY(), enemy1.getDirection(), enemy1.getTeam());
        }

        for (Spell_1 playerSpell : hero.spells) {
            if (hero.thread != null && hero.thread.isAlive()){//绘制子弹{
                    DrawSpell(graphics, playerSpell.getX(), playerSpell.getY(), playerSpell.getDirection());
            }
        }
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
    public void DrawSpell(Graphics graphics,int tankX,int tankY,int direction){
        switch (direction){
            case 0:
                graphics.fill3DRect(tankX+33,tankY+10,6,6,true);
                break;
            case 1:
                graphics.fill3DRect(tankX+33,tankY+105,6,6,true);
                break;
            case 2:
                graphics.fill3DRect(tankX+85,tankY+62,6,6,true);
                break;
            case 3:
                graphics.fill3DRect(tankX-18,tankY+62,6,6,true);
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repaint();
        }
    }
}
