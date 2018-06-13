package lyd.activity;
import lyd.dao.PwdDAO;
import lyd.model.Tb_info;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class Set extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set); 
        Button submit=(Button) findViewById(R.id.btnsubmit);
        submit.setOnClickListener(new OnClickListener() {
   			@Override
   			public void onClick(View arg0) {
   				// TODO Auto-generated method stub
   				PwdDAO pwdDAO=new PwdDAO(Set.this);
   				String user=((TextView) findViewById(R.id.user)).getText().toString();
   				String password=((TextView) findViewById(R.id.pwd)).getText().toString();
   				Tb_info info=new Tb_info(user, password);
   				if(pwdDAO.getCount()==0){
   					pwdDAO.add(info);
   				}else{
   					pwdDAO.update(info);
   					 }
   				Toast.makeText(Set.this, "×¢²á³É¹¦£¡", Toast.LENGTH_SHORT).show();
   				}
   			});
        }
}