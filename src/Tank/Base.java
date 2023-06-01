package Tank;

public class Base {

    private int tankX;
    private int tankY;
    private int direction;
    private int speed = 5;

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setTankX(int tankX) {
        this.tankX = tankX;
    }

    public void setTankY(int tankY) {
        this.tankY = tankY;
    }

    public Base(int x, int y){
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
    public void moveRightTankX(){
        if (tankX < 1640){
            tankX += speed;
        }
    }

    public void moveLeftTankX(){
        if(tankX > 0){
            tankX -= speed;
        }
    }

    public void moveUpTankY(){
        if (tankY > 0){
            tankY -= speed;
        }
    }

    public void moveTankY(){
        if (tankY < 900){
            tankY += speed;
        }
    }

    public int getDirection() {
        return direction;
    }
}
