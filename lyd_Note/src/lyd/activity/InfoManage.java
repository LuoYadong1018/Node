package lyd.activity;

import lyd.dao.InaccountDAO;
import lyd.dao.OutaccountDAO;
import lyd.model.Tb_inaccount;
import lyd.model.Tb_outaccount;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InfoManage extends Activity {
	protected static final int DATE_DIALOG_ID=0;
	TextView tvtitle,textView;
	EditText txtMoney,txtTime,txtHA,txtMark;
	Spinner spType;
	Button btnEdit,btnDel;
	String[] strInfos;
	String strid,strType;
	private int mYear,mMonth,mDay;
	OutaccountDAO outaccountDAO=new OutaccountDAO(InfoManage.this);
	InaccountDAO inaccountDAO=new InaccountDAO(InfoManage.this);
	 protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.infomanage);
		tvtitle=(TextView) findViewById(R.id.inouttitle);
		textView=(TextView) findViewById(R.id.tvInOut);
		txtMoney=(EditText) findViewById(R.id.txtInOutMoney);
		txtTime=(EditText) findViewById(R.id.txtInOutTime);
		spType=(Spinner) findViewById(R.id.spInOutType);
		txtHA=(EditText) findViewById(R.id.txtInOut);
		txtMark=(EditText) findViewById(R.id.txtInOutMark);
		btnEdit=(Button) findViewById(R.id.btnInOutEdit);
		btnDel=(Button) findViewById(R.id.btnInOutDelete);
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		strInfos=bundle.getStringArray(Showinfo.FLAG);
		strid=strInfos[0];
		strType=strInfos[1];
		if(strType.equals("btnoutinfo")){
			tvtitle.setText("支出管理");
			textView.setText("地点");
			Tb_outaccount tb_outaccount=outaccountDAO.find(Integer.parseInt(strid));
			txtMoney.setText(String.valueOf(tb_outaccount.getMoney()));
			txtTime.setText(tb_outaccount.getTime());
			ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_dropdown_item_1line);
			spType.setAdapter(adapter);
			spType.setPrompt(tb_outaccount.getType());
			txtHA.setText(tb_outaccount.getAddress());
			txtMark.setText(tb_outaccount.getMark());
		}else if(strType.equals("btnininfo")){
			tvtitle.setText("收入管理");
			textView.setText("付款方");
			Tb_inaccount tb_inaccount=inaccountDAO.find(Integer.parseInt(strid));
			txtMoney.setText(String.valueOf(tb_inaccount.getMoney()));
			txtTime.setText(tb_inaccount.getTime());
			ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.intype, android.R.layout.simple_dropdown_item_1line);
			spType.setAdapter(adapter);
			spType.setPrompt(tb_inaccount.getType());
			txtHA.setText(tb_inaccount.getHandler());
			txtMark.setText(tb_inaccount.getMark());
		}
		btnEdit.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(strType.equals("btnoutinfo")){
					Tb_outaccount tb_outaccount=new Tb_outaccount();
					tb_outaccount.setid(Integer.parseInt(strid));
					tb_outaccount.setMoney(Double.parseDouble(txtMoney.getText().toString()));
					tb_outaccount.setTime(txtTime.getText().toString());
					tb_outaccount.setType(spType.getSelectedItem().toString());
					tb_outaccount.setAddress(txtHA.getText().toString());
					tb_outaccount.setMark(txtMark.getText().toString());
					outaccountDAO.update(tb_outaccount);					
				}else if(strType.equals("btnininfo")){
					Tb_inaccount tb_inaccount=new Tb_inaccount();
					tb_inaccount.setid(Integer.parseInt(strid));
					tb_inaccount.setMoney(Double.parseDouble(txtMoney.getText().toString()));
					tb_inaccount.setTime(txtTime.getText().toString());
					tb_inaccount.setType(spType.getSelectedItem().toString());
					tb_inaccount.setHandler(txtHA.getText().toString());
					tb_inaccount.setMark(txtMark.getText().toString());
					inaccountDAO.update(tb_inaccount);	
				}
				Toast.makeText(InfoManage.this, "数据修改成功！", Toast.LENGTH_SHORT).show();
			}
		});
		btnDel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(strType.equals("btnoutinfo")){
					outaccountDAO.delete(Integer.parseInt(strid));
				}else if(strType.equals("btnininfo")){
					inaccountDAO.delete(Integer.parseInt(strid));
				}
				Toast.makeText(InfoManage.this, "数据删除成功！", Toast.LENGTH_SHORT).show();
			}
		});
	}	
}

