package com.morning.bread.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

// @JsonProperty
// 이 어노테이션이 붙은 멤버필드, getter, setter에만 JSON 관련 작업을 하게 되는 것이다. 
// 즉 JSON 문자열로 출력하거나 또는 JSON 문자열에서 읽어들이는 것이다.
// 예를 들어 @JsonProperty("unitID") 라고 멤버필드에 붙이면 unitID란 Key로 해당 필드의 값을 JSON으로 출력하거나 
// 또는 JSON 문자열에서 unitID란 Key에 해당되는 값을 읽어 멤버 필드에 넣게 된다. 
// 멤버 필드에서 설정하게 되면 JSON 문자열로 읽고 쓰는 작업 모두를 할수 있으며 getter 함수에 붙인다면 
// JSON 문자열로 출력하는 작업만 가능하며, setter 함수에 붙인다면 JSON 문자열에서 읽어들이는 작업만 가능하게 된다.  
// 지금의 예에서는 @JsonProperty("unitID")라고 설정해서 필드 변수명과 Json Key 이름을 달리 주었으나 이 Key를 생략하면 필드 변수명 동일하게 Json Key 이름을 사용하게 된다.

@Entity
public class User {
	
	@Id
	@GeneratedValue // 자동으로 증가 시켜 준다.
	@JsonProperty // Database 를 자바 클래스로 매핑하는데 DB의 컬럼명이 알기 어려울 경우등에 유용하게 사용할 수 있다.
	private Long id;
	
	@Column(nullable=false, length=20)
	private String userId;
	
	@JsonProperty
	private String password;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String email;
	
	@JsonProperty
	private String delyn;
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param delyn the delyn to set
	 */
	public void setDelyn(String delyn) {
		this.delyn = delyn;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", password=" + password + ", name=" + name + ", email="
				+ email + ", delyn=" + delyn + "]";
	}
	public void update(User newUser) {
		this.password = newUser.password;
		this.name = newUser.name;
		this.email = newUser.email;
		this.delyn = newUser.delyn;
	}
	
	
}
