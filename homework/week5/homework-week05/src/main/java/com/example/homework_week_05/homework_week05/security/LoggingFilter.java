package com.example.homework_week_05.homework_week05.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest =
                new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse =
                new ContentCachingResponseWrapper(response);

        filterChain.doFilter(wrappedRequest, wrappedResponse);

        logRequest(wrappedRequest);
        logResponse(wrappedResponse);

        wrappedResponse.copyBodyToResponse();
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        String body = new String(
                request.getContentAsByteArray(),
                StandardCharsets.UTF_8
        );

        log.info("➡️ Request: {} {} body={}",
                request.getMethod(),
                request.getRequestURI(),
                body);
    }

    private void logResponse(ContentCachingResponseWrapper response) {
        String body = new String(
                response.getContentAsByteArray(),
                StandardCharsets.UTF_8
        );

        log.info("⬅️ Response: status={} body={}",
                response.getStatus(),
                body);
    }
}
