package Jasper.SideProject.SpringSecurity.Dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PersonTranrq implements Serializable{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("ID")
	private String id;	
}
