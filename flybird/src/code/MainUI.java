package code;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 主界面
 */

public class MainUI extends JPanel { // 继承JPanel父类
    // 要在其他类调用的定义为public
    public JFrame frame = new JFrame("飞翔的小鸟");
    // 主背景图片
    BufferedImage mbackground;
    // 音乐类
    Music bgm;

    public MainUI() throws IOException {
        mbackground = ImageIO.read(getClass().getResource("/resources/mbg.png"));
        bgm = new Music(0);
    }

    // 重绘主界面
    public void paint(Graphics g) {
        // 先调用一次JPanel父类中的paint方法
        super.paint(g);
        // 画图
        g.drawImage(mbackground, 0, 0, frame.getWidth(), frame.getHeight(), null);
    }

    public void showUI() {
        // 设置容器JFrame的参数
        // 大小
        frame.setSize(440,670);
        // 默认居中
        frame.setLocationRelativeTo(null);
        // 关闭后结束所有进程
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 将JPanel添加到frame中，放在中间位置
        frame.add(this,BorderLayout.CENTER);
        // 显示frame
        frame.setVisible(true);
        // 禁止改变大小
        frame.setResizable(false);

        // 实例化listeners并将这个继承于JPanel的类传过去
        MainListeners listeners = new MainListeners(this);
        // 给JPanel加监听器
        this.addMouseListener(listeners);
    }

    // 主函数
    public static void main(String[] args) throws IOException {
        MainUI ui = new MainUI();
        // 播放背景音乐
        ui.bgm.play();
        // 显示主界面
        ui.showUI();
    }
}
