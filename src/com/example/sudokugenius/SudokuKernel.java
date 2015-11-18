package com.example.sudokugenius;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import java.util.Random;

import java.util.Random;

public class SudokuKernel extends Activity{
	
	private int primpuzzle[] = new int[9*9];
	
	private int nowpuzzle[] =  new int[9*9];
	
	private final String prox[] = new String[9];
	

    private void creatPuzzle(){ 
    	
    	 prox[0]  =  "094860205765003008000490060"+
		             "000600000003052406926014000"+
		             "608709020000500080540080103";
    	 
    	 prox[1] =  "700614002010009607002580000"+
		             "430920000270000096000063021"+
    			     "000078100107300040800152003";
    	 
		 prox[2] =  "700163050805047020000000007"+
		            "050004910270508063039600070"+
				    "500000000040870605090452001";
		 
		 prox[3] =  "050000080300000006000130040"+
		            "080320009620718035700094060"+
		            "070052000900000008010000050";
		 
		 prox[4] =  "093052010001000005000014300"+
		            "034007068180605039950400720"+
				    "002570000300000600040360150";
		 
		 prox[5] =  "050000000073100250102500760"+
		            "000008020080021000034760800"+
				    "000680041000007000000014000";
		 
		 prox[6] =  "300000008025000700107000203"+
		            "000000510250000069701060004"+
				    "900534001010800075506009400";
		 
		 prox[7] =  "069010030007900000000840006"+
		            "000300100530070048002008000"+
				    "600023000000001500010080470";
				 
		 prox[8] =  "000001200500034000010070960"+
				 	"003007000240060097000400100"+
				    "058010040000790005006800000";
    			     
    	
    }
	
	 
	
	
	private void predo(){
		
	   creatPuzzle();
	   Random random = new Random();
	   int x = random.nextInt(9)%(8);
       for (int i = 0;i < 9;i++)
		 for (int j = 0;j < 9;j++)
			 primpuzzle[i*9+j] = prox[x].charAt(i*9+j) - '0';
       for (int i = 0;i < 9;i++)
  		 for (int j = 0;j < 9;j++)
  			 nowpuzzle[i*9+j] = prox[x].charAt(i*9+j) - '0';
       
	}
	
	private Puzzle newInterface;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sudokuprocess();
		newInterface = new Puzzle(this);
		setContentView(newInterface);
		newInterface.requestFocus();
		predo();
		sudokuprocess();
	}
	

	private final int used[][][] = new int [9][9][];
	
	
	int trans(int x,int y){ 
		return nowpuzzle[x * 9 + y];
	}
	
	private void add(int x,int y,int va){ 
		if (primpuzzle[x*9+y] == 0)
		nowpuzzle[x * 9 + y] = va; 
	}
	
	
	//calculate the used dynamic
	
	private int[] calculate(int x,int y){ 
		int c[] = new int[9];
		for (int i = 0;i < 9;i++) if (i != y){ 
			int t = trans(x,i);
			if (t > 0) c[t-1] = t;
		}
		
		for (int i = 0;i < 9;i++) if (i != x){ 
			int t = trans(i,y);
			if (t > 0) c[t-1] = t;
		}
		
		int stx = (x / 3) * 3;
		int sty = (y / 3) * 3;
		for (int i = stx;i < stx+3;i++)
			for (int j = sty;j < sty+3;j++)
				if (i != x || j != y){ 
					int t = trans(i,j);
					if (t > 0) c[t-1] = t;
				}
		int numx = 0;
		
		for (int num : c) if (num > 0) numx++;
		
		int ans[] = new int [numx];
		
		int tot = 0;
		
		for (int num : c) if (num > 0) ans[tot++] = num;
		
		return ans;
	}
	

	//calculate used
	
	private void sudokuprocess(){ 
		for (int x = 0;x < 9;x++)
			for (int y = 0;y < 9;y++) 
				used[x][y] = calculate(x,y);
				
	}
	
	
	//put x y va
	
	protected boolean Work(int x,int y,int va){ 
		int possible[]  = used[x][y];
		if (va == 0) return false;
		for (int num : possible){ 
			if (num == va) return false;
		}
		add(x,y,va);
		sudokuprocess();
		return true;
	}
	
	
    //
	protected String get(int x,int y){ 
	
		int v = primpuzzle[x*9+y];
		if (v != 0) return String.valueOf(v);
		else return "";
	}
	
	protected String getx(int x,int y){ 
		
		int v = nowpuzzle[x*9+y];
	    if (v == 0) return "";
	    else return String.valueOf(v);
	}
	
	
	protected void showkeypad(int x,int y){ 
	
		Dialog v = new KeyPad(this,used[x][y],newInterface);
		if (primpuzzle[x*9+y] == 0) v.show();
	}
	
	

}
