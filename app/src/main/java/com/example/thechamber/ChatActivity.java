package com.example.thechamber;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thechamber.utilities.ChatAdapter;
import com.example.thechamber.utilities.ChatMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {
    private EditText messageEditText;
    private Button sendButton;
    private RecyclerView chatRecyclerView;
    private List<ChatMessage> messages;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        chatRecyclerView = findViewById(R.id.chatRecyclerView);

        messages = new ArrayList<>();
        adapter = new ChatAdapter(messages);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true); // Yeni mesajlar eklenirken alttan başla
        chatRecyclerView.setLayoutManager(layoutManager);
        chatRecyclerView.setAdapter(adapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = messageEditText.getText().toString().trim();
                if (!message.isEmpty()) {
                    String timestamp = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                    ChatMessage chatMessage = new ChatMessage("Kullanıcı Adı", message, timestamp);
                    messages.add(chatMessage);
                    adapter.notifyItemInserted(messages.size() - 1); // Yeni mesajı ekle
                    chatRecyclerView.smoothScrollToPosition(messages.size() - 1); // En son mesaja kaydır

                    messageEditText.getText().clear();
                }
            }
        });
    }
}
