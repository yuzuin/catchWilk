package Game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

public class backgroundMusic {
	public static void main(String[] args) {
		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setSize(200,200);
		f.setVisible(true);
		
		File file = new File("./src/sound/ingameBGM.wav");
		System.out.println(file.exists());
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			System.out.println();
			Clip clip = AudioSystem.getClip();
//			clip.stop();
			clip.open(ais);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
