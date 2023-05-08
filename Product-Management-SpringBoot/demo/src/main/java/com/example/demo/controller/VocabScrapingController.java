package com.example.demo.controller;



import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.jwt.JwtTokenFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class VocabScrapingController {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @RequestMapping(value = "/scrapingForm", method = RequestMethod.GET)
    public String homepage(HttpServletRequest request, HttpSession session){
        String token = (String) session.getAttribute("token");
        if(token != null){
            request.setAttribute("Authorization", "Bearer " + token);
        }
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
