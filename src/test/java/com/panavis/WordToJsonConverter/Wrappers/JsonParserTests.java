package com.panavis.WordToJsonConverter.Wrappers;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonParserTests {

    private JSONObject createJsonWithNameAndStringValues(String[] names, String[] values) {
        assert(names.length == values.length);
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < names.length; i++)
            jsonObject.put(names[i], values[i]);
        return jsonObject;
    }

    @Test
    public void shouldParseOneNameAndStringValuePairObject() {
        //  { "case" : "value" }

        JSONObject unwrappedJsonObject = createJsonWithNameAndStringValues(
                                        new String[] {Keywords.CASE},
                                        new String[] {"value"});
        JsonObject wrappedJsonObject = JsonParser.parseJsonObject(unwrappedJsonObject);
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addNameValuePair(Keywords.CASE, "value");

        assertEquals(expectedJsonObject.toString(), wrappedJsonObject.toString());
    }

    @Test
    public void shouldParseOneNameAndArrayWithTwoStringsObject() {
        // {
        //      "case": ["one", "two"]
        //  }

        String one = "one";
        String two = "two";
        JSONArray unwrappedJsonArray = new JSONArray();
        unwrappedJsonArray.put(one);
        unwrappedJsonArray.put(two);
        JSONObject unwrappedJsonObject = new JSONObject();
        unwrappedJsonObject.put(Keywords.CASE, unwrappedJsonArray);
        JsonObject actualWrappedJsonObject = JsonParser.parseJsonObject(unwrappedJsonObject);

        JsonArray wrappedJsonArray = new JsonArray();
        wrappedJsonArray.putValue(one);
        wrappedJsonArray.putValue(two);
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addNameValuePair(Keywords.CASE, wrappedJsonArray);

        assertEquals(expectedJsonObject.toString(), actualWrappedJsonObject.toString());
    }


    @Test
    public void shouldParseOneNameAndArrayWithOneJsonObjectValuePairObject() {
        //  {
        //      "case": [
        //                  {   "nested_name": "nested_value"   }
        //              ]
        //  }

        String nestedName = "nested_name";
        String nestedValue = "nested_value";
        JSONObject nestedUnwrappedJsonObject = createJsonWithNameAndStringValues(
                                        new String[] {nestedName},
                                        new String[] {nestedValue});
        JSONArray unwrappedJsonArray = new JSONArray();
        unwrappedJsonArray.put(nestedUnwrappedJsonObject);
        JSONObject topLevelUnwrappedJsonObject = new JSONObject();
        topLevelUnwrappedJsonObject.put(Keywords.CASE, unwrappedJsonArray);
        JsonObject actualWrappedJsonObject = JsonParser.parseJsonObject(topLevelUnwrappedJsonObject);

        JsonObject nestedWrappedJsonObject = new JsonObject();
        nestedWrappedJsonObject.addNameValuePair(nestedName, nestedValue);
        JsonArray wrappedJsonArray = new JsonArray();
        wrappedJsonArray.putValue(nestedWrappedJsonObject);
        JsonObject topLevelExpectedJsonObject = new JsonObject();
        topLevelExpectedJsonObject.addNameValuePair(Keywords.CASE, wrappedJsonArray);

        assertEquals(topLevelExpectedJsonObject.toString(), actualWrappedJsonObject.toString());
    }

    @Test
    public void shouldParseOneNameAndArrayWithTwoJsonObjectValuePairObject() {
        //  {
        //      "case": [
        //                  {   "nested_name_one": "nested_value_one"   },
        //                  {   "nested_name_two": "nested_value_two"   }
        //              ]
        //  }

        String nestedNameOne = "nested_name_one";
        String nestedValueOne = "nested_value_one";
        String nestedNameTwo = "nested_name_two";
        String nestedValueTwo = "nested_value_two";

        JSONObject nestedUnwrappedJsonObjectOne = createJsonWithNameAndStringValues(
                new String[]{nestedNameOne},
                new String[]{nestedValueOne});
        JSONObject nestedUnwrappedJsonObjectTwo = createJsonWithNameAndStringValues(
                new String[]{nestedNameTwo},
                new String[]{nestedValueTwo});
        JSONArray unwrappedJsonArray = new JSONArray();
        unwrappedJsonArray.put(nestedUnwrappedJsonObjectOne);
        unwrappedJsonArray.put(nestedUnwrappedJsonObjectTwo);

        JSONObject topLevelUnwrappedJsonObject = new JSONObject();
        topLevelUnwrappedJsonObject.put(Keywords.CASE, unwrappedJsonArray);
        JsonObject actualWrappedJsonObject = JsonParser.parseJsonObject(topLevelUnwrappedJsonObject);

        JsonObject nestedWrappedJsonObjectOne = new JsonObject();
        nestedWrappedJsonObjectOne.addNameValuePair(nestedNameOne, nestedValueOne);
        JsonObject nestedWrappedJsonObjectTwo = new JsonObject();
        nestedWrappedJsonObjectTwo.addNameValuePair(nestedNameTwo, nestedValueTwo);
        JsonArray wrappedJsonArray = new JsonArray();
        wrappedJsonArray.putValue(nestedWrappedJsonObjectOne);
        wrappedJsonArray.putValue(nestedWrappedJsonObjectTwo);
        JsonObject topLevelExpectedJsonObject = new JsonObject();
        topLevelExpectedJsonObject.addNameValuePair(Keywords.CASE, wrappedJsonArray);

        assertEquals(topLevelExpectedJsonObject.toString(), actualWrappedJsonObject.toString());
    }

    @Test
    public void shouldParseOneNameAndArrayWithOneJsonObjectWithNestedJson() {
        //  {
        //      "case": [
        //                  {   "name_level_one": [
        //                                          {   "name_level_two": "value_level_two" }
        //                                        ]
        //                  }
        //              ]
        //  }

        String nameLevelOne = "name_level_one";
        String nameLevelTwo = "name_level_two";
        String valueLevelTwo = "value_level_two";
        JSONObject levelTwoUnwrappedJsonObject = new JSONObject();
        levelTwoUnwrappedJsonObject.put(nameLevelTwo, valueLevelTwo);
        JSONArray levelTwoUnwrappedJsonArray = new JSONArray();
        levelTwoUnwrappedJsonArray.put(levelTwoUnwrappedJsonObject);
        JSONObject levelOneUnwrappedJsonObject = new JSONObject();
        levelOneUnwrappedJsonObject.put(nameLevelOne, levelTwoUnwrappedJsonArray);
        JSONArray levelOneUnwrappedJsonArray = new JSONArray();
        levelOneUnwrappedJsonArray.put(levelOneUnwrappedJsonObject);
        JSONObject topLevelUnwrappedJsonObject = new JSONObject();
        topLevelUnwrappedJsonObject.put(Keywords.CASE, levelOneUnwrappedJsonArray);
        JsonObject expectedJsonObject = JsonParser.parseJsonObject(topLevelUnwrappedJsonObject);

        JsonObject levelTwoWrappedJsonObject = new JsonObject();
        levelTwoWrappedJsonObject.addNameValuePair(nameLevelTwo, valueLevelTwo);
        JsonArray levelTwoWrappedJsonArray = new JsonArray();
        levelTwoWrappedJsonArray.putValue(levelTwoWrappedJsonObject);
        JsonObject levelOneWrappedJsonObject = new JsonObject();
        levelOneWrappedJsonObject.addNameValuePair(nameLevelOne, levelTwoWrappedJsonArray);
        JsonArray levelOneWrappedJsonArray = new JsonArray();
        levelOneWrappedJsonArray.putValue(levelOneWrappedJsonObject);
        JsonObject topLevelWrappedJsonObject = new JsonObject();
        topLevelWrappedJsonObject.addNameValuePair(Keywords.CASE, levelOneWrappedJsonArray);

        assertEquals(expectedJsonObject.toString(), topLevelWrappedJsonObject.toString());
    }
}
