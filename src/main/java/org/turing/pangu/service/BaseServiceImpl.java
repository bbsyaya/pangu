package org.turing.pangu.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import org.turing.pangu.dao.BaseDao;
import org.turing.pangu.model.PageModel;
import org.turing.pangu.model.ParamModel;

@Transactional
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaseServiceImpl.class);

	private Class<T> modelClass;

	/** baseDao */
	private BaseDao<T, ID> baseDao;

	public void setBaseDao(BaseDao<T, ID> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	@Transactional(readOnly = true)
	public T select(ID id) {
		return baseDao.select(id);

	}

	@Override
	@Transactional(readOnly = true)
	public T selectModel(T model) {
		return baseDao.selectModel(model);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<T> selectAll() {
		return baseDao.selectAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<T> selectList(T model) {
		return baseDao.selectList(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> selectCertainList(ID... ids) {
		return baseDao.selectCertainList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public PageModel<T> selectPage(ParamModel<T> param) {
		return baseDao.selectPage(param);
	}

	@Override
	@Transactional(readOnly = true)
	public <P, R> PageModel<R> selectPageExtend(ParamModel<P> param) {
		return baseDao.selectPageExtend(param);
	}

	@Override
	@Transactional(readOnly = true)
	public long selectCount(T model) {
		return baseDao.selectCount(model);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean exists(ID id) {
		return baseDao.exists(id);
	}

	@Override
	@Transactional
	public boolean insert(T model) {
		return baseDao.insert(model);
	}

	@Override
	public T insertReturnModel(T model) {
		return baseDao.insertReturnModel(model);
	}

	@Override
	@Transactional
	public boolean update(T model) {
		return baseDao.update(model);
	}

	@Override
	@Transactional
	public boolean delete(ID id) {
		return baseDao.delete(id);
	}
}