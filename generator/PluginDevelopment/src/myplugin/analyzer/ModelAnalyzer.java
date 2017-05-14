package myplugin.analyzer;

import java.util.Iterator;
import java.util.List;

import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.EnumerationLiteralImpl;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMEnumeration;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.fmmodel.FMProperty;
import myplugin.generator.fmmodel.NumValProp;
import myplugin.generator.fmmodel.Role;
import myplugin.generator.fmmodel.TextValProp;
import myplugin.generator.fmmodel.UIClass;
import myplugin.generator.fmmodel.UIProperty;


/** Model Analyzer takes necessary metadata from the MagicDraw model and puts it in 
 * the intermediate data structure (@see myplugin.generator.fmmodel.FMModel) optimized
 * for code generation using freemarker. Model Analyzer now takes metadata only for ejb code 
 * generation

 * @ToDo: Enhance (or completely rewrite) myplugin.generator.fmmodel classes and  
 * Model Analyzer methods in order to support GUI generation. */ 


public class ModelAnalyzer {	
	//root model package
	private Package root;
	
	//java root package for generated code
	private String filePackage;
	
	public ModelAnalyzer(Package root, String filePackage) {
		super();
		this.root = root;
		this.filePackage = filePackage;
	}

	public Package getRoot() {
		return root;
	}
	
	public void prepareModel() throws AnalyzeException {
		FMModel.getInstance().getClasses().clear();
		FMModel.getInstance().getEnumerations().clear();
		processPackage(root, filePackage);
	}
	
	private void processPackage(Package pack, String packageOwner) throws AnalyzeException {
		//Recursive procedure that extracts data from package elements and stores it in the 
		// intermediate data structure
		
		if (pack.getName() == null) throw  
			new AnalyzeException("Packages must have names!");
		
		String packageName = packageOwner;
		if (pack != root) {
			packageName += "." + pack.getName();
		}
		
		if (pack.hasOwnedElement()) {
			
			for (Iterator<Element> it = pack.getOwnedElement().iterator(); it.hasNext();) {
				Element ownedElement = it.next();
				if (ownedElement instanceof Class) {
					Class cl = (Class)ownedElement;
					FMClass fmClass = getClassData(cl, packageName);
					FMModel.getInstance().getClasses().add(fmClass);
				}
				
				if (ownedElement instanceof Enumeration) {
					Enumeration en = (Enumeration)ownedElement;
					FMEnumeration fmEnumeration = getEnumerationData(en, packageName);
					FMModel.getInstance().getEnumerations().add(fmEnumeration);
				}								
			}
			
			for (Iterator<Element> it = pack.getOwnedElement().iterator(); it.hasNext();) {
				Element ownedElement = it.next();
				if (ownedElement instanceof Package) {					
					Package ownedPackage = (Package)ownedElement;
					if (StereotypesHelper.getAppliedStereotypeByString(ownedPackage, "BusinessApp") != null)
						//only packages with stereotype BusinessApp are candidates for metadata extraction and code generation:
						processPackage(ownedPackage, packageName);
				}
			}
			
			/** @ToDo:
			  * Process other package elements, as needed */ 
		}
	}
	
