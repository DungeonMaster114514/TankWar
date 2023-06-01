package Tank;

import Spell.Spell_1;

import java.util.Vector;

public class Hero extends Base{
    private final int team =1;//坦克队伍
    public Thread thread = null;
    public boolean isLive = true;
    Spell_1 newPlayerSpell;
    public Vector<Spell_1> spells = new Vector<>();
    public Hero(int x , int y ){
        super(x,y);
    }//坦克初始位置
    public int getTeam(){
        return team;
    }

    public void shot(){
        if (getDirection() == 0){
            spells.add(newPlayerSpell = new Spell_1(8, getTankX() + 33, getTankY() + 10, getDirection(), this));
        }
        else if(getDirection() == 1){
            spells.add(newPlayerSpell = new Spell_1(8, getTankX() + 33, getTankY() + 105, getDirection(), this));
        }
        else if(getDirection() == 2){
            spells.add(newPlayerSpell = new Spell_1(8, getTankX() + 85, getTankY() + 62, getDirection(), this));
        }
        else{
            spells.add(newPlayerSpell = new Spell_1(8, getTankX() - 18, getTankY() + 62, getDirection(), this));
        }
        thread = new Thread(newPlayerSpell);
        thread.start();
    }

    public void ResurgenceWait(){
        HeroResurgenceWait heroResurgenceWait = new HeroResurgenceWait(this);
        Thread thread1 = new Thread(heroResurgenceWait);
        thread1.start();
    }
}
