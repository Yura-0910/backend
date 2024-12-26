package ru.effective_mobile.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
/**
 * Аутентификация через JWT
 */
public class FilterAuthJWT extends OncePerRequestFilter {

  @Autowired
  private UserDetailsServiceImpl userDetailsServiceImpl;
  @Autowired
  private JwtService jwtService;

  @Autowired
  @Qualifier("handlerExceptionResolver")
  private HandlerExceptionResolver resolver;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      // Получаем токен из заголовка
      String authHeader = request.getHeader("Authorization");
      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
      }

      // Обрезаем префикс и получаем email из токена
      String jwt = authHeader.substring("Bearer ".length());
      String emailName = jwtService.extractEmailFromToken(jwt);

      UserDetails userDetails = null;
      if (emailName != null && SecurityContextHolder.getContext().getAuthentication() == null &&
          !emailName.equals("UserNotFoundInJWT")) {

        //Заполняем UserDetails данными из БД
        userDetails = userDetailsServiceImpl.loadUserByUsername(emailName);
      } else {
        filterChain.doFilter(request, response);
        return;
      }

      //Аутентификация внутри "if": точнее в методе isEmailAndPwdMatch
      if (userDetails != null && jwtService.isTokenNotExpired(jwt) &&
          jwtService.isEmailAndPwdMatch(userDetails.getUsername(), userDetails.getPassword())) {

        //Наполняем SecurityContextHolder данными
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails.getUsername(),
            userDetails.getPassword(),
            userDetails.getAuthorities()
        );
        Object detailsFromRequest = new WebAuthenticationDetailsSource().buildDetails(request);
        authToken.setDetails(detailsFromRequest);
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
      }

      filterChain.doFilter(request, response);
    } catch (Exception e) {
      /**
       * В "resolver" передаем исключение, возникшее в блоке try
       * "resolver" должен быть типа HandlerExceptionResolver, что бы его можно было
       * отловить в ControllerAdvice
       *
       * Так поступаем, так как ошибки на этапе аутентификации возникают до того, как
       * ControllerAdvice начинает работать, т.е. ControllerAdvice просто так не может
       * отловить ошибки на стадии аутентификации, нужно пробросить ошибку на уровень
       * ControllerAdvice
       */
      resolver.resolveException(request, response, null, e);
    }
  }
}
