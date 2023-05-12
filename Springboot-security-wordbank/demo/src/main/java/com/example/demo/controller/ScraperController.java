package com.example.demo.controller;




import com.example.demo.entities.UserEntity;
import com.example.demo.service.impl.UserServiceImpl;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;
import java.util.*;

@Controller
public class VocabScrapingController {
    @Autowired
    UserServiceImpl userService;


    @GetMapping(value="/scraping")
    public String scraping(@RequestParam("url") String url, Model model) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        Optional<UserEntity> user = userService.findByEmail(username);
        model.addAttribute("user", user.get());

        Document doc = Jsoup.connect(url).get();
        String text = doc.text();

// Initialize the CoreNLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

// Process the text
        Annotation document = new Annotation(text);
        pipeline.annotate(document);

// Get the tokens and their Part-Of-Speech tags
        List<CoreLabel> tokens = document.get(CoreAnnotations.TokensAnnotation.class);
        List<String> posTags = tokens.stream().map(token -> token.get(CoreAnnotations.PartOfSpeechAnnotation.class)).collect(Collectors.toList());

// Identify plural nouns and remove their endings
        Set<String> filteredWords = new HashSet<>();
        for (int i = 0; i < tokens.size(); i++) {
            CoreLabel token = tokens.get(i);
            String word = token.word();

            if (posTags.get(i).startsWith("NNS")) {  // Identify plural nouns
                Inflection inflection = Inflection.get(word.toLowerCase(), "NNS");  // Get the inflection of the noun
                if (inflection.isPlural()) {  // Check if the noun is plural
                    // Remove the endings 'es' or 's'
                    if (word.endsWith("es")) {
                        word = word.substring(0, word.length() - 2);
                    } else if (word.endsWith("s")) {
                        word = word.substring(0, word.length() - 1);
                    }
                }
            }
            // Filter out numbers and special characters and remove endings 'ed' or 's' from plural nouns
            if (word.matches("[a-zA-Z]+(-[a-zA-Z]+)?") && word.length() > 2 && !word.matches("(?i)^(ss|te|be|ce).+")) {
                filteredWords.add(word.toLowerCase());
            } else {
                // Add the original word to the filtered set if it was not added above
                filteredWords.add(word);
            }
        }


    }


}
