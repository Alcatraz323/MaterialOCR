package com.alcatraz.mdocr.adapters;
import android.content.*;
import android.support.design.widget.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.alcatraz.mdocr.*;
import com.alcatraz.mdocr.beans.*;
import java.util.*;
import android.view.View.*;
import android.net.*;

public class OpenSourceAdapter extends RecyclerView.Adapter<OpenSourceContainer>
{
	List<OSP> data;
	Context ctx;
	LayoutInflater lf;
	public OpenSourceAdapter(Context c,List<OSP> data){
		ctx=c;
		this.data=data;
		lf=(LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public OpenSourceContainer onCreateViewHolder(ViewGroup p1, int p2)
	{
		View v=lf.inflate(R.layout.opensource_holder,p1,false);
		OpenSourceContainer mvh=new OpenSourceContainer(v);
		// TODO: Implement this method
		return mvh;
	}
	public int Dp2Px(Context context, float dp) { 
		final float scale = context.getResources().getDisplayMetrics().density; 
		return (int) (dp * scale + 0.5f); 
	} 
	@Override
	public void onBindViewHolder(OpenSourceContainer p1, int p2)
	{
		View root=p1.getView();
		final OSP cur=data.get(p2);
		CardView cv=(CardView) root.findViewById(R.id.listcardCardView1);
		cv.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Intent localIntent = new Intent("android.intent.action.VIEW");
					localIntent.setData(Uri.parse(cur.getUrl()));
					ctx.startActivity(localIntent);
					// TODO: Implement this method
				}
			});
		TextView name=(TextView) root.findViewById(R.id.opensourceholderTextView1);
		TextView author=(TextView) root.findViewById(R.id.opensourceholderTextView2);
		TextView intro=(TextView) root.findViewById(R.id.opensourceholderTextView3);
		TextView lic=(TextView) root.findViewById(R.id.opensourceholderTextView4);
		name.setText(cur.getName());
		author.setText(cur.getAuthor());
		intro.setText(cur.getIntro());
		lic.setText(cur.getLicense());
		// TODO: Implement this method
	}

	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return data.size();
	}
}
class OpenSourceContainer extends RecyclerView.ViewHolder{
	View hold;
	public OpenSourceContainer(View v){
		super(v);
		hold=v;
	}
	public View getView(){
		return hold;
	}
}
