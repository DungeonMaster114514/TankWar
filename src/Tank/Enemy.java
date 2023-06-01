package Tank;
import Spell.Spell_2;
import java.util.Random;
import java.util.Vector;
public class Enemy extends Base implements Runnable{

    public Enemy(int x, int y) {
        super(x, y);
        setSpeed(1);
        random = new Random().nextInt(150)+200;
    }
    public int random;
    public int getTeam() {
        return team;
    }
    public Vector<Spell_2> enemySpells = new Vector<>();
    private final int team =100;//设置队伍颜色
    public boolean isLive = true;
    public Thread MoveThread = new Thread(this);//敌人移动线程
    Spell_2 newEnemySpell;//敌人子弹线程
    Thread EnemyThread ;//子弹移动线程
    int step = 0;
    public void run(){
        while (isLive) {
            toMove();
            if (enemySpells.size() == 0){
                shot();
            }
        }
    }
    public void toMove(){
        switch (getDirection()){
            case 0:
                if (getTankY() < 20){
                    setDirection(3);
                    break;
                }
                moveUpTankY();
                break;
            case 1:
                if (getTankY() > 1000){
                    setDirection(4);
                    break;
                }
                moveTankY();
                break;
            case 2:
                if (getTankX() > 1640){
                    setDirection(0);
                    break;
                }
                moveRightTankX();
                break;
            case 3:
                if (getTankX() < 20){
                    setDirection(1);
                    break;
                }
                moveLeftTankX();
                break;
        }
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        step++;
        if (step == random){
            setDirection(new Random().nextInt(3));
            step = 0;
        }
    }

    public void shot(){
        if (getDirection() == 0) {
            enemySpells.add(newEnemySpell = new Spell_2(4, getTankX() + 33, getTankY() + 10, getDirection(), this));
        } else if (getDirection() == 1) {
            enemySpells.add(newEnemySpell = new Spell_2(4, getTankX() + 33, getTankY() + 105, getDirection(), this));
        } else if (getDirection() == 2) {
            enemySpells.add(newEnemySpell = new Spell_2(4, getTankX() + 85, getTankY() + 62, getDirection(), this));
        } else {
            enemySpells.add(newEnemySpell = new Spell_2(4, getTankX() - 18, getTankY() + 62, getDirection(), this));
        }
        EnemyThread = new Thread(newEnemySpell);
        EnemyThread.start();
    }
}
