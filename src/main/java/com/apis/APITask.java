package com.apis;

import com.google.gson.JsonObject;
import com.utils.API;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;


public class APITask {
    private API apiUtils = new API();
    private String subURI = "/rest/v1/tasks";
    public Response getActiveTasks(String baseURI, String bearerToken) {
        String uri = baseURI + subURI;
        return apiUtils.sendGetWithAuthorizeRequest(uri, bearerToken);
    }

    public void verifyTaskHasBeenCreatedSuccessfully(String baseURI, String bearerToken, String taskName) {
        Response response = getActiveTasks(baseURI, bearerToken);
        boolean result = false;
        try {
            JSONArray array = new JSONArray(response.body().string());
            for (int i = 0; i < array.length(); i++) {
                JSONObject jObject = array.getJSONObject(i);
                String keyValue = jObject.get("content").toString();
                if (keyValue.equals(taskName)) {
                    result = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(result);
    }

    public String getIdBaseOnTaskName(String baseURI, String bearerToken, String taskName) {
        Response response = getActiveTasks(baseURI, bearerToken);
        String taskId = null;
        try {
            JSONArray array = new JSONArray(response.body().string());
            for (int i = 0; i < array.length(); i++) {
                JSONObject jObject = array.getJSONObject(i);
                String keyValue = jObject.get("content").toString();
                if (keyValue.equals(taskName)) {
                    taskId = jObject.get("id").toString();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskId;
    }

    public void reopenATaskSuccessfully(String baseURI, String bearerToken, String taskName){
        String taskId = getIdBaseOnTaskName(baseURI, bearerToken, taskName);
        String updatedURI = baseURI + subURI + "/" + taskId + "/reopen";
        JsonObject data = null;
        Response response = apiUtils.sendPostWithAuthorizeRequest(updatedURI, data, bearerToken);
        Assert.assertEquals(200, response.code());
    }


}
