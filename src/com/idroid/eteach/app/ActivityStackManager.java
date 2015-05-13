package com.idroid.eteach.app;

import java.util.Stack;

import android.content.Context;

import com.idroid.eteach.ui.base.ActivityBase;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出<br>
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
	 * 获取当前Activity栈中元素个数
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
	 * 获取当前Activity（栈顶Activity）
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
	 * 获取当前Activity（栈顶Activity） 没有找到则返回null
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
	 * 结束当前Activity（栈顶Activity）
	 */
	public void finishActivity() {
		ActivityBase activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(ActivityBase activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity = null;
		}
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (ActivityBase activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空
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
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++)
			if (null != activityStack.get(i))
				activityStack.get(i).finish();
		activityStack.clear();
	}
	
	/**
     * 应用程序退出
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
