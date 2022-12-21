package com.example.web_scraping;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        JsoupLogic Scraper = new JsoupLogic();
        List<JsoupLogic.Product> products = Scraper.GetProducts();

        for (JsoupLogic.Product product : products) {
            System.out.println(
                    String.format("Product:\n%s\n%s\n\n", product.getName(), product.getPrice())
            );
        }
    }
}
