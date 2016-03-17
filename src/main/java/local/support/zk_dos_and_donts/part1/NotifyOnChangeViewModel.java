package local.support.zk_dos_and_donts.part1;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.ListModelList;

import local.support.zk_dos_and_donts.common.ExampleBean;
import local.support.zk_dos_and_donts.common.ExampleModelProvider;

public class NotifyOnChangeViewModel {

	private ExampleModelProvider myModelProvider = new ExampleModelProvider();
	
	private ListModelList<ExampleBean> myBeanListModel = myModelProvider.getNewBeanListModelList();
	
	public ListModelList<ExampleBean> getMyBeanListModel() {
		return myBeanListModel;
	}
	public void setMyBeanListModel(ListModelList<ExampleBean> chapter2BeanListModel) {
		this.myBeanListModel = chapter2BeanListModel;
	}
	
	@Command("updateWholeModel")
	public void hangleUpdateWholeModel(){
		myBeanListModel.get(0).setValue1("Full model update");
		BindUtils.postNotifyChange(null, null, this, "myBeanListModel");
	}
	@Command("updateSingleProperty")
	public void handleUpdateSingleProperty(){
		ExampleBean myexampleBean = myBeanListModel.get(0);
		myexampleBean.setValue1("Single property update");
		myBeanListModel.notifyChange(myexampleBean);
	}
}
