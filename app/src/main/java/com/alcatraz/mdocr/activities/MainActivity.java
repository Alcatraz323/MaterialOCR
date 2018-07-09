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
import java.io.*;
import android.support.design.internal.*;
import android.net.*;
import android.provider.*;
import android.util.*;
import com.yalantis.ucrop.*;
import android.text.*;
import android.database.*;
import android.graphics.*;
import android.content.res.*;

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
	Uri cur_out;
	TessOCR mTessOCR;
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
				//Setting Button
				delDir("/sdcard/TestDirc/");
				break;
			case R.id.item2:
				startAbout();
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
		//initData();
		mTessOCR=new TessOCR(this);
		findViews();
		initViews();
    }
	
	public void delDir(String path)...{
		File dir=new File(path);
		if(dir.exists()){
			File[] tmp=dir.listFiles();
			for(int i=0;i<tmp.length;i++){
				if(tmp[i].isDirectory()){
					delDir(path+"/"+tmp[i].getName());
				}
				else{
					tmp[i].delete();
				}
			}
			dir.delete();
		}
		return null;
	}
	public void findViews()
	{
		tb = (Toolbar) findViewById(R.id.mainToolbar1);
		dl = (DrawerLayout) findViewById(R.id.mainDrawerLayout1);
		nv = (NavigationView) findViewById(R.id.navigation);
		rv = (RecyclerView) findViewById(R.id.mainRc1);
		fsd = (FabSpeedDial) findViewById(R.id.mainFabSpeedDial1);
		Nv = findViewById(R.id.mainView1);
	}
	public void initViews()
	{
		nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

				@Override
				public boolean onNavigationItemSelected(MenuItem p1)
				{
					if (p1.getItemId() == R.id.nav_support_1_2)
					{
						startSettings();
					}
					else if (p1.getItemId() == R.id.nav_support_2_3)
					{
						startAbout();
					}
					// TODO: Implement this method
					return false;
				}
			});
		mra = new MainRecyclerAdapter(this, data);
		rv.setLayoutManager(new LinearLayoutManager(this));
		rv.setItemAnimator(new DefaultItemAnimator());
		rv.setAdapter(mra);
		fsd.setMenuListener(new FabSpeedDial.MenuListener(){

				@Override
				public boolean onPrepareMenu(NavigationMenu navigationMenu)
				{

					// TODO: Implement this method
					return true;
				}

				@Override
				public boolean onMenuItemSelected(MenuItem menuItem)
				{
					switch (menuItem.getItemId())
					{
						case R.id.fab_1:
							launchCamera();
							break;
						case R.id.fab_2:
							launchGallerySelection();
							break;
					}
					// TODO: Implement this method
					return true;
				}

				@Override
				public void onMenuClosed()
				{
					// TODO: Implement this method
				}
			});
		tb.setTitle(R.string.app_name);
		setSupportActionBar(tb);
		new DrawerLayoutUtil().setImmersiveToolbarWithDrawer(tb, dl, this, Nv, "#3f51b5", Build.VERSION.SDK_INT);
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO: Implement this method
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_CANCELED)
		{
			switch (requestCode)
			{
				case Constants.REQUEST_CAMERA:
					try
					{
						startUcrop(cur_out);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					//调用相机了，要调用图片裁剪的方法
					break;
				case Constants.REQUEST_GALLERY :
					Log.e("mdocrh",data.getData().getEncodedPath());
					before_crop(data);
					break;
				case UCrop.REQUEST_CROP:
					Uri croppedFileUri = UCrop.getOutput(data);
					uriOCR(croppedFileUri, new OCR(){

							@Override
							public void onFinished(final String res)
							{
								runOnUiThread(new Runnable(){

										@Override
										public void run()
										{
											toast(res);
											// TODO: Implement this method
										}
									});
								// TODO: Implement this method
							}
						});
					break;
			}
		}
	}
	
	public void toast(String s){
		Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
	}
	interface OCR{
		void onFinished(String res);
	}
	private void uriOCR(Uri uri,OCR inace) {
		if (uri != null) {
			InputStream is = null;
			try {
				is = getContentResolver().openInputStream(uri);
				Bitmap bitmap = BitmapFactory.decodeStream(is);
				doOCR(bitmap,inace);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	private void doOCR(final Bitmap bitmap,final OCR inace) {
		new Thread(new Runnable() {
				public void run() {

					final String result = mTessOCR.getOCRResult(bitmap);

					runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								if (result != null && !result.equals("")) {
									inace.onFinished(result);
								}
								
							}

						});

				};
			}).start();
	}
	private void before_crop(Intent data)
    {
        try {
            String path;
            if(data != null){
                Uri uri = data.getData();
                if(!TextUtils.isEmpty(uri.getAuthority())){
                    Cursor cursor = this.getContentResolver().query(uri,new String[]{MediaStore.Images.Media.DATA},null,null,null);
                    if(null == cursor){
                        return;
                    }
                    cursor.moveToFirst();
                    //拿到了照片的path
                    path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    cursor.close();
                    path = "file://"+path;

                }
                else
                    path=uri.toString();
                Uri uri_crop = Uri.parse(path);
                startUcrop(uri_crop);
                //启动裁剪界面，配置裁剪参数
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
	
	public void launchCamera()
	{
		File sdcardTempFile=new File(Constants.root + System.currentTimeMillis());
		Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);  
		cur_out=Uri.fromFile(sdcardTempFile); 
		intent.putExtra(MediaStore.EXTRA_OUTPUT, cur_out); 
		startActivityForResult(intent, Constants.REQUEST_CAMERA); 
	}
	public void launchGallerySelection()
	{
		Intent choosePicIntent = new Intent(Intent.ACTION_PICK, null);
        choosePicIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(choosePicIntent, Constants.REQUEST_GALLERY);
	}
	private void startUcrop(Uri uri_crop)
	{
        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), "CropImage.png"));
        UCrop uCrop = UCrop.of(uri_crop, destinationUri);
        UCrop.Options options = new UCrop.Options();
        options.setToolbarTitle(getString(R.string.crop_ti));
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        options.setFreeStyleCropEnabled(true);
        uCrop.useSourceImageAspectRatio();
        uCrop.withOptions(options);
        try
		{
            uCrop.start(this);
        }
		catch (Exception e)
		{
            e.printStackTrace();
        }
	}
	public void startAbout()
	{
		startActivity(new Intent(this, Author.class));
	}
	public void startSettings()
	{
		//Settings
	}
	public void initData()
	{
		IO.read(new File(Constants.getDataInternalDirectory(this)), new IO.ReadMonitor(){

				@Override
				public void onLine(String line)
				{
					data.add(IO.processRawData(line));
					// TODO: Implement this method
				}

				@Override
				public void callFinish()
				{
					runOnUiThread(new Runnable(){

							@Override
							public void run()
							{
								mra.notifyItemInserted(0);
								// TODO: Implement this method
							}
						});
					// TODO: Implement this method
				}
			});
	}

}
