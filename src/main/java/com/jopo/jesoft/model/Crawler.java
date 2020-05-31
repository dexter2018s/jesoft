/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.model;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author joelh
 */
public class Crawler {

    public void listAllLinks(String url) throws IOException {

        System.out.println("Parsing page " + url + "...");
        Document doc = Jsoup.connect(url).get(); //se conecta a la url y obtiene el documento HTML 
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");
        System.out.println("");
        System.out.printf("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img")) {
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            } else {
                System.out.printf(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
            }
        }

        System.out.printf("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            System.out.printf(" * %s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"));
        }

        System.out.printf("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }

    }

    public String listMediaLinksJpg(String url) throws IOException {
        System.out.println("Parsing page " + url + "...");
        Document doc = Jsoup.connect(url).get(); //se conecta a la url y obtiene el documento HTML
        Elements media = doc.select(".productPicture");
        System.out.println("Elementos encontrados: " + media.size());
        imagenURL = "";
//        if (media.isEmpty()) {
//            System.out.println("No existe ninguna imagen disponible");
//        }
        for (Element src : media) {
            imagenURL = src.attr("abs:src");
            System.out.println(imagenURL);            
        }
//        if (media.size() > 1) {
//            System.out.println("TIENE QUE SELECCIONAR UNA IMAGEN DE LA SIGUIENTE LISTA:");
//            for (Element src : media) {
//                System.out.println(src.attr("abs:src"));
//                imagenURL = "";
//            }
//        }
        return imagenURL;
    }

    public String listText(String url) throws IOException {
        System.out.println("Parsing page " + url + "...");
        Document doc = Jsoup.connect(url).get(); //se conecta a la url y obtiene el documento HTML
        Elements descriptions = doc.select(".productDescription");
        System.out.println("Elementos encontrados: " + descriptions.size());
        if (descriptions.isEmpty()) {
            System.out.println("No existe ninguna imagen disponible");
        }

        if (descriptions.size() == 1) {
            description = descriptions.get(0).getElementsByClass("productDescription").text();
        }

        if (descriptions.size() > 1) {
            System.out.println("TIENE QUE SELECCIONAR UNA IMAGEN DE LA SIGUIENTE LISTA:");
            for (Element src : descriptions) {
                description = src.attr("abs:src");
                System.out.println(description);
                if (description.contains("https://download.schneider-electric.com/files")) {
                    imagenSchneider = description;
                }
                description = "";
            }
        }
        return description;
    }

    public String listTextSchneider(String url) throws IOException {
        System.out.println("Parsing page " + url + "...");
        Document doc = Jsoup.connect(url).get(); //se conecta a la url y obtiene el documento HTML
        Elements descriptions = doc.select("[src]");
        System.out.println("Elementos encontrados: " + descriptions.size());

        if (descriptions.size() >= 1) {
            System.out.println("TIENE QUE SELECCIONAR UNA IMAGEN DE LA SIGUIENTE LISTA:");
            for (Element src : descriptions) {
                description = src.attr("abs:src");
                System.out.println(description);
                if (description.contains("https://download.schneider-electric.com/files")) {
                    imagenSchneider = description;
                }
                description = "";
            }
        }
        return imagenSchneider;
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width) {
            return s.substring(0, width - 1) + ".";
        } else {
            return s;
        }
    }
    private String imagenURL;
    private String description;
    private String imagenSchneider;
}
