package lyd.activity;

import lyd.dao.PwdDAO;
import lyd.model.Tb_info;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Login extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final EditText txtpwd=(EditText) findViewById(R.id.txtLogin);
        final EditText txtuser=(EditText) findViewById(R.id.txtLogin1);
        Button btlogin=(Button) findViewById(R.id.btlogin);
        btlogin.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Login.this,MainActivity.class);
				PwdDAO pwdDAO=new PwdDAO(Login.this);
				if(pwdDAO.getCount()==0||pwdDAO.find().getPwd().isEmpty()){
					if(txtpwd.getText().toString().isEmpty()){
						startActivity(intent);
					}else{
						Toast.makeText(Login.this, "请不要输入任何密码登录", Toast.LENGTH_SHORT).show();
					}
				}else{
					if(pwdDAO.find().getPwd().equals(txtpwd.getText().toString())){
						startActivity(intent);
					}else{
						Toast.makeText(Login.this, "请输入正确的密码", Toast.LENGTH_SHORT).show();
					}
				}
				txtpwd.setText("");
			}	
			 });
        Button btclose=(Button) findViewById(R.id.btexit);
        btclose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			finish();
			}
        });
        Button btregister=(Button) findViewById(R.id.btregister);
   	   	btregister.setOnClickListener(new OnClickListener() {
   			@Override
   			public void onClick(View arg0) {
   				// TODO Auto-generated method stub
   				PwdDAO pwdDAO=new PwdDAO(Login.this);
   				String name=txtuser.getText().toString();
   				String password=txtpwd.getText().toString();
   				Tb_info info=new Tb_info(name, password);
   				if(pwdDAO.getCount()==0){
   					pwdDAO.add(info);
   				}else{
   					pwdDAO.update(info);
   					 }
   				Toast.makeText(Login.this, "注册成功！", Toast.LENGTH_SHORT).show();
   			}
   		});
       
  }}
