package com.tcs.models;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PlanCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer pcID;
	String planCategoryName;
	@Column(updatable = false)
	@CreationTimestamp()
	LocalDate createDate;
	@Column(insertable = false)
	@UpdateTimestamp()
	LocalDate updateDate;
	String createdBy;
	String updatedBy;
	
	
}
