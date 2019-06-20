package cn.cqlyy.aigou.domain;

/**
 * This is an entity class 这是一个实体类
 * @Author: cqlyy
 * @Date: 2019/06/19 17:48
 * @Version 1.0
 */
public class Employee {
    private Long id;
    private String username;
    private String password;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