	private FMClass getClassData(Class cl, String packageName) throws AnalyzeException {
		
		System.out.println("**********************************************");
		System.out.println("Class: " + cl.getName());
		
		if (cl.getName() == null) 
			throw new AnalyzeException("Classes must have names!");
		
		FMClass fmClass = new FMClass(cl.getName(), packageName, cl.getVisibility().toString());
		Iterator<Property> it = ModelHelper.attributes(cl);
		while (it.hasNext()) {
			Property p = it.next();
			FMProperty prop = getPropertyData(p, cl);
			fmClass.addProperty(prop);	
		}	
		
		// SportShopsProfile metadata					
		
		Stereotype uiElementStereotype = StereotypesHelper.getAppliedStereotypeByString(cl, "UIElement");

		String label = null;
		
		if(uiElementStereotype != null) {
			
			String name = "";
			
			List<Property> tags = uiElementStereotype.getOwnedAttribute();
			
	        for (int j = 0; j < tags.size(); ++j)
	        {
	        	Property tagDef = tags.get(j);
	        	String tagName = tagDef.getName();
	        	
	        	List value = StereotypesHelper.getStereotypePropertyValue(cl, uiElementStereotype, tagName);
	        	
	        	if(value.size() > 0) {
	        	
	        		switch(tagName) {
	            	
	        		case "label" : label = (String) value.get(0); break;
		            
		            }
	        	}
	        }
		}
		

		Stereotype roleStereotype = StereotypesHelper.getAppliedStereotypeByString(cl, "Role");

		if(roleStereotype != null) {
			
			String name = "";
			
			List<Property> tags = roleStereotype.getOwnedAttribute();
			
	        for (int j = 0; j < tags.size(); ++j)
	        {
	        	Property tagDef = tags.get(j);
	        	String tagName = tagDef.getName();
	        	
	        	List value = StereotypesHelper.getStereotypePropertyValue(cl, roleStereotype, tagName);
	        	
	        	if(value.size() > 0) {
	        	
		            switch(tagName) {
		            	
		            case "name" : name = (String) value.get(0); break;
		            
		            }
	        	}
	        }
	        
	        Role role = new Role(name);
	        
	        fmClass.setRole(role);
	        
	        System.out.println("Role: " + name);
		}
		
		
		Stereotype managedByStereotype = StereotypesHelper.getAppliedStereotypeByString(cl, "ManagedBy");

		if(managedByStereotype != null) {
			
			Class role;
			String managedByRole = null;
			
			List<Property> tags = managedByStereotype.getOwnedAttribute();
			
	        for (int j = 0; j < tags.size(); ++j)
	        {
	        	Property tagDef = tags.get(j);
	        	String tagName = tagDef.getName();
	        	
	        	List value = StereotypesHelper.getStereotypePropertyValue(cl, managedByStereotype, tagName);
	        	
	        	if(value.size() > 0) {
	        	
		            switch(tagName) {
		            	
		            case "role" : role = (Class) value.get(0); managedByRole = role.getName(); break;
		            
		            }
	        	}
	        }
	        
	        fmClass.setManagedBy(managedByRole);
	        
	        System.out.println("ManagedBy: " + managedByRole);
		}
		
		// UIProfile metadata
		
		Stereotype uiClassStereotype = StereotypesHelper.getAppliedStereotypeByString(cl, "UIClass");

		if(uiClassStereotype != null) {
		
			Boolean add = true;
			Boolean view = true;
			Boolean update = true;
			Boolean delete = true;
			String groupPresType = null;
			String singlePresType = null;
			Property presProperty;
			String presPropertyName = null;
			
			
			List<Property> tags = uiClassStereotype.getOwnedAttribute();
			
	        for (int j = 0; j < tags.size(); ++j)
	        {
	            Property tagDef = tags.get(j);
	            String tagName = tagDef.getName();
	            List value = StereotypesHelper.getStereotypePropertyValue(cl, uiClassStereotype, tagName);
	            
	            if(value.size() > 0) {
		      
	            	switch(tagName) {
	            	
			            case "add" : add = (Boolean) value.get(0); break;
			            case "view" : view = (Boolean) value.get(0); break;
			            case "update" : update = (Boolean) value.get(0); break;
			            case "delete" : delete = (Boolean) value.get(0); break;
			            case "groupPresType" : 
			            	EnumerationLiteralImpl groupPresEnum = (EnumerationLiteralImpl) value.get(0);
			            	groupPresType = groupPresEnum.getName();			            
			            	break;
			            case "singlePresType" : 
			            	EnumerationLiteralImpl singlePresEnum = (EnumerationLiteralImpl) value.get(0);
			            	singlePresType = singlePresEnum.getName();
			            	break;
			            case "presProperty" : 
			            	presProperty = (Property) value.get(0); 
			            	presPropertyName = presProperty.getName();
			            	break;
	            	}
			     }
	        }
		       
		    UIClass uiClass = new UIClass(label, add, view, update, delete, groupPresType, singlePresType, presPropertyName);
		       
		    fmClass.setUiClass(uiClass);
		    
		    System.out.println("UIClass(label, add, view, update, delete, groupPresType, singlePresType, presPropertyName): " + 
		    		label + " " + add + " " + view + " " + update + " " + delete + " " + groupPresType + " " + singlePresType + " " + presPropertyName);
		}
		
		/** @ToDo:
		 * Add import declarations etc. */		
		return fmClass;
	}
	
