package code;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * 登录用户的对话框
 */

public class LoginUser extends JDialog {
    // 用户名标签，密码标签
    JLabel nameLab = new JLabel("用户名：");
    JLabel pswLab = new JLabel("密    码：");
    // 用户输入姓名文本框
    JTextField nameTf = new JTextField();
    // 用户输入密码框
    JPasswordField pswPf = new JPasswordField();
    // 取消按钮，注册按钮
    JButton cancelBtn = new JButton("取消");
    JButton loginBtn = new JButton("登录");
    // 用户监听器
    UserListeners u;

    public LoginUser(JFrame parent) {
        // 显式调用父类构造方法，创建模态对话框
        super(parent, "登录", true);
        // 设置对话框为空布局
        this.setLayout(null);

        // 设置用户名标签，用户文本框的字体大小以及位置
        nameLab.setFont(new Font("Serif", Font.BOLD, 13));
        nameLab.setBounds(20, 20, 60, 12);
        nameTf.setFont(new Font("Serif", Font.BOLD, 13));
        nameTf.setBounds(82, 17, 120, 20);

        // 设置密码标签，用户密码框的字体大小以及位置
        pswLab.setFont(new Font("Serif", Font.BOLD, 13));
        pswLab.setBounds(20, 50, 60, 12);
        pswPf.setFont(new Font("Serif", Font.BOLD, 13));
        pswPf.setBounds(82, 47, 120, 20);

        // 设置取消按钮的字体大小以及位置
        cancelBtn.setFont(new Font("Serif", Font.BOLD, 13));
        cancelBtn.setBounds(15, 80, 70, 20);

        // 设置注册按钮的字体大小以及位置
        loginBtn.setFont(new Font("Serif", Font.BOLD, 13));
        loginBtn.setBounds(130, 80, 70, 20);

        try {
            // 实例化u并将这个继承于JDialog的类传过去
            u = new UserListeners(this);
            u.login();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 将这些组件添加到对话框中
        this.add(nameLab);
        this.add(nameTf);
        this.add(pswLab);
        this.add(pswPf);
        this.add(cancelBtn);
        this.add(loginBtn);

        // 设置对话框大小
        this.setSize(230, 150);
        // 设置对话框位于父窗口中心
        this.setLocationRelativeTo(parent);
        // 关闭后结束该对话框所有进程
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // 显示对话框，对话框关闭前，程序将阻塞于此
        this.setVisible(true);
    }

}
