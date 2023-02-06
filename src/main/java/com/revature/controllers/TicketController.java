package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.revature.service.CreateExpenseTicket;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
public class TicketController implements HttpHandler {
    
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

    // private void getRequest(HttpExchange exchange) throws IOException{


    //     //Get all Tickets

    //     TicketService serv = new TicketService();
    //     String jsonCurrentList = serv.getAllTickets();

    //     exchange.sendResponseHeaders(200, jsonCurrentList.getBytes().length);

    //     OutputStream os = exchange.getRequestBody();
    //     os.write(jsonCurrentList.getBytes());
    //     os.close();

    // }

    private void postRequest(HttpExchange exchange)throws IOException {

        InputStream is = exchange.getRequestBody();

        StringBuilder textbuilder = new StringBuilder();

        try (Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;

            while ((c = reader.read()) != -1) {
                textbuilder.append((char)c);
            }
        }

        exchange.sendResponseHeaders(200, textbuilder.toString().getBytes().length);

        CreateExpenseTicket ticket = new CreateExpenseTicket();
        ticket.createTicket(textbuilder.toString());

        OutputStream os = exchange.getResponseBody();
        os.write(textbuilder.toString().getBytes());
        os.close();

    }



}