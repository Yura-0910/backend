package ru.effective_mobile.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import java.security.KeyPair;
import io.jsonwebtoken.security.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.effective_mobile.db.entity.Roles;
import ru.effective_mobile.db.entity.Users;
import ru.effective_mobile.db.repository.RolesRepository;

@Service
@AllArgsConstructor
public class JwtService {

  private RolesRepository rolesRepository;

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
    KeyPair pair = alg.keyPair().build();

    return Jwts.builder().claims(someData).subject(myUser.getEmail())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
        .signWith(pair.getPrivate(), alg).compact();
  }
}
