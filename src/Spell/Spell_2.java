package Spell;

import Tank.Enemy;
public class Spell_2 implements Runnable {
    public Spell_2(int spellSpeed, int X, int Y, int Direction,Enemy enemy){
        this.SpellSpeed = spellSpeed;
        this.Direction = Direction;
        this.X = X;
        this.Y = Y;
        this.enemy = enemy;
    }
    public int SpellSpeed;
    public int Direction;
    Enemy enemy;//用于移除多余的子弹对象
    private int X;
    private int Y;
    public boolean isLive = true;

    public int getDirection() {
        return Direction;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public void run() {
        while (X <= 1680 && Y <= 1000 && X >= 0 && Y >= 0){
            switch (Direction) {
                case 0:
                    Y -= SpellSpeed;
                    break;
                case 1:
                    Y += SpellSpeed;
                    break;
                case 2:
                    X += SpellSpeed;

                    break;
                case 3:
                    X -= SpellSpeed;
                    break;
            }
            System.out.println(Thread.currentThread().getName()+"子弹射出来力,坐标X:"+X+"坐标Y:"+Y);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        enemy.enemySpells.remove(this);
        System.out.println(Thread.currentThread().getName()+"敌人子弹以销毁");
    }
}

