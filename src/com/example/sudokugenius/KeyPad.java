package com.example.sudokugenius;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;

public class KeyPad extends Dialog {
         private final View keys[] = new View[9];
         private View keypad;
         
         private final int used[];
         
         private final Puzzle mypuzzle;
         
         public KeyPad(Context context,int used[],Puzzle puzzle){ 
        	 super(context);
        	 this.used = used;
        	 this.mypuzzle = puzzle;
         }
         
         
        private void findViews(){
        	keys[0] =  findViewById(R.id.key1);
        	keys[1] =  findViewById(R.id.key2);
        	keys[2] =  findViewById(R.id.key3);
        	keys[3] =  findViewById(R.id.key4);
        	keys[4] =  findViewById(R.id.key5);
        	keys[5] =  findViewById(R.id.key6);
        	keys[6] =  findViewById(R.id.key7);
        	keys[7] =  findViewById(R.id.key8);
        	keys[8] =  findViewById(R.id.key9);
         }
        
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.keypad);
			findViews();
		//	for (int element : used){ 
		//		if (element != 0) keys[element-1].setVisibility(View.INVISIBLE);
			//}
			setListeners();
		}
		
		private void setListeners(){ 
		  for (int i = 0;i < keys.length;i++){ 
			  final int t = i + 1;
			  keys[i].setOnClickListener(new View.OnClickListener(){
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					returnResult(t);
				}});
		  }
		}
		
				
	   private boolean isvalid(int t){ 
		   for (int x : used){
			   if (t == x) return false;
		   }
		   return true;
	   }
	
		
		public void returnResult(int t){ 
			//if (isvalid(t))
			mypuzzle.setnum(t);
			dismiss();
		
		   
		}
		
		
        
		
         
         
}
