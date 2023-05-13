package Tank;

import Spell.Spell_1;

import java.util.Vector;

public class Hero extends Base{
    private final int team =1;//坦克队伍
    public Thread thread = null;
    public Spell_1 newPlayerSpell;
    public Vector<Spell_1> spells = new Vector<>();
    public Hero(int x , int y ){
        super(x,y);
    }//坦克初始位置
    public int getTeam(){
        return team;
    }

    public void shot(){
        spells.add(newPlayerSpell = new Spell_1(9,getTankX(),getTankY(),getDirection(),this));
        thread = new Thread(newPlayerSpell);
        thread.start();
    }
}
