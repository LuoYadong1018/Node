package lyd.activity;

import java.util.ArrayList;

import java.util.List;

import lyd.adapter.ListViewAdapterOfBill;
import lyd.dao.UserService;
import lyd.model.GridViewEntity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class FgBill extends Fragment implements OnClickListener {
	private Button add_btn_of_bill;
	private View view;
	/**
	 * adapter相关的变量
	 */
	private ListView billlistview;
	private GridViewEntity ent;
	private ListViewAdapterOfBill adapter;
	private List<GridViewEntity> list;
	/**
	 * 获得数据库bill数据相关变量
	 */
	private UserService service;
	private Integer[] img1 = { R.drawable.star, R.drawable.table_money, R.drawable.snacks,
			R.drawable.traffic, R.drawable.recharge, R.drawable.shopping, R.drawable.amusement,
			R.drawable.house_rent, R.drawable.engagement_yuehui, R.drawable.shoppingcart, R.drawable.dailynecessities,
			R.drawable.cigarette };
	private Integer[] img2 = { R.drawable.salary, R.drawable.part_time_job, R.drawable.bonus,
			R.drawable.borrow, R.drawable.pockage_money, R.drawable.investment, R.drawable.the_gift_of_money };
	private List<GridViewEntity> dblist;
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		init();
		initView();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fgbill, container, false);

		return view;
	}

	private void init() {

		add_btn_of_bill = (Button) view.findViewById(R.id.add_btn_of_bill);
		add_btn_of_bill.setOnClickListener(this);
		billlistview = (ListView) view.findViewById(R.id.billlistview);
	}
	private void initView() {
		service = new UserService(this.getActivity());
		dblist=new ArrayList<GridViewEntity>() ;
		dblist = service.findAllBill();
		for (GridViewEntity dbent : dblist) {
			int state=Integer.parseInt(dbent.getBillState().toString().trim());
			if(state==0){
				dbent.setImg(img1[dbent.getImg()]);
				
			}else if(state==1){
				dbent.setImg(img2[dbent.getImg()]);
			}
		}
		adapter = new ListViewAdapterOfBill(getActivity(), dblist);
		billlistview.setAdapter(adapter);
	}  
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.add_btn_of_bill:
			Intent intent = new Intent(getActivity(), AddBill.class);
			getActivity().startActivity(intent);
			break;
		default:
			break;
		}
	}
}
