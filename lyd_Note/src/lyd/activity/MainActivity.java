package lyd.activity;
import java.util.ArrayList;


import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
public class MainActivity extends Activity {
	GridView gvInfo;//创建GridView对象
	String[] titles = new String[]{//定义字符串数组，存储系统功能
		"新增支出","新增收入","我的支出","我的收入","数据管理","系统设置","收支便签","帮助","退出"};
	int[] images = new int[]{//定义int数组，存储对应得图标
			R.drawable.addout,R.drawable.addinaccount,R.drawable.outacc,R.drawable.inacc,R.drawable.showinfo,
			R.drawable.set,R.drawable.accountflag,R.drawable.help,R.drawable.exit};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gvInfo=(GridView) findViewById(R.id.gvInfo);
        pictureAdapter adapter=new  pictureAdapter(titles,images,this);
        gvInfo.setAdapter(adapter);
        gvInfo.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?>arg0,View arg1,int arg2,long arg3){
        		Intent intent=null;
        		switch(arg2){
        		case 0:
        			intent=new Intent(MainActivity.this,AddOutaccount.class);
        			startActivity(intent);
        			break;
        		case 1:
        			intent=new Intent(MainActivity.this,AddInaccount.class);
        			startActivity(intent);
        			break;
        		case 2:
        			intent=new Intent(MainActivity.this,Outaccountinfo.class);
        			startActivity(intent);
        			break;
        		case 3:
        			intent=new Intent(MainActivity.this,Inaccountinfo.class);
        			startActivity(intent);
        			break;
        		case 4:
        			intent=new Intent(MainActivity.this,Showinfo.class);
        			startActivity(intent);
        			break;
        		case 5:
        			intent=new Intent(MainActivity.this,Set.class);
        			startActivity(intent);
        			break;
        		case 6:
        			intent=new Intent(MainActivity.this,Accountflag.class);
        			startActivity(intent);
        			break;
        		case 7:
        			intent=new Intent(MainActivity.this,Help.class);
        			startActivity(intent);
        			break;
        		case 8:
        			finish();
        		}
        	}
		});
    }}
   
    class pictureAdapter extends BaseAdapter{//创建基于BaseAdapter的子类
    	private LayoutInflater inflater;//创建LayoutInflater对象
    	private List<Picture>pictures;//创建List泛型集合
    	public pictureAdapter(String[] titles,int[]images,Context context){
    		super();
    		pictures=new ArrayList<Picture>();//初始化泛型集合对象
    		inflater=LayoutInflater.from(context);//初始化LayoutInflater对象
    		for(int i=0;i<images.length;i++){//遍历图像数组
    			Picture picture=new Picture(titles[i],images[i]);//使用标题和图像生成Picture对象
    			pictures.add(picture);//将Picture对象添加到泛型集合中
    		}
    	}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if(null!=pictures){
				return pictures.size();
			}else{
			return 0;
		}
}
		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return pictures.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			ViewHolder viewHolder;
			if(arg1==null){
				arg1=inflater.inflate(R.layout.gvitem, null);
				viewHolder=new ViewHolder();
				viewHolder.title=(TextView) arg1.findViewById(R.id.ItemTitle);
				viewHolder.image=(ImageView) arg1.findViewById(R.id.ItemImage);
				arg1.setTag(viewHolder);
			}else{
				viewHolder=(ViewHolder) arg1.getTag();
			}
			viewHolder.title.setText(pictures.get(arg0).getTitle());
			viewHolder.image.setImageResource(pictures.get(arg0).getImageld());
			return arg1;
		}
    	
    }

    class ViewHolder{
    	public TextView title;
    	public ImageView image;
    }
    class Picture{
    	private String title;
    	private int imageld;
    	public Picture(){
    		super();
    	}
    	public Picture(String title,int imageld){
    		super();
    		this.title=title;
    		this.imageld=imageld;
    	}
    	public String getTitle(){
    		return title;
    	}
    	public void setTitle(String title){
    		this.title=title;
    	}
    	public int getImageld(){
    		return imageld;
    	}
    	public void setimagele(){
    		this.imageld=imageld;
    	}
    }