package com.idroid.eteach.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.idroid.eteach.R;
import com.idroid.eteach.adapter.StudentAdapter;
import com.idroid.eteach.ui.base.ActivityBase;
import com.idroid.eteach.widget.MatrixImageView;

public class ScaleBitmapActivity extends ActivityBase implements OnClickListener {

	private MatrixImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.scale_bitmap_layout);
		img = (MatrixImageView) findViewById(R.id.img);

		Intent intent = getIntent();

		int id = intent.getIntExtra("res", -1);

		String path = intent.getStringExtra("path");
		
		String url = intent.getStringExtra("url");

		if (id != -1)
//			img.setBackgroundResource(id);
			img.setImageBitmap(BitmapFactory.decodeResource(getResources(), id));

		if (path != null && !path.equals("")) {
			Bitmap bm = BitmapFactory.decodeFile(path, new Options());
			img.setImageBitmap(bm);
		}
		
		if (url != null && !url.equals("")) {
			ImageListener listener = ImageLoader.getImageListener(img,
					R.drawable.scale_act_default, R.drawable.scale_act_default);
			
			ImageLoader loader = new ImageLoader(Volley.newRequestQueue(this), new StudentAdapter.BitmapCache());
			loader.get(url , listener);
			LayoutParams p = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		}
	}

	//一下代码无用
	boolean big = true;
	
	@Override
	public void onClick(View v) {
		Drawable drawable = img.getDrawable();
		
		Bitmap bitmap;

		if (drawable instanceof BitmapDrawable) {
			BitmapDrawable bd = (BitmapDrawable) drawable;
			bitmap = bd.getBitmap();
		} else {
			int w = drawable.getIntrinsicWidth();
			int h = drawable.getIntrinsicHeight();

			Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;

			bitmap = Bitmap.createBitmap(w, h, config);

			// 注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
			Canvas canvas = new Canvas(bitmap);
			drawable.setBounds(0, 0, w, h);
			drawable.draw(canvas);
		}
		
		
		if(!big){
			big = !false;
			bitmap = big(bitmap);
		}else {
			big = !true;
			bitmap = small(bitmap);
		}
		
		img.setImageBitmap(bitmap);
//		img.setBackground(new BitmapDrawable(bitmap));
	}

	private static Bitmap big(Bitmap bitmap) {
		Matrix matrix = new Matrix();
		matrix.postScale(1.5f, 1.5f); // 长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizeBmp;
	}

	private static Bitmap small(Bitmap bitmap) {
		Matrix matrix = new Matrix();
		matrix.postScale(0.8f, 0.8f); // 长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizeBmp;
	}
}
