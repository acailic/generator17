package myplugin.generator.fmmodel;


public class FMProperty extends FMElement  {
	
	//Property type
	private String type;
	// Property visibility (public, private, protected, package)
	private String visibility;
	//Multiplicity (lower value)
	private Integer lower;
	//Multiplicity (upper value) 
	private Integer upper;

	private Boolean association;
	
	private String aggregationKind;
	
	// (RestaurantProfile)
	
	private Boolean hidden;
	
	private Boolean findBy;
	
	private ValidationProperty validationProperty;
	
	// (UIProfile)
	
	private Boolean next;
	private String nextPresPropertyName;
	
	private Boolean zoom;
	private String zoomPresPropertyName;
	
	private UIProperty uiProperty;	
	
	/** @ToDo: Add length, precision, unique... whatever is needed for ejb class generation
	 * Also, provide these meta-attributes or tags in the modeling languange metaclass or 
	 * stereotype */

	
	public FMProperty(String name, String type, String visibility, int lower, int upper, Boolean association, String aggregationKind) {
		super(name);
		this.type = type;
		this.visibility = visibility;
		
		this.lower = lower;
		this.upper = upper;	
		
		this.association = association;
		this.aggregationKind = aggregationKind;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	public Integer getLower() {
		return lower;
	}

	public void setLower(Integer lower) {
		this.lower = lower;
	}

	public Integer getUpper() {
		return upper;
	}

	public void setUpper(Integer upper) {
		this.upper = upper;
	}
	
	public Boolean getAssociation() {
		return association;
	}

	public void setAssociation(Boolean association) {
		this.association = association;
	}

	public String getAggregationKind() {
		return aggregationKind;
	}

	public void setAggregationKind(String aggregationKind) {
		this.aggregationKind = aggregationKind;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public Boolean getFindBy() {
		return findBy;
	}

	public void setFindBy(Boolean findBy) {
		this.findBy = findBy;
	}

	public ValidationProperty getValidationProperty() {
		return validationProperty;
	}

	public void setValidationProperty(ValidationProperty validationProperty) {
		this.validationProperty = validationProperty;
	}

	public UIProperty getUiProperty() {
		return uiProperty;
	}

	public void setUiProperty(UIProperty uiProperty) {
		this.uiProperty = uiProperty;
	}

	public Boolean getNext() {
		return next;
	}

	public void setNext(Boolean next) {
		this.next = next;
	}

	public String getNextPresPropertyName() {
		return nextPresPropertyName;
	}

	public void setNextPresPropertyName(String nextPresPropertyName) {
		this.nextPresPropertyName = nextPresPropertyName;
	}

	public Boolean getZoom() {
		return zoom;
	}

	public void setZoom(Boolean zoom) {
		this.zoom = zoom;
	}

	public String getZoomPresPropertyName() {
		return zoomPresPropertyName;
	}

	public void setZoomPresPropertyName(String zoomPresPropertyName) {
		this.zoomPresPropertyName = zoomPresPropertyName;
	}


}
