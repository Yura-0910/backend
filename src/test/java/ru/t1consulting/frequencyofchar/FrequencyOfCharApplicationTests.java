package ru.t1consulting.frequencyofchar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@SpringBootTest
class FrequencyOfCharApplicationTests {
	@Autowired private InputController inputController;
	@Autowired private CalculationService calculationService;

	@Test
	void contextLoads() {
		assertNotNull(inputController);
		assertNotNull(calculationService);
	}

	@Test
	void inputDataContainsOnlyLetters(){
		String inputData = "HelloWorld5";
		String expectedResult = "Обрабатываемая строка должна содержать только буквы";

		assertThat(calculationService.initialCheckAllCharIsLetter(inputData), is(expectedResult));
	}

	@Test
	void checkThatCharacterFrequencyIsCorrect(){
		String inputData = "aaaaabcccc";
		String expectedResult = "\"a\":5, \"c\":4, \"b\":1";

		assertThat(calculationService.initialCheckAllCharIsLetter(inputData), is(expectedResult));
	}
}
