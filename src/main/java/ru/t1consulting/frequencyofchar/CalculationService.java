package ru.t1consulting.frequencyofchar;

import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CalculationService {

    public String initialCheckAllCharIsLetter(String dataFromRequest){
        String processingResult = "";
        boolean allCharsIsLetter = true;

        char[] allCharsSeparately = dataFromRequest.trim().toLowerCase().toCharArray();
        for(char currentChar: allCharsSeparately){
            if (!Character.isLetter(currentChar)){
                allCharsIsLetter = false;
                processingResult = "Обрабатываемая строка должна содержать только буквы";
            }
        }
        if(allCharsIsLetter){
            processingResult = countingFrequencyOfChars(allCharsSeparately);
        }
        return processingResult;
    }

    public String countingFrequencyOfChars(char[] allCharsSeparately){
        Map<Character, Integer> mapWithCharFrequency = new HashMap<>();
        Map<Character, Integer> sortingResult;
        String processingResult;

        for(char currentChar: allCharsSeparately){
            if(!mapWithCharFrequency.containsKey(currentChar)){
                addCurrentCharToMap(mapWithCharFrequency, currentChar);
            }
            else{
                increaseCurrentCharFrequencyInMap(mapWithCharFrequency, currentChar);
            }
        }
        sortingResult = sortingOfMap(mapWithCharFrequency);
        processingResult = createStringRequiredFormat(sortingResult);
        return processingResult;
    }

    public void addCurrentCharToMap(Map<Character, Integer>  map, char currentChar){
        int initialFrequency = 1;
        map.put(currentChar, initialFrequency);
    }

    public void increaseCurrentCharFrequencyInMap(Map<Character, Integer> map, char currentChar){
        int incrementStep = 1;
        map.put(currentChar, (map.get(currentChar) + incrementStep));
    }

    public Map<Character, Integer> sortingOfMap(Map<Character, Integer> inputData){
        //Результат сортировки помещаем в LinkedHashMap, т.е эта коллекция сохраняет порядок
        return inputData.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public String createStringRequiredFormat(Map<Character, Integer> inputData){
        return inputData.keySet()
                .stream()
                .map(key -> "\"" + key +"\"" + ":" + inputData.get(key))
                .collect(Collectors.joining(", "));
    }
}
