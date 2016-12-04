package iip.hr.recent.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import iip.hr.recent.R;
import iip.hr.recent.model.User;
import iip.hr.recent.utilities.IntentFactory;

public class StartScreenActivity extends AppCompatActivity {

    @BindView(R.id.dashboard)
    protected Button dashboard;

    @BindView(R.id.nfc)
    protected Button nfc;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        ButterKnife.bind(this);

        Typeface libreFranklin = Typeface.createFromAsset(getAssets(), "fonts/LibreFranklin-Bold.ttf");
        dashboard.setTypeface(libreFranklin);
        nfc.setTypeface(libreFranklin);

        user = (User) getIntent().getSerializableExtra(IntentFactory.USER);
    }

    @OnClick(R.id.dashboard)
    protected void dashboard() {
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        intent.putExtra(IntentFactory.USER, user);
        startActivity(intent);
    }

    @OnClick(R.id.nfc)
    protected void nfc() {
        Intent intent = new Intent(getApplicationContext(), NfcActivity.class);
        startActivity(intent);
    }


}
