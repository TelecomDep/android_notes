package com.example.android_notes.activities

import android.os.Bundle
import android.os.Message
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_notes.R
import org.zeromq.SocketType
import org.zeromq.ZContext
import org.zeromq.ZMQ


class SocketsActivity : AppCompatActivity() {
    private var log_tag : String = "MY_LOG_TAG"

    private lateinit var tvSockets: TextView
    private var textString : String = ""
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sockets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvSockets = findViewById(R.id.tvSockets)
        handler = Handler(Looper.getMainLooper())


    }
    fun startServer() {
        val context = ZMQ.context(1)
        val socket = ZContext().createSocket(SocketType.REP)
        socket.bind("tcp://*:2222") // Replace with your server's IP and port
        var counter: Int = 0
        while(true){
            counter++
            val requestBytes = socket.recv(0)
            val request = String(requestBytes, ZMQ.CHARSET)
            println("[SERVER] Received request: [$request]")
            handler.postDelayed({
                tvSockets.text = "Received MSG from Client = $counter"
            }, 0)
            // Process the request (e.g., simulate work)
            Thread.sleep(1000) // Simulate some work

            // Prepare and send the reply
            val response = "Hello from Android ZMQ Server!"
            socket.send(response.toByteArray(ZMQ.CHARSET), 0)
            println("[SERVER] Sent reply: [$response]")
        }
        socket.close();
        context.close();
    }

    fun startClient() {
        val context = ZMQ.context(1)
        val socket = ZContext().createSocket(SocketType.REQ)
        socket.connect("tcp://localhost:2222") // Replace with your server's IP and port
        val request = "Hello from Android client!"
        for(i in 0..10){
            socket.send(request.toByteArray(ZMQ.CHARSET), 0)
            Log.d(log_tag, "[CLIENT] SendT: $request")

            val reply = socket.recv(0)
            Log.d(log_tag, "[CLIENT] Received: " + String(reply, ZMQ.CHARSET))
        }
        socket.close()
        context.close()
    }
    override fun onResume() {
        super.onResume()
        val runnableServer = Runnable{startServer()}
        val threadServer = Thread(runnableServer)
        threadServer.start()

        Thread.sleep(1000)

        val runnableClient = Runnable{startClient()}
        val threadClient = Thread(runnableClient)
        threadClient.start()

    }
}