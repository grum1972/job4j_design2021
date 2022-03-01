package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (str.contains("msg")) {
                        str = str.substring(str.indexOf('=') + 1);
                        str = str.substring(0, str.indexOf(' '));
                        if (str.contains("Hello")) {
                            out.write("Hello!".getBytes());
                        }
                        if (str.contains("What")) {
                            out.write("What".getBytes());
                        }
                        if (str.contains("Bye")) {
                            out.write("Bye".getBytes());
                            server.close();
                        }
                        out.flush();
                    }
                }
            }
        }
    }
}
