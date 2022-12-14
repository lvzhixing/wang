package cn.itsource.dto;

public class User {

    private Long id;
    private String name;
    private String intro;

    public User(Long id, String name, String intro) {
        this.id = id;
        this.name = name;
        this.intro = intro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}