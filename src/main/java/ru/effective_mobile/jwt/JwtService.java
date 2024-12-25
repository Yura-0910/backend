package ru.effective_mobile.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import java.security.KeyPair;
import io.jsonwebtoken.security.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.effective_mobile.db.entity.Roles;
import ru.effective_mobile.db.entity.Users;
import ru.effective_mobile.db.repository.RolesRepository;

@Service
@RequiredArgsConstructor
public class JwtService {

  private final RolesRepository rolesRepository;
  private KeyPair pair;
  private String emailFromJWT = null;
  private Date expirationDate = null;
  private String passwordFromJWT = null;

  //Генерация токена
  public String generateToken(Users myUser) {
    //Вспомогательная данные, для генерации токена
    Map<String, Object> someData = new HashMap<>();
    someData.put("id", myUser.getUserId());
    someData.put("fio", myUser.getFio());
    someData.put("user_pwd", myUser.getPassword());
    //Извлекаем название роли по ее id
    Roles role = rolesRepository.findByRoleId(myUser.getRoleId());
    someData.put("role", role.getRole());

    //Генерация токена
    SignatureAlgorithm alg = SIG.RS256;
    pair = alg.keyPair().build();

    return Jwts.builder().claims(someData).subject(myUser.getEmail())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
        .signWith(pair.getPrivate(), alg).compact();
  }

  //Извлекаем email из JWT токена
  public String extractEmailFromToken(String token) {
    Claims claims = Jwts.parser().verifyWith(pair.getPublic()).build().parseSignedClaims(token)
        .getPayload();

    //Извлекаем, на будущее:: время истечения срока службы токена
    expirationDate = claims.getExpiration();

    //Извлекаем пароль из токена, на будущее:: используем при аутентификации
    passwordFromJWT = claims.get("user_pwd", String.class);

    //Если через Bearer Token
    emailFromJWT = claims.getSubject();

    return emailFromJWT;
  }

  //Проверяем, не истекло ли время жизни токена
  public boolean isTokenNotExpired(String token) {
    boolean notExpired = false;
    if (emailFromJWT != null) {
      notExpired = expirationDate.after(new Date());
    }
    return notExpired;
  }

  /*
   * Вручную проверяем, совпадает ли email\pwd из запроса и из БД:: то есть вручную проводим
   * аутентификацию
   */
  public boolean isEmailAndPwdMatch(String loginFromDB, String passwordFromDB) {
    return emailFromJWT.equals(loginFromDB) && passwordFromJWT.equals(passwordFromDB);
  }

}
