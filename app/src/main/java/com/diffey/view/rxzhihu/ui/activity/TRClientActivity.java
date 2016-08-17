package com.diffey.view.rxzhihu.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.adapter.TRClientAdapter;
import com.diffey.view.rxzhihu.api.TRApi;
import com.diffey.view.rxzhihu.api.TRService;
import com.diffey.view.rxzhihu.base.SimpleActivity;
import com.diffey.view.rxzhihu.bean.ChatBean;
import com.diffey.view.rxzhihu.bean.NewsEntity;
import com.diffey.view.rxzhihu.bean.TREntity;
import com.diffey.view.rxzhihu.contant.Contant;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TRClientActivity extends SimpleActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.trc_list)
    ListView trcList;

    @Bind(R.id.trc_edit)
    EditText trcEdit;

    @Bind(R.id.trc_btn_send)
    Button trcBtnSend;

    @Bind(R.id.trc_bottom)
    RelativeLayout trcBottom;

    private TRClientAdapter trClientAdapter;
    private TRApi service;

    @Override
    protected int getContentView() {
        return R.layout.activity_trclient;
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);

        initToolBar();
        trClientAdapter = new TRClientAdapter(this);
        trcList.setAdapter(trClientAdapter);
    }

    private void initToolBar() {
        toolbar.setTitle("小er");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_actionbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        service = TRService.createTRService();
        trClientAdapter.addData(new ChatBean(TRClientAdapter.TYPE_ROBOT, Contant.TRC_ROBOT_REC));
    }

    @Override
    protected void initListener() {
        super.initListener();
        trcBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = trcEdit.getText().toString();
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                addData(new ChatBean(TRClientAdapter.TYPE_USER, str));
                trcEdit.setText("");
                gainChat(str);
            }
        });
    }

    private void gainChat(String str) {
        service.getTRResponse(Contant.TRC_KEY, str, Contant.TRC_USER_ID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TREntity>() {
                    @Override
                    public void call(TREntity trEntity) {
                        if (trEntity != null) {
                            String str;
                            if (trEntity.getCode() == 40004) {
                                str = Contant.TRC_ROBOT_REST;
                            } else {
                                str = trEntity.getText();
                            }
                            addData(new ChatBean(TRClientAdapter.TYPE_ROBOT, str));
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        addData(new ChatBean(TRClientAdapter.TYPE_ROBOT, Contant.TRC_ROBOT_FAILED));
                    }
                });
    }

    private void addData(ChatBean chatBean) {
        trClientAdapter.addData(chatBean);
        trcList.setSelection(trClientAdapter.getCount());
    }
}
