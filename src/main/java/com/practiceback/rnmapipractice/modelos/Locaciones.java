package main.java.com.practiceback.rnmapipractice.modelos;

public class Locaciones {

    private int id;
    private String name;
    private String type;
    private String dimension;

    public Locaciones(int id, String name, String type, String dimension) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.dimension = dimension;
    }

    public Locaciones(LocacionesRnMApi locacionesRnMApi){
        this.id = locacionesRnMApi.id();
        this.name = locacionesRnMApi.name();
        this.type = locacionesRnMApi.type();
        this.dimension = locacionesRnMApi.dimension();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return "--------------------------------" + "\n" +
                "Name: " + this.name + ", \n" +
                "Status: " + this.type + ", \n" +
                "Dimension: " + this.dimension + ", \n" +
                "---------------------------------";
    }
}
