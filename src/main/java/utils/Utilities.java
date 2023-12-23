package utils;

import io.restassured.path.json.JsonPath;

public class Utilities {

    /**
     * method to convert String to Json.
     * @param stringToConvert - pass string to convert
     * @return - JsonPath
     */
    public JsonPath stringToJson(String stringToConvert){
        JsonPath json = new JsonPath(stringToConvert);
        return json;
    }

    /**
     * method to return "key": "value", the value of key from Json.
     * @param jsonPath - json format
     * @param objectKey - key
     * @return - value
     */
    public String getObjectValueFromJson(JsonPath jsonPath, String objectKey){
        return jsonPath.get(objectKey);
    }
}
