package local.support.zk_dos_and_donts.part1;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModelList;

import local.support.zk_dos_and_donts.common.ExampleBean;
import local.support.zk_dos_and_donts.common.ExampleModelProvider;

public class WiringEfficiencyViewModel {

	private ExampleModelProvider myModelProvider = new ExampleModelProvider();
	
	private ListModelList<ExampleBean> myBeanListModel = myModelProvider.getNewBeanListModelList();
	
	public ListModelList<ExampleBean> getMyBeanListModel() {
		return myBeanListModel;
	}
	public void setMyBeanListModel(ListModelList<ExampleBean> chapter2BeanListModel) {
		this.myBeanListModel = chapter2BeanListModel;
	}
	
	@Command("updateValue")
	public void handleUpdateValue(){
		ExampleBean myNewBean = new ExampleBean("new value 1", "new value 2", "new value 3");
		myBeanListModel.add(myNewBean);
	}
}
