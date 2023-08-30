package com.tcs.models;

import java.sql.Blob;

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

public class CoTriggers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer coTriggerId;
	Integer caseNumber;
	Blob coPdf;
	String triggerStatus;

}
