// VolumeController.java
package game;

import java.util.Scanner;

public class VolumeController {
    public static boolean setVolume(Scanner scanner) {
        while (true) {
            System.out.println("设置音量：\n1. 背景音乐\n2. 效果音\n3. 切换背景音乐静音状态\n4. 切换效果音静音状态\n5. 退出设置");
            int volumeChoice = scanner.nextInt();
            if (volumeChoice == 1) {
                System.out.println("输入背景音乐音量（-80 到 6）：");
                float bgVolume = scanner.nextFloat();
                AudioPlayer.setVolume(bgVolume, true);
            } else if (volumeChoice == 2) {
                System.out.println("输入效果音音量（-80 到 6）：");
                float effectVolume = scanner.nextFloat();
                AudioPlayer.setVolume(effectVolume, false);
            } else if (volumeChoice == 3) {
                AudioPlayer.toggleMusicMute();
                System.out.println("背景音乐静音状态已切换。");
            } else if (volumeChoice == 4) {
                AudioPlayer.toggleEffectMute();
                System.out.println("效果音静音状态已切换。");
            } else if (volumeChoice == 5) {
                return false; // 退出设置
            } else {
                System.out.println("无效的选项！");
            }
        }
    }
}