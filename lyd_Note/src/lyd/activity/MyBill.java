package lyd.activity;
import java.util.ArrayList;

import java.util.List;

import lyd.adapter.ViewPagerAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyBill extends FragmentActivity {
	/**
	 * viewpager相关成员变量
	 */
	private ViewPager viewpager;
	private ViewPagerAdapter viewadapter;
	private List<Fragment> list;
	private FragmentManager fm;
	private FgBill fbill;
	/**
	 * userinfo-bottom相关成员变量
	 */
	private View xian_account,xian_bill,xian_note;
	private RelativeLayout bill_layout,account_layout;
	private TextView text_account,text_bill;
	private MypagerLister pagerlister;
	private MyReLayoutLister relayoutlister;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_interface);
		initView();
		init();	
	}
	/**
	 * 实例化userinfo
	 */
	private void initView(){
		viewpager=(ViewPager)findViewById(R.id.viewpager);
		bill_layout=(RelativeLayout)findViewById(R.id.bill_layout);
		account_layout=(RelativeLayout)findViewById(R.id.account_layout);
		text_bill=(TextView)findViewById(R.id.text_bill);
		text_bill.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MyBill.this, "此次监听——逗你玩", Toast.LENGTH_SHORT).show();
			}
		});
		xian_account=findViewById(R.id.xian_account);
		xian_bill=findViewById(R.id.xian_bill);
		/**
		 * 各组件注册自定义监听器
		 */
		pagerlister=new MypagerLister();
		viewpager.setOnPageChangeListener(pagerlister);
		relayoutlister=new MyReLayoutLister();
		bill_layout.setOnClickListener(relayoutlister);
		account_layout.setOnClickListener(relayoutlister);
	}
	/**
	 * 实例化fragment
	 */
	private void init(){
		fm=getSupportFragmentManager();
		fbill=new FgBill();
		list=new ArrayList<Fragment>();
		list.add(fbill);
		viewadapter=new ViewPagerAdapter(fm, list);
		viewpager.setAdapter(viewadapter);
	}
	
	private class MypagerLister implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			if(arg0==2){
				int resourceAndVnum=viewpager.getCurrentItem();
				clearChoicked();
				changechoicked(resourceAndVnum);
			}
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	private  class MyReLayoutLister implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			clearChoicked();
			changechoicked(v.getId());
		}
	}
     private  void clearChoicked(){
    	 text_bill.setTextColor(text_bill.getResources().getColor(R.color.black));
    	 xian_bill.setBackgroundColor(xian_bill.getResources().getColor(R.color.red));
	}

	public void changechoicked(int resourceAndVnum) {
		
		switch (resourceAndVnum) {
		case R.id.bill_layout:case 1:
			text_bill.setTextColor(text_bill.getResources().getColor(R.color.blues));
			xian_bill.setBackgroundColor(xian_note.getResources().getColor(R.color.blues));
			viewpager.setCurrentItem(1);
			break;
		case R.id.account_layout:case 2:
			text_account.setTextColor(text_account.getResources().getColor(R.color.blues));
			xian_account.setBackgroundColor(xian_note.getResources().getColor(R.color.blues));
			viewpager.setCurrentItem(2);
			break;
		default:
			break;
		}
	}	
}
