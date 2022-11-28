/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrmws;

import MongodbHelper.MongoHelper;
import com.hrmws.ServerEndpoint;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.websocket.DeploymentException;

/**
 *
 * @author mac
 */
public class Server {

    /**
     * @param args the command line arguments
     * @throws javax.websocket.DeploymentException
     */
    public static void main(String[] args) throws DeploymentException, UnknownHostException{
        org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost", 6652, "/ws", ServerEndpoint.class);
        MongoHelper s = new MongoHelper();
        server.start();
        System.out.println("Press any key to stop the server..");
        new Scanner(System.in).nextLine();
        server.stop();
        }
    }
    

