package lyd.dao;

import lyd.model.Tb_info;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class PwdDAO {
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	public PwdDAO(Context context){
		helper =new DBOpenHelper(context);
		db=helper.getWritableDatabase();
	}
	public void add(Tb_info tb_info){
		db.execSQL("insert into tb_info (name, pwd) values (?,?)", 
				new Object[]{tb_info.getName(), tb_info.getPwd()});
	}
	public void update(Tb_info tb_info){
		db.execSQL("update tb_info set name=?,"
	+"pwd=?",new Object[]{tb_info.getName(),tb_info.getPwd()});
	}
	public void delete(Integer...ids){
		if(ids.length>0){
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<ids.length;i++){
				sb.append('?').append(',');
			}
			sb.deleteCharAt(sb.length()-1);
			db.execSQL("delete from tb_info", (Object[])ids);
		}
	}
	public long getCount(){
		Cursor cursor = db.rawQuery("select count(name) from tb_info", null);
		if(cursor.moveToNext()){
			return cursor.getLong(0);
		}
		cursor.close();
		return 0;
	}
	public Tb_info find() {
		// TODO Auto-generated method stub
		Cursor cursor =db.rawQuery("select name,pwd from tb_info",null);
		if (cursor.moveToNext()){
			return new Tb_info(
					cursor.getString(cursor.getColumnIndex("name")),
				cursor.getString(cursor.getColumnIndex("pwd")));
		}
		cursor.close();
		return null;
		
	}
	
}
