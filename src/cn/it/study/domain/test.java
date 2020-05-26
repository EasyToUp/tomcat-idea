package cn.it.study.domain;

import cn.it.study.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class test {

    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate( JDBCUtils.getDataSource() );
        System.out.println(template);
        String sql = "select * from user";
        List<User> users = template.query( sql, new BeanPropertyRowMapper<User>( User.class ) );
        for (User user : users) {
            System.out.println( user );
        }
    }
}
