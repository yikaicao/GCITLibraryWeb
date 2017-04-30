package com.gcit.lms.entity;

import java.io.Serializable;

public class Publisher implements Serializable {

	private static final long serialVersionUID = 3148598448836792516L;

	private Integer pubId;
	private String pubName;
	private String pubAddr;
	private String pubPhone;

	/**
	 * @return the pubId
	 */
	public Integer getPubId() {
		return pubId;
	}

	/**
	 * @param pubId
	 *            the pubId to set
	 */
	public void setPubId(Integer pubId) {
		this.pubId = pubId;
	}

	/**
	 * @return the pubName
	 */
	public String getPubName() {
		return pubName;
	}

	/**
	 * @param pubName
	 *            the pubName to set
	 */
	public void setPubName(String pubName) {
		this.pubName = pubName;
	}

	/**
	 * @return the pubAddr
	 */
	public String getPubAddr() {
		return pubAddr;
	}

	/**
	 * @param pubAddr
	 *            the pubAddr to set
	 */
	public void setPubAddr(String pubAddr) {
		this.pubAddr = pubAddr;
	}

	/**
	 * @return the pubPhone
	 */
	public String getPubPhone() {
		return pubPhone;
	}

	/**
	 * @param pubPhone
	 *            the pubPhone to set
	 */
	public void setPubPhone(String pubPhone) {
		this.pubPhone = pubPhone;
	}

}
