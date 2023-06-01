package Tank;

public class Bomb {
    public Bomb(int X,int Y){
        this.X = X;
        this.Y = Y;
    }
    public int X;
    public int Y;
    public boolean isLive = true;

    public int life = 600;
    public void loseLife(){
        if(life > 0){
            --this.life;
        }
        else {
            isLive = false;
        }
    }
}
