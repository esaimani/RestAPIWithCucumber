package utils;

public enum Constants {

    ADDPLACEAPI("maps/api/place/add/json"),

    GETPLACEAPI("maps/api/place/get/json"),

    DELETEPLACEAPI("maps/api/place/delete/json"),

    UPDATEPLACEAPI("maps/api/place/update/json");

    private final String resource;

    Constants(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }


}
