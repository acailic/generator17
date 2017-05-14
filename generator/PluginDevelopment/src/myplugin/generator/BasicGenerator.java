package myplugin.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import javax.swing.JOptionPane;

import myplugin.generator.options.GeneratorOptions;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * Abstract generator that creates necessary environment for code generation 
 * (creating directory for code generation, fetching template, creating file with given name 
 * for code generation etc). It should be ancestor for all generators in this project. 
*/

public abstract class BasicGenerator {

	protected GeneratorOptions generatorOptions; 
	protected String outputPath;	
	protected String templateName;
	protected String templateDir;
	protected String outputFileName;
	protected boolean overwrite = false;
	protected String filePackage;
	protected Configuration cfg;
	protected Template template;
	
	public BasicGenerator(GeneratorOptions generatorOptions) {
		this.generatorOptions = generatorOptions;
		this.outputPath = generatorOptions.getOutputPath();
		this.templateName = generatorOptions.getTemplateName();
		this.templateDir = generatorOptions.getTemplateDir();
		this.outputFileName = generatorOptions.getOutputFileName();
		this.overwrite = generatorOptions.getOverwrite();
		this.filePackage = generatorOptions.getFilePackage();
	}

	public void generate() throws IOException {		
		if (outputPath == null) {
			throw new IOException("Output path is not defined!");
		}	
		if (templateName == null) {
			throw new IOException("Template name is not defined!");
		}
		if (outputFileName == null) {
			throw new IOException("Output file name is not defined!");
		}
		if (filePackage == null) {
			throw new IOException("Package name for code generation is not defined!");
		}

		cfg = new Configuration();		

		final String tName = templateName + ".ftl";
		try {
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			template = cfg.getTemplate(tName);
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			File op = new File(outputPath);
			if (!op.exists())
				if (!op.mkdirs()) {
					throw new IOException(
							"An error occurred during folder creation " + outputPath);
				}
		} catch (IOException e) {
			throw new IOException("Can't find template " + tName + ". In directory: "
					+templateDir +" templateDir . Output path: "+outputPath +" . ispis:"+cfg.getDefaultEncoding()
					, e);
		}

	}

	public Writer getWriter(String fileNamePart, String packageName) throws IOException {
		if (packageName != filePackage) {
			packageName.replace(".", File.separator);		
			filePackage = packageName;
		}
			
		String fullPath = outputPath
				+ File.separator
				+ (filePackage.isEmpty() ? "" : packageToPath(filePackage)
						+ File.separator)
				+ outputFileName.replace("{0}", fileNamePart);

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

	protected String packageToPath(String pack) {
		return pack.replace(".", File.separator);
	}

	public boolean isOverwrite() {
		return overwrite;
	}

	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}

	public Writer getWriter() throws IOException {
		return getWriter("", filePackage);

	}

	public void setOutputPath(String output) {
		this.outputPath = output;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}		
	
	public Configuration getCfg() {
		return cfg;
	}

	public void setCfg(Configuration cfg) {
		this.cfg = cfg;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public String getTemplateName() {
		return templateName;
	}
	
	public String getTemplateDir() {
		return templateDir;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public String getFilePackage() {
		return filePackage;
	}

	public void setFilePackage(String filePackage) {
		this.filePackage = filePackage;
	}

}
