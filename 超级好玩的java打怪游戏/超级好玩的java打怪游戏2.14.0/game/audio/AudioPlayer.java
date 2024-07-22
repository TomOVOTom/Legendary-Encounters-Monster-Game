// AudioPlayer.java
package game.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AudioPlayer {

    private static Clip musicClip; // 用于播放背景音乐的音频剪辑对象
    private static List<Clip> effectClips = new ArrayList<>(); // 用于播放效果音的音频剪辑对象列表
    private static boolean isMusicMuted = false; // 跟踪背景音乐是否被静音的标志
    private static boolean isEffectMuted = false; // 跟踪音效是否被静音的标志
    private static String currentMusicPath; // 当前背景音乐路径
    private static List<String> currentEffectPaths = new ArrayList<>(); // 当前效果音路径列表

    /**
     * 播放声音的方法。
     * @param filePath 音频文件路径
     * @param loop 是否循环播放
     * @param isBackgroundMusic 是否是背景音乐
     * @param volume 音量大小，范围为0.0f到1.0f
     */
    public static void playSound(String filePath, boolean loop, boolean isBackgroundMusic, float volume) {
        if ((isBackgroundMusic && isMusicMuted) || (!isBackgroundMusic && isEffectMuted)) {
            return; // 如果被静音，则不播放声音
        }

        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.err.println("音频文件未找到：" + filePath);
                return;
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat baseFormat = audioInputStream.getFormat();
            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );

            AudioInputStream decodedAudioInputStream = AudioSystem.getAudioInputStream(decodedFormat, audioInputStream);

            Clip clip;
            if (isBackgroundMusic) {
                if (musicClip != null && musicClip.isOpen()) {
                    musicClip.stop();
                    musicClip.close();
                }
                musicClip = AudioSystem.getClip();
                clip = musicClip;
                currentMusicPath = filePath; // 记录当前背景音乐路径
            } else {
                clip = getAvailableEffectClip();
                currentEffectPaths.add(filePath); // 记录当前效果音路径
            }

            clip.open(decodedAudioInputStream);
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volume); // 调整音量
            }

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    event.getLine().close();
                }
            });

            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("播放声音失败：" + e.getMessage());
        }
    }

    /**
     * 获取可用的效果音剪辑对象。如果没有可用的剪辑对象，则创建一个新的。
     * @return 可用的效果音剪辑对象
     * @throws LineUnavailableException 如果无法获取剪辑对象时抛出异常
     */
    private static Clip getAvailableEffectClip() throws LineUnavailableException {
        for (Clip clip : effectClips) {
            if (!clip.isOpen()) {
                return clip;
            }
        }
        Clip newClip = AudioSystem.getClip();
        effectClips.add(newClip);
        return newClip;
    }

    /**
     * 停止播放背景音乐。
     */
    public static void stopMusic() {
        if (musicClip != null) {
            musicClip.stop();
            musicClip.close();
        }
    }

    /**
     * 停止播放所有效果音。
     */
    public static void stopEffect() {
        for (Clip clip : effectClips) {
            if (clip.isOpen()) {
                clip.stop();
                clip.close();
            }
        }
    }

    /**
     * 设置音量。
     * @param volume 音量大小，范围为0.0f到1.0f
     * @param isBackgroundMusic 是否设置背景音乐的音量
     */
    public static void setVolume(float volume, boolean isBackgroundMusic) {
        Clip targetClip = isBackgroundMusic ? musicClip : null;
        if (targetClip != null && targetClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) targetClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume); // 设置音量
        }
    }

    /**
     * 切换背景音乐静音状态。如果当前静音，则取消静音并重新播放；否则静音背景音乐。
     */
    public static void toggleMusicMute() {
        isMusicMuted = !isMusicMuted;
        if (isMusicMuted) {
            stopMusic();
        } else {
            // 重新播放当前背景音乐
            playSound(currentMusicPath, true, true, -10.0f);
        }
    }

    /**
     * 切换音效静音状态。如果当前静音，则取消静音并重新播放所有效果音；否则静音所有效果音。
     */
    public static void toggleEffectMute() {
        isEffectMuted = !isEffectMuted;
        if (isEffectMuted) {
            stopEffect();
        } else {
            // 重新播放所有当前效果音
            for (String effectPath : currentEffectPaths) {
                playSound(effectPath, false, false, -5);
            }
        }
    }
}
