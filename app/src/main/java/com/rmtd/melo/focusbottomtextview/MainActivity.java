package com.rmtd.melo.focusbottomtextview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mTextView;
    ScrollView mScrollView;
    Button mButton,floatButton;

    int count;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textview);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        mButton = (Button) findViewById(R.id.btn_add);
        floatButton= (Button) findViewById(R.id.btn_float);
        mTextView.setMovementMethod(ScrollingMovementMethod.getInstance());


        //xml
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                mTextView.append("\r\n");
                mTextView.append(count + "");
                scrollToBottom(mScrollView, mTextView);
            }
        });


    }


    /**
     * 根据scrolview 和子view去测量滑动的位置
     *
     * @param scrollView
     * @param view
     */
    private void scrollToBottom(final ScrollView scrollView, final View view) {

        handler.post(new Runnable() {

            @Override
            public void run() {
                if (scrollView == null || view == null) {
                    return;
                }
                // offset偏移量。是指当textview中内容超出 scrollview的高度，那么超出部分就是偏移量
                int offset = view.getMeasuredHeight()
                        - scrollView.getMeasuredHeight();
                if (offset < 0) {
                    offset = 0;
                }
                //scrollview开始滚动
                scrollView.scrollTo(0, offset);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler = null;
        }

    }
}
