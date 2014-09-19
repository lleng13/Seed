package per.jacobs.seed.annotation.action;

import java.lang.annotation.Annotation;

public class PauseProcessor {
	public void excuteBeforeOperation(Object obj, Class cl) {
		
	}
	private void pause(int time) {
		if (time <= 0) {
			return;
		}
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
	}
}
