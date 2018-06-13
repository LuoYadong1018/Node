package lyd.activity;

import lyd.dao.FlagDAO;
import lyd.model.Tb_flag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FlagManage extends Activity{
	EditText txtFlag;
	Button btnEdit,btnDel;
	String strid;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.flagmanage);
	        txtFlag=(EditText) findViewById(R.id.txtFlagManage);
	        btnEdit=(Button) findViewById(R.id.btnFlagManageEdit);
	        btnDel=(Button) findViewById(R.id.btnFlagManageDelete);
	        Intent intent=getIntent();
	        Bundle bundle=intent.getExtras();
	        strid=bundle.getString(Showinfo.FLAG);
	        final FlagDAO flagDAO=new FlagDAO(FlagManage.this);
	        txtFlag.setText(flagDAO.find(Integer.parseInt(strid)).getFlag());
	        btnEdit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Tb_flag tb_flag=new Tb_flag();
					tb_flag.setid(Integer.parseInt(strid));
					tb_flag.setFlag(txtFlag.getText().toString());
					flagDAO.update(tb_flag);
					Toast.makeText(FlagManage.this, "便签修改成功！", Toast.LENGTH_SHORT).show();
				}
			}); 
	        btnDel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					flagDAO.delete(Integer.parseInt(strid));
					Toast.makeText(FlagManage.this, "便签删除成功", Toast.LENGTH_SHORT).show();
					finish();
				}
			});
	 	}
	 
}