	private FMProperty getPropertyData(Property p, Class cl) throws AnalyzeException {
		
		System.out.println("-----------------------------------------------");
		System.out.println("Property: " + p.getName());
		
		String attName = p.getName();
		if (attName == null) 
			throw new AnalyzeException("Properties of the class: " + cl.getName() +
					" must have names!");
		Type attType = p.getType();
		if (attType == null)
			throw new AnalyzeException("Property " + cl.getName() + "." +
			p.getName() + " must have type!");
		
		String typeName = attType.getName();
		if (typeName == null)
			throw new AnalyzeException("Type ot the property " + cl.getName() + "." +
			p.getName() + " must have name!");		
			
		int lower = p.getLower();
		int upper = p.getUpper();
		
		boolean association = false;
		String aggregationKind = "none";

		if(p.getAssociation() != null) {
			association = true;
			aggregationKind = p.getAggregation().toString().toLowerCase();
		}
		
		FMProperty fmProperty = new FMProperty(attName, typeName, p.getVisibility().toString(), 
				lower, upper, association, aggregationKind);
		
		
		// RestaurantProfile metadata
	
		Stereotype uiElementStereotype = StereotypesHelper.getAppliedStereotypeByString(p, "UIElement");

		String label = null;
		
		if(uiElementStereotype != null) {
			
			String name = "";
			
			List<Property> tags = uiElementStereotype.getOwnedAttribute();
			
	        for (int j = 0; j < tags.size(); ++j)
	        {
	        	Property tagDef = tags.get(j);
	        	String tagName = tagDef.getName();
	        	
	        	List value = StereotypesHelper.getStereotypePropertyValue(p, uiElementStereotype, tagName);
	        	
	        	if(value.size() > 0) {
	        	
	        		switch(tagName) {
	            	
	        		case "label" : label = (String) value.get(0); break;
		            
		            }
	        	}
	        }
		}
		
		
		Stereotype hiddenStereotype = StereotypesHelper.getAppliedStereotypeByString(p, "Hidden");
		Boolean hidden = (hiddenStereotype != null)? true : false;
		fmProperty.setHidden(hidden);
		
		Stereotype findByStereotype = StereotypesHelper.getAppliedStereotypeByString(p, "FindBy");
		Boolean findBy = (findByStereotype != null)? true : false;
		fmProperty.setFindBy(findBy);
		
		System.out.println("Hidden " + hidden);
		System.out.println("FindBy" + findBy);
		
		Stereotype valPropStereotype = StereotypesHelper.getAppliedStereotypeByString(p, "ValidationProperty");
		Boolean unique = false;
		Boolean notNull = false;
		
		if(valPropStereotype != null) {
			List<Property> tags = valPropStereotype.getOwnedAttribute();
	        for (int j = 0; j < tags.size(); ++j)
	        {
	        	Property tagDef = tags.get(j);
	        	String tagName = tagDef.getName();
	        	
	        	List value = StereotypesHelper.getStereotypePropertyValue(p, valPropStereotype, tagName);
	        	
	        	if(value.size() > 0) {
	        	
		            switch(tagName) {
		            	
		            case "unique" : unique = (Boolean) value.get(0); break;
		            case "notNull" : notNull = (Boolean) value.get(0); break;
		            
		            }
	        	}
	        
	        }
		}
		
		Stereotype textValPropStereotype = StereotypesHelper.getAppliedStereotypeByString(p, "TextValProp");
		
		if(textValPropStereotype != null) {	
			
			Integer minLength = null;
			Integer maxLength = null;	
			
			List<Property> tags = textValPropStereotype.getOwnedAttribute();
			
	        for (int j = 0; j < tags.size(); ++j)
	        {
	            Property tagDef = tags.get(j);
	            String tagName = tagDef.getName();
	            List value = StereotypesHelper.getStereotypePropertyValue(p, textValPropStereotype, tagName);
	        
	            if(value.size() > 0) {
		            switch(tagName) {
		            	
		            case "minLength" : minLength = (Integer) value.get(0); break;
		            case "maxLength" : maxLength = (Integer) value.get(0); break;
		            
		            }
	            }
	        }
			TextValProp textValProp = new TextValProp(unique, notNull, minLength, maxLength);
			fmProperty.setValidationProperty(textValProp);
			
			System.out.println("TextValProperty(unique, notNull, minLength, maxLength): " + unique + " " + notNull + " " + minLength + " " + maxLength);
		}
		
		Stereotype numValPropStereotype = StereotypesHelper.getAppliedStereotypeByString(p, "NumValProp");
		
		if(numValPropStereotype != null) {
			
			Integer minValue = null;
			Integer maxValue = null;
			
			List<Property> tags = numValPropStereotype.getOwnedAttribute();
			
	        for (int j = 0; j < tags.size(); ++j)
	        {
	            Property tagDef = tags.get(j);
	            String tagName = tagDef.getName();
	            List value = StereotypesHelper.getStereotypePropertyValue(p, numValPropStereotype, tagName);
	            
		       if(value.size() > 0) {
		    	   
		            switch(tagName) {
		            	
		            case "minValue" : minValue = (Integer) value.get(0); break;
		            case "maxValue" : maxValue = (Integer) value.get(0); break;
		            
		            }
	            }
	        }
			NumValProp numValProp = new NumValProp(unique, notNull, minValue, maxValue);
			fmProperty.setValidationProperty(numValProp);
			
			System.out.println("NumValProperty(unique, notNull, minValue, maxValue): " + unique + " " + notNull + " " + minValue + " " + maxValue);
		}
		
		// UIProfile metadata
		
		Stereotype nextStereotype = StereotypesHelper.getAppliedStereotypeByString(p, "Next");
		Boolean next = (nextStereotype != null)? true : false;
		fmProperty.setNext(next);
		
		String nextPresPropertyName = null;
		
		if(next) {
			
			List<Property> tags = nextStereotype.getOwnedAttribute();
			
	        for (int j = 0; j < tags.size(); ++j)
	        {
	        	Property tagDef = tags.get(j);
	        	String tagName = tagDef.getName();
	        	
	        	List value = StereotypesHelper.getStereotypePropertyValue(p, nextStereotype, tagName);
	        	
	        	if(value.size() > 0) {
	        	
	        		switch(tagName) {
	            	
	        		case "presProperty" : 
	        			Property nextPresProperty = (Property) value.get(0); 
	        			nextPresPropertyName = nextPresProperty.getName();
	        			break;
		            
		            }
	        	}
	        }
	         
	        fmProperty.setNextPresPropertyName(nextPresPropertyName);
		}
		
		
		Stereotype zoomStereotype = StereotypesHelper.getAppliedStereotypeByString(p, "Zoom");
		Boolean zoom = (zoomStereotype != null)? true : false;
		fmProperty.setZoom(zoom);
		
		String zoomPresPropertyName = null;
		
		if(zoom) {
			
			List<Property> tags = zoomStereotype.getOwnedAttribute();
			
	        for (int j = 0; j < tags.size(); ++j)
	        {
	        	Property tagDef = tags.get(j);
	        	String tagName = tagDef.getName();
	        	
	        	List value = StereotypesHelper.getStereotypePropertyValue(p, zoomStereotype, tagName);
	        	
	        	if(value.size() > 0) {
	        	
	        		switch(tagName) {
	            	
	        		case "presProperty" : 
	        			Property zoomPresProperty = (Property) value.get(0); 
	        			zoomPresPropertyName = zoomPresProperty.getName();
	        			break;
		            
		            }
	        	}
	        }
	        
	        fmProperty.setZoomPresPropertyName(zoomPresPropertyName);
		}
		
		System.out.println("Next: " + next + " NextPresPropertyName: " + nextPresPropertyName);
		System.out.println("Zoom: " + zoom + " ZoomPresPropertyName: " + zoomPresPropertyName);
		
		Stereotype uiPropertyStereotype = StereotypesHelper.getAppliedStereotypeByString(p, "UIProperty");

		if(uiPropertyStereotype != null) {
		
			String formType = "text";
			Boolean readOnly = false;
			
			List<Property> tags = uiPropertyStereotype.getOwnedAttribute();
			
	        for (int j = 0; j < tags.size(); ++j)
	        {
	            Property tagDef = tags.get(j);
	            String tagName = tagDef.getName();
	            List value = StereotypesHelper.getStereotypePropertyValue(p, uiPropertyStereotype, tagName);
	            
	            if(value.size() > 0) {
		      
	            	switch(tagName) {
	            	
			            case "formType" : 
			            	EnumerationLiteralImpl formTypeEnum = (EnumerationLiteralImpl) value.get(0);
			            	formType = formTypeEnum.getName();
			            	break;
			            case "readOnly" : readOnly = (Boolean) value.get(0); break;
		       
		    	   	}
			     }
	        }
		       
		    UIProperty uiProperty = new UIProperty(label, formType, readOnly);
		       
		    fmProperty.setUiProperty(uiProperty);
		    
		    System.out.println("UIProperty(label, formType, readOnly): " + label + " " + formType + " " + readOnly);
		}
		
		return fmProperty;		
	}	
	
	private FMEnumeration getEnumerationData(Enumeration enumeration, String packageName) throws AnalyzeException {
		FMEnumeration fmEnum = new FMEnumeration(enumeration.getName(), packageName);
		List<EnumerationLiteral> list = enumeration.getOwnedLiteral();
		for (int i = 0; i < list.size() - 1; i++) {
			EnumerationLiteral literal = list.get(i);
			if (literal.getName() == null)  
				throw new AnalyzeException("Items of the enumeration " + enumeration.getName() +
				" must have names!");
			fmEnum.addValue(literal.getName());
		}
		return fmEnum;
	}	
	
	
}
