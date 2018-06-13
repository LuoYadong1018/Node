package lyd.activity;
import java.util.List;


import lyd.dao.FlagDAO;
import lyd.model.Tb_flag;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
public class Showinfo extends Activity {
    protected static final String FLAG = "id";
    ListView lvinfo;
    Button btnoutinfo, btnininfo,btnflaginfo,btnmybill;
    String strType;
	String[] strInfos;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showinfo);
        lvinfo=(ListView) findViewById(R.id.lvinfo);
        btnoutinfo = (Button) findViewById(R.id.btnoutinfo);// 获取布局文件中的支出信息按钮
        btnininfo = (Button) findViewById(R.id.btnininfo);// 获取布局文件中的收入信息按钮
        btnflaginfo=(Button) findViewById(R.id.btnflaginfo);
        btnmybill=(Button) findViewById(R.id.btnmybill);
        btnflaginfo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowInfo(R.id.btnflaginfo);
			}
    	});
		btnoutinfo.setOnClickListener(new OnClickListener() {
			// 为支出信息按钮设置监听事件
			@Override
			public void onClick(View arg0) {
			// TODO Auto-generated method stub
			ShowInfo(R.id.btnoutinfo);// 显示支出信息
			}
	});
			btnininfo.setOnClickListener(new OnClickListener() {
			// 为收入信息按钮设置监听事件
			@Override
			public void onClick(View arg0) {
			// TODO Auto-generated method stub
			ShowInfo(R.id.btnininfo);// 显示收入信息
			}
	});
			btnmybill.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ShowInfo(R.id.btnmybill);
			}
	});
        lvinfo.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String strInfos=String .valueOf(((TextView)view).getText());
				String strid=strInfos.substring(0,strInfos.indexOf('|'));
				Intent intent=null;
				
				if(strType=="btnoutinfo"|strType=="btnininfo"){
						intent=new Intent(Showinfo.this,FlagManage.class);
						intent.putExtra(FLAG, new String[]{strid,strType});
				}else if(strType=="btnflaginfo"){
					intent=new Intent(Showinfo.this,FlagManage.class);
					intent.putExtra(FLAG, strid);
				}
				startActivity(intent);
			}
		});
}
			private void ShowInfo(int intType) {
				// TODO Auto-generated method stub
				String[] strinfos=null;
				ArrayAdapter<String>arrayAdapter=null;
				Intent intent=null;
				switch(intType){
				case R.id.btnoutinfo:
					 strType = "outinfo";
					intent=new Intent(Showinfo.this,TotalChart.class);
					intent.putExtra("passType", strType);
					startActivity(intent);break;
				case R.id.btnininfo:
					 strType = "ininfo";
					intent=new Intent(Showinfo.this,TotalChart.class);
					intent.putExtra("passType", strType);
					startActivity(intent);break;
				case R.id.btnmybill:
					 strType = "ininfo";
					intent=new Intent(Showinfo.this,MyBill.class);
					intent.putExtra("passType", strType);
					startActivity(intent);break;
				case R.id.btnflaginfo:
					 strType = "btnflaginfo";
					FlagDAO flaginfo=new FlagDAO(Showinfo.this);
					List<Tb_flag> listFlags=flaginfo.getScrollData(0, (int)flaginfo.getCount());
					strInfos=new String[listFlags.size()];
					int n=0;
					for(Tb_flag tb_flag: listFlags){
						strInfos[n]=tb_flag.getid()+"|"+tb_flag.getFlag();
						if(strInfos[n].length()>15){
							strInfos[n]=strInfos[n].substring(0, 15)+"......";
							n++;
						}
					}
					arrayAdapter=new ArrayAdapter<String>(this,
							android.R.layout.simple_list_item_1, strInfos);
					lvinfo.setAdapter(arrayAdapter);
					break;
				}
			
			}
}
