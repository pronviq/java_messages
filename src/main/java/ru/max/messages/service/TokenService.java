package ru.max.messages.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import ru.max.messages.exceptions.Exceptions;

@Service
public class TokenService {
	@Value("${ACCESS_SECRET}")
  private String accessSecret;

	public Long verifyIdFromToken(String accessToken) {
		Algorithm algorithm = Algorithm.HMAC256(accessSecret);
    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
    DecodedJWT jwt;
    try {
      jwt = jwtVerifier.verify(accessToken);
    } catch (Exception e) {
			throw Exceptions.UNAUTHORIZED;
    }

    Long userId = jwt.getClaim("user_id").asLong();
		return userId;
	}
}
