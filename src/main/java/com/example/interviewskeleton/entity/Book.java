package com.example.interviewskeleton.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @Column(name = "publication_year")
    private Integer year;
    private String seoName;
    private String productCode;

    public void generateSeoName() {
        this.seoName = author + " - " + title + " (" + year + ")";
    }

    public void generateProductCode() {
        if (seoName == null) {
            productCode = null;
            return;
        }

        StringBuilder codeBuilder = new StringBuilder();
        for (char ch : seoName.toCharArray()) {
            if (ch == '(' || ch == ')' || ch == '-' || ch == ' ') {
                codeBuilder.append('.');
            } else if ("AEIOUaeiou".contains(String.valueOf(ch))) {
                codeBuilder.append('1');
            } else if (Character.isAlphabetic(ch) || Character.isDigit(ch)) {
                codeBuilder.append('0');
            }
        }
        int startIndex = Math.max(0, codeBuilder.length() - 5);
        int endIndex = codeBuilder.length();
        codeBuilder.replace(startIndex, endIndex, String.valueOf(year));
        codeBuilder.append('.');
        this.productCode = codeBuilder.toString();
    }
}
