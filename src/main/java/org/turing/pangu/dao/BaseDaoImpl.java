package org.turing.pangu.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import org.turing.pangu.mapper.BaseMapper;
import org.turing.pangu.model.Page;
import org.turing.pangu.model.PageModel;
import org.turing.pangu.model.ParamModel;
import org.turing.pangu.service.BaseServiceImpl;

public class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaseServiceImpl.class);

	private Class<T> modelClass;

	public BaseDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) t).getActualTypeArguments();
		Class<T> modelClass = (Class<T>) params[0];
	}
	
	/** baseMapper */
	private BaseMapper<T, ID> baseMapper;

	public void setBaseMapper(BaseMapper<T, ID> baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public T select(ID id) {
		T t = null;
		try {
			t = baseMapper.select(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return t;

	}

	@Override
	@Transactional(readOnly = true)
	public T selectModel(T model) {
		T t = null;
		try {
			List<T> list = baseMapper.selectList(model);
			if (list.size() == 0) {
			} else if (list.size() == 1) {
				t = list.get(0);
			} else {
				t = list.get(list.size() - 1);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return t;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<T> selectAll(){
		List<T> list = null;
		try {
			list = baseMapper.selectAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<T> selectList(T model) {
		List<T> list = null;

		try {
			list = baseMapper.selectList(model);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> selectCertainList(ID... ids) {
		List<T> list = null;
		try {
			list = baseMapper.selectCertainList(ids);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public PageModel<T> selectPage(ParamModel<T> param) {
		List<T> modelList = new ArrayList<T>();
		PageModel<T> pageModel = new PageModel<T>();
		try {
			Long totalCount = baseMapper.selectCount(param.getModel());
			if (totalCount > 0) {
				modelList = baseMapper.selectPage(param);
			}
			pageModel.setModelList(modelList);
			Page page = param.getPage();
			page.setTotalCount(totalCount);
			pageModel.setPage(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return pageModel;
	}

	@Override
	@Transactional(readOnly = true)
	public <P, R> PageModel<R> selectPageExtend(ParamModel<P> param) {
		List<R> modelList = new ArrayList<R>();
		PageModel<R> pageModel = new PageModel<R>();
		try {
			Long totalCount = baseMapper.selectCountExtend(param.getModel());

			if (totalCount > 0) {
				modelList = baseMapper.selectPageExtend(param);
			}
			pageModel.setModelList(modelList);
			Page page = param.getPage();
			page.setTotalCount(totalCount);
			pageModel.setPage(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return pageModel;
	}

	@Override
	@Transactional(readOnly = true)
	public long selectCount(T model) {
		long count = 0;
		try {
			count = baseMapper.selectCount(model);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return count;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean exists(ID id) {
		boolean flag = false;
		try {
			flag = this.select(id) != null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean insert(T model) {
		long flag = 0;
		try {
			flag = baseMapper.insert(model);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return flag > 0;
	}

	@Override
	public T insertReturnModel(T model) {
		try {
			baseMapper.insert(model);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return model;
	}

	@Override
	@Transactional
	public boolean update(T model) {
		int result = 0;
		try {
			result = baseMapper.update(model);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result > 0;
	}

	@Override
	@Transactional
	public boolean delete(ID id) {
		int result = 0;
		try {
			result = baseMapper.delete(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result > 0;
	}
}