package myplugin.generator.fmmodel;

public class UIProperty {
	
	private String label;
	
	private String formType;
	
	private Boolean readOnly;

	
	public UIProperty(String label, String formType, Boolean readOnly) {
		super();
		this.label = label;
		this.formType = formType;
		this.readOnly = readOnly;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
