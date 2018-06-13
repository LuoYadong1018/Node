package lyd.activity;

import java.util.List;

import lyd.dao.InaccountDAO;
import lyd.model.Tb_inaccount;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
public class Inaccountinfo extends Activity{
	public static final String FLAG = "id";
	ListView lvinfo;
	String strType = "";
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inaccountinfo);
		lvinfo=(ListView) findViewById(R.id.lvinaccountinfo);
		Showinfo(R.id.btnininfo);
	lvinfo.setOnItemClickListener(new OnItemClickListener(){
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			String strInfo=String.valueOf(((TextView)view).getText());
			String strid=strInfo.substring(0,strInfo.indexOf('|'));
			Intent intent=new Intent(Inaccountinfo.this,InfoManage.class);
			intent.putExtra(FLAG, new String[]{strid,strType});
			startActivity(intent);
		}
	});
}
	private void Showinfo(int intType) {
		// TODO Auto-generated method stub
		String[] strInfos=null;
		ArrayAdapter<String>arrayAdapter=null;
		strType="btnininfo";
		InaccountDAO inaccountinfo=new InaccountDAO(Inaccountinfo.this);
		List<Tb_inaccount> listinfos=inaccountinfo.getScrollData(0,(int)inaccountinfo.getCount());
		strInfos=new String[listinfos.size()];
		int m=0;
		for(Tb_inaccount tb_inaccount:listinfos){
			strInfos[m]=tb_inaccount.getid()+"|"+tb_inaccount.getType()+
					""+String.valueOf(tb_inaccount.getMoney())+"Ԫ   "+tb_inaccount.getTime();
							m++;	
		}
		arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,strInfos);
		lvinfo.setAdapter(arrayAdapter);
		}
	}