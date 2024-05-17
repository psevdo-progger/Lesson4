package com.mirea.usatyukds.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mirea.usatyukds.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Handler	mainThreadHandler	=	new	Handler(Looper.getMainLooper())	{
            @Override
            public	void	handleMessage(Message msg)	{
                Log.d(MainActivity.class.getSimpleName(),	"Task	execute.	This	is	result:	"	+	msg.getData().getString("result"));
            }
        };
        MyLooper	myLooper	=	new	MyLooper(mainThreadHandler);
        myLooper.start();
        binding.editTextMirea.setText("Мой номер по списку №22");
        binding.buttonMirea.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public	void	onClick(View	v)	{
                Message	msg	=	Message.obtain();
                Bundle	bundle	=	new	Bundle();
                bundle.putString("KEY",	binding.editTextAge.getText().toString() + " years old. Profession is a " + binding.editTextWork.getText().toString());
                msg.setData(bundle);
                myLooper.mHandler.sendMessage(msg);
            }
        });
    }
}