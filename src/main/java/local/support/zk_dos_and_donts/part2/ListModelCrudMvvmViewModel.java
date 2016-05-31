package local.support.zk_dos_and_donts.part2;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModelList;

import local.support.zk_dos_and_donts.common.ExampleBean;

public class ListModelCrudMvvmViewModel {

	private ListModelList<ExampleBean> myListModel;
	private int index;
	private String value1;
	private String value2;
	private String value3;
	
	public ListModelList<ExampleBean> getMyListModel() {
		return myListModel;
	}
	
	@Init
	public void init(){
		myListModel = new ListModelList<ExampleBean>();
		for (int i = 0; i < 10; i++) {
			myListModel.add(new ExampleBean("value 1-"+i, "value 2-"+i, "value 3-"+i));
		}
		myListModel.setMultiple(false);
	}	
	
	// MMVM commands
	@Command
	public void cmdInsertItemAt(){
		insertItemAt(index, new ExampleBean(value1, value2, value3));
	}
	@Command
	public void cmdInsertItemBeforeSelected(){
		ExampleBean selectedBean = myListModel.getSelection().iterator().next();
		insertItemBefore(selectedBean, new ExampleBean(value1, value2, value3));
	}
	@Command
	public void cmdInsertItemFirst(){
		insertItemFirst(new ExampleBean(value1, value2, value3));
	}
	@Command
	public void cmdInsertItemLast(){
		insertItemLast(new ExampleBean(value1, value2, value3));
	}
	@Command
	public void cmdRemoveSelectedItem(){
		ExampleBean selectedBean = myListModel.getSelection().iterator().next();
		removeItem(selectedBean);
		myListModel.addToSelection(myListModel.get(0));
	}
	@Command
	public void cmdRemoveItemAt(){
		removeItemAt(index);
		myListModel.addToSelection(myListModel.get(0));
	}
	@Command
	public void cmdUpdateValue1OnIndex(){
		updateValue1OnIndex(index,value1);
	}
	@Command
	public void cmdUpdateAllValuesOnIndex(){
		updateAllValuesOnIndex(index,value1,value2,value3);
	}
	@NotifyChange({"value1","value2","value3","index"})
	@Command
	public void handleSelect(){
		ExampleBean selectedBean = myListModel.getSelection().iterator().next();
		value1 = selectedBean.getValue1();
		value2 = selectedBean.getValue2();
		value3 = selectedBean.getValue3();
		index = myListModel.indexOf(selectedBean);
	}
	
	//internal logic
	private void insertItemAt(int index, ExampleBean item){
		myListModel.add(index, item);
	}
	private void insertItemBefore(ExampleBean target, ExampleBean item){
		int targetIndex = myListModel.indexOf(target);
		insertItemAt(targetIndex,item);
	}
	private void insertItemFirst(ExampleBean item){
		insertItemAt(0, item);
	}
	public void insertItemLast(ExampleBean item){
		myListModel.add(item);
	}
	private void removeItem(ExampleBean item){
		myListModel.remove(item);
	}
	private void removeItemAt(int index){
		myListModel.remove(index);
	}
	private void updateValue1OnIndex(int index, String newValue1){
		myListModel.get(index).setValue1(newValue1);
		BindUtils.postNotifyChange(null, null, myListModel.get(index), "value1");
	}
	private void updateAllValuesOnIndex(int index, String newValue1, String newValue2, String newValue3){
		ExampleBean exampleBean = myListModel.get(index);
		exampleBean.setValue1(newValue1);
		exampleBean.setValue2(newValue2);
		exampleBean.setValue3(newValue3);
		myListModel.notifyChange(exampleBean);
	}
	
	//Getters and setters
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
}
