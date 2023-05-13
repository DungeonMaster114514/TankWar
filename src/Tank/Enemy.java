package Tank;

public class Enemy extends Base{

    private final int team =100;
    public Enemy(int x, int y) {
        super(x, y);
    }

    public int getTeam() {
        return team;
    }
}
