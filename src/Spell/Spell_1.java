package Spell;

import Tank.Hero;

public class Spell_1 implements Runnable {
    public Spell_1(int spellSpeed, int X, int Y, int Direction,Hero hero){
        this.SpellSpeed = spellSpeed;
        this.Direction = Direction;
        this.X = X;
        this.Y = Y;
        this.hero = hero;
    }
    private int SpellSpeed;
    private int Direction;
    Hero hero;//用于移除多余的子弹对象
    private int X;
    private int Y;


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
        while (X <= 2560 && Y <= 1600 && X >= 0 && Y >= 0){
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
        hero.spells.remove(this);
    }
}
