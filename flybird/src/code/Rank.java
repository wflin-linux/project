package code;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * 排名类
 */

public class Rank extends JDialog {
    // 标签个数
    int count = 5;
    // 用户名
    String[] name;
    // 分数
    int[] score;
    // 排名标签数组
    JLabel[] labs = new JLabel[count];
    // 排名文本框数组
    //JTextField[] tefs = new JTextField[count];
    // 连接数据库类
    Jdbc jdbc = new Jdbc();

    public Rank(JFrame parent) throws SQLException, ClassNotFoundException {
        // 显式调用父类构造方法，创建模态对话框
        super(parent, "前五排名", true);
        // 设置对话框为空布局
        this.setLayout(null);
        // 获取用户名
        name = jdbc.name();
        // 获取分数
        score = jdbc.score();
        // 释放数据库资源
        jdbc.releaseResource();
        // 初始化排名标签数组
        for (int i = 0; i < labs.length; i++) {
            labs[i] = new JLabel();
            if (name[i] != null && i == 0) {
                labs[i].setText("第一名：" + name[i] + "    " + score[i]);
            }
            else if (name[i] != null && i == 1) {
                labs[i].setText("第二名：" + name[i] + "    " + score[i]);
            }
            else if (name[i] != null && i == 2) {
                labs[i].setText("第三名：" + name[i] + "    " + score[i]);
            }
            else if (name[i] != null && i == 3) {
                labs[i].setText("第四名：" + name[i] + "    " + score[i]);
            }
            else if (name[i] != null && i == 4) {
                labs[i].setText("第五名：" + name[i] + "    " + score[i]);
            }
            this.add(labs[i]);
        }

        labs[0].setFont(new Font("Serif", Font.BOLD, 18));
        labs[0].setBounds(15, 15, 160, 20);

        labs[1].setFont(new Font("Serif", Font.BOLD, 18));
        labs[1].setBounds(15, 45, 160, 20);

        labs[2].setFont(new Font("Serif", Font.BOLD, 18));
        labs[2].setBounds(15, 75, 160, 20);

        labs[3].setFont(new Font("Serif", Font.BOLD, 18));
        labs[3].setBounds(15, 105, 160, 20);

        labs[4].setFont(new Font("Serif", Font.BOLD, 18));
        labs[4].setBounds(15, 135, 160, 20);

        // 设置对话框大小
        this.setSize(200, 210);
        // 设置对话框位于父窗口中心
        this.setLocationRelativeTo(parent);
        // 关闭后结束该对话框所有进程
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // 显示对话框，对话框关闭前，程序将阻塞于此
        this.setVisible(true);
    }
}
