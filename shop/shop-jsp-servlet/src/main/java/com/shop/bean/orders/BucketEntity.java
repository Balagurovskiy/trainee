package com.shop.bean.orders;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;

@Entity
@Table(name = "bucket")
public class BucketEntity extends OrderBaseEntity{

	@Column
	private int processed;

	public int getProcessed() {
		return processed;
	}

	public void setProcessed(int processed) {
		this.processed = processed;
	}
	public boolean isProcessed() {
		return (processed == 1);
	}
}
