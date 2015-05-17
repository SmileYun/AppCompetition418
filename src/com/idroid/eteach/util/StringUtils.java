package com.idroid.eteach.util;

public class StringUtils {

	public static boolean isEmpty(String audioPath) {
		if (audioPath != null && "".equals(audioPath))
			return false;
		return true;
	}

}
