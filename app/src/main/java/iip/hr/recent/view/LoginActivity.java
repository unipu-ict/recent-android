package iip.hr.recent.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import iip.hr.recent.R;
import iip.hr.recent.model.RetrofitFactory;
import iip.hr.recent.model.User;
import iip.hr.recent.utilities.IntentFactory;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.tv_app_name)
    protected TextView logo;

    @BindView(R.id.et_username)
    protected EditText username;

    @BindView(R.id.et_password)
    protected EditText password;

    @BindView(R.id.b_login)
    protected Button login;

    @BindView(R.id.tv_error)
    protected TextView error;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Typeface jaapokki = Typeface.createFromAsset(getAssets(), "fonts/jaapokki.ttf");
        Typeface caviarDreams = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams.ttf");
        logo.setTypeface(jaapokki);
        username.setTypeface(caviarDreams);
        password.setTypeface(caviarDreams);
        login.setTypeface(caviarDreams);
        error.setTypeface(caviarDreams);

    }

    @OnClick(R.id.b_login)
    protected void login() {

        User user = new User(username.getText().toString(), password.getText().toString());

        subscription = RetrofitFactory.createService().login(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        Intent intent = new Intent(getApplicationContext(), StartScreenActivity.class);
                        intent.putExtra(IntentFactory.USER, user);
                        startActivity(intent);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        error.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) subscription.unsubscribe();
    }
}
