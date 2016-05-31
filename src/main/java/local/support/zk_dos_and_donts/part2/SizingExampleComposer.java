package local.support.zk_dos_and_donts.part2;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import local.support.zk_dos_and_donts.common.ExampleBean;

public class SizingExampleComposer extends SelectorComposer {

	@Wire
	private Listbox goodListbox;
	@Wire
	private Listbox badListbox;
	
		
	private ListModelList<ExampleBean> listboxModel;
	private ListitemRenderer<ExampleBean> ListboxRenderer;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		ListboxRenderer = new ListitemRenderer<ExampleBean>() {
			public void render(Listitem item, ExampleBean data, int index) throws Exception {
				Listcell cell1 = new Listcell();
				cell1.appendChild(new Label(data.getValue1()));
				Listcell cell2 = new Listcell();
				cell2.appendChild(new Label(data.getValue2()));
				Listcell cell3 = new Listcell();
				cell3.appendChild(new Label(data.getValue3()));
				item.appendChild(cell1);
				item.appendChild(cell2);
				item.appendChild(cell3);
			}
		};
		
		listboxModel = new ListModelList<ExampleBean>();
		for (int i = 0; i < 5; i++) {
			listboxModel.add(new ExampleBean("grid model "+i+"-1", "grid model "+i+"-2", "grid model "+i+"-3"));
		}
		goodListbox.setModel(listboxModel);
		goodListbox.setItemRenderer(ListboxRenderer);
		
		badListbox.setModel(listboxModel);
		badListbox.setItemRenderer(ListboxRenderer);
	}
	
	@Listen("onClick=#toggleVFlexToBadListbox")
	public void handleBtnVflex(){
		System.out.println(badListbox.getVflex());
		//Initialy Vflex:false (inactive) and the rows property define the listbox size.
		badListbox.setVflex((badListbox.getVflex().equals("min")?"false":"min"));
	}
	
}
