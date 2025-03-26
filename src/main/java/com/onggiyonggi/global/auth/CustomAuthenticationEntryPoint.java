package com.onggiyonggi.global.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onggiyonggi.global.response.ApiResponse;
import com.onggiyonggi.global.response.Status;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        String exception = (String) request.getAttribute("exception");
        log.info("entrypoint" + exception);
        if (exception == null) {
            setResponse(response, Status.UNAUTHORIZED);
            return;
        }

        switch (exception) {
            case "JWT_NULL" -> setResponse(response, Status.JWT_NULL);
            case "JWT_INVALID" -> setResponse(response, Status.JWT_INVALID);
            default -> setResponse(response, Status.UNAUTHORIZED);
        }
    }

    private void setResponse(HttpServletResponse response, Status status) throws IOException {
        final Map<String, Object> body = new HashMap<>();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ApiResponse<Object> responseBody = ApiResponse.onFailure(
            status.getCode(),
            status.getMessage(),
            null
        );

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), responseBody);
    }
}
