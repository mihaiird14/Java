import java.io.*;
import java.net.*;
import java.util.*;
class ChatClient {
    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        socket = new Socket("localhost", 8888);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(() -> {
            String message;
            try {
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
            }
        }).start();

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while ((input = userInput.readLine()) != null) {
            out.println(input);
            if ("/quit".equalsIgnoreCase(input)) {
                break;
            }
        }

        socket.close();
    }
}

class ClientHandler implements Runnable {
    private static ThreadLocal<Socket> clientSocket = new ThreadLocal<>();
    private BufferedReader in;
    private PrintWriter out;
    private String name;
    private boolean isAdmin = false;

    public ClientHandler(Socket socket) {
        clientSocket.set(socket);
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.get().getInputStream()));
            out = new PrintWriter(clientSocket.get().getOutputStream(), true);
            out.println("Enter your name:");
            name = in.readLine();
            if ("admin".equalsIgnoreCase(name)) {
                isAdmin = true;
            }
            out.println("Bun venit " + name + "!");
            ChatServer.broadcast(name + " s-a alaturat chat-ului", this);
        } catch (IOException e) {
            close();
        }
    }

    public void run() {
        String message;
        try {
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("/quit")) {
                    out.println("La revedere!");
                    break;
                } else if (message.equalsIgnoreCase("/shutdown") && isAdmin) {
                    ChatServer.shutdown();
                    break;
                } else {
                    ChatServer.broadcast(name + ": " + message, this);
                }
            }
        } catch (IOException e) {
            //
        } finally {
            ChatServer.removeClient(this);
            ChatServer.broadcast(name + " a parasit chat-ul", this);
            close();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void close() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket.get() != null) clientSocket.get().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ChatServer {
    private static final int PORT = 8888;
    private static final List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());
    private static volatile boolean isRunning = true;

    public static void main(String[] args) {
        System.out.println("Server started on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(clientSocket);
                clients.add(handler);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            if (isRunning) {
                e.printStackTrace();
            }
        }
    }

    public static void broadcast(String message, ClientHandler sender) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                if (client != sender) {
                    client.sendMessage(message);
                }
            }
        }
    }

    public static void removeClient(ClientHandler clientHandler) {
        synchronized (clients) {
            clients.remove(clientHandler);
        }
    }

    public static void shutdown() {
        isRunning = false;
        synchronized (clients) {
            for (ClientHandler client : clients) {
                client.sendMessage("Server is shutting down...");
                client.close();
            }
            clients.clear();
        }
        System.out.println("Server shut down.");
        System.exit(0);
    }
}
