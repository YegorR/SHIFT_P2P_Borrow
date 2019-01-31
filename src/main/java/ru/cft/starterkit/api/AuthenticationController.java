package ru.cft.starterkit.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.cft.starterkit.entity.Borrower;
import ru.cft.starterkit.entity.Investor;
import ru.cft.starterkit.exception.BorrowerNotFoundException;
import ru.cft.starterkit.exception.InvestorNotFoundException;
import ru.cft.starterkit.repository.BorrowerRepository;
import ru.cft.starterkit.repository.InvestorRepository;
import ru.cft.starterkit.repository.TimerRepository;
import ru.cft.starterkit.repository.implement.SampleEntityRepositoryImpl;
import ru.cft.starterkit.service.AuthenticationService;
import ru.cft.starterkit.service.LogicService;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public UUID login(
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "investor") boolean investor){
        try {
            log.info("Аутентификация: login={}, password={}, investor={}", login, password, investor);
            return authenticationService.login(login, password, investor);
        }
        catch (BorrowerNotFoundException|InvestorNotFoundException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }

    }


}
