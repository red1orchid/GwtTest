package com.gwt.sample.server;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> valuesMap = parseJsonData(req);
        String name = (String)valuesMap.get("name");
        String message = (String)valuesMap.get("message");

        JSONObject jsonObject = createResponseObject(name, message);
        ServletOutputStream stream = resp.getOutputStream();
        stream.print(jsonObject.toJSONString());
    }

    private JSONObject createResponseObject(String name, String message) {
        JSONObject jsonData = new JSONObject();
        jsonData.put("name", name);
        jsonData.put("message", String.format(message, name));

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("data", jsonData);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("response", jsonResponse);
        return jsonObject;
    }

    private Map<String, Object> parseJsonData(HttpServletRequest req) throws IOException {
        String body = readBody(req);
        JSONObject jsonValue = (JSONObject) JSONValue.parse(body);
        return (Map<String, Object>)jsonValue.get("data");
    }

    private String readBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } finally {
            reader.close();
        }
        return sb.toString();
    }
}
