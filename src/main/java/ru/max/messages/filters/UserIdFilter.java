package ru.max.messages.filters;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import ru.max.messages.contexts.UserContext;
import ru.max.messages.service.TokenService;

@Component
@RequiredArgsConstructor
public class UserIdFilter extends OncePerRequestFilter {
  private final TokenService tokenService;

  @Value("${ACCESS_SECRET}")
  private String accessSecret;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    // System.out.println("\tFILTERED " + request.getServletPath());
        
    String authHeader = request.getHeader("Authorization");
    if (authHeader == null) {
      authHeader = request.getParameter("accessToken");
    }

    if (authHeader == null) {
      handleAuthError(response, "Unauthorized", HttpStatus.UNAUTHORIZED.value());
      return;
    }

    String accessToken = authHeader.replace("Bearer ", "");
    if (accessToken == null) {
      handleAuthError(response, "Unauthorized", HttpStatus.UNAUTHORIZED.value());
      return;
    }

    Long userId = tokenService.verifyIdFromToken(accessToken);
    if (userId == null) {
      handleAuthError(response, "Internal Server Error", 500);
      return;
    }

		UserContext.setUserId(userId);
    // System.out.println("\tFILTER DONE ID IS " + userId);

    filterChain.doFilter(request, response);
  }

  private void handleAuthError(HttpServletResponse response, String message, int status) throws IOException {
    response.setStatus(status);
    response.setContentType("application/json");

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(Map.of("message", message, "status", status));
    response.getWriter().write(json);
  }
}
