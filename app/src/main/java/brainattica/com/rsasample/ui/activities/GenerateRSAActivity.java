package brainattica.com.rsasample.ui.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import brainattica.com.rsasample.R;
import brainattica.com.rsasample.crypto.RSA;


public class GenerateRSAActivity extends ActionBarActivity {

    private ProgressBar progress;

    private TextView generated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_rs);
        progress = (ProgressBar) findViewById(R.id.progress);
        generated = (TextView) findViewById(R.id.generated);

        new Thread(new Runnable() {
            @Override
            public void run() {
                RSA.generate();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisibility(View.GONE);
                        generated.setVisibility(View.VISIBLE);
                    }
                });
            }
        }).start();
    }

}
