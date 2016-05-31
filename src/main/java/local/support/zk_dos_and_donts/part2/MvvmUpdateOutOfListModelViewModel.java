package local.support.zk_dos_and_donts.part2;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import local.support.zk_dos_and_donts.common.ExampleBean;
import local.support.zk_dos_and_donts.common.ExampleBeanContainer;

public class MvvmUpdateOutOfListModelViewModel {
	private ExampleBean myBean;
	private List<ExampleBean> myBeanList;
	private ExampleBeanContainer myBeanContainer;
	
	
	@Init
	public void init(){
		myBean = new ExampleBean("String 1", "String 2", "String 3");
		myBeanList = new ArrayList<ExampleBean>();
		myBeanList.add(new ExampleBean("List String 11","List String 12","List String 13"));
		myBeanList.add(new ExampleBean("List String 21","List String 22","List String 23"));
		myBeanList.add(new ExampleBean("List String 31","List String 32","List String 33"));
		myBeanContainer = new ExampleBeanContainer(
				new ExampleBean("child bean 1", "child bean 2", "child bean 3"),
				new ExampleBean("child bean 4", "child bean 5", "child bean 6")
			);
	}
	
	@Command
	public void handleMyBeanUpdate(){
		myBean.setValue1("String 1 - Updated");
		BindUtils.postNotifyChange(null, null, myBean, "value1");
	}

	@Command
	public void handleMyBeanContainerUpdate(){
		ExampleBean bean1 = myBeanContainer.getBean1();
		bean1.setValue1("Child bean 1 - updated");
		BindUtils.postNotifyChange(null, null, bean1, "value1");
	}

	@Command
	public void handleMyBeanListUpdate(){
		ExampleBean selectedBean = myBeanList.get(0);
		selectedBean.setValue1("List String 11 - updated");
		BindUtils.postNotifyChange(null, null, selectedBean, "value1");
	}
	
	@Command
	public void handleMyBeanListFullUpdate(){
		ExampleBean selectedBean = myBeanList.get(0);
		selectedBean.setValue1("List String 11 - full List updated");
		BindUtils.postNotifyChange(null, null, this, "myBeanList");
	}

	public ExampleBean getMyBean() {
		return myBean;
	}

	public List<ExampleBean> getMyBeanList() {
		return myBeanList;
	}

	public ExampleBeanContainer getMyBeanContainer() {
		return myBeanContainer;
	}
	
	
}
