package com.progressSoft.FXDeal.Controller;

import com.progressSoft.FXDeal.Model.Deal;
import com.progressSoft.FXDeal.Repo.DealRepo;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.util.*;

@RestController

public class ApiController {
    @Autowired
    private DealRepo dealRepo;

    Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping(value = "/")
    public String getWelcome() {
        return "Welcome to Deals";
    }

    @PostMapping(value = "/makeDeal")
    public String makeDeal(@Valid @RequestBody Deal deal) {


        //to prevent importing same request twice
        deal.setUUID(UUID.randomUUID().toString());
        //validation format and type in Controller instead of Model
        if (isLetterOnly(deal.getOrdering_Currency()) &&
                isLetterOnly(deal.getTo_Currency_ISO_Code()) &&
                isLengthCorrect(deal.getOrdering_Currency()) &&
                isLengthCorrect(deal.getTo_Currency_ISO_Code())
        ) {
            logger.info("Successful Create Request " + deal.getUUID());

            dealRepo.save(deal);

            return "Deal Created";
        }
        logger.error("It is wrong " + deal.getUUID());

        return "Deal Refused due to type or formatting errors";
    }

    @GetMapping(value = "/deal")
    public Optional<Deal> getDeal(@RequestParam Long Deal_Id) {
        return this.dealRepo.findById(Deal_Id);

    }

    @GetMapping(value = "/deals")
    public List<Deal> getDeals() {
        return this.dealRepo.findAll();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();

            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    private boolean isLetterOnly(String code) {
        return code.matches("[a-zA-Z]+");
    }

    private boolean isLengthCorrect(String code) {
        return code.length() == 3;
    }

}
