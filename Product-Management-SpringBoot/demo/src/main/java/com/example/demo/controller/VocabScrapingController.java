package com.example.demo.controller;



import com.example.demo.dto.request.SignInForm;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.jwt.JwtTokenFilter;
import com.example.demo.security.userPrincipal.UserPrinciple;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
public class VocabScrapingController {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;
    @RequestMapping(value = "/scrapingForm", method = RequestMethod.GET)
    public String homepage(HttpServletRequest request){
        jwtTokenFilter.getJwt(request);
        return "scraper";
    }

    @GetMapping(value="/scraping")
    public String scraping(@RequestParam("url") String url, Model model) throws IOException {

        Document doc = Jsoup.connect(url).get();
        String text = doc.text();
        String[] words = text.split("\\s+");
        Set<String> filteredWords = new HashSet<>();
        //
        for (String word : words) {
            // Filter out numbers and special characters
            if (word.matches("[a-zA-Z]+(-[a-zA-Z]+)?")&&word.length()>2) {
                filteredWords.add(word.toLowerCase());
            }
        }
        if(filteredWords.isEmpty()){
            filteredWords.add("no word found");
        }

        model.addAttribute("vocabs", filteredWords);

        return "scraper";
    }


}
