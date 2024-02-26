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

package com.bluetooth.communicatorexample.fragments;


import android.animation.Animator;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bluetooth.communicator.BluetoothCommunicator;
import com.bluetooth.communicator.Message;
import com.bluetooth.communicator.Peer;
import com.bluetooth.communicatorexample.Global;
import com.bluetooth.communicatorexample.MainActivity;
import com.bluetooth.communicatorexample.R;
import com.bluetooth.communicatorexample.gui.CustomAnimator;
import com.bluetooth.communicatorexample.gui.GuiTools;
import com.bluetooth.communicatorexample.gui.MessagesAdapter;
import com.bluetooth.communicatorexample.translation.Translator;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;


public class ConversationFragment extends Fragment {
    private ProgressBar loading;
    private static final float LOADING_SIZE_DP = 24;
    private EditText editText;
    private AppCompatImageButton sendButton;
    private RecyclerView mRecyclerView;
    protected TextView description;
    private ConstraintLayout constraintLayout;
    private Global global;
    private MainActivity activity;
    private MessagesAdapter mAdapter;
    private RecyclerView.SmoothScroller smoothScroller;

    public ConversationFragment() {
        //an empty constructor is always needed for fragments
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conversation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText);
        sendButton = view.findViewById(R.id.button_send);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        description = view.findViewById(R.id.description);
        loading = view.findViewById(R.id.progressBar2);
        constraintLayout = view.findViewById(R.id.container2);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (MainActivity) requireActivity();
        global = (Global) activity.getApplication();
        Toolbar toolbar = activity.findViewById(R.id.toolbarConversation);
        activity.setActionBar(toolbar);
        // we give the constraint layout the information on the system measures (status bar etc.), which has the fragmentContainer,
        // because they are not passed to it if started with a Transaction and therefore it overlaps the status bar because it fitsSystemWindows does not work
        WindowInsets windowInsets = activity.getFragmentContainer().getRootWindowInsets();
        if (windowInsets != null) {
            constraintLayout.dispatchApplyWindowInsets(windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), 0));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);
        smoothScroller = new LinearSmoothScroller(activity) {
            @Override
            protected int calculateTimeForScrolling(int dx) {
                return 100;
            }
        };

        mAdapter = new MessagesAdapter("phone", new MessagesAdapter.Callback() {
            @Override
            public void onFirstItemAdded() {
                description.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (global.getBluetoothCommunicator().getConnectedPeersList().size() > 0) {
                    //sending message
                    if (editText.getText().length() > 0) {
                        //the sender will be inserted by the receiver device, so you don't need to enter it
                        Message message = new Message(global, "m", editText.getText().toString(), global.getBluetoothCommunicator().getConnectedPeersList().get(0));
                        global.getBluetoothCommunicator().sendMessage(message);
                        editText.setText("");
                        //aggiunta del messaggio alla lista dei messaggi
                        mAdapter.addMessage(message);
                        //smooth scroll
                        smoothScroller.setTargetPosition(mAdapter.getItemCount() - 1);
                        mRecyclerView.getLayoutManager().startSmoothScroll(smoothScroller);
                    }
                }*/
                //global.getEncoderSession().run(Collections.singletonMap("image", inputTensor))
                if (editText.getText().length() > 0) {
                    //the sender will be inserted by the receiver device, so you don't need to enter it
                    String inputText = editText.getText().toString();
                    Message message = new Message(global, inputText);
                    editText.setText("");
                    //aggiunta del messaggio alla lista dei messaggi
                    mAdapter.addMessage(message);
                    //smooth scroll
                    smoothScroller.setTargetPosition(mAdapter.getItemCount() - 1);
                    mRecyclerView.getLayoutManager().startSmoothScroll(smoothScroller);
                    //traduzione
                    //String result = global.getTranslator().translate("eng_Latn","ita_Latn",inputText);
                    //String result = global.getTranslator().translate("eng","ita",inputText);
                    Handler mHandler = new Handler(Looper.getMainLooper());
                    new Thread(new Runnable() {
                        public void run() {
                            String result = global.getTranslator().translate("en","it",inputText);
                            mHandler.post(new Runnable() {
                                public void run(){
                                    Message message2 = new Message(global, "m", result);
                                    //aggiunta del messaggio alla lista dei messaggi
                                    mAdapter.addMessage(message2);
                                    //smooth scroll
                                    smoothScroller.setTargetPosition(mAdapter.getItemCount() - 1);
                                    mRecyclerView.getLayoutManager().startSmoothScroll(smoothScroller);
                                }
                            });
                        }
                    }).start();
                }else{
                    if(global.getTranslator()==null){
                        String downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
                        //String encoderPath = downloadPath.concat("/models/encoder_model_quantized.onnx");
                        //String encoderPath = downloadPath.concat("/models/Seamless_encoder_quantized.onnx");
                        //String encoderPath = downloadPath.concat("/models/NLLB_encoder_quantized.onnx");
                        String encoderPath = downloadPath.concat("/models/Madlad_encoder_quantized.onnx");
                        //String decoderPath = downloadPath.concat("/models/decoder_model_quantized.onnx");
                        //String decoderPath = downloadPath.concat("/models/Seamless_decoder_complete_fp16_compatible.onnx");
                        //String decoderPath = downloadPath.concat("/models/Seamless_decoder_complete_quantized.onnx");
                        //String decoderPath = downloadPath.concat("/models/NLLB_decoder_complete_fp16.onnx");
                        //String decoderPath = downloadPath.concat("/models/NLLB_decoder_quantized_static.onnx");
                        //String decoderPath = downloadPath.concat("/models/Madlad_decoder_fp16.onnx");
                        String decoderPath = downloadPath.concat("/models/Madlad_decoder_quantized_static_test.onnx");
                        //String vocabPath = downloadPath.concat("/models/sentencepiece_bpe.model");
                        String vocabPath = downloadPath.concat("/models/spiece.model");
                        global.setTranslator(new Translator(vocabPath,encoderPath,decoderPath,Translator.MADLAD_FIXED));
                        Toast.makeText(global,"modelli caricati",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void appearLoading() {
        int loadingSizePx = GuiTools.convertDpToPixels(activity, LOADING_SIZE_DP);
        CustomAnimator animator = new CustomAnimator();
        Animator animation = animator.createAnimatorSize(loading, 1, 1, loadingSizePx, loadingSizePx, getResources().getInteger(R.integer.durationShort));
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(loading != null) {
                    loading.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animation.start();
    }
}
