package studio.devcode.recyclerviewdemo;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import studio.devcode.recyclerviewdemo.MessageAdapter;
import studio.devcode.recyclerviewdemo.Messages;
import studio.devcode.recyclerviewdemo.R;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewSend;
    private EditText editTextMessage;
    private List<Messages> messagesList;
    private MessageAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_view_layout);
        messagesList = new ArrayList<>();

        imageViewSend = findViewById(R.id.send);
        editTextMessage = findViewById(R.id.type_message);

        setData("12345", "abc");
        setData("12345", "hello");
        setData("123145", "you");
        setData("12345", "there");
        setData("121345", "aqqqbc");
        setData("123145", "abqqc");
        setData("123145", "aqqbc");
        setData("12345", "awwbc");
        setData("123145", "eeabc");
        setData("12345", "abeeec");
        setData("112345", "addbc");
        setData("12345", "abdcc");
        setData("112345", "abccc");
        setData("121345", "abccc");
        setData("12345", "abccc");
        setAdapter();

        recyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
        onSendButtonClick();
        onSoftInputKeyboardOpen();
    }

    private void setData(String from, String message) {
        messagesList.add(new Messages(message, "text", from));
    }


    private void onSendButtonClick(){
        imageViewSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextMessage.getText().toString().isEmpty() || !editTextMessage.getText().toString().equals(" ")){
                    mAdapter.addItem(new Messages(editTextMessage.getText().toString().toUpperCase(), "text", "Ali"));
                    editTextMessage.setText("");
                    recyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
                }
            }
        });
    }


    private void setAdapter() {
        try {
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(mAdapter.getItemCount());


            } else {
                mAdapter = new MessageAdapter(this, messagesList, "12345");
                mLayoutManager.setSmoothScrollbarEnabled(true);
                mLayoutManager.setStackFromEnd(true);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onSoftInputKeyboardOpen(){
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
             @Override
             public void onLayoutChange(View v,
                                        int left, int top, int right, int bottom,
                                        int oldLeft, int oldTop, int oldRight, int oldBottom) {
                 if (bottom < oldBottom) {
                     recyclerView.postDelayed(new Runnable() {
                         @Override
                         public void run() {
                             recyclerView.smoothScrollToPosition(
                                     recyclerView.getAdapter().getItemCount() - 1);
                         }
                     }, 100);
                 }
             }
         });
    }


}