package com.alcatraz.mdocr;

import java.io.File;

import android.graphics.Bitmap;
import android.os.Environment;

import com.googlecode.tesseract.android.TessBaseAPI;
import android.content.*;
import java.io.*;

public class TessOCR
{
	private TessBaseAPI mTess;

	public TessOCR(Context ctz)
	{
		// TODO Auto-generated constructor stub
		mTess = new TessBaseAPI();
		String language = "eng";
        String path = ctz.getFilesDir().getAbsolutePath();
        File file = new File(path + "/tessdata");

        if (!file.exists())
		{
            file.mkdirs();
            try
			{
                InputStream is = getClass().getClassLoader().getResourceAsStream("assets/eng.traineddata");
                File file2 = new File(file.getAbsoluteFile() + "/eng.traineddata");
                FileOutputStream out = new FileOutputStream(file2);
                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1)
				{
                    out.write(buffer, 0, len);
                    out.flush();
                }
                is.close();
                out.close();
            }
			catch (FileNotFoundException e)
			{
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
			catch (IOException e)
			{
                e.printStackTrace();
            }
		}
		mTess.init(path, language);
	}

	public String getOCRResult(Bitmap bitmap)
	{
		mTess.setImage(bitmap);
		String result = mTess.getUTF8Text();
		return result;
    }

	public void onDestroy()
	{
		if (mTess != null)
			mTess.end();
	}

}
