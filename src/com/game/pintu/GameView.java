package com.game.pintu;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.game.config.Config;

public class GameView extends View implements DialogInterface.OnClickListener {
	private int w,h;	//��Ϸ������
	private Bitmap img;		//��ͼ��ͼƬ
	private Paint paint;	//������һ������
	private Bitmap[] bitmap;	//���ָ��ͼƬ������
	private Rect[] rect;	//��Ӧ��ͼƬ�ľ��ο�
	private int n;		//�տ��λ��
	private int array[];	//������
	private int o;	//����ΪͼƬ��Ϊ��*���Ĵ�С 
	private int kk;	//�տ��ֵ
	private int startK;	//�ִ�����Ļʱ����Ŀ�
	
	
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO �Զ����ɵĹ��캯�����
		switch(Config.imageId){
		case R.id.iv1:
			img = BitmapFactory.decodeResource(getResources(), R.drawable.icon1);
			break;
		case R.id.iv2:
			img = BitmapFactory.decodeResource(getResources(), R.drawable.icon2);
			break;
		case R.id.iv3:
			img = BitmapFactory.decodeResource(getResources(), R.drawable.icon3);
			break;
		case R.id.iv4:
			img = BitmapFactory.decodeResource(getResources(), R.drawable.icon4);
			break;
		case R.id.iv5:
			img = BitmapFactory.decodeResource(getResources(), R.drawable.icon5);
			break;
		case R.id.iv6:
			img = BitmapFactory.decodeResource(getResources(), R.drawable.icon6);
			break;
		
		}
		
		
		
		
		
		paint = new Paint();	
		Config.bushu = 0;	//��������ʼ��Ϊ0
		o = Config.nandu;	//�Ѷ�,Ҳ����ͼ�α��ֳ��˼�������
		
		
		rect = new Rect[o*o];	
		bitmap = new Bitmap[o*o];
		array = new int[o*o];
		
		n = 0;	
		
		//��ʼ����Ϸ������
		this.w = Config.metrics.widthPixels;
		this.h = Config.metrics.heightPixels/3*2;
		//��ʼ��ͼƬ
		paint.setColor(Color.BLACK);
		
		img = Bitmap.createScaledBitmap(img, w, h, true);
		
		
		for( int y = 0; y < o; y++){
			for(int x = 0; x < o; x++){
				//�ָ�ͼƬ
				bitmap[y*o+x] = Bitmap.createBitmap(img,x * (w / o) + 2, y * (h / o) + 2,w / o - 2,h / o - 2);
				rect[y*o+x] = new Rect(x * (w / o) + 2, y * (h / o) + 2, (x+1) * w / o - 2, (y+1) * h / o - 2 );
				array[y*o+x] = y*o+x;
				//ͼƬ��Ҫ��ʾ��λ��
			}
		}
			
		
		
