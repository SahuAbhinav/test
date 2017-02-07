package com.advanz.erp.masters.entity.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_pro_tax_deduct_type")
public class ProfessionalTaxDeductTypeEntity implements Serializable{
	
		@Id @GeneratedValue(generator="system-incr")
		@GenericGenerator(name="system-incr", strategy = "increment")
		@Column(name="ptax_deduct_type_id")
		
		private Integer ptaxDeductTypeId;
		
		@Column(name="ptax_deduct_type")
		private String ptaxDeductType;
		
		public Integer getPtaxDeductTypeId() {
			return ptaxDeductTypeId;
		}

		public void setPtaxDeductTypeId(Integer ptaxDeductTypeId) {
			this.ptaxDeductTypeId = ptaxDeductTypeId;
		}

		public String getPtaxDeductType() {
			return ptaxDeductType;
		}

		public void setPtaxDeductType(String ptaxDeductType) {
			this.ptaxDeductType = ptaxDeductType;
		}

		@Override
		public String toString() {
			return "ProfessionalTaxDeductTypeEntity [ptaxDeductTypeId=" + ptaxDeductTypeId
				
					+ ", ptaxDeductType=" + ptaxDeductType + "]";
		}
}
