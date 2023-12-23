package com.example.interviewskeleton.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FirstProblemService {
    private static final String FORBIDDEN_CHARACTER = "a";

    public void saveWords(List<String> words) {
        CompletableFuture<Void>[] futures = new CompletableFuture[words.size()];

        for(int i = 0; i < words.size(); i++){
            String word = words.get(i);
            futures[i] = CompletableFuture.runAsync(() -> saveWordToExternalApi(word));
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures);
        checkForForbiddenCharacter(words);
    }

    private void checkForForbiddenCharacter(List<String> words){
        for (String word : words) {
            if (word.contains(FORBIDDEN_CHARACTER)) {
                throw new UnsupportedOperationException("This word is not valid!");
            }
        }
    }

    private void saveWordToExternalApi(String word) {
        try {
            // Here we call external API. We use a sleep of 1s to simulate that it takes a lot of time, and we have no control over it.
            Thread.sleep(1000);
        } catch (Throwable anyException) {
            log.info("[BEST EFFORT] Saving word '{}' failed: {}", word, anyException.getMessage());
        }
    }
}
