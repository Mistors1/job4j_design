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
                    String output = "";
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains("GET /?msg=Hello")) {
                            output = "Hello, dear friend.";
                        } else if (str.contains("GET /?msg=Exit")) {
                            output = "Server is closed";
                            server.close();
                        } else if (str.contains("GET /?msg=")) {
                            String[] parts = str.split("=");
                            String[] msg = parts[1].split(" ");
                            output = msg[0];
                        }
                    }
                    out.write(output.getBytes());
                    out.flush();
                }
            }
        }
    }
}
