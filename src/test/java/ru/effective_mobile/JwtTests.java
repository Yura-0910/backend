package ru.effective_mobile;

import java.util.Arrays;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import ru.effective_mobile.dto.SignUpDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
@TestMethodOrder(OrderAnnotation.class)
class JwtTests {
	@Autowired
	private TestRestTemplate testRestTemplate;
	private static String jwtToken;

	//Регистрация нового пользователя
	@Test
	@Order(1)
	void test_001_signUp() {
		String tmpToken = null;
		String strURL = "/api/signUpJWT";
		String answer = "Вы успешно зарегистрировались, ваш токен::";

		SignUpDto signUpDTO = new SignUpDto();
		signUpDTO.setEmail("test@yandex.ru");
		signUpDTO.setPassword("test_pwd");
		signUpDTO.setRole("USER");

		ResponseEntity<String> response = testRestTemplate.postForEntity(strURL, signUpDTO,
				String.class);

		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
		assertThat(response.getBody(), containsString(answer));

		//Извлекаем JWT токен из тела ответа
		tmpToken = response.getBody();
		jwtToken = tmpToken.substring(answer.length() + 1);
	}

	//Проверяем аутентификацию
	@Test
	@Order(2)
	void test_002_signIn() {
		String strURL = "/api/signInJWT";
		String answer = "test@yandex.ru:: Вы успешно прошли аутентификацию";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		if (jwtToken != null) {
			headers.set("Authorization", "Bearer " + jwtToken);
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			ResponseEntity<String> responseEntity = testRestTemplate.exchange(strURL, HttpMethod.POST,
					entity, String.class);
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.ACCEPTED));
			assertThat(responseEntity.getBody(), containsString(answer));
		}
		else {
			throw new RuntimeException("Не получен токен из первого теста");
		}
	}
}
