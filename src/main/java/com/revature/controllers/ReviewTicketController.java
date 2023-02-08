package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.revature.service.reviewTickets;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ReviewTicketController implements HttpHandler {
    
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String verb = exchange.getRequestMethod();

        switch (verb) {
            // case "GET":
            //     getRequest(exchange);
            //     break;
            case "POST":
                postRequest(exchange);
                break;

            default:
                break;

        }

    }



    private void postRequest(HttpExchange exchange)throws IOException {

        InputStream is = exchange.getRequestBody();

        StringBuilder textbuilder = new StringBuilder();
        String pendingTickets;

        try (Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;

            while ((c = reader.read()) != -1) {
                textbuilder.append((char)c);
            }
        }

        
        reviewTickets reviewTickets = new reviewTickets();
        pendingTickets = reviewTickets.collectTickets(textbuilder.toString());
        
        exchange.sendResponseHeaders(200, pendingTickets.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(pendingTickets.getBytes());
        os.close();

        

    }



}