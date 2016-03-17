package local.support.zk_dos_and_donts.part1;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;

import local.support.zk_dos_and_donts.common.ExampleBean;
import local.support.zk_dos_and_donts.common.ExampleModelProvider;


public class CompVsModelComposer extends SelectorComposer {

	@Wire
	private Grid myGrid;
	@Wire
	private Listbox myListbox;

	private ExampleModelProvider myModelProvider = new ExampleModelProvider();
	private ListModelList<ExampleBean> myGridListModelList = myModelProvider.getNewBeanListModelList();
	private ListModelList<String> myListboxListModelList = myModelProvider.getNewStringListModelList();
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		myGrid.setModel(myGridListModelList);
		myListbox.setModel(myListboxListModelList);
	}
	
	@Listen("onClick=#myGridWrongBtn")
	public void handleGridWrongBtn() {
		ExampleBean myNewBean = new ExampleBean("wrong example 1", "wrong example 2", "wrong example 3");
		Row myNewRow = new Row();
		myNewRow.appendChild(new Label(myNewBean.getValue1()));
		myNewRow.appendChild(new Label(myNewBean.getValue2()));
		myNewRow.appendChild(new Label(myNewBean.getValue3()));
		//This will cause the view to be out of sync with the content of the model.
		//Using a model, this will actually cause an incorrect size / index exception
		myGrid.getRows().appendChild(myNewRow);
		Messagebox.show("Actual model size = "+myGridListModelList.getSize());
	}
	@Listen("onClick=#myGridGoodBtn")
	public void handleGridGoodBtn() {
		ExampleBean myNewBean = new ExampleBean("good example 1", "good example 2", "good example 3");
		//Adding a new element to the model will trigger automatic updates of the view.
		myGridListModelList.add(myNewBean);
		Messagebox.show("Actual model size = "+myGridListModelList.getSize());
	}
	
	@Listen("onClick=#myLbWrongBtn")
	public void handleLbWrongBtn() {
		myListbox.clearSelection();
		//This will cause the view to be out of sync with the actual selection state of the model.
		//The first index will appear selected, but the selection state of the model will not be updated.
		myListbox.setSelectedIndex(0);
		//Show the value of the current selection
		Messagebox.show("Currently selected ="+myListboxListModelList.getSelection());
	}
	@Listen("onClick=#myLbGoodBtn")
	public void handleLbGoodBtn() {
		myListboxListModelList.clearSelection();
		//Setting selection on the model will trigger automatic updates of the view.
		myListboxListModelList.addToSelection(myListboxListModelList.get(0));
		//Show the value of the current selection
		Messagebox.show("Currently selected ="+myListboxListModelList.getSelection());
	}
}
