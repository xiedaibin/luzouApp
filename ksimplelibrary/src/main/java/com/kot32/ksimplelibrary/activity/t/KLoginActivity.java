package com.kot32.ksimplelibrary.activity.t;

import com.kot32.ksimplelibrary.activity.i.IBaseAction;
import com.kot32.ksimplelibrary.activity.t.base.KSimpleBaseActivityImpl;

/**
 * Created by kot32 on 15/11/9.
 */

@Deprecated
public abstract class KLoginActivity extends KSimpleBaseActivityImpl implements IBaseAction {
//
//    public static final int LOGIN_FAILED = 0;
//    private NetworkTask networkTask;
//
//    /**
//     * 开始登录
//     *
//     * @param loginParams 登录的参数以键值对形式写入HashMap 中传入
//     */
//    public void startLogin(HashMap<String, String> loginParams, Class responseClass) {
//
//        if (TextUtils.isEmpty(getServerURL())) {
//            Log.e("警告", "服务器地址为空");
//            return;
//        }
//
//        NetworkTask networkTask =
//                new NetworkTask(getTaskTag(), this, responseClass, loginParams, getServerURL(), NetworkTask.GET) {
//
//                    @Override
//                    protected void onPreExecute() {
//                        super.onPreExecute();
//                        onStartLogin();
//                    }
//
//                    @Override
//                    public void onExecutedMission(NetworkExecutor.NetworkResult result) {
//                        onLoginSuccess((BaseResponse) result.resultObject);
//                    }
//                    @Override
//                    public void onExecutedFaild(NetworkExecutor.NetworkResult result) {
//                        onLoginFailed(result.tips);
//                    }
//                };
//        SimpleTaskManager.startNewTask(networkTask);
//
//    }
//
//    /**
//     * 服务器地址
//     */
//    public abstract String getServerURL();
//
//
//    /**
//     * 开始登录
//     */
//    public void onStartLogin() {
//    }
//
//    /**
//     * 登录完毕后将用户信息保存
//     */
//    public void onLoginSuccess(BaseResponse responseModel) {
//        //遍历对象中的 UserModel 数据
//        for (Field field : FieldUtil.getAllDeclaredFields(responseModel.getClass())) {
//            if (field.getType() == BaseUserModel.class) {
//                try {
//                    BaseUserModel baseUserModel = (BaseUserModel) FieldUtil.get(field, responseModel);
//                    if (baseUserModel == null) {
//                        Log.e("警告", "返回数据中不包含用户数据模型");
//                        return;
//                    }
//                    if (getSimpleApplicationContext() != null) {
//                        getSimpleApplicationContext().setUserModel(baseUserModel);
//                        PreferenceManager.setLocalUserModel(baseUserModel);//
//                    }
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     * 登录失败
//     */
//    public void onLoginFailed(String failedTips) {
//        Toast.makeText(this, "登录失败，原因：" + failedTips, Toast.LENGTH_SHORT).show();
//    }


}
