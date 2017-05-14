package myplugin.generator.fmmodel;

public class ValidationProperty {

	private Boolean unique;
	
	private Boolean notNull;

	public ValidationProperty(Boolean unique, Boolean notNull) {
		super();
		this.unique = unique;
		this.notNull = notNull;
	}

	public Boolean getUnique() {
		return unique;
	}

	public void setUnique(Boolean unique) {
		this.unique = unique;
	}

	public Boolean getNotNull() {
		return notNull;
	}

	public void setNotNull(Boolean notNull) {
		this.notNull = notNull;
	}
	
}
