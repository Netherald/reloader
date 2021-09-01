package net.projecttl.api.reloader

import org.bukkit.plugin.Plugin

import java.lang.reflect.Array

class RemoteReload {

    private final Plugin plugin
    private final int port
    private final String secretMessage
    RemoteReload(Plugin plugin, int port, String secretMessage) {
        this.plugin = plugin
        this.port = port
        this.secretMessage = secretMessage
    }

    private ReloaderAPI api
    private ServerSocket serverSocket
    private Thread taskThread

    void getFromSocket() {
        try {
            taskThread = new Thread(() -> {
                while (true) {
                    serverSocket = new ServerSocket(port)
                    println("WAITING CONNECTION...")
                    Socket socket = serverSocket.accept()

                    DataInputStream inputStream = new DataInputStream(socket.getInputStream())
                    String str = inputStream.readUTF()

                    if (str == secretMessage) {
                        api.reloadPluginAll()
                    }
                }
            })

            taskThread.start()
        } catch (Exception exception) {
            exception.printStackTrace()
        }
    }
}
