package com.javaPro.extraField;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

public class ExtraField {

	@Field("Created_Date")
	private Date createdDate;

	@Field("Last_Modified_Date")
	private Date lastModifiedDate;

	@Field("Record_Status")
	private int recordStatus;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public int getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}

}
