package brainattica.com.rsasample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import brainattica.com.rsasample.R;

/**
 * Created by javiermanzanomorilla on 03/04/15.
 */
public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.generate_rsa:
                startActivity(new Intent(this, GenerateRSAActivity.class));
                break;
            case R.id.encrypt_decrypt:
                startActivity(new Intent(this, EncryptOrDecryptActivity.class));
                break;
            case R.id.sign_verify:
                startActivity(new Intent(this, SingOrVerifyActivity.class));
                break;
            case R.id.clear:
                break;
        }

    }


}
