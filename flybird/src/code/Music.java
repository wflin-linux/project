package code;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * 音乐类
 */

public class Music {
    // 设置标识符，用来判断播放bgm1或bgm2
    public int flag;
    // 创建一个Clip对象
    public Clip bgm;

    public Music(int flag) {
        this.flag = flag;
    }

    // 播放音乐
    public void play() {
        try {
            bgm = AudioSystem.getClip();
            // bgm1
            if (flag == 0) {
                // 背景音乐路径，记得加后缀名.wav
                InputStream is1 = Music.class.getClassLoader().getResourceAsStream("music/background.wav");
                AudioInputStream ais1 = AudioSystem.getAudioInputStream(is1);
                // 打开音乐
                bgm.open(ais1);
                // 播放
                bgm.start();
                // 循环播放
                bgm.loop(Clip.LOOP_CONTINUOUSLY);
                System.out.println("播放音乐，成功！"); // 打印“播放音乐，成功！”
            }
            // bgm2
            else if (flag == 1) {
                InputStream is2 = Music.class.getClassLoader().getResourceAsStream("music/jump.wav");
                AudioInputStream ais2 = AudioSystem.getAudioInputStream(is2);
                // 打开音乐
                bgm.open(ais2);
                // 播放
                bgm.start();
            }

        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}
