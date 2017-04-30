package com.gcit.lms.entity;

import java.io.Serializable;

public class Genre implements Serializable {

	private static final long serialVersionUID = -8342999060307514702L;

	private Integer genreId;
	private String genreName;

	/**
	 * @return the genreId
	 */
	public Integer getGenreId() {
		return genreId;
	}

	/**
	 * @param genreId
	 *            the genreId to set
	 */
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	/**
	 * @return the genreName
	 */
	public String getGenreName() {
		return genreName;
	}

	/**
	 * @param genreName
	 *            the genreName to set
	 */
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

}
