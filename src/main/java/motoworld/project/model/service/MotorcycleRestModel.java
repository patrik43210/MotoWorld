package motoworld.project.model.service;

public class MotorcycleRestModel {


    private String id;
    private String make;
    private String model;
    private Integer cc;
    private Integer hp;
    private String type;
    private String image;

    public MotorcycleRestModel() {
    }

    public String getId() {
        return id;
    }

    public MotorcycleRestModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getMake() {
        return make;
    }

    public MotorcycleRestModel setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public MotorcycleRestModel setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getCc() {
        return cc;
    }

    public MotorcycleRestModel setCc(Integer cc) {
        this.cc = cc;
        return this;
    }

    public Integer getHp() {
        return hp;
    }

    public MotorcycleRestModel setHp(Integer hp) {
        this.hp = hp;
        return this;
    }

    public String getType() {
        return type;
    }

    public MotorcycleRestModel setType(String type) {
        this.type = type;
        return this;
    }



    public String getImage() {
        return image;
    }

    public MotorcycleRestModel setImage(String image) {
        this.image = image;
        return this;
    }
}
