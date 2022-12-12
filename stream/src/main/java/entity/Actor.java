package entity;

/**
 * @ProjectName practice
 * @PackageName entity
 * @ClassName Actor
 * @Author zhanggeyang
 * @Date 2022-05-11 22:31
 * @Description
 * @Version 1.0
 */

public class Actor {
    private String name;

    public Actor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
