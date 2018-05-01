package com.prasad.roostack.constants;

/**
 * 
 * @author lgunti
 * @createdDate 08 Nov 2014  10:00
 *
 */
public enum LevelConstants {

	ADMIN( 1, "admin"),
	USER( 2, "user"),
	VIEWER( 3, "viewer");

	private int id;
	private String description;
	
	
	/**
	 * LevelConstants Constructor
	 * 
	 * @param id
	 * @param description
	 */
	private LevelConstants(final int id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * getInstance by id
	 * 
	 * @param id
	 * @return
	 */
	public static LevelConstants getInstance(int id) {

		for (LevelConstants levelConstants : LevelConstants.values()) {
			if (levelConstants.id == id) {
				return levelConstants;
			}
		}

		throw new IllegalArgumentException(" Invalid LevelConstants with id " + id);
	}
	
	
	/**
	 * getInstance by description
	 * 
	 * @param description
	 * @return
	 */
	public static LevelConstants getInstance(String description) {

		for (LevelConstants levelConstants : LevelConstants.values()) {
			if (levelConstants.description == description) {
				return levelConstants;
			}
		}

		throw new IllegalArgumentException(" Invalid LevelConstants with description " + description);
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
