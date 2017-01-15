package org.turing.pangu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, creatorVisibility = Visibility.NONE)
public class PageModel<T> implements Serializable{
	
	private Page page;
	 
	/**
	 *	数据实体集合
	 **/
	private List<T> modelList=new ArrayList<T>();

	@JsonProperty
	public List<T> getModelList() {
		return modelList;
	}

	public void setModelList(List<T> modelList) {
		this.modelList = modelList;
	}

	@JsonProperty
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
}
