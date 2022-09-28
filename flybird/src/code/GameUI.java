package code;

import javax.swing.*;

/**
 * 游戏界面
 */

public class GameUI {
    public JFrame gFrame = new JFrame("飞翔的小鸟");
    public BirdGame birdGame;
    public GameThread gameThread;

    public GameUI(String name) throws Exception {
        // 实例化游戏的线程类并传入游戏界面类
        gameThread = new GameThread(this);
        // 将登录用户名传入到游戏类
        birdGame = new BirdGame(name);
    }

    public void show() {
        // 给gFrame添加JPanel
        gFrame.add(birdGame);

        // 设置窗体容器
        gFrame.setSize(440, 670);
        gFrame.setLocationRelativeTo(null);
        // 隐藏和释放该窗口
        gFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // 禁止改变大小
        gFrame.setResizable(false);
        // 显示游戏界面
        gFrame.setVisible(true);

        // 调用游戏开始的方法
        this.startGame();
    }

    // 开始游戏
    public void startGame() {
        // 开启线程
        gameThread.start();
    }
}
