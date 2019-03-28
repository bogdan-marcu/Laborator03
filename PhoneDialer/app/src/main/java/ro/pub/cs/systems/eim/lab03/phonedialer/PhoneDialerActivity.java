package ro.pub.cs.systems.eim.lab03.phonedialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PhoneDialerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);

        for(int i = 1; i <= 13; ++i){
            if(i == 4)
                continue;
            String buttonID = "button" + i;
            int id = getResources().getIdentifier(buttonID, "id", getPackageName());
            Button button = findViewById(id);
            button.setOnClickListener(this);
        }
        ImageButton callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(this);
        ImageButton hangUpButton = findViewById(R.id.hangUpButton);
        hangUpButton.setOnClickListener(this);
        ImageButton backspaceButton = findViewById(R.id.backspaceButton);
        backspaceButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        TextView textView = findViewById(R.id.numberContainer);

        switch (v.getId()){
            case (R.id.backspaceButton):{
                CharSequence text = textView.getText();
                if(text.length() > 0){
                    textView.setText(text.subSequence(0, text.length() - 1));
                }
                break;
            }
            case (R.id.callButton):{
                if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            PhoneDialerActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + textView.getText().toString()));
                    startActivity(intent);
                }
                break;
            }
            case (R.id.hangUpButton):{
                finish();
                break;
            }
            default:{
                textView.append(((Button)v).getText());
            }
        }
    }
}
