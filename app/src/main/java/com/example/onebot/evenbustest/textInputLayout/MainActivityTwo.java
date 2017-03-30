package com.example.onebot.evenbustest.textInputLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.onebot.evenbustest.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by onebot on 2016/10/27.
 */

public class MainActivityTwo extends Activity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        usernameWrapper.setHint("Username");
        passwordWrapper.setHint("Password");
        passwordWrapper.setCounterEnabled(true);//是否启用计数器
        passwordWrapper.setCounterMaxLength(15);//启用计数器时，最大字数限制（仅仅用做显示）
        btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();

                //获取数据 Textinputlayout 可直接gerEditText
                String username=usernameWrapper.getEditText().getText().toString();
                String password=passwordWrapper.getEditText().getText().toString();


                //特点 可以显示错误  需要的方法是setErrorEnabled和setError
                if(!validateEmail(username)){
                    usernameWrapper.setError("Not a valid email address!");

                }else if(!validatePassword(password)){
                    passwordWrapper.setError("Not a valid password!");
                }else{
                    usernameWrapper.setErrorEnabled(false);
                    passwordWrapper.setErrorEnabled(false);
                    doLogin();
                }
            }
        });
    }

    public void doLogin() {
        Toast.makeText(getApplicationContext(), "OK! I'm performing login.", Toast.LENGTH_SHORT).show();
        // TODO: login procedure; not within the scope of this tutorial.
    }


    /**
     * 隐藏软键盘
     */
    private void hideKeyboard(){
        View view=getCurrentFocus();
        if(view!=null){
            ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    /**
     * 判断用户输入框
     * @param email
     * @return
     */
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 判断密码
     * @param password
     * @return
     */
    public boolean validatePassword(String password) {
        return password.length() > 5;
    }

}
