package lyd.activity;
import java.util.Calendar;

import lyd.dao.OutaccountDAO;
import lyd.model.Tb_outaccount;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.style.UpdateLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddOutaccount extends Activity {
	protected static final int DATE_DIALOG_ID=0;
	EditText txtMoney,txtTime,txtAddress,txtMark;
	Spinner spType;
	Button btnSaveButton;
	Button btnCancelButton;
	private int mYear;
	private int mMonth;
	private int mDay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addoutaccount);
		txtMoney=(EditText) findViewById(R.id.txtMoney);
		txtTime=(EditText) findViewById(R.id.txtTime);
		txtAddress=(EditText) findViewById(R.id.txtAddress);
		txtMark=(EditText) findViewById(R.id.txtMark);
		spType=(Spinner) findViewById(R.id.spType);
		btnSaveButton=(Button) findViewById(R.id.btnSave);
		btnCancelButton=(Button) findViewById(R.id.btnCancel);
		txtTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
			}
		});
		btnSaveButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0){
			String strMoney = txtMoney.getText().toString();
		if(!strMoney.isEmpty()){
			OutaccountDAO outaccountDAO = new OutaccountDAO(
						AddOutaccount.this);
		Tb_outaccount tb_outaccount = new Tb_outaccount(
			outaccountDAO.getMaxId() + 1, 
			Double.parseDouble(strMoney), 
			txtTime.getText().toString(), 
			spType.getSelectedItem().toString(),
			txtAddress.getText().toString(),
			txtMark.getText().toString());
			outaccountDAO.add(tb_outaccount);
			Toast.makeText(AddOutaccount.this, "新增支出数据添加成功！",Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(AddOutaccount.this, "请输入支出金额！",Toast.LENGTH_SHORT).show();
		}
		}
	});
		btnCancelButton.setOnClickListener(new OnClickListener() {
			// 为取消按钮设置监听事件
			@Override
			public void onClick(View arg0) {
			 
			// TODO Auto-generated method stub
			txtMoney.setText("");
			txtMoney.setHint("0.00");
			txtTime.setText("");
			txtTime.setHint("2018-04-26");
			txtAddress.setText("");
			txtMark.setText("");
			spType.setSelection(0);
			finish();
			}
		});
		final Calendar c=Calendar.getInstance();
		mYear=c.get(Calendar.YEAR);
		mMonth=c.get(Calendar.MONTH);
		mDay=c.get(Calendar.DAY_OF_MONTH);
		updateDisplay();
	}
	
	protected Dialog onCreateDialog(int id){
		switch(id){
		case DATE_DIALOG_ID:
		return new DatePickerDialog(this, mDateSetListener,mYear, mMonth, mDay);
		}
		return null;
	}
	private DatePickerDialog.OnDateSetListener
	mDateSetListener=new DatePickerDialog.OnDateSetListener(){
		public void onDateSet(DatePicker view,int year,int monthOfYear,int dayOfMonth){
			mYear=year;
			mMonth=monthOfYear;
			mDay=dayOfMonth;
			updateDisplay();
		}
	};
	private void updateDisplay() {
		// TODO Auto-generated method stub
		txtTime.setText(new StringBuilder().append(mYear)
				.append("-").append(mMonth+1).append("-").append(mDay));
	}
	
}