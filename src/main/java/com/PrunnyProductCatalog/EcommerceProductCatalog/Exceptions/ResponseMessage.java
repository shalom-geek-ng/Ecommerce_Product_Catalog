package com.PrunnyProductCatalog.EcommerceProductCatalog.Exceptions;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
	private String details;
	private String message;
	private LocalDate timeStamp;
}
