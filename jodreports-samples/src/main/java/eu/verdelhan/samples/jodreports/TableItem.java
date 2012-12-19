package eu.verdelhan.samples.jodreports;


public class TableItem {

	private String foo;
	private String bar;
	private int test;

	public TableItem() {
	}

	public TableItem(String foo, String bar, int test) {
		this.foo = foo;
		this.bar = bar;
		this.test = test;
	}

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	public int getTest() {
		return test;
	}

	public void setTest(int test) {
		this.test = test;
	}
}
