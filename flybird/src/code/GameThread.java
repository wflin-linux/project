package code;

/**
 * 游戏的线程类
 */

public class GameThread extends Thread {
    GameUI gui;

    // 使用构造器传入游戏界面gui，方便调用里面定义的参数
    public GameThread(GameUI gui) {
        this.gui = gui;
    }

    // 线程的run方法
    @Override
    public void run() {
        try {
            // 调用BirdGame类中的action()方法，开始游戏
            gui.birdGame.action();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
