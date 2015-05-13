package com.idroid.eteach.app;

import java.util.Stack;

import android.content.Context;

import com.idroid.eteach.ui.base.ActivityBase;

/**
 * Ӧ�ó���Activity�����ࣺ����Activity�����Ӧ�ó����˳�<br>
 */
public class ActivityStackManager {

	private static final ActivityStackManager instance = new ActivityStackManager();
	private static Stack<ActivityBase> activityStack;

	private ActivityStackManager() {
	}

	public static ActivityStackManager getInstance() {
		return instance;
	}

	/**
	 * ��ȡ��ǰActivityջ��Ԫ�ظ���
	 */
	public int getStackCount() {
		return activityStack.size();
	}

	public void addActivity(ActivityBase activity) {
		if (activityStack == null)
			activityStack = new Stack<ActivityBase>();
		activityStack.add(activity);
	}

	/**
	 * ��ȡ��ǰActivity��ջ��Activity��
	 */
	public ActivityBase getTopActivity() {
		if (activityStack == null) {
			throw new NullPointerException("Activity stack is Null,your Activity must extend KJActivity");
		}
		if (activityStack.isEmpty()) {
			return null;
		}
		return activityStack.lastElement();
	}

	/**
	 * ��ȡ��ǰActivity��ջ��Activity�� û���ҵ��򷵻�null
	 */
	public ActivityBase findActivity(Class<?> cls) {
		ActivityBase activity = null;
		for (ActivityBase aty : activityStack) {
			if (aty.getClass().equals(cls)) {
				activity = aty;
				break;
			}
		}
		return activity;
	}

	/**
	 * ������ǰActivity��ջ��Activity��
	 */
	public void finishActivity() {
		ActivityBase activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * ����ָ����Activity
	 */
	public void finishActivity(ActivityBase activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity = null;
		}
	}

	/**
	 * ����ָ����Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (ActivityBase activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * �رճ���ָ��activity�����ȫ��activity ���cls��������ջ�У���ջȫ�����
	 * 
	 * @param cls
	 */
	public void finishOthersActivity(Class<?> cls) {
		for (ActivityBase activity : activityStack) {
			if (!(activity.getClass().equals(cls))) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * ��������Activity
	 */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++)
			if (null != activityStack.get(i))
				activityStack.get(i).finish();
		activityStack.clear();
	}
	
	/**
     * Ӧ�ó����˳�
     * 
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            Runtime.getRuntime().exit(0);
        } catch (Exception e) {
            Runtime.getRuntime().exit(-1);
        }
    }
}
