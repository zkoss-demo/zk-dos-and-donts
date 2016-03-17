package local.support.zk_dos_and_donts.part1;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.Label;

public class CompAndDesktopComposer extends SelectorComposer {

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		//A label is instantiated in the first desktop created in the session.
		if(Sessions.getCurrent().getAttribute("CrossDesktopComponent")==null)
			Sessions.getCurrent().setAttribute("CrossDesktopComponent",new Label("Cross Desktop Label"));
		//Following desktops (page refresh) will try to add a component that below to the first desktop.
		Label myLabel = (Label)Sessions.getCurrent().getAttribute("CrossDesktopComponent");
		comp.appendChild(myLabel);
	}
	
}
