package com.alcatraz.mdocr.activities;

import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.alcatraz.mdocr.*;
import com.alcatraz.mdocr.adapters.*;
import com.alcatraz.mdocr.beans.*;
import com.alcatraz.support.v4.appcompat.*;
import io.github.yavski.fabspeeddial.*;
import java.util.*;

import android.support.v7.widget.Toolbar;
import com.alcatraz.mdocr.R;

public class MainActivity extends AppCompatActivity 
{
	//Widgets
	android.support.v7.widget.Toolbar tb;
	View Nv;
	FabSpeedDial fsd;
	NavigationView nv;
	RecyclerView rv;
	LinearLayout emptyLayer;
	DrawerLayout dl;
	MainRecyclerAdapter mra;
	//Data
	List<Bean> data=new LinkedList<Bean>();
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		new MenuInflater(this).inflate(R.menu.main_menu, menu);
		// TODO: Implement this method
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.item1:
				List<String> teat=new ArrayList<String>();
				String dor="";
				Bean b=new Bean(teat,dor);
				data.add(b);
				mra.notifyItemInserted(0);
				break;
			case R.id.item2:
				startActivity(new Intent(this,Author.class));
				break;
		}
		// TODO: Implement this method
		return super.onOptionsItemSelected(item);
	}


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		initData();
		findViews();
		initViews();
    }
	public void findViews()
	{
		tb = (Toolbar) findViewById(R.id.mainToolbar1);
		dl = (DrawerLayout) findViewById(R.id.mainDrawerLayout1);
		nv = (NavigationView) findViewById(R.id.navigation);
		rv = (RecyclerView) findViewById(R.id.mainRc1);
		//emptyLayer = (LinearLayout) findViewById(R.id.mainLinearLayout1);
		fsd = (FabSpeedDial) findViewById(R.id.mainFabSpeedDial1);
		Nv = findViewById(R.id.mainView1);
	}
	public void initViews()
	{
		mra=new MainRecyclerAdapter(this,data);
		rv.setLayoutManager(new LinearLayoutManager(this));
		rv.setItemAnimator(new DefaultItemAnimator());
		rv.setAdapter(mra);
		tb.setTitle(R.string.app_name);
		new DrawerLayoutUtil().setImmersiveToolbarWithDrawer(tb, dl, this, Nv, "#3f51b5", Build.VERSION.SDK_INT);
		setSupportActionBar(tb);
	}
	public void initData()
	{
		List<String> teat=new ArrayList<String>();
		String dor="";
		Bean b=new Bean(teat,dor);
		//data.add(b);
		data.add(b);
	}
}
