package org.turing.pangu.dao;

import java.io.Serializable;
import java.util.List;

import org.turing.pangu.model.PageModel;
import org.turing.pangu.model.ParamModel;

public interface BaseDao<T, ID extends Serializable> {
	/**
	 * 查找实体
	 * 
	 * @author turing
	 * @param id
	 * @return
	 */
	public T select(ID id);

	/**
	 * 查找实体
	 * 
	 * @author turing
	 * @param model
	 *            （存在多个返回最后的实体）
	 * @return
	 */
	public T selectModel(T model);

	/**
	 * 查找列表
	 * 
	 * @author turing
	 * @param model
	 * @return
	 */
	public List<T> selectList(T model);

	/**
	 * 查找确定列表
	 * 
	 * @author turing
	 * @param ids
	 * @return
	 */
	public List<T> selectCertainList(ID... ids);

	/**
	 * 查找分页
	 * 
	 * @author turing
	 * @param param
	 * @return
	 */
	public PageModel<T> selectPage(ParamModel<T> param);

	/**
	 * 查找分页扩展
	 * 
	 * @author turing
	 * @param param
	 * @return
	 */
	public <P, R> PageModel<R> selectPageExtend(ParamModel<P> param);

	/**
	 * 查找数据条数
	 * 
	 * @author turing
	 * @param model
	 * @return
	 */
	public long selectCount(T model);

	/**
	 * 是否存在数据
	 * 
	 * @author turing
	 * @param id
	 * @return
	 */
	public boolean exists(ID id);

	/**
	 * 插入实体
	 * 
	 * @author turing
	 * @param model
	 * @return
	 */
	public boolean insert(T model);

	/**
	 * 插入返回
	 * 
	 * @author turing
	 * @param user
	 * @return
	 */
	public T insertReturnModel(T model);

	/**
	 * 更新实体
	 * 
	 * @author turing
	 * @param model
	 * @return
	 */
	public boolean update(T model);

	/**
	 * 删除实体
	 * 
	 * @author turing
	 * @param id
	 * @return
	 */
	public boolean delete(ID id);
}
