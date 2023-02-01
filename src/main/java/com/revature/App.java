package com.revature;

import java.net.InetSocketAddress;

import com.revature.models.*;
import com.revature.service.*;
import com.revature.utils.ConnectionUtil;
import com.revature.repositories.*;
import com.revature.controllers.*;

import com.sun.net.httpserver.HttpServer;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws Exception{
        System.out.println("Starting backend server...");

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/", new EmployeeController());

        server.setExecutor(null);
        server.start();

    }
}