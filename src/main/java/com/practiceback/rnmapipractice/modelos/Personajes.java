package main.java.com.practiceback.rnmapipractice.modelos;

public class Personajes {
    int id;
    String name;
    String status;
    String species;
    String image;

    public Personajes(int id, String name, String status, String species, String image) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.image = image;
    }

    public Personajes(PersonajesRnMApi personajesRnMApi) {
        this.id = personajesRnMApi.id();
        this.name = personajesRnMApi.name();
        this.status = personajesRnMApi.status();
        this.species = personajesRnMApi.species();
        this.image = personajesRnMApi.image();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "--------------------------------" + "\n" +
                "Name: " + this.name + ", \n" +
                "Status: " + this.status + ", \n" +
                "Species: " + this.species + ", \n" +
                "Image: " + this.image + ", \n" +
                "---------------------------------";
    }
}

