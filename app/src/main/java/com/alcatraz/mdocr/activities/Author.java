package com.alcatraz.mdocr.activities;
import android.content.*;
import android.net.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import com.alcatraz.mdocr.*;
import com.alcatraz.mdocr.adapters.*;
import com.alcatraz.support.v4.appcompat.*;
import java.util.*;

import com.alcatraz.mdocr.R;
import android.support.v7.widget.*;
import com.alcatraz.mdocr.beans.*;
import android.graphics.*;

public class Author extends AppCompatActivity
{
	List<Integer> imgs=new ArrayList<Integer>();
	Map<Integer,List<String>> data=new HashMap<Integer,List<String>>();
	ListView lv;
	android.support.v7.widget.Toolbar tb;
	AppBarLayout abl;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.author);
		initData();
		initViews();
	}
	public int Dp2Px(Context context, float dp) { 
		final float scale = context.getResources().getDisplayMetrics().density; 
		return (int) (dp * scale + 0.5f); 
	} 
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		switch (item.getItemId())
		{
			case android.R.id.home:
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void initViews()
	{
		tb = (android.support.v7.widget.Toolbar) findViewById(R.id.mytoolbar_1);
		setSupportActionBar(tb);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		abl=(AppBarLayout) findViewById(R.id.appbar);
		StatusBarUtil.setColor(this,getResources().getColor(R.color.default_colorPrimary));
		lv = (ListView) findViewById(R.id.authorcontentListView1);
		AuthorAdapter aa=new AuthorAdapter(this, data, imgs);
		lv.setAdapter(aa);
		lv.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					if (p1.getItemAtPosition(p3).toString().equals(getString(R.string.au_l_3)))
					{
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Alcatraz323")));
					}else if (p1.getItemAtPosition(p3).toString().equals(getString(R.string.au_l_4))){
						String str = "market://details?id=" + getPackageName();
						Intent localIntent = new Intent("android.intent.action.VIEW");
						localIntent.setData(Uri.parse(str));
						startActivity(localIntent);
					}else if(p1.getItemAtPosition(p3).toString().equals(getString(R.string.au_l_2))){
						showOSPDialog();
					}
				}
				// TODO: Implement this metho
			});
	}
	public void showOSPDialog(){
		View v=getLayoutInflater().inflate(R.layout.ad_ops,null);
		new android.support.v7.app.AlertDialog.Builder(this)
		.setTitle(R.string.au_ops)
		.setView(v)
		.setNegativeButton(R.string.ad_nb3,null).show();
		RecyclerView rv=(RecyclerView) v.findViewById(R.id.opRc1);
		List<OSP> dat=new Constants().getOsps();
		OpenSourceAdapter mra=new OpenSourceAdapter(this,dat);
		rv.setLayoutManager(new LinearLayoutManager(this));
		rv.setItemAnimator(new DefaultItemAnimator());
		rv.setAdapter(mra);
		rv.addItemDecoration(new com.alcatraz.mdocr.DividerItemDecoration(this,LinearLayout.HORIZONTAL,Dp2Px(this,8),Color.parseColor("#eeeeee")));
	}
	public void initData()
	{
		imgs.add(R.drawable.ic_information_outline);
		imgs.add(R.drawable.ic_account);
		imgs.add(R.drawable.ic_github);
		imgs.add(R.drawable.ic_arrow_right);
		List<String> l1=new ArrayList<String>();
		l1.add(getString(R.string.au_l_1));
		l1.add("---");
		List<String> l2=new ArrayList<String>();
		l2.add(getString(R.string.au_l_2));
		l2.add(getString(R.string.au_l_2_1));
		List<String> l3=new ArrayList<String>();
		l3.add(getString(R.string.au_l_3));
		l3.add("");
		List<String> l4=new ArrayList<String>();
		l4.add(getString(R.string.au_l_4));
		l4.add(getString(R.string.au_l_4_1));
		data.put(0, l1);
		data.put(1, l2);
		data.put(2, l3);
		data.put(3, l4);

	}
}
