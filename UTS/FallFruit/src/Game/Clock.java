package Game;

import java.text.*;

public class Clock{
	private int Hours;
	private int Minutes;
	private int Seconds;
	private boolean timesUp;
	
	public Clock(){
		Hours = 0;
		Minutes = 0;
		Seconds = 0;
		timesUp = false;
	}
	
	public boolean getTimesUp(){
		return timesUp;
	}
	
	public void timeUp(){
		timesUp = true;
	}
	
	public String displayTime(){
		MessageFormat mf = new MessageFormat("{0}:{1}:{2}");
		DecimalFormat df = new DecimalFormat("00");
		Object[] objs = {df.format(Hours),df.format(Minutes),df.format(Seconds)};
		
		return mf.format(objs);
	}
	
	public void increaseSeconds(){
		Seconds++;
		if(Seconds >= 60){
			Seconds = 0;
			increaseMinutes();
		}
	}
	
	public void decreaseSeconds(){
		if(!(Hours == 0 && Minutes == 0 && Seconds == 0))
			Seconds--;
		else
			timeUp();
		if(Seconds < 0 && !(Hours == 0 && Minutes == 0)){
			Seconds = 59;
			decreaseMinutes();
		}
	}
	
	public void increaseMinutes(){
		Minutes++;
		if(Minutes >= 60){
			Minutes = 0;
			increaseHours();
		}
	}
	
	public void decreaseMinutes(){
		Minutes--;
		if(Minutes < 0){
			Minutes = 59;
			decreaseHours();
		}
	}
	
	public void decreaseHours(){
		Hours--;
		if(Hours < 0){
			Hours = 0;
		}
	}
	
	public void increaseHours(){
		Hours++;
		if(Hours >= 99){
			Hours = 0;
		}
	}
	
	public void resetClock(){
		Hours = 0;
		Minutes = 0;
		Seconds = 0;
	}
	
	public void setTimeClock(int x,int y,int z){
		Hours = x;
		Minutes = y;
		Seconds = z;
	}
}
