package com.RESTAPI.demo.service;


import com.RESTAPI.demo.domain.Calculation;
import com.RESTAPI.demo.dto.CalculationDto;
import com.RESTAPI.demo.dto.CalculationHistoryResponseDto;
import com.RESTAPI.demo.dto.CalculationResponseDto;
import com.RESTAPI.demo.dto.CalculationSelectDto;
import com.RESTAPI.demo.exception.CharacterLimitException;
import com.RESTAPI.demo.repository.CalculationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Transactional
@Service
public class CalculationService {

    private final CalculationRepository calculationRepository;

    @Autowired
    public CalculationService(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    public List<CalculationHistoryResponseDto> showHistory(String username) {

        List<Calculation> result = calculationRepository.findbyName(username);

        List<CalculationHistoryResponseDto> history = new ArrayList<CalculationHistoryResponseDto>();

        //결과를 DTO로 싸주는 과정
        for (Calculation iteration : result) {
            CalculationHistoryResponseDto calculationHistoryResponseDto = CalculationHistoryResponseDto.builder()
                   .formula(iteration.getFormula())
                   .isCorrect(iteration.isCorrect())
                   .build();
            history.add(calculationHistoryResponseDto);
        }

        return history;
    }

    public CalculationResponseDto calculate (CalculationDto calculationDto) {

        List<String> formula = new ArrayList<>();

        // 패턴 매칭으로 문자열로 들어온 수식 기호와 숫자로 분리
        Pattern p = Pattern.compile("\\d+|\\D");
        Matcher m = p.matcher(calculationDto.getFormula());

        while (m.find()) {
            String split = m.group();
            formula.add(split);
        }

        String myAnswer = formula.get(formula.size() - 1);
        formula = formula.subList(0, formula.size() - 2);

        double answer = resultPrint(organize(formula));

        if (answer == Double.parseDouble(myAnswer)) {
            Calculation calculation = Calculation.builder()
                    .username(calculationDto.getUsername())
                    .formula(calculationDto.getFormula())
                    .isCorrect(true)
                    .build();

            calculationRepository.save(calculation);

            return CalculationResponseDto.builder()
                    .isCorrect(true)
                    .build();
        }
        else {
            Calculation calculation = Calculation.builder()
                    .username(calculationDto.getUsername())
                    .formula(calculationDto.getFormula())
                    .isCorrect(false)
                    .build();

            calculationRepository.save(calculation);

            return CalculationResponseDto.builder()
                    .isCorrect(false)
                    .correctAnswer(answer)
                    .build();
        }
    }

    private String[] organize(List<String> formula) {

        ArrayList<String> sb = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < formula.size(); i++) {
            String now = formula.get(i);
            switch (now) {
                case "+":
                case "-":
                case "*":
                case "/":
                    while (!stack.isEmpty() && priority(stack.peek()) >= priority(now)) {
                        sb.add(stack.pop());
                    }
                    stack.push(now);
                    break;
                case "(":
                    if (formula.get(i+1).equals("-")) {
                        sb.add("-"+formula.get(i+2));
                        i += 3;
                    } else {
                        stack.push(now);
                    }
                    break;
                case ")":
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        sb.add(stack.pop());
                    }
                    stack.pop();
                    break;
                default:
                    sb.add(now);
            }
        }

        while (!stack.isEmpty()) {
            sb.add(stack.pop());
        }

        String[] result = new String[sb.size()];

        for(int i = 0; i < sb.size(); i++) {
            result[i]=sb.get(i);
        }

        return result;
    }

    private int priority(String operator){

        return switch (operator) {
            case "(", ")" -> 0;
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> -1;
        };
    }

    public double resultPrint(String[] str) {

        Stack<Double> stack = new Stack<>();

        for (String x : str) {

            if (!x.equals("+") && !x.equals("-") && !x.equals("*") && !x.equals("/")) {
                stack.push(Double.parseDouble(x));
            } else {
                double a = stack.pop();
                double b = stack.pop();

                switch (x) {
                    case "+":
                        stack.push(b+a);
                        break;
                    case "-":
                        stack.push(b-a);
                        break;
                    case "*":
                        stack.push(b*a);
                        break;
                    case "/":
                        stack.push(b/a);
                        break;
                }
            }
        }

        return stack.pop();
    }
}
