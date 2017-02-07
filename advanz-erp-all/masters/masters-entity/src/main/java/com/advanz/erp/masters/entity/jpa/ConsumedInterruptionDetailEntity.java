package com.advanz.erp.masters.entity.jpa;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name="t_consumed_interruption_detail ")
public class ConsumedInterruptionDetailEntity extends BaseEntity {
	
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sno")
		private Integer sno;
	

	@Column(name="blanket_prod_id")
	private Integer blanketProdId;
	
	@Column(name="roll_no_l")
	private Integer runNo1;
	
	@Column(name="length_l")
	private Double length;
	

	@Column(name="width_l")
	private Double width;
	
	@Column(name="thick_l")
	private Double thick;
	
	@Column(name="weight_l")
	private Double weight;
	
	@Column(name="density_l")
	private Double density;
	
	@Column(name="remark_l")
	private String remark;
	
	
	@Column(name="roll_no_r")
	private Integer rollNoR;
	
	@Column(name="length_r")
	private Double lengthR;
	
	@Column(name="width_r")
	private Double widthR;
	
	@Column(name="thick_r")
	private Double thickR;
	
	@Column(name="weight_r")
	private Double weightR;
	
	@Column(name="density_r")
	private Double densityR;
	
	@Column(name="remark_r")
	private String remarkR;

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public Integer getBlanketProdId() {
		return blanketProdId;
	}

	public void setBlanketProdId(Integer blanketProdId) {
		this.blanketProdId = blanketProdId;
	}

	public Integer getRunNo1() {
		return runNo1;
	}

	public void setRunNo1(Integer runNo1) {
		this.runNo1 = runNo1;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getThick() {
		return thick;
	}

	public void setThick(Double thick) {
		this.thick = thick;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getDensity() {
		return density;
	}

	public void setDensity(Double density) {
		this.density = density;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRollNoR() {
		return rollNoR;
	}

	public void setRollNoR(Integer rollNoR) {
		this.rollNoR = rollNoR;
	}

	public Double getLengthR() {
		return lengthR;
	}

	public void setLengthR(Double lengthR) {
		this.lengthR = lengthR;
	}

	public Double getWidthR() {
		return widthR;
	}

	public void setWidthR(Double widthR) {
		this.widthR = widthR;
	}

	public Double getThickR() {
		return thickR;
	}

	public void setThickR(Double thickR) {
		this.thickR = thickR;
	}

	public Double getWeightR() {
		return weightR;
	}

	public void setWeightR(Double weightR) {
		this.weightR = weightR;
	}

	public Double getDensityR() {
		return densityR;
	}

	public void setDensityR(Double densityR) {
		this.densityR = densityR;
	}

	public String getRemarkR() {
		return remarkR;
	}

	public void setRemarkR(String remarkR) {
		this.remarkR = remarkR;
	}

	@Override
	public String toString() {
		return "ConsumedInterruptionDetailEntity [sno=" + sno
				+ ", blanketProdId=" + blanketProdId + ", runNo1=" + runNo1
				+ ", length=" + length + ", width=" + width + ", thick="
				+ thick + ", weight=" + weight + ", density=" + density
				+ ", remark=" + remark + ", rollNoR=" + rollNoR + ", lengthR="
				+ lengthR + ", widthR=" + widthR + ", thickR=" + thickR
				+ ", weightR=" + weightR + ", densityR=" + densityR
				+ ", remarkR=" + remarkR + "]";
	}
	
	
	
	
	
	
	
	

}
