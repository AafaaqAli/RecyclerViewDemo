package studio.devcode.recyclerviewdemo;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;



public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private Context context;
    private List<Messages> messagesList;
    private String currentUid;

    public MessageAdapter(Context context, List<Messages> messagesList, String currentUid) {
        try {
            this.currentUid = currentUid;
            this.messagesList = messagesList;
            this.context = context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        MyViewHolder myViewHolder;
        try {
            view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.message_single_layout, parent, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    protected void showLog(String msg) {
        Log.d(this.getClass().getSimpleName(), msg);
    }

    protected boolean checkNull(String data) {
        return data != null && (!data.isEmpty());
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        try {
            Messages messages = messagesList.get(position);
            if (messages != null) {
                String messageType = messages.getType();
                if (checkNull(messages.getFrom()) && messages.getFrom().equals(currentUid)) {
                    if (checkNull(messageType) && messageType.equals("text")) {
                        holder.chatText_1.setText(messages.getMessage());
                        holder.messageSingleLayout.setVisibility(View.GONE);
                        holder.messageSingleLayout_1.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (checkNull(messageType) && messageType.equals("text")) {
                        holder.chatText.setText(messages.getMessage());
                        holder.messageSingleLayout.setVisibility(View.VISIBLE);
                        holder.messageSingleLayout_1.setVisibility(View.GONE);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView chatText, chatText_1, chatTime, chatTime_1;
        public LinearLayout messageSingleLayout, messageSingleLayout_1;
        private final ImageView fileDownload;
        private final ImageView fileDownload_1;

        public MyViewHolder(View itemView) {
            super(itemView);
            chatText = itemView.findViewById(R.id.chatText);
            chatText_1 = itemView.findViewById(R.id.chatText_1);
            chatTime = itemView.findViewById(R.id.chatTime);
            chatTime_1 = itemView.findViewById(R.id.chatTime_1);
            messageSingleLayout = itemView.findViewById(R.id.messageSingleLayout);
            messageSingleLayout_1 = itemView.findViewById(R.id.messageSingleLayout_1);
            fileDownload = itemView.findViewById(R.id.fileDownload);
            fileDownload_1 = itemView.findViewById(R.id.fileDownload_1);

        }
    }

    public void addItem(Messages message) {
        messagesList.add(message);
        notifyItemInserted(messagesList.size());
    }


}

