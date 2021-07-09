package com.example.onlinestore.utility.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinestore.R;

public class CustomToast extends Toast {

    TextView toastTitle;
    TextView toastDescription;
    ImageView toastIcon;
    View customToastLayout;


    public CustomToast(Context context, View layout) {
        super(context);
        this.customToastLayout = layout;
        this.toastIcon = customToastLayout.findViewById(R.id.custom_toast_image);
        this.toastTitle = customToastLayout.findViewById(R.id.custom_toast_title);
        this.toastDescription = customToastLayout.findViewById(R.id.custom_toast_description);

    }


    public void show(CustomToastMode mode, String description, int duration) {
        this.toastDescription.setText(description);

        switch (mode){
            case INFO:
                toastIcon.setImageResource(R.drawable.ic_info);
                toastTitle.setText(R.string.info);
                break;
            case APPROVE:
                toastIcon.setImageResource(R.drawable.ic_check);
                toastTitle.setText(R.string.approve);
                break;
            case ERROR:
                toastIcon.setImageResource(R.drawable.ic_error);
                toastTitle.setText(R.string.error);
                break;
            case WARNING:
                toastIcon.setImageResource(R.drawable.ic_warning);
                toastTitle.setText(R.string.warning);
                break;
        }
        setGravity(Gravity.CENTER_VERTICAL,0,0);
        setDuration(duration);
        setView(customToastLayout);
        super.show();
    }
}
