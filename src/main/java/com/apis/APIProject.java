package com.apis;

import com.google.gson.JsonObject;
import com.utils.API;
import okhttp3.Response;


import org.junit.Assert;


public class APIProject {
    private API apiUtils = new API();
    private String subURI = "/rest/v1/projects";
    public void createProjectSuccessfully(String baseURI, String projectName, String bearerToken) {
        String uri = baseURI + subURI;
        JsonObject data = new JsonObject();
        data.addProperty("name", projectName);
        Response response = apiUtils.sendPostWithAuthorizeRequest(uri, data, bearerToken);
        Assert.assertEquals(200, response.code());
    }
}
