package payLoads;

public class Payload {

    public static String addPlacePayload(){
        return "{\n" +
                "    \"location\": {\n" +
                "        \"lat\": -38.383494,\n" +
                "        \"lng\": 33.427362\n" +
                "    },\n" +
                "    \"accuracy\": 50,\n" +
                "    \"name\": \"Frontline house\",\n" +
                "    \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "    \"address\": \"29, side layout, cohen 09\",\n" +
                "    \"types\": [\n" +
                "        \"shoe park\",\n" +
                "        \"shop\"\n" +
                "    ],\n" +
                "    \"website\": \"http://google.com\",\n" +
                "    \"language\": \"French-IN\"\n" +
                "}";
    }
    
    public static String updatePlacePayload(String placeId, String newAddress){
        return "{\n" +
                "\"place_id\":\""+placeId+"\",\n" +
                "\"address\":\""+newAddress+"\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";
    }

    public enum Address{
        MUMBAI_ADDRESS("B-302, Sigma tower, Mumbai");

        public final String locationAddress;
        Address(String locationAddress){
            this.locationAddress = locationAddress;
        }
    }
}
