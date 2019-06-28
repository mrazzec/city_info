package by.dorozhuk.citytouristbot.model;

public class Info {

    private Integer id;

    private String info;

    private City city;

    public Info() {
    }

    public Info(String info, City city) {
        this.city = city;
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
