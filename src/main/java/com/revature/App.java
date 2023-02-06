package com.revature;

import java.net.InetSocketAddress;

import com.revature.controllers.*;

import com.sun.net.httpserver.HttpServer;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws Exception{
        System.out.println("Starting backend server...");

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/promotion", new ManagerController());
        server.createContext("/register", new EmployeeController());
        server.createContext("/createTicket", new TicketController());
        server.createContext("/reviewTicket", new ReviewTicketController());
        server.createContext("/processTicket", new ProcessTicketController());

        server.setExecutor(null);
        server.start();
    }
}