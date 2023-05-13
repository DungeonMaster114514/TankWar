package Tank;

public class Base {

    private int tankX;
    private int tankY;
    private int direction;

    int speed = 5;
    public Base(int x,int y){
        tankX = x;
        tankY = y;
    }
    public int getTankX() {
        return tankX;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getTankY() {
        return tankY;
    }

    //坦克移动方法
    public void moveUpTankX(){
        tankX+=speed;
    }

    public void moveTankX(){
        tankX-=speed;
    }

    public void moveUpTankY(){
        tankY-=speed;
    }

    public void moveTankY(){
        tankY+=speed;
    }

    public int getDirection() {
        return direction;
    }
}
