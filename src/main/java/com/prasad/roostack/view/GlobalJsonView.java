package com.prasad.roostack.view;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

import static com.prasad.roostack.constants.CommonConstants.*;

/**
 * Created by Leeladurga Prasad Gunti on 06-Apr-2015 11:26.
 */
public class GlobalJsonView implements View {
    @Override
    public String getContentType() {
        return APPLICATION_TYPE_JSON;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Object outputResponse = model.get(RESPONSE_KEY);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(outputResponse);

        PrintWriter writer = response.getWriter();
        writer.write(jsonString);
    }
}
