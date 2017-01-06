package ca.ggolda.android_game_campaign;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterMessages extends ArrayAdapter<InstanceMessage> {
    public AdapterMessages(Context context, int resource, List<InstanceMessage> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.message_slide, parent, false);
        }

        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        ImageView colorSide = (ImageView) convertView.findViewById(R.id.color_side);



        InstanceMessage message = getItem(position);

        Log.e("Playaco", ""+GameActivity.playerColor);

        // Set color of icone next to name in chat
        if (message.getName() != null) {
            if (message.getName().equals(GameActivity.username)) {
                if (GameActivity.playerColor.equals("red")) {
                    colorSide.setImageResource(R.drawable.red_none);
                    colorSide.setBackgroundColor(Color.parseColor("#FF0000"));

                } else if (GameActivity.playerColor.equals("blue")) {
                    colorSide.setImageResource(R.drawable.blue_none);
                    colorSide.setBackgroundColor(Color.parseColor("#0000FF"));
                }
            } else {
                if (GameActivity.playerColor.equals("red")) {
                    colorSide.setImageResource(R.drawable.blue_none);
                    colorSide.setBackgroundColor(Color.parseColor("#0000FF"));

                } else if (GameActivity.playerColor.equals("blue")) {
                    colorSide.setImageResource(R.drawable.red_none);
                    colorSide.setBackgroundColor(Color.parseColor("#FF0000"));
                }
            }
        }

        authorTextView.setText(message.getName());
        messageTextView.setText(message.getText());

        return convertView;
    }
}
