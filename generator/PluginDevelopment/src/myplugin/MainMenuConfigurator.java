package myplugin;

import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.ActionsManager;
import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.MDActionsCategory;

public class MainMenuConfigurator implements AMConfigurator
{
	
	
	/**
	 * Actions that will be added to manager.
	 */
	private NMAction[] actions;

	/**
	 * Creates configurator.
	 * @param actions actions to be added to main menu.
	 */
	public MainMenuConfigurator(NMAction[] actions)
	{
		this.actions = actions;
	}

	/**
	 * @see com.nomagic.actions.AMConfigurator#configure(ActionsManager)
	 *  Methods adds action to given manager XCoder category.
	 */
	public void configure(ActionsManager mngr)
	{
		ActionsCategory category = (ActionsCategory) mngr.getActionFor("Code Generation");
		
		if( category == null )
		{
			category = new MDActionsCategory("Code Generation", "Code Generation");
			category.setNested(true);
			mngr.addCategory(category);
		}
		for(int i=0;i<actions.length;i++) {
		   category.addAction(actions[i]);
		}
	}
	
	public int getPriority()
	{
		return AMConfigurator.MEDIUM_PRIORITY;
	}

}