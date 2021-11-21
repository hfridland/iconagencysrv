package com.iconagency.quotes.web;

import com.iconagency.quotes.entity.User;
import com.iconagency.quotes.repository.UserRepository;
import com.iconagency.quotes.security.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/reportToken")
@CrossOrigin
public class ReportTokenController {
    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/QuoteWithoutSDPrice/{quoteId}")
    public ResponseEntity<String> getReportToken(Principal principal, @PathVariable("quoteId") String quoteId) {
        Map<String, String> params = new HashMap<>();
        params.put("quoteId", quoteId);
        User user = getUserByPrincipal(principal);
        String token = jwtTokenProvider.generateToken(user, params);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));

    }

}
