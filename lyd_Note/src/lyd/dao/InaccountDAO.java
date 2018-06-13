package lyd.dao;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import java.util.List;

import lyd.model.Tb_inaccount;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class InaccountDAO {
private DBOpenHelper helper;
private SQLiteDatabase db;
public InaccountDAO(Context context){
	helper = new DBOpenHelper(context);
	
}
public void add(Tb_inaccount tb_inaccount){
	db=helper.getWritableDatabase();
	db.execSQL("insert into tb_inaccount(_id, money, time, type, handler, mark) values"
		+"(?,?,?,?,?,?)",new Object[]{tb_inaccount.getid(),tb_inaccount.getMoney(),
			tb_inaccount.getTime(),tb_inaccount.getType(),tb_inaccount.getHandler(),
			tb_inaccount.getMark()});
}
public void update(Tb_inaccount tb_inaccount){
	db=helper.getWritableDatabase();
	db.execSQL("update tb_inaccount set money=?,time=?,type=?,handler=?,mark=? where _id = ?",
			new Object[]{tb_inaccount.getMoney(),
				tb_inaccount.getTime(),tb_inaccount.getType(),tb_inaccount.getHandler(),
				tb_inaccount.getMark(),tb_inaccount.getid()});
}
public Tb_inaccount find(int id){
	db=helper.getWritableDatabase();
	Cursor cursor =db.rawQuery("select _id, money, time, type, handler, mark from tb_inaccount where _id=?",
		new String[]{String.valueOf(id)});
	if (cursor.moveToNext()) {
		return new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),
				cursor.getDouble(cursor.getColumnIndex("money")),
				cursor.getString(cursor.getColumnIndex("time")),
				cursor.getString(cursor.getColumnIndex("type")),
				cursor.getString(cursor.getColumnIndex("handler")),
				cursor.getString(cursor.getColumnIndex("mark")));
	}
	cursor.close();
	return null;
	}
public void delete(Integer...ids){
	
	if(ids.length>0){
		StringBuffer sb=new StringBuffer();
		for (int i=0;i<ids.length;i++){
			sb.append('?').append(',');
		}
	sb.deleteCharAt(sb.length()-1);
	db=helper.getWritableDatabase();
	db.execSQL("delete from tb_inaccount where _id in("+sb+")", (Object[])
			ids);
	}
}
public List<Tb_inaccount> getScrollData(int start ,int count){
	db=helper.getWritableDatabase();
	List<Tb_inaccount>tb_inaccounts=new ArrayList<Tb_inaccount>();
	Cursor cursor=db.rawQuery("select * from tb_inaccount limit ?, ?",
			new String []{String.valueOf(start),String.valueOf(count)});
	while (cursor.moveToNext()){
		tb_inaccounts.add(new
				Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),
						cursor.getDouble(cursor.getColumnIndex("money")),
							cursor.getString(cursor.getColumnIndex("time")),
									cursor.getString(cursor.getColumnIndex("type")),
							cursor.getString(cursor.getColumnIndex("handler")),
									cursor.getString(cursor.getColumnIndex("mark"))));
		}
	cursor.close();
	return tb_inaccounts;
}
public long getCount(){
	db=helper.getWritableDatabase();
	Cursor cursor = db.rawQuery("select count(_id)from tb_inaccount", null);
	if(cursor.moveToNext()){
		return cursor.getLong(0);
	}
	cursor.close();
	return 0;
}
public int getMaxId(){
	db=helper.getWritableDatabase();
	Cursor cursor=db.rawQuery("select max(_id) from tb_inaccount", null);
	while(cursor.moveToLast()){
		return cursor.getInt(0);
	}
	cursor.close();
	return 0; 
}
public Map<String, Float> getTotal(){
	db=helper.getWritableDatabase();
	Cursor cursor=db.rawQuery("select type,sum(money) from tb_inaccount group by type",null);
	int count=0;
	count=cursor.getCount();
	Map<String, Float> map=new HashMap<String,Float>();
	cursor.moveToFirst();
	for(int i=0;i<count;i++){
		map.put(cursor.getString(0), cursor.getFloat(1));
		cursor.moveToNext();
	}
	return map;
}
}
