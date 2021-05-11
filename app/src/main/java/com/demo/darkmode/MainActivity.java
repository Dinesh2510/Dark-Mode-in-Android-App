package com.demo.darkmode;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    private static final String NIGHT_MODE = "night_mode";
    private SharedPreferences mSharedPref;
    Switch switch1;
    Method method;
    private String themMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        method = new Method(MainActivity.this);



        SwitchMaterial switchMaterial = findViewById(R.id.switch_setting);
        MaterialTextView textViewContactUs = findViewById(R.id.textView_contactUs_setting);
        MaterialTextView textViewFaq = findViewById(R.id.textView_faq_setting);
        MaterialTextView textViewPP = findViewById(R.id.textView_privacy_policy_setting);
        MaterialTextView textViewShareApp = findViewById(R.id.textView_shareApp_setting);
        MaterialTextView textViewRateApp = findViewById(R.id.textView_rateApp_setting);
        MaterialTextView textViewMoreApp = findViewById(R.id.textView_moreApp_setting);
        MaterialTextView textViewAboutUs = findViewById(R.id.textView_aboutUs_setting);
        MaterialTextView textViewThemType = findViewById(R.id.textView_themType_setting);
        ConstraintLayout conThem = findViewById(R.id.con_them_setting);
        ImageView imageView = findViewById(R.id.imageView_them_setting);

        if (method.isDarkMode()) {
            Glide.with(getApplicationContext()).load(R.drawable.mode_dark)
                    .placeholder(R.drawable.no_login)
                    .into(imageView);
        } else {
            Glide.with(getApplicationContext()).load(R.drawable.mode_icon)
                    .placeholder(R.drawable.no_login)
                    .into(imageView);
        }

        switchMaterial.setChecked(method.pref.getBoolean(method.notification, true));


        switch (method.themMode()) {
            case "system":
                textViewThemType.setText(getResources().getString(R.string.system_default));
                break;
            case "light":
                textViewThemType.setText(getResources().getString(R.string.light));
                break;
            case "dark":
                textViewThemType.setText(getResources().getString(R.string.dark));
                break;
        }



        conThem.setOnClickListener(v -> {

            Dialog dialog = new Dialog(MainActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialogbox_them);

            dialog.getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            RadioGroup radioGroup = dialog.findViewById(R.id.radioGroup_them);
            MaterialTextView textViewOk = dialog.findViewById(R.id.textView_ok_them);
            MaterialTextView textViewCancel = dialog.findViewById(R.id.textView_cancel_them);

            switch (method.themMode()) {
                case "system":
                    radioGroup.check(radioGroup.getChildAt(0).getId());
                    break;
                case "light":
                    radioGroup.check(radioGroup.getChildAt(1).getId());
                    break;
                case "dark":
                    radioGroup.check(radioGroup.getChildAt(2).getId());
                    break;
            }

            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                MaterialRadioButton rb = group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    switch (checkedId) {
                        case R.id.radioButton_system_them:
                            themMode = "system";
                            break;
                        case R.id.radioButton_light_them:
                            themMode = "light";
                            break;
                        case R.id.radioButton_dark_them:
                            themMode = "dark";
                            break;
                        default:
                            break;
                    }
                }
            });

            textViewOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vOk) {
                    method.editor.putString(method.themSetting, themMode);
                    method.editor.commit();
                    dialog.dismiss();

                    MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), SplashScreen.class));
                    MainActivity.this.finishAffinity();

                }
            });

            textViewCancel.setOnClickListener(vCancel -> dialog.dismiss());

            dialog.show();

        });


    }




}