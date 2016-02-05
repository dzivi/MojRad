package org.ivan.MojRad.classes;

public class Level {
	private int LevelID;
	private String Name;

	public Level() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Level(String name) {
		super();
		Name = name;
	}

	public int getLevelID() {
		return LevelID;
	}

	public void setLevelID(int levelID) {
		LevelID = levelID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
