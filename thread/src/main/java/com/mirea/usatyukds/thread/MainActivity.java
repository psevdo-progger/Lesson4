package com.mirea.usatyukds.thread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mirea.usatyukds.thread.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int Lessons;
    private int days;
    private	int	counter	=	0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        TextView infoTextView = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Имя текущего потока: " + mainThread.getName());
// Меняем имя и выводим в текстовом поле
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: 09, НОМЕР ПО СПИСКУ: 22, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: тяжело");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(),	"Stack:	"	+	Arrays.toString(mainThread.getStackTrace()));
        binding.buttonMirea.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public	void	onClick(View	v)	{
                new	Thread(new	Runnable()	{
                    public	void run()	{
                        int	numberThread	=	counter++;
                        Log.d("ThreadProject",	String.format("Запущен	поток	№ %d	студентом	группы	№	%s	номер	по списку	№ %d	",	numberThread,	"БСБО-09-21",	22));
//                        long	endTime	=	System.currentTimeMillis()	+	20	*	1000;
//                        while	(System.currentTimeMillis()	<	endTime)	{
//                            synchronized	(this)	{
//                                try	{
//                                    wait(endTime	- System.currentTimeMillis());
//                                    Log.d(MainActivity.class.getSimpleName(),	"Endtime:	"	+	endTime);
//                                }	catch	(Exception	e)	{
//                                    throw	new	RuntimeException(e);
//                                }
//                            }
                        days = Integer.parseInt(binding.editDays.getText().toString());
                        Lessons = Integer.parseInt(binding.editLessons.getText().toString());
                        runOnUiThread(() -> {
                            binding.result.setText(String.format("Среднее количество пар: %s", ((float) Lessons / (float) days)));
                        });
                        String f = String.format("Среднее количество пар: %s", ((float) Lessons / (float) days));
                        Log.d("thread", f);
                        Log.d("ThreadProject",	"Выполнен поток №	"	+	numberThread);
                    }
                }).start();
            }
        });
        Log.d(MainActivity.class.getSimpleName(),	"Group:	"	+	mainThread.getThreadGroup());
    }
}
