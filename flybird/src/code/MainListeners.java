package code;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

/**
 * 主界面监听器
 */

public class MainListeners implements MouseListener {
    MainUI ui;
    int clickx, clicky;
    // 登录对话框是否关闭的标识符
    boolean flag = false;
    // 登录用户名
    String name;

    // 通过构造器传入主界面，方便调用主界面定义的参数
    public MainListeners(MainUI ui) {
        this.ui = ui;
    }

    public void mouseClicked(MouseEvent e) {
        // 获得x,y坐标
        clickx = e.getX();
        clicky = e.getY();
        // 调试-> 输出鼠标点击的位置
        System.out.println(clickx + " " + clicky);

        // 当鼠标点击到PATE范围时，打开注册界面
        if (clickx > 170 && clickx < 262 && clicky > 350 && clicky < 408) {
            RegisteredUser registeredUser = new RegisteredUser(ui.frame);

        }
        // 当鼠标点击到"开始"范围时,先打开登录对话框，然后打开游戏界面
        else if (clickx >40 && clickx < 192 && clicky > 472 && clicky < 556) {
            LoginUser loginUser = new LoginUser(ui.frame);
            // 获取登录对话框的状态
            flag = loginUser.u.flag;
            // 获取登录用户名
            name = loginUser.u.name;
            //实例化GameUI
            GameUI ui1 = null;
            // 登录对话框关闭，则启动游戏
            if (flag) {
                try {
                    ui1 = new GameUI(name);
                    ui1.show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        // 当鼠标点击到"排名"范围时，打开排名对话框
        else if (clickx >246 && clickx < 392 && clicky > 492 && clicky < 577) {
            try {
                Rank rank = new Rank(ui.frame);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
