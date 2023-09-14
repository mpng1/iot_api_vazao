package br.com.pointer.sii.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.pointer.sii.api.domain.usuario.Usuario;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;
	
	public String gerarToken(Usuario usuario) {
		try {
		    var algorithm = Algorithm.HMAC256(secret);
		    return JWT.create()
		        .withIssuer("Pointer TI Digital")
		        .withSubject(usuario.getLogin())
		        .withExpiresAt(dataExpiracao())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("Erro ao gerar token", exception);
		}
	}
	
	public String getSubject(String tokenJWT) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
		    return JWT.require(algorithm)
		        // specify an specific claim validations
		        .withIssuer("Pointer TI Digital")
		        // reusable verifier instance
		        .build()
		        .verify(tokenJWT)
		        .getSubject();
		} catch (JWTVerificationException exception){
		    throw new RuntimeException("Token inválido");
		}
	}

	private Instant dataExpiracao() {
		// TODO Auto-generated method stub
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
