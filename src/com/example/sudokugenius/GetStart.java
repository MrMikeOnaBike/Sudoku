package com.example.sudokugenius;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GetStart extends Activity {

	private Button startGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start);
        startGame = (Button)findViewById(R.id.StartButton);
        startGame.setOnClickListener(new buttonclick());
    }
    
    
    class buttonclick implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			Intent myintent = new Intent();
			myintent.setClass(GetStart.this, SudokuKernel.class);
			GetStart.this.startActivity(myintent);
		} 
    }
    



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.get_start, menu);
       
        return true;
    }
    
    
    
}
