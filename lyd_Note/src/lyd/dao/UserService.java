package lyd.dao;
import java.util.ArrayList;

import java.util.List;

import lyd.model.GridViewEntity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class UserService {
	public static final String TOTE_TITLE="title";
	public static final String TOTE_CONTENT="content";
	public static final String TOTE_DTIMES="dtimes";
	public static final String BILL_BILL="bill";
	public static final String BILL_IMG="img";
	public static final String BILL_BILLTYPE="billtype";
	public static final String BILL_SATTE="billstate";
	public static final String BILL_TIME="logtime";
	public static final String BILL_ID="id";
	public static final String ACCOUNT_NAME="accountname";
	public static final String ACCOUNT_MEMORY="money";
	private GridViewEntity gve;
	DBOpenHelper dbOpenHelper;
 public UserService(Context context){	 
	 dbOpenHelper=new DBOpenHelper(context);
		 
 }
 /**
  * bill表中插入账单记录
  * @param gve 账单记录对象（账户，消费类型，收支，消费金额，消费对应的图片索引，）
  * @return
  */
 public Long insertBill( GridViewEntity gve){
	 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
	 ContentValues values=new ContentValues();
	 values.put(BILL_BILL, gve.getBill());
	 values.put(BILL_IMG, gve.getImg());
	 values.put(BILL_BILLTYPE,gve.getBillType());
	 values.put(BILL_SATTE,gve.getBillState());
	 values.put(BILL_TIME, gve.getTime());
	 long e=db.insert("bill", null, values);
	 db.close();
	 return e; 
 }
 /**
  * 更新bill表中数据
  * @param id 消费记录对应的id,
  * @param values 更新的数据
  * @return
  */
 public long updateBill(String id, ContentValues values){
	 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
	 long e= db.update("bill", values," id = ? ", new String[]{id});
	 db.close();
	 return e; 
 }
 
 public List<GridViewEntity> findAllBill(){
	 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
	 Cursor cursor=db.query("bill", null, null, null, null, null, "id desc");
	 List<GridViewEntity> list=new ArrayList<GridViewEntity>();
	 while (cursor.moveToNext()) {
		 String dbill=cursor.getString(cursor.getColumnIndex(BILL_BILL));
		 String dimg=cursor.getString(cursor.getColumnIndex(BILL_IMG));
		 String dbstate=cursor.getString(cursor.getColumnIndex(BILL_SATTE));
		 String dbtype=cursor.getString(cursor.getColumnIndex(BILL_BILLTYPE));
		 GridViewEntity entity=new GridViewEntity();
		 entity.setBillType(dbtype);
		 entity.setBillState(dbstate);
		 entity.setBill(dbill);
		 entity.setImg(Integer.parseInt(dimg));
		 list.add(entity);
	 }
	 return list;
 }
 public GridViewEntity findGve(String billtype,String time){
	 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
	 Cursor cursor=db.query("bill", null,"billtype=? and logtime=? ",new String[]{billtype,time}, null, null, null);
	 while(cursor.moveToNext()){
		 String dbill=cursor.getString(cursor.getColumnIndex(BILL_BILL));
		 String dimg=cursor.getString(cursor.getColumnIndex(BILL_IMG));
		 int img=Integer.parseInt(dimg);
		 String dtype=cursor.getString(cursor.getColumnIndex(BILL_BILLTYPE));
		 String dstate=cursor.getString(cursor.getColumnIndex(BILL_SATTE));
		 String dtime=cursor.getString(cursor.getColumnIndex(BILL_TIME));
		 String did=cursor.getString(cursor.getColumnIndex(BILL_ID));
		 int id=Integer.parseInt(did);
		 gve=new GridViewEntity();
		 gve.setBill(dbill);
	     gve.setBillState(dstate);
	     gve.setBillType(dtype);
	     gve.setId(id);
	     gve.setImg(img);
	     gve.setTime(dtime);
	 }
	 return gve; 
 }
}
