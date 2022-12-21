package com.example.web_scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsoupLogic {
    private static final String EBAY_URL = "https://www.ebay.com/globaldeals";
    private static final String CARD_CLASS = "dne-itemtile-detail";
    private static final String TITLE_CLASS = "dne-itemtile-title";
    private static final String PRICE_SELECTOR = ".dne-itemtile-price .first";

    class Product {
        private String name;
        private String price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String getPrice) {
            this.price = getPrice;
        }
    }

    public List<Product> GetProducts() {
        List<Product> products = new ArrayList<>();

        Document doc;
        try {
            doc = Jsoup.connect(EBAY_URL).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements productElements = doc.getElementsByClass(CARD_CLASS);
        for (Element productElement : productElements) {
            Product product = new Product();
            Elements titleElements = productElement.getElementsByClass(TITLE_CLASS);
            if (!titleElements.isEmpty()) {
                product.setName(titleElements.get(0).attr("title"));
            }
            Elements priceElements = productElement.select(PRICE_SELECTOR);
            if (!priceElements.isEmpty()) {
                product.setPrice(priceElements.get(0).text());
            }
            products.add(product);
        }
        return products;
    }
}
