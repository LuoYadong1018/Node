package lyd.activity;
import java.util.Map;
import lyd.dao.InaccountDAO;
import lyd.dao.OutaccountDAO;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class TotalChart extends Activity {
	private float[] money=new float[]{600,1000,600,300,1500};
	private int[]color=new int[]{Color.GREEN,Color.YELLOW,Color.RED,Color.BLACK,Color.BLUE};
	private final int WIDTH=30;
	private final int OFFSET=15;
	private int x=70;
	private int y=329;
	private int height=220;
	String[] type=null;
	private String passType="";
   
    float maxMoney(float[] money){
		float max=money[0];
		for (int i=0;i<money.length-1;i++){
			if(max<money[i+1]){
				max=money[i+1];
			}
		}
		return max;	
	}
	float[] getMoney(String flagType){
		Map mapMoney=null;
		System.out.println(flagType);
		if("ininfo".equals(flagType)){
			InaccountDAO inaccountinfo=new InaccountDAO(TotalChart.this);
			mapMoney=inaccountinfo.getTotal();
			}else if("outinfo".equals(flagType)){
				OutaccountDAO outaccountinfo=new OutaccountDAO(TotalChart.this);
				mapMoney=outaccountinfo.getTotal();
			}
		int size=type.length;
		float[] money1=new float[size];
		for(int i=0;i<size;i++){
			money1[i]=(mapMoney.get(type[i])!=null?((Float)mapMoney.get(type[i])):0);
		}
		return money1;
	}
	public class MyView extends View{
		public MyView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
		protected void onDraw(Canvas canvas){
			super.onDraw(canvas);
			canvas.drawColor(Color.WHITE);
			Paint paint=new Paint();
			paint.setAntiAlias(true);
			paint.setStrokeWidth(1);
			paint.setColor(Color.BLACK);
			canvas.drawLine(50, 330, 300, 330, paint);
			canvas.drawLine(50, 100, 50, 330, paint);
			paint.setStyle(Style.FILL);
			int left=0;
			money=getMoney(passType);
			float max=maxMoney(money);
			for(int i=0;i<money.length;i++){
				
				paint.setColor(color[i]);
				left=x+i*(OFFSET+WIDTH);
				canvas.drawRect(left, y-height/max*money[i],left+WIDTH,y,paint);
			}
			paint.setColor(Color.BLACK);
			int tempY=0;
			for(int i=0;i<11;i++){
				tempY=y-height+height/10*i+1;
				canvas.drawLine(47, tempY, 50, tempY, paint);
				paint.setTextSize(12);
				canvas.drawText(String.valueOf((int)(max/10*(10-i))), 15, tempY+5, paint);
				}
			paint.setColor(Color.BLACK);
			paint.setTextSize(21);
			if("outinfo".equals(passType)){
				canvas.drawText("支出统计图", 40, 55, paint);
				
			}else if("ininfo".equals(passType)){
				canvas.drawText("收入统计图", 40, 55, paint);
			}
			paint.setTextSize(16);
			String str_type="";
			for(int i=0;i<type.length;i++){
				str_type+=type[i]+"   ";
				
			}
			canvas.drawText(str_type, 68, 350, paint);
		}
		
	}
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.chart);
	        Intent intent=getIntent();
	        Bundle bundle=intent.getExtras();
	        passType=bundle.getString("passType");
	        Resources res=getResources();
	        if("outinfo".equals(passType)){
	        	type=res.getStringArray(R.array.type);
	        }else if("ininfo".equals(passType)){
	        	type=res.getStringArray(R.array.intype);
	        }
	        FrameLayout ll=(FrameLayout) findViewById(R.id.canvas);
	        ll.addView(new MyView(this));
	    }
}
