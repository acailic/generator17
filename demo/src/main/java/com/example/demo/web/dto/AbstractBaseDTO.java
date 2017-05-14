package com.example.demo.web.dto;

import javax.persistence.MappedSuperclass;

/**
 * Base entity class.
 * 
 * @author d.gajic
 */
@MappedSuperclass
public abstract class AbstractBaseDTO {

	/**
	 * Primary key
	 */
	private Long id;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
