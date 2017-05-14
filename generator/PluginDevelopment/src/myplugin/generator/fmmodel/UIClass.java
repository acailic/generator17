package myplugin.generator.fmmodel;

public class UIClass {

	// UIClass Stereotype Tagged Values (UIProfile)
	
	private String label;
	
	private Boolean add;
	private Boolean view;
	private Boolean update;
	private Boolean delete;
	
	private String groupPresType;
	private String singlePresType;
	
	private String presPropertyName;

	public UIClass(String label, Boolean add, Boolean view, Boolean update, Boolean delete, String groupPresType,
			String singlePresType, String presPropertyName) {
		super();
		this.label = label;
		this.add = add;
		this.view = view;
		this.update = update;
		this.delete = delete;
		this.groupPresType = groupPresType;
		this.singlePresType = singlePresType;
		this.presPropertyName = presPropertyName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getAdd() {
		return add;
	}

	public void setAdd(Boolean add) {
		this.add = add;
	}

	public Boolean getView() {
		return view;
	}

	public void setView(Boolean view) {
		this.view = view;
	}

	public Boolean getUpdate() {
		return update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public String getGroupPresType() {
		return groupPresType;
	}

	public void setGroupPresType(String groupPresType) {
		this.groupPresType = groupPresType;
	}

	public String getSinglePresType() {
		return singlePresType;
	}

	public void setSinglePresType(String singlePresType) {
		this.singlePresType = singlePresType;
	}

	public String getPresPropertyName() {
		return presPropertyName;
	}

	public void setPresPropertyName(String presPropertyName) {
		this.presPropertyName = presPropertyName;
	}

	
}
