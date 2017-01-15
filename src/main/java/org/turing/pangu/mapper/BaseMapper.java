package org.turing.pangu.mapper;

import java.io.Serializable;
import java.util.List;

import org.turing.pangu.model.ParamModel;

public interface BaseMapper<T,ID extends Serializable> {
	
	/**
	 * 查找实体
	 * @author turing
	 * @param id
	 * @return
	 */
	public T select(ID id);
	
	/**
	 * 查找实体
	 * @author turing
	 * @param model
	 * @return
	 */
	public T selectModel(T model);
	
	/**
	 * 查找列表
	 * @author turing
	 * @param model
	 * @return
	 */
	public List<T> selectList(T model);
	
	/**
	 * 查找确定列表
	 * @author turing
	 * @param ids
	 * @return
	 */
	public List<T> selectCertainList(ID... ids);
	
	/**
	 * 查找分页
	 * @author turing
	 * @param param
	 * @return
	 */
	public List<T> selectPage(ParamModel<T> param);
	
	/**
	 * 查找分页扩展
	 * @author turing
	 * @param param
	 * @return
	 */
	public <P,R> List<R> selectPageExtend(ParamModel<P> param);
	
	
	/**
	 * 查找数据条数
	 * @author turing
	 * @param model
	 * @return
	 */
	public <P> long selectCount(P model);

	/**
	 * 查找数据条数
	 * @author turing
	 * @param model
	 * @return
	 */
	public <P> long selectCountExtend(P model);
	
	/**
	 * 插入实体
	 * @author turing
	 * @param model
	 * @return
	 */
	public int insert(T model);
	
	/**
	 * 更新实体
	 * @author turing
	 * @param model
	 * @return
	 */
	public int update(T model);
	
	/**
	 * 删除实体
	 * @author turing
	 * @param id
	 * @return
	 */
	public int delete(ID id);
}
