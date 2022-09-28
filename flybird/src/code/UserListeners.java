package code;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * 用户监听器
 */

public class UserListeners {
    // 注册用户类
    RegisteredUser registeredUser;
    // 登录用户类
    LoginUser loginUser;
    // 连接数据库类
    Jdbc jdbc = new Jdbc();
    // 用户名
    String name;
    // 用户密码
    char[] psw;
    String password;
    // 登录对话框是否关闭的标识符
    boolean flag = false;

    // 传入注册用户的构造方法
    public UserListeners(RegisteredUser registeredUser) throws SQLException, ClassNotFoundException {
        this.registeredUser = registeredUser;

    }

    // 传入登录用户的构造方法
    public UserListeners(LoginUser loginUser) throws SQLException, ClassNotFoundException {
        this.loginUser = loginUser;

    }

    // 注册
    public void register(){
        // 为注册用户的对话框中的注册按钮添加监听器
        this.registeredUser.registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = 0;
                // 获取注册用户姓名
                name = registeredUser.nameTf.getText();
                // 获取注册用户的密码
                psw = registeredUser.pswPf.getPassword();
                password = new String(psw);
                count = jdbc.insert(name, password);
                // 调试
                System.out.println(name);
                System.out.println(password);
                // 注册成功
                if (count != 0) {
                    JOptionPane.showMessageDialog(registeredUser, "注册用户成功!", "注册成功", JOptionPane.PLAIN_MESSAGE);
                    // 释放数据库资源
                    jdbc.releaseResource();
                    // 关闭后结束该对话框所有进程
                    registeredUser.dispose();
                }
                // 注册失败
                else {
                    JOptionPane.showMessageDialog(registeredUser, "注册用户失败，请重新注册!!!", "注册失败", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        // 为注册用户的对话框中的取消按钮添加监听器
        this.registeredUser.cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 释放数据库资源
                jdbc.releaseResource();
                // 关闭后结束该对话框所有进程
                registeredUser.dispose();
            }
        });
    }

    // 登录
    public void login() {
        // 为登录用户的对话框中的登录按钮添加监听器
        this.loginUser.loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean loginSuccess = false;
                // 获取登录用户姓名
                name = loginUser.nameTf.getText();
                // 获取登录用户的密码
                psw = loginUser.pswPf.getPassword();
                password = new String(psw);
                loginSuccess = jdbc.compare(name, password);
                // 调试-> 输出用户名和密码
                System.out.println(name);
                System.out.println(password);
                // 登录成功
                if (loginSuccess) {
                    // 登录对话框关闭，设为true
                    flag = true;
                    JOptionPane.showMessageDialog(loginUser, "登录用户成功!", "登录成功", JOptionPane.PLAIN_MESSAGE);
                    // 释放数据库资源
                    jdbc.releaseResource();
                    // 关闭后结束该对话框所有进程
                    loginUser.dispose();
                }
                // 登录失败
                else {
                    JOptionPane.showMessageDialog(loginUser, "登录用户失败，用户名或密码有误，请重新登录!!!", "登录失败", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // 为登录用户的对话框中的取消按钮添加监听器
        this.loginUser.cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 释放数据库资源
                jdbc.releaseResource();
                // 关闭后结束该对话框所有进程
                loginUser.dispose();
            }
        });
    }
}
