package com.advanz.erp.masters.entity.jpa;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "m_blanket_weight_record")

public class BlanketWeightRecordEntity extends BaseEntity {
	
	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="weight_id")
	private Integer weightId;
	
	@Column(name="weight_date")
	private Date weightDate;
	
	@Column(name="weight_time")
	private Time weightTime;
	
	@Column(name="weight")
	private Double weight;
	
	@Column(name="blanket_type")
	private Character blanketType;

	public Integer getWeightId() {
		return weightId;
	}

	public void setWeightId(Integer weightId) {
		this.weightId = weightId;
	}

	public Date getWeightDate() {
		return weightDate;
	}

	public void setWeightDate(Date weightDate) {
		this.weightDate = weightDate;
	}

	public Time getWeightTime() {
		return weightTime;
	}

	public void setWeightTime(Time weightTime) {
		this.weightTime = weightTime;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Character getBlanketType() {
		return blanketType;
	}

	public void setBlanketType(Character blanketType) {
		this.blanketType = blanketType;
	}
	
	
		
}
