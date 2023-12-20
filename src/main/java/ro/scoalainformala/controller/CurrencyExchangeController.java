package ro.scoalainformala.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import ro.scoalainformala.BnrReader;
import ro.scoalainformala.CurrencyExchange;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CurrencyExchangeController {

    @RequestMapping("/")
    public String showForm(Model model) {
        List<CurrencyExchange> currencies = BnrReader.readCurrencies();
        model.addAttribute("currencies", currencies);
        return "currency-exchange-form";
    }

    @GetMapping("/all-currencies")
    public String showAllCurrencies(Model model) {
        List<CurrencyExchange> currencies = BnrReader.readCurrencies();
        model.addAttribute("currencies", currencies);
        return "all-currencies";
    }

}
