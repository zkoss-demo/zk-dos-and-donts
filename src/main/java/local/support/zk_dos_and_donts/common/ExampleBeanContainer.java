package local.support.zk_dos_and_donts.common;

public class ExampleBeanContainer {
	private ExampleBean bean1;
	private ExampleBean bean2;
	
	public ExampleBeanContainer(ExampleBean bean1, ExampleBean bean2) {
		this.bean1 = bean1;
		this.bean2 = bean2;
	}

	public ExampleBean getBean1() {
		return bean1;
	}

	public ExampleBean getBean2() {
		return bean2;
	}
	
}
