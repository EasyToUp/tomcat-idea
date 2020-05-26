package cn.it.study.service;

import cn.it.study.domain.PageBean;
import cn.it.study.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理业务接口
 */
public interface UserService {

    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<User> findAll();

    public User login(User user);

    void addUser(User user);

    void deleteUser(String id);

    User findUserById(String id);

    void updateUser(User user);

    void delSelecedUser(String[] ids);

    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
