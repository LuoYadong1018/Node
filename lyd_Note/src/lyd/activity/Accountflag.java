package lyd.activity;

import lyd.dao.FlagDAO;
import lyd.model.Tb_flag;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Accountflag extends Activity{
	EditText txtFlag;
	Button btnflagSaveButton;
	Button btnflagCancelButton;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accountflag);
		txtFlag=(EditText) findViewById(R.id.txtFlag);
		btnflagSaveButton=(Button) findViewById(R.id.btnflagSave);
		btnflagCancelButton=(Button) findViewById(R.id.btnflagCancel);
		btnflagSaveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String strFlag=txtFlag.getText().toString();
				if(!strFlag.isEmpty()){
					FlagDAO flagDAO=new FlagDAO(Accountflag.this);
					Tb_flag tb_flag=new Tb_flag(flagDAO.getMaxId()+1,strFlag);
					flagDAO.add(tb_flag);
					Toast.makeText(Accountflag.this, "新增便签成功", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(Accountflag.this, "请输入便签", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		btnflagCancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txtFlag.setText("");
				finish();
			}
		});
		}
	}
