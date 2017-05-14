package myplugin.generator.options;

/** TypeMapping: UML 2.0 to java (or any other destination language) type mapping */

public class TypeMapping {
	private String uMLType;
	private String destType;
	
	//libraryName: name used for import declaration  
	private String libraryName;
	
	public TypeMapping(String uMLType, String destType, String libraryName) {
		super();
		this.uMLType = uMLType;
		this.destType = destType;
		this.libraryName = libraryName;
	}

	public String getuMLType() {
		return uMLType;
	}

	public void setuMLType(String uMLType) {
		this.uMLType = uMLType;
	}

	public String getDestType() {
		return destType;
	}

	public void setDestType(String destType) {
		this.destType = destType;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
	
	
	
}
