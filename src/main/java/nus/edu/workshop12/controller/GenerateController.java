package nus.edu.workshop12.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.boot.logging.LoggerGroup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nus.edu.workshop12.model.Generate;

@Controller
public class GenerateController {
    private Logger logger = LoggerFactory.getLogger(GenerateController.class);

    @GetMapping("/generate")
    public String showGenrateForm(Model model) {
        Generate generate = new Generate();
        //generate.setNumberVal(10000);
        model.addAttribute("generate", generate);
        return "generate";
    }

    @PostMapping("/generate")
    public String generateNumbers(@ModelAttribute Generate generate, Model model) {
        logger.info("From the form " + generate.getNumberVal());
        //generate random # number from 1 to 10
        List<Integer> randomNumberList = new ArrayList<>();
        for(int i = 0; i < generate.getNumberVal(); i++) {
            int num = randomNumber();
            while(randomNumberList.contains(num)) {
                num = randomNumber();
            }
            randomNumberList.add(num);
        }
        logger.info("Random number list" + randomNumberList);

        model.addAttribute("randNumsResultPicDir", appendList(randomNumberList));
        model.addAttribute("randNumsResult", randomNumberList);
        return "result";
    }

    public int randomNumber() {
        int minValue = 1;
        int maxValue = 30;
        return (int) ((Math.random() * (maxValue - minValue + 1)) + minValue);
    }

    public List<String> appendList(List<Integer> numArray) {
        List<String> newListofString = new ArrayList<>();
        for (Integer integer : numArray) {
            newListofString.add("images/number" + integer.toString() + ".jpg");           
        }
        return newListofString;
    }
}
