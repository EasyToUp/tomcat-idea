package cn.it.study.service.impl;

import cn.it.study.dao.UserDao;
import cn.it.study.dao.impl.UserDaoImpl;
import cn.it.study.domain.PageBean;
import cn.it.study.domain.User;
import cn.it.study.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User login(User user) {
        return userDao.findUserByUsernameAndPassword( user.getUsername(), user.getPassword() );
    }

    @Override
    public void addUser(User user) {
        userDao.add( user );
    }

    @Override
    public void deleteUser(String id) {
        userDao.delete( Integer.parseInt( id ) );
    }

    @Override
    public User findUserById(String id) {
        return userDao.findById( Integer.parseInt( id ) );
    }

    @Override
    public void updateUser(User user) {
        userDao.update( user );
    }

    @Override
    public void delSelecedUser(String[] ids) {
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                userDao.delete( Integer.parseInt( id ) );
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt( _currentPage );
        if (currentPage <= 0) {
            currentPage = 1;
        }
        int rows = Integer.parseInt( _rows );
        PageBean<User> pageBean = new PageBean<User>();
        pageBean.setCurrentPage( currentPage );
        pageBean.setRows( rows );
        int totalCount = userDao.findTotalCount( condition );
        pageBean.setTotalCount( totalCount );
        int totalPage = totalCount % rows == 0 ? totalCount / rows : totalCount / rows + 1;
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        if (currentPage <= 0) {
            currentPage = 1;
        }
        int start = (currentPage - 1) * rows;
        List<User> list = userDao.findByPage( start, rows, condition );
        pageBean.setList( list );
        pageBean.setTotalPage( totalPage );
        return pageBean;
    }
}
