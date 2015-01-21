package Game.utility;

import java.util.ArrayList;

public class PlayerLevel extends Player{
	ArrayList<Integer> unlock = new ArrayList<Integer>();

	public ArrayList<Integer> getUnlock() {
		return unlock;
	}

	public void setUnlock(ArrayList<Integer> unlock) {
		this.unlock = unlock;
	}

	public void addUnlock(int a) {
		this.unlock.add(a);
	}
	
	public void removeUnlock(int a) {
		this.unlock.remove(a);
	}
}
