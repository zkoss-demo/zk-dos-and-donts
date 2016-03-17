package local.support.zk_dos_and_donts.common;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.ListModelList;

public class ExampleModelProvider {

	public ListModelList<ExampleBean> getNewBeanListModelList(){
		List<ExampleBean> innerList = new ArrayList<ExampleBean>();
		for (int i = 0; i < 5; i++) {
			innerList.add(new ExampleBean("Example "+i+"-1", "Example "+i+"-2", "Example "+i+"-3"));
		}		
		return new ListModelList<ExampleBean>(innerList);
	}
	public ListModelList<String> getNewStringListModelList(){
		List<String> innerList = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			innerList.add("Example "+i);
		}		
		return new ListModelList<String>(innerList);
	}
}
