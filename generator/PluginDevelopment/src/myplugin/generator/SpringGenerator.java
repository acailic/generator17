package myplugin.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.fmmodel.FMProperty;
import myplugin.generator.options.GeneratorOptions;
import freemarker.template.TemplateException;

/** EJB generator that now generates incomplete ejb classes based on MagicDraw 
 * class model 
 * @ToDo: enhance resources/templates/ejbclass.ftl template and intermediate data structure
 *  (@see myplugin.generator.fmmodel) in order to generate complete ejb classes 
 */

public class SpringGenerator extends BasicGenerator {	
	
	public SpringGenerator(GeneratorOptions generatorOptions) {			
		super(generatorOptions);			
	}

	public void generate() {
		
		try {
			super.generate();
		} catch (IOException e) {		
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		List<FMClass> classes = FMModel.getInstance().getClasses();
		for (int i = 0; i < classes.size(); i++) {
			FMClass cl = classes.get(i);			
			Writer out;
			Map<String, Object> context = new HashMap<String, Object>();
			try {
				out = getWriter(cl.getName(), getFilePackage());
				if (out != null) {
					context.clear();
					context.put("class", cl);
					context.put("properties", cl.getProperties());					
					context.put("importedPackages", cl.getImportedPackages());
					getTemplate().process(context, out);
					out.flush();
				}
			} catch (TemplateException e) {	
				JOptionPane.showMessageDialog(null, e.getMessage());
			}	
			catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}	
		}	
	}
	
	public void generateApplicationFile() {
		// Test skup
		try {
			super.generate();
		} catch (IOException e) {		
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		List<FMClass> classes = FMModel.getInstance().getClasses();
		
		LinkedList<FMClass> orderedClasses = orderClasses(classes);

		Writer out;
		Map<String, Object> context = new HashMap<String, Object>();
		try {
			out = getWriter("Application", getFilePackage());
			if (out != null) {
				context.clear();
				context.put("classes", orderedClasses);
				getTemplate().process(context, out);
				out.flush();
			}
		} catch (TemplateException e) {	
			JOptionPane.showMessageDialog(null, e.getMessage());
		}	
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}	
			
	}
	
	public LinkedList<FMClass> orderClasses(List<FMClass> classes) {
		LinkedList<FMClass> ret = new LinkedList<FMClass>();
		for(FMClass cl : classes) {
			if(numberOfAssociationLists(cl) == 3) {
				ret.add(cl);
			}
		}
		for(FMClass cl : classes) {
			if(numberOfAssociationLists(cl) == 2) {
				ret.add(cl);
			}
		}
		for(FMClass cl : classes) {
			if(numberOfAssociationLists(cl) == 1 && numberOfAssociations(cl) == 0) {
				ret.add(cl);
			}
		}
		for(FMClass cl : classes) {
			if(numberOfAssociationLists(cl) == 1 && numberOfAssociations(cl) > 0) {
				ret.add(cl);
			}
		}
		for(FMClass cl : classes) {
			if(numberOfAssociationLists(cl) == 0 && !cl.getName().equals("Admin")) {
				ret.add(cl);
			}
		}
		return ret;	
	}
	
	public int numberOfAssociations(FMClass cl) {
		int counter = 0;
		for(FMProperty property : cl.getProperties()) {
			if(property.getUpper() == 1 && property.getAssociation() == true) {
				counter++;
			}
		}	
		return counter;
	}
	
	public int numberOfAssociationLists(FMClass cl) {
		int counter = 0;
		for(FMProperty property : cl.getProperties()) {
			if(property.getUpper() == -1 && property.getAssociation() == true) {
				counter++;
			}
		}	
		return counter;
	}
	
	@Override
	public Writer getWriter(String fileNamePart, String packageName) throws IOException {
		if (packageName != filePackage) {
			packageName.replace(".", File.separator);		
			filePackage = packageName;
		}
		
		String generatedFileName = "";
		// ova dva slucaja moraju pre model i dto
	    if(templateName.startsWith("modeltodto")) {
			generatedFileName = fileNamePart + "To" + fileNamePart + "DTO";
		}
		else if(templateName.startsWith("dtotomodel")) {
			generatedFileName = fileNamePart + "DTOTo" + fileNamePart;
		}
		else if(templateName.startsWith("model")) {
			generatedFileName = fileNamePart;
		}
		else if(templateName.startsWith("repository")) {
			generatedFileName = fileNamePart + "Repository";
		}
		// ovaj slucaj mora pre sledeceg 
		else if(templateName.startsWith("serviceimpl")) {
			generatedFileName = fileNamePart + "ServiceImpl";
		}
		else if(templateName.startsWith("service")) {
			generatedFileName = fileNamePart + "Service";
		}
		else if(templateName.startsWith("dto")) {
			generatedFileName = fileNamePart + "DTO";
		}
		else if(templateName.startsWith("controller")) {
			generatedFileName = "Api" + fileNamePart + "Controller";
		}
		else {
			generatedFileName = fileNamePart;
		}
		
		String fullPath = outputPath
				+ File.separator
				+ (filePackage.isEmpty() ? "" : packageToPath(filePackage)
						+ File.separator)
				+ outputFileName.replace("{0}", generatedFileName);

		File of = new File(fullPath);
		if (!of.getParentFile().exists())
			if (!of.getParentFile().mkdirs()) {
				throw new IOException("An error occurred during output folder creation "
						+ outputPath);
			}

		System.out.println(of.getPath());
		System.out.println(of.getName());

		if (!isOverwrite() && of.exists())
			return null;

		return new OutputStreamWriter(new FileOutputStream(of));

	}
	
	/*
	public LinkedList<FMClass> orderClasses(List<FMClass> classes) {
		LinkedList<FMClass> ret = new LinkedList<>();
		for (Iterator<FMClass> iterator = classes.iterator(); iterator.hasNext();) {
			FMClass cl = iterator.next();
			if(!ret.contains(cl)) {
				iterator.remove();
				if(hasAssociationList(cl)) {
					System.out.println("has association");				
					ret.addAll(orderClasses(classes));	
				}
				System.out.println("doesn't have association");
				ret.add(cl);	
			}
		}
		return ret;	
	}
	
	public boolean hasAssociationList(FMClass cl) {
		for(FMProperty property : cl.getProperties()) {
			if(property.getUpper() == -1 && property.getAssociation() == true) {
				return true;
			}
		}	
		return false;
	}*/
}
 

