package utils;

import pojo.AddPlace;
import pojo.Location;

import java.util.LinkedList;
import java.util.List;

public class TestDataBuilder {

    public AddPlace addPlacePayload(String name,String language, String address){
        AddPlace pojo = new AddPlace();
        pojo.setAccuracy(60);
        pojo.setLanguage(language);
        pojo.setAddress(address);
        pojo.setName(name);
        pojo.setWebsite("http://google.com");
        pojo.setPhone_number("(+91) 983 893 3937");

        List<String> types = new LinkedList<>();
        types.add("shoe park");
        types.add("shop");

        pojo.setTypes(types);

        Location location = new Location();
        location.setLat(38.383494);
        location.setLng(33.427362);

        pojo.setLocation(location);
        return pojo;
    }

}