		kk = o*o-1;
		n = o*o-1;
		//ѭ������
		for(int i = 0; i < o*o*10;i++){
			yyy();
		}
		
	}
	//ʹ���Զ���ķ������ҳ���ô���ܸ�ԭ
	private void yyy(){
		int ran = 0;
		
		List<Integer> tt = new ArrayList<Integer>(); 
		if(isLeft(n)){
			tt.add(0);
			ran++;
		}
		if(isRight(n)){
			tt.add(1);
			ran++;
		}
		if(isTop(n)){
			tt.add(2);
			ran++;
		}
		if(isButtom(n)){
			tt.add(3);
			ran++;
		}
		int tem;
		switch(tt.get((int) (Math.random()* ran))){
		case 0:	//��������
			tem = array[n];
			array[n] = array[n-1];
			array[n-1] = tem;
			n--;
			
			break;
		case 1:
			tem = array[n];
			array[n] = array[n+1];
			array[n+1] = tem;
			n++;
			break;
		case 2:
			tem = array[n];
			array[n] = array[n-o];
			array[n-o] = tem;
			n -= o;
			break;
		case 3:
			tem = array[n];
			array[n] = array[n+o];
			array[n+o] = tem;
			n += o;
			break;
		}
		
		
		
		
		
		
	}

	
		
	

	@Override
	protected void onDraw(Canvas canvas) {
		 //TODO �Զ����ɵķ������
		for( int y = 0; y < o; y++){
			for(int x = 0; x < o; x++){
				int u = array[y*o+x];
				if(o * o - 1 == u){	//����ǿտ�,��������ɫ��
					paint.setColor(Color.YELLOW);
					canvas.drawRect(rect[y*o+x], paint);
				}else{
					canvas.drawBitmap(bitmap[u], (float)rect[y*o+x].left, (float)rect[y*o+x].top, paint);
				
				}
				
			}
		}
		
		invalidate();
		super.onDraw(canvas);
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO �Զ����ɵķ������
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			startK = getTouchXY(event);
			break;
		case MotionEvent.ACTION_UP:
			int k = getTouchXY(event);	//������Ŀ�
			if(k != startK){				//�����ָ���µĿ���̧��ʱ�Ŀ鲻ͬ,������ĳ�鱻�����
				return false;
			}
			
			
			
			if(k != -1){
				
				if(isLeft(k) ){	//�տ������
					if( array[k -1] == o * o - 1){
						array[k-1] = array[k];
						array[k] = o * o - 1;
						Config.bushu++;
						kk = k;
						isOk();
						return true;
					}
				}
				if(isRight(k) ){
					if(array[k+1] == o * o - 1){
						array[k+1] = array[k];
						array[k] = o * o - 1;
						Config.bushu++;
						kk = k;
						isOk();
						return true;
					}
				}
				if(isTop(k)){
					if(array[k-o] == o * o - 1){
						array[k-o] = array[k];
						array[k] = o * o - 1;
						Config.bushu++;
						kk = k;
						isOk();
						return true;
					}
				}
				if(isButtom(k)){
					if(array[k+o] == o * o - 1){
						array[k+o] = array[k];
						array[k] = o * o - 1;
						Config.bushu++;
						kk = k;
						isOk();
						return true;
					}
				}
			}
			break;
		}
		return true;
		
	}
	
	private int getTouchXY(MotionEvent event){	//���������ȡ���ĸ��鱻�����
		
		for( int y = 0; y < o; y++){
			for(int x = 0; x < o; x++){
				if(rect[y*o+x].contains((int)event.getX(), (int)event.getY())){
					
					return y*o+x;
				}
			}
		}
		return -1;
		
	}
	/*private int find(int n){
		for(int i = 0; i < o*o ;i++){
			if(array[i] == n){
				return i;
			}
		}
		return -1;
	}*/
	
	private boolean isLeft(int k){	//�жϱ���Ŀ������Ƿ񳬳���Ե
		for(int i = 0; i < o ;i++){
			if( k == o * i){
				return false;
			}
		}
		return true;	
	}
	private boolean isRight(int k){	
		for(int i = 0; i < o ;i++){
			if( k == o * (i+1)-1){
				return false;
			}
		}
		return true;	
	}
	private boolean isTop(int k){	
		for(int i = 0; i < o ;i++){
			if( k == i){
				return false;
			}
		}
		return true;	
	}
	private boolean isButtom(int k){	
		for(int i = 0; i < o ;i++){
			if( k == o * o - i-1){
				return false;
			}
		}
		return true;	
	}
	
	private boolean isOk(){
		for( int i = 0; i < array.length-1; i++){
			if(array[i+1]-array[ i] != 1 ){
				return false;	
			}	
		}
		new AlertDialog.Builder(getContext())
		.setTitle("���")
		.setMessage("��ʱ:"+(int)((System.currentTimeMillis()-Config.startTime)/1000))
		.setPositiveButton("ȷ��", this)
		.setNegativeButton("ȡ��", this)
		.show();
		return true;
	}




	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO �Զ����ɵķ������
		
	}

}
