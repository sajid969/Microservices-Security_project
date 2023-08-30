package com.tcs.bindings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChildDto {
	
	String childName;
	String childAge;
	String childAadhar;

}
