package com.tcs.models;

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
@AllArgsConstructor
@NoArgsConstructor

public class Children {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer childId;
	Integer caseNumber;
	String childName;
	String childAge;
	String childAadhar;

}
