package com.idroid.eteach.util;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

import com.idroid.eteach.R;

public class MediaRecorderUtil {
	public static final String AUDIO_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
			+ "etech" + File.separator + "audio";

	private MediaRecorder mRecorder;

	private String mAudioPath; // 要播放的声音的路径
	private boolean mIsRecording;// 是否正在录音
	private boolean mIsPlaying;// 是否正在播放
	private OnPlayListener listener;
	private MediaPlayer mPlayer;

	private void initRecorder() {
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
		mRecorder.setOutputFile(mAudioPath);
		mIsRecording = true;
	}

	public void recordAudio() {
		initRecorder();
		try {
			mRecorder.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mRecorder.start();
	}

	/** 获取音量值，只是针对录音音量 */
	public int getVolum() {
		int volum = 0;
		if (mRecorder != null && mIsRecording)
			volum = mRecorder.getMaxAmplitude();
		if (volum != 0)
			volum = (int) (10 * Math.log(volum) / Math.log(10)) / 7;
		return volum;
	}

	/** 停止录音 */
	public void stopRecord() {
		if (mRecorder != null) {
			mRecorder.stop();
			mRecorder.release();
			mRecorder = null;
			mIsRecording = false;
		}
	}

	public void startPlay(String audioPath) {
		if (!mIsPlaying) {
			if (!StringUtils.isEmpty(audioPath)) {
				mPlayer = new MediaPlayer();
				try {
					mPlayer.setDataSource(audioPath);
					mPlayer.prepare();
					mPlayer.start();
					if (listener != null) {
						listener.starPlay();
					}
					mIsPlaying = true;
					mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
						@Override
						public void onCompletion(MediaPlayer mp) {
							if (listener != null) {
								listener.stopPlay();
							}
							mp.release();
							mPlayer = null;
							mIsPlaying = false;
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
			}
		} // end playing
	}

	public interface OnPlayListener {
		/** 播放声音结束时调用 */
		void stopPlay();

		/** 播放声音开始时调用 */
		void starPlay();
	}
}