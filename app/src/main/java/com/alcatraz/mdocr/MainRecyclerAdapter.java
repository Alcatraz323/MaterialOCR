package com.alcatraz.mdocr;
import android.support.v7.widget.*;
import android.view.*;
import android.content.*;
import java.util.*;
import android.support.design.widget.*;
import android.widget.*;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainViewHolder>
{
	List<Bean> data;
	Context ctx;
	LayoutInflater lf;
	public MainRecyclerAdapter(Context c,List<Bean> data){
		ctx=c;
		this.data=data;
		lf=(LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public MainViewHolder onCreateViewHolder(ViewGroup p1, int p2)
	{
		View v=lf.inflate(R.layout.main_rec_item,p1,false);
		MainViewHolder mvh=new MainViewHolder(v);
		// TODO: Implement this method
		return mvh;
	}
	public int Dp2Px(Context context, float dp) { 
		final float scale = context.getResources().getDisplayMetrics().density; 
		return (int) (dp * scale + 0.5f); 
	} 
	@Override
	public void onBindViewHolder(MainViewHolder p1, int p2)
	{
		View root=p1.getView();
		FloatingActionButton fab=(FloatingActionButton) root.findViewById(R.id.fab);
		fab.setRight(Dp2Px(ctx,32));
		ImageView imgv=(ImageView) root.findViewById(R.id.mainrecabvlayerImageView1);
		fab.setTop(imgv.getHeight());
		Spinner sp=(Spinner) root.findViewById(R.id.mainrecabvlayerSpinner1);
		// TODO: Implement this method
	}

	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return data.size();
	}
	
}
class MainViewHolder extends RecyclerView.ViewHolder{
	View hold;
	public MainViewHolder(View v){
		super(v);
		hold=v;
	}
	public View getView(){
		return hold;
	}
}
