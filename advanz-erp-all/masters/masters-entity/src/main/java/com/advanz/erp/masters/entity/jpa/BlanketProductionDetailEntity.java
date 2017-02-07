package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="t_blanket_production_detail")
public class BlanketProductionDetailEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sno")
	private Integer sno;
	
	@Column(name="blanket_prod_id")
	private Integer blanketProductionId;
	
	@Column(name="roll_no_l")
	private Integer rollNoLeft;
	
	@Column(name="length_l")
	private Double lengthLeft;
	
	@Column(name="width_l")
	private Double widthLeft;
	
	@Column(name="thick_l")
	private Double thickLeft;
	
	@Column(name="weight_l")
	private Double weightLeft;
	
	@Column(name="density_l")
	private Double densityLeft;
	
	@Column(name="remark_l")
	private String remarkLeft;
	
	@Column(name="roll_no_r")
	private Integer rollNoRight;
	
	@Column(name="length_r")
	private Double lengthRight;
	
	@Column(name="width_r")
	private Double widthRight;
	
	@Column(name="thick_r")
	private Double thickRight;
	
	@Column(name="weight_r")
	private Double weightRight;
	
	@Column(name="density_r")
	private Double densityRight;
	
	@Column(name="remark_r")
	private String remarkRight;

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public Integer getBlanketProductionId() {
		return blanketProductionId;
	}

	public void setBlanketProductionId(Integer blanketProductionId) {
		this.blanketProductionId = blanketProductionId;
	}

	public Integer getRollNoLeft() {
		return rollNoLeft;
	}

	public void setRollNoLeft(Integer rollNoLeft) {
		this.rollNoLeft = rollNoLeft;
	}

	public Double getLengthLeft() {
		return lengthLeft;
	}

	public void setLengthLeft(Double lengthLeft) {
		this.lengthLeft = lengthLeft;
	}

	public Double getWidthLeft() {
		return widthLeft;
	}

	public void setWidthLeft(Double widthLeft) {
		this.widthLeft = widthLeft;
	}

	public Double getThickLeft() {
		return thickLeft;
	}

	public void setThickLeft(Double thickLeft) {
		this.thickLeft = thickLeft;
	}

	public Double getWeightLeft() {
		return weightLeft;
	}

	public void setWeightLeft(Double weightLeft) {
		this.weightLeft = weightLeft;
	}

	public Double getDensityLeft() {
		return densityLeft;
	}

	public void setDensityLeft(Double densityLeft) {
		this.densityLeft = densityLeft;
	}

	public String getRemarkLeft() {
		return remarkLeft;
	}

	public void setRemarkLeft(String remarkLeft) {
		this.remarkLeft = remarkLeft;
	}

	public Integer getRollNoRight() {
		return rollNoRight;
	}

	public void setRollNoRight(Integer rollNoRight) {
		this.rollNoRight = rollNoRight;
	}

	public Double getLengthRight() {
		return lengthRight;
	}

	public void setLengthRight(Double lengthRight) {
		this.lengthRight = lengthRight;
	}

	public Double getWidthRight() {
		return widthRight;
	}

	public void setWidthRight(Double widthRight) {
		this.widthRight = widthRight;
	}

	public Double getThickRight() {
		return thickRight;
	}

	public void setThickRight(Double thickRight) {
		this.thickRight = thickRight;
	}

	public Double getWeightRight() {
		return weightRight;
	}

	public void setWeightRight(Double weightRight) {
		this.weightRight = weightRight;
	}

	public Double getDensityRight() {
		return densityRight;
	}

	public void setDensityRight(Double densityRight) {
		this.densityRight = densityRight;
	}

	public String getRemarkRight() {
		return remarkRight;
	}

	public void setRemarkRight(String remarkRight) {
		this.remarkRight = remarkRight;
	}
	
	
	
	
	

}
