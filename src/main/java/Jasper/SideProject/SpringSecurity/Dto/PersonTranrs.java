package Jasper.SideProject.SpringSecurity.Dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PersonTranrs implements Serializable{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("Age")
	private String age;
	
	@JsonProperty("Weight")
	private String weight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	
}
