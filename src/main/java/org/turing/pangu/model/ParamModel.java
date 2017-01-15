package org.turing.pangu.model;

public class ParamModel<T>{
	
	private Page page;

	public ParamModel(int pageIndex, int pageSize) {
		initModel(pageIndex, pageSize, null);
	}

	public ParamModel(int pageIndex, int pageSize, T model) {
		initModel(pageIndex, pageSize, model);
	}

	private void initModel(int pageIndex, int pageSize, T model) {
		this.model = model;
		this.page=new Page(pageIndex,pageSize);
	}

	private T model;

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public ParamModel() {
		super();
	}
	
	
}
