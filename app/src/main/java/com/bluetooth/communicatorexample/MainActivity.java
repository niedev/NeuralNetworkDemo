/*
 * Copyright 2016 Luca Martino.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copyFile of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bluetooth.communicatorexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;

import com.bluetooth.communicatorexample.fragments.ConversationFragment;
import com.bluetooth.communicatorexample.translation.Translator;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int PAIRING_FRAGMENT = 0;
    public static final int CONVERSATION_FRAGMENT = 1;
    public static final int DEFAULT_FRAGMENT = CONVERSATION_FRAGMENT;
    public static final int NO_PERMISSIONS = -10;
    public static final String[] REQUIRED_PERMISSIONS = new String[]{
            Manifest.permission.MANAGE_EXTERNAL_STORAGE
    };
    private static final int STORAGE_PERMISSION_CODE = 23;
    private Global global;
    private int currentFragment = -1;
    private CoordinatorLayout fragmentContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        global = (Global) getApplication();

        // Clean fragments (only if the app is recreated (When user disable permission))
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        // Remove previous fragments (case of the app was restarted after changed permission on android 6 and higher)
        List<Fragment> fragmentList = fragmentManager.getFragments();
        for (Fragment fragment : fragmentList) {
            if (fragment != null) {
                fragmentManager.beginTransaction().remove(fragment).commit();
            }
        }

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        fragmentContainer = findViewById(R.id.fragment_container);
        if(!Environment.isExternalStorageManager()) {
            requestForStoragePermissions();
        }
        String downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        //String encoderPath = downloadPath.concat("/models/encoder_model_quantized.onnx");
        //String decoderPath = downloadPath.concat("/models/decoder_model_quantized.onnx");
        String encoderPath = downloadPath.concat("/models/Seamless_encoder_quantized.onnx");
        String decoderPath = downloadPath.concat("/models/Seamless_decoder_complete_fp16_compatible.onnx");
        //String decoderPath = downloadPath.concat("/models/Seamless_decoder_complete_quantized.onnx");
        String vocabPath = downloadPath.concat("/models/sentencepiece_bpe.model");
        //global.setTranslator(new Translator(vocabPath,encoderPath,decoderPath,Translator.SEAMLESS));
        //String en_text = "Also unlike 2014, there aren’t nearly as many loopholes. You can’t just buy a 150-watt incandescent or a three-way bulb, the ban covers any normal bulb that generates less than 45 lumens per watt, which pretty much rules out both incandescent and halogen tech in their entirety.";
        //String result = global.getTranslator().translate("eng_Latn","ita_Latn",en_text);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // when we return to the app's gui we choose which fragment to start based on connection status
        setFragment(DEFAULT_FRAGMENT);
    }

    public void setFragment(int fragmentName) {
        switch (fragmentName) {
            case CONVERSATION_FRAGMENT: {
                // possible setting of the fragment
                if (getCurrentFragment() != CONVERSATION_FRAGMENT) {
                    ConversationFragment conversationFragment = new ConversationFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.replace(R.id.fragment_container, conversationFragment);
                    transaction.commit();
                    currentFragment = CONVERSATION_FRAGMENT;
                }
                break;
            }
        }
    }

    public int getCurrentFragment() {
        if (currentFragment != -1) {
            return currentFragment;
        } else {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (currentFragment != null) {
                if (currentFragment.getClass().equals(ConversationFragment.class)) {
                    return CONVERSATION_FRAGMENT;
                }
            }
        }
        return -1;
    }

    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener confirmExitListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exitFromConversation();
            }
        };
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            if (fragment instanceof ConversationFragment) {
                showConfirmExitDialog(confirmExitListener);
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    public void exitFromConversation() {

    }

    protected void showConfirmExitDialog(DialogInterface.OnClickListener confirmListener) {
        //creazione del dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("Confirm exit");
        builder.setPositiveButton(android.R.string.ok, confirmListener);
        builder.setNegativeButton(android.R.string.cancel, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public CoordinatorLayout getFragmentContainer() {
        return fragmentContainer;
    }


    /*private byte[] readEncoder(){
        try {
            return IOUtils.toByteArray(getResources().openRawResource(R.raw.encoder_model_quantized));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] readDecoder(){
        try {
            return IOUtils.toByteArray(getResources().openRawResource(R.raw.decoder_model_quantized));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    private void requestForStoragePermissions() {
        try {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            Uri uri = Uri.fromParts("package", this.getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
        }catch (Exception e){
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
            startActivity(intent);
        }
    }

}