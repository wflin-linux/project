package code;

/**
 * 游戏类
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class BirdGame extends JPanel {
    // 背景图片
    BufferedImage background;
    // 开始图片
    BufferedImage startImage;
    // 结束图片
    BufferedImage gameOverImage;
    // 地面
    Ground ground;
    // 柱子
    Column column1, column2;
    // 小鸟
    Bird bird;
    // 音乐类
    Music bgm;
    // 用户名
    String name;
    // 游戏分数
    int score;
    // 游戏状态
    int state;
    // 状态常量
    public static final int START = 0;// 开始
    public static final int RUNNING = 1;// 运行
    public static final int GAME_OVER = 2;// 结束

    /**
     * 初始化游戏
     */
    public BirdGame(String name) throws Exception {
        // 初始化音乐类
        bgm = new Music(1);
        // 获取登录用户名
        this.name = name;
        // 初始化背景图片
        background = ImageIO.read(getClass().getResource("/resources/bg.png"));
        // 初始化开始、结束图片
        startImage = ImageIO.read(getClass().getResource("/resources/start.png"));
        gameOverImage = ImageIO.read(getClass().getResource("/resources/gameover.png"));
        // 初始化地面、柱子、小鸟
        ground = new Ground();
        column1 = new Column(1);
        column2 = new Column(2);
        bird = new Bird();

        // 初始化分数
        score = 0;

        // 初始化状态
        state = START;

    }

    /**
     * 绘制界面
     */
    public void paint(Graphics g) {
        // 绘制背景
        g.drawImage(background, 0, 0, null);
        // 绘制地面
        g.drawImage(ground.image, ground.x, ground.y, null);

        // 绘制柱子
        g.drawImage(column1.image, column1.x - column1.width / 2, column1.y - column1.height / 2, null);
        g.drawImage(column2.image, column2.x - column2.width / 2, column2.y - column2.height / 2, null);

        // 绘制小鸟（旋转坐标系）
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(-bird.alpha, bird.x, bird.y);
        g.drawImage(bird.image, bird.x - bird.width / 2, bird.y - bird.height / 2, null);
        g2.rotate(bird.alpha, bird.x, bird.y);

        // 绘制分数
        Font f1 = new Font(Font.SANS_SERIF, Font.BOLD, 27);
        Font f2 = new Font(Font.SANS_SERIF, Font.BOLD, 40);
        g.setFont(f1);
        g.drawString("得分:", 10, 60);
        g.setColor(Color.WHITE);
        g.drawString("得分:", 10 - 2, 60 - 2);
        g.setFont(f2);
        g.setColor(Color.BLACK);
        g.drawString("" + score, 80, 65);
        g.setColor(Color.WHITE);
        g.drawString("" + score, 80 - 3, 65 - 3);

        // 绘制开始与结束界面
        switch (state) {
            case START:
                g.drawImage(startImage, 0, 0, null);
                break;
            case GAME_OVER:
                g.drawImage(gameOverImage, 0, 0, null);
                break;
        }
    }

    // 开始游戏
    public void action() throws Exception {
        // 连接数据库的类
        Jdbc jdbc = new Jdbc();
        // 调试-> 输出用户名
        System.out.println(name);
        MouseListener l = new MouseAdapter() {
            // 鼠标按下事件
            public void mousePressed(MouseEvent e) {
                try {
                    switch (state) {
                        case START:
                            // 在开始状态，按下鼠标则转为运行状态。
                            state = RUNNING;
                            break;
                        case RUNNING:
                            // 在运行状态，按下鼠标则小鸟向上飞行并且播放小鸟的声音。
                            bgm.play();
                            bird.flappy();
                            break;
                        case GAME_OVER:
                            // 在结束状态，按下鼠标则重置数据，再次转为开始态。
                            column1 = new Column(1);
                            column2 = new Column(2);
                            bird = new Bird();
                            score = 0;
                            state = START;
                            break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        // 将监听器添加到当前的面板上
        addMouseListener(l);
        // 不断的移动与重绘
        while (true) {
            switch (state) {
                case START:
                    // 小鸟做出飞行动作
                    bird.fly();
                    // 地面向左移动一步
                    ground.step();
                    break;
                case RUNNING:
                    // 地面向左移动一步
                    ground.step();
                    // 柱子向左移动一步
                    column1.step();
                    column2.step();
                    // 小鸟做出飞行动作
                    bird.fly();
                    // 小鸟上下移动一步
                    bird.step();
                    // 计算分数
                    if (bird.x == column1.x || bird.x == column2.x) {
                        score++;
                    }
                    // 检测是否发生碰撞
                    if (bird.hit(ground) || bird.hit(column1) || bird.hit(column2)) {
                        // 记录数据库中执行DML语句的条数以及指定用户获取的分数
                        int[] count = {0, 0};
                        // 获取指定用户的分数
                        count = jdbc.score(name);
                        if (count[0] != 0) {
                            System.out.println("查询指定用户: " + name + " 成功!");
                            if (count[1] < score) {
                                // 将其置为0
                                count[0] = 0;
                                // 更新用户信息
                                count[0] = jdbc.update(name, score);
                                if (count[0] != 0) {
                                    System.out.println("更新用户信息成功!");
                                } else {
                                    System.out.println("更新用户信息失败!");
                                }
                            }
                        }
                        else {
                            System.out.println("查询用户失败！");
                        }

                        state = GAME_OVER;
                    }
                    break;
            }
            // 重新绘制界面
            repaint();
            // 休眠 1000/60 毫秒
            Thread.sleep(1000 / 60);
        }

    }

    /**
     * 小鸟
     */
    public static class Bird {
        //图片
        BufferedImage image;
        //位置
        int x, y;
        //宽高
        int width, height;
        //大小（用于碰撞检测）
        int size;
        //重力加速度
        double g;
        //位移的间隔时间
        double t;
        //最初上抛速度
        double v0;
        //当前上抛速度
        double speed;
        //经过时间t之后的位移
        double s;
        //小鸟的倾角（弧度）
        double alpha;
        //一组图片，记录小鸟的动画帧
        BufferedImage[] images;
        //动画帧数组的下标
        int index;

        //初始化小鸟
        public Bird() throws Exception {
            //初始化基本参数
            image = ImageIO.read(getClass().getResource("/resources/0.png"));
            width = image.getWidth();
            height = image.getHeight();
            x = 132;
            y = 280;
            size = 40;
            //初始化位移参数
            g = 4;
            v0 = 20;
            t = 0.25;
            speed = v0;
            s = 0;
            alpha = 0;
            //初始化动画帧参数
            images = new BufferedImage[8];
            for (int i = 0; i < 8; i++) {
                images[i] = ImageIO.read(getClass().getResource("/resources/" + i + ".png"));

            }
            index = 0;
        }

        //飞行动作
        public void fly() {
            index++;
            image = images[(index / 10) % 8];
        }

        //移动一步
        public void step() {
            double v0 = speed;
            //计算上抛运动位移
            s = v0 * t + g * t * t / 2;
            //计算鸟的坐标位置
            y = y - (int) s;
            //计算下次移动速度
            double v = v0 - g * t;
            speed = v;
            //计算倾角（反正切函数）
            alpha = Math.atan(s / 8);
        }

        //向上飞行
        public void flappy() {
            //重置速度
            speed = v0;
        }

        //检测小鸟是否碰撞到地面
        public boolean hit(Ground ground) {
            boolean hit = y + size / 2 > ground.y;
            if (hit) {
                y = ground.y - size / 2;
                alpha = -3.14159265358979323 / 2;
            }
            return hit;
        }

        //检测小鸟是否撞到柱子
        public boolean hit(Column column) {
            //先检测是否在柱子的范围内
            if (x > column.x - column.width / 2 - size / 2
                    && x < column.x + column.width / 2 + size / 2) {
                //再检测是否在柱子的缝隙中
                if (y > column.y - column.gap / 2 - size / 2
                        && y < column.y + column.gap / 2 - size / 2) {
                    return false;
                }
                return true;
            }
            return false;
        }

    }
}
