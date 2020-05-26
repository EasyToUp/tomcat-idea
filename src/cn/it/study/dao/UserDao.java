package cn.it.study.dao;

import cn.it.study.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作
 */
public interface UserDao {

    public List<User> findAll();

    User findUserByUsernameAndPassword(String username, String password);

    void add(User user);

    void delete(Integer id);

    User findById(Integer id);

    void update(User user);

    int findTotalCount(Map<String, String[]> condition);

    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
