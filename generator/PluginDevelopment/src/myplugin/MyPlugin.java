package myplugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;

import myplugin.generator.SpringGenerator;
import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;

/** MagicDraw plugin that performes code generation */
public class MyPlugin extends com.nomagic.magicdraw.plugins.Plugin {
	
	String pluginDir = null; 
	
	public void init() {
		JOptionPane.showMessageDialog( null, "My Plugin init");
		
		pluginDir = getDescriptor().getPluginDirectory().getAbsolutePath();
		//pluginDir= "resources";
		// Creating submenu in the MagicDraw main menu 	
		ActionsConfiguratorsManager manager = ActionsConfiguratorsManager.getInstance();		
		manager.addMainMenuConfigurator(new MainMenuConfigurator(getSubmenuActions()));
		
		/** @Todo: load project options (@see myplugin.generator.options.ProjectOptions) from 
		 * ProjectOptions.xml and take ejb generator options */
		
		String output_path = getOutputPath();
			
		// Spring Model
		GeneratorOptions springModelOptions = new GeneratorOptions(output_path, "modelclass", "templates\\spring", "{0}.java", true, "src//main//java//com//example//demo//model"); 				
		springModelOptions.setTemplateDir(pluginDir + File.separator + springModelOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringModelGenerator", springModelOptions);
		
		// Spring Repository
		GeneratorOptions springRepositoryOptions = new GeneratorOptions(output_path, "repositoryclass", "templates\\spring", "{0}.java", true, "src//main//java//com//example//demo//repository"); 				
		springRepositoryOptions.setTemplateDir(pluginDir + File.separator + springRepositoryOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringRepositoryGenerator", springRepositoryOptions);
		
		// Spring Service
		GeneratorOptions springServiceOptions = new GeneratorOptions(output_path, "serviceclass", "templates\\spring", "{0}.java", true, "src//main//java//com//example//demo//service"); 				
		springServiceOptions.setTemplateDir(pluginDir + File.separator + springServiceOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringServiceGenerator", springServiceOptions);
		
		// Spring Service Implementation
		GeneratorOptions springServiceImplOptions = new GeneratorOptions(output_path, "serviceimplclass", "templates\\spring", "{0}.java", true, "src//main//java//com//example//demo//service/impl"); 				
		springServiceImplOptions.setTemplateDir(pluginDir + File.separator + springServiceImplOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringServiceImplGenerator", springServiceImplOptions);
	
		// Spring dto
		GeneratorOptions springDTOOptions = new GeneratorOptions(output_path, "dtoclass", "templates\\spring", "{0}.java", true, "src//main//java//com//example//demo//web//dto"); 				
		springDTOOptions.setTemplateDir(pluginDir + File.separator + springDTOOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringDTOGenerator", springDTOOptions);
			
		// Spring controller
		GeneratorOptions springControllerOptions = new GeneratorOptions(output_path, "controllerclass", "templates\\spring", "{0}.java", true, "src//main//java//com//example//demo//web//controller"); 				
		springControllerOptions.setTemplateDir(pluginDir + File.separator + springControllerOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringControllerGenerator", springControllerOptions);
		
		// Spring support model to dto
		GeneratorOptions springModelToDTOOptions = new GeneratorOptions(output_path, "modeltodtoclass", "templates\\spring", "{0}.java", true, "src//main//java//com//example//demo//support"); 				
		springModelToDTOOptions.setTemplateDir(pluginDir + File.separator + springModelToDTOOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringModelToDTOGenerator", springModelToDTOOptions);
				
		// Spring support dto to model
		GeneratorOptions springDTOtoModelOptions = new GeneratorOptions(output_path, "dtotomodelclass", "templates\\spring", "{0}.java", true, "src//main//java//com//example//demo//support"); 				
		springDTOtoModelOptions.setTemplateDir(pluginDir + File.separator + springDTOtoModelOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringDTOToModelGenerator", springDTOtoModelOptions);
				
		// Spring Application file
		GeneratorOptions springApplicationFileOptions = new GeneratorOptions(output_path, "application", "templates\\spring", "{0}.java", true, "src//main//java//com//example//demo"); 				
		springApplicationFileOptions.setTemplateDir(pluginDir + File.separator + springApplicationFileOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringApplicationFileGenerator", springApplicationFileOptions);
	
		// *************** ANGULAR GENERATOR OPTIONS ***************
		
		// entity list
		GeneratorOptions angularEntityListPageOptions = new GeneratorOptions(output_path, "entitylist", "templates\\angular\\html", "{0}.html", true, "src//main//webapp//static//app//html//partial"); 				
		angularEntityListPageOptions.setTemplateDir(pluginDir + File.separator + angularEntityListPageOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("AngularEntityListPageGenerator", angularEntityListPageOptions);

		// add edit entity
		GeneratorOptions angularAddEditEntityPageOptions = new GeneratorOptions(output_path, "addeditentity", "templates\\angular\\html", "{0}.html", true, "src//main//webapp//static//app//html//partial"); 				
		angularAddEditEntityPageOptions.setTemplateDir(pluginDir + File.separator + angularAddEditEntityPageOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("AngularAddEditEntityPageGenerator", angularAddEditEntityPageOptions);
				
		// view list
		GeneratorOptions angularViewEntityPageOptions = new GeneratorOptions(output_path, "viewentity", "templates\\angular\\html", "{0}.html", true, "src//main//webapp//static//app//html//partial"); 				
		angularViewEntityPageOptions.setTemplateDir(pluginDir + File.separator + angularViewEntityPageOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("AngularViewEntityPageGenerator", angularViewEntityPageOptions);
		
		// index
		GeneratorOptions angularIndexPageOptions = new GeneratorOptions(output_path, "index", "templates\\angular\\html", "{0}.html", true, "src//main//webapp//static//app//html"); 				
		angularIndexPageOptions.setTemplateDir(pluginDir + File.separator + angularIndexPageOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("AngularIndexPageGenerator", angularIndexPageOptions);
		
		// angular routes
		GeneratorOptions angularRoutesOptions = new GeneratorOptions(output_path, "angularroutes", "templates\\angular", "{0}.js", true, "src//main//webapp//static//app//js"); 				
		angularRoutesOptions.setTemplateDir(pluginDir + File.separator + angularRoutesOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("AngularRoutesGenerator", angularRoutesOptions);
			
		// angular services
		GeneratorOptions angularServicesOptions = new GeneratorOptions(output_path, "angularservices", "templates\\angular", "{0}.js", true, "src//main//webapp//static//app//js"); 				
		angularServicesOptions.setTemplateDir(pluginDir + File.separator + angularServicesOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("AngularServicesGenerator", angularServicesOptions);
		
		// angular controllers
		GeneratorOptions angularControllersOptions = new GeneratorOptions(output_path, "angularcontrollers", "templates\\angular", "{0}.js", true, "src//main//webapp//static//app//js"); 				
		angularControllersOptions.setTemplateDir(pluginDir + File.separator + angularControllersOptions.getTemplateDir()); //apsolutna putanja
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("AngularControllersGenerator", angularControllersOptions);
				
	}
	
	private String getOutputPath() {
		String output_path = "";
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("resources//projectoptions.properties");
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			output_path = prop.getProperty("OUTPUT_PATH");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return output_path;
	}

	private NMAction[] getSubmenuActions()
	{
	   return new NMAction[]{
			new GenerateAction("Generate"),
	   };
	}
	
	public boolean close() {
		return true;
	}
	
	public boolean isSupported() {				
		return true;
	}
}


