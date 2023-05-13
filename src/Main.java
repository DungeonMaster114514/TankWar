import Panel.PanelTank;
import javax.swing.*;

public class Main extends JFrame {//Listener为监听器,用于监听键盘事件
    PanelTank panelTank = new PanelTank();
    public static void main(String[] args) {
        Main main = new Main();//new 一个窗口(其实就是JFrame)
    }

    public Main(){
        //初始化框架
        this.add(panelTank);
        this.addKeyListener(panelTank);//使框架JFrame 能够接收到PanelTank面板监听的键盘事件
        this.setSize(2560,1600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}