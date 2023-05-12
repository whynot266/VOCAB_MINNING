package com.example.demo.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.example.demo.entities.UserEntity;
import com.example.demo.service.impl.UserServiceImpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScraperController {

    @Autowired
    UserServiceImpl userService;
    @GetMapping("/scraping")
    public String scrape(@RequestParam("url") String url, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        Optional<UserEntity> user = userService.findByEmail(username);

        model.addAttribute("bankCount", userService.bankCount(user.get()));
        model.addAttribute("user", user.get());
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

        String text = doc.text();
        String[] words = text.split("\\s+");
        Set<String> filteredWords = new HashSet<>();

        for (String word : words) {
            // Filter out numbers and special characters
            if (word.matches("[a-zA-Z]+(-[a-zA-Z]+)?") && word.length() > 2) {
                // Remove plural endings from nouns
                String singularNoun = removePluralEnding(word);
                filteredWords.add(singularNoun.toLowerCase());
            }
        }

        if (filteredWords.isEmpty()) {
            filteredWords.add("no word found");
        }

        model.addAttribute("vocabs", filteredWords);

        return "scraper";
    }

    private static String removePluralEnding(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        if (word.endsWith("sses")) {
            return word.substring(0, word.length() - 2);
        } else if (word.endsWith("ses")) {
            return word.substring(0, word.length() - 1);
        } else if (word.endsWith("xes")) {
            return word.substring(0, word.length() - 2);
        } else if (word.endsWith("zes")) {
            return word.substring(0, word.length() - 1);
        } else if (word.endsWith("ches")) {
            return word.substring(0, word.length() - 2);
        } else if (word.endsWith("shes")) {
            return word.substring(0, word.length() - 2);
        } else if (word.endsWith("men")) {
            return word.substring(0, word.length() - 2) + "an";
        } else if (word.endsWith("ies")) {
            return word.substring(0, word.length() - 3) + "y";
        } else if (word.contains("-")) {  // Check if word has hyphen
            return word;  // Keep hyphen
        } else if (word.endsWith("s") && !word.endsWith("ous") && !word.endsWith("ess") && !word.endsWith("oes") && !word.endsWith("this")) {
            return word.substring(0, word.length() - 1);
        } else {
            return word;
        }
    }






}
