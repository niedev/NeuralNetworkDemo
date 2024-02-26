package com.bluetooth.communicatorexample.translation;

import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.LongBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OnnxValue;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtLoggingLevel;
import ai.onnxruntime.OrtSession;
import ai.onnxruntime.providers.NNAPIFlags;

public class Translator {
    public static final int NLLB = 0;
    public static final int NLLB_FIXED = 1;
    public static final int SEAMLESS = 2;
    public static final int MADLAD = 3;
    public static final int MADLAD_FIXED = 4;
    private final int mode;
    private Tokenizer tokenizer;
    private OrtEnvironment onnxEnv;
    private OrtSession encoderSession;
    private OrtSession decoderSession;

    //test phrase
    /*Also, unlike in 2014, there aren’t nearly as many loopholes. You can’t just buy a 150-watt incandescent or a three-way bulb, the ban covers any normal bulb that generates less than 45 lumens per watt, which pretty much rules out both incandescent and halogen tech in their entirety.
     */

    public Translator(String vocabPath, String encoderPath, String decoderPath, int mode){
        this.mode = mode;
        onnxEnv = OrtEnvironment.getEnvironment(OrtLoggingLevel.ORT_LOGGING_LEVEL_VERBOSE);
        try {
            OrtSession.SessionOptions decoderOptions = new OrtSession.SessionOptions();
            //EnumSet<NNAPIFlags> flags = EnumSet.of(NNAPIFlags.USE_FP16);
            //EnumSet<NNAPIFlags> flags = EnumSet.of(NNAPIFlags.CPU_DISABLED);
            //decoderOptions.addConfigEntry("session.load_model_format", "ORT");
            //decoderOptions.addNnapi(flags);
            //decoderOptions.setCPUArenaAllocator(false);
            decoderOptions.setSessionLogLevel(OrtLoggingLevel.ORT_LOGGING_LEVEL_VERBOSE);
            decoderOptions.setSessionLogVerbosityLevel(0);
            //decoderOptions.setInterOpNumThreads(8);
            //decoderOptions.setIntraOpNumThreads(8);
            //decoderOptions.setOptimizationLevel(OrtSession.SessionOptions.OptLevel.BASIC_OPT);
            //decoderOptions.addTvm();
            //decoderOptions.addNnapi();
            //decoderOptions.setIntraOpNumThreads(1);
            //decoderOptions.setCPUArenaAllocator(false);
            //decoderOptions.setMemoryPatternOptimization(false);
            //decoderOptions.setMemoryPatternOptimization(true);
            //decoderOptions.setCPUArenaAllocator(false);
            //decoderOptions.disableProfiling();
            //decoderOptions.setExecutionMode(OrtSession.SessionOptions.ExecutionMode.PARALLEL);
            //decoderOptions.addArmNN(true);
            //decoderOptions.addACL(true);
            decoderSession = onnxEnv.createSession(decoderPath, decoderOptions);

            OrtSession.SessionOptions encoderOptions = new OrtSession.SessionOptions();
            //EnumSet<NNAPIFlags> flags = EnumSet.of(NNAPIFlags.USE_FP16);
            //EnumSet<NNAPIFlags> flags = EnumSet.of(NNAPIFlags.CPU_DISABLED);
            //encoderOptions.addNnapi(flags);
            //encoderOptions.addTvm();
            //encoderOptions.addNnapi();
            //encoderOptions.setMemoryPatternOptimization(false);
            encoderOptions.setCPUArenaAllocator(false);
            //encoderOptions.disableProfiling();
            //encoderOptions.setExecutionMode(OrtSession.SessionOptions.ExecutionMode.PARALLEL);
            //encoderOptions.addArmNN(true);
            //encoderOptions.addACL(true);
            encoderSession = onnxEnv.createSession(encoderPath, encoderOptions);

            int a=1;

        } catch (OrtException e) {
            throw new RuntimeException(e);
        }
        tokenizer = new Tokenizer(vocabPath, mode);
    }

    public String translate(String srcLanguage, String tgtLanguage, String text){
        //tokenizzazione
        long initTime = System.currentTimeMillis();
        long time = System.currentTimeMillis();
        TokenizerResult input = tokenizer.tokenize(srcLanguage,tgtLanguage,text);
        android.util.Log.i("performance", "Tokenization done in: " + (System.currentTimeMillis()-time) + "ms");
        //esecuzione dell'encoder
        time = System.currentTimeMillis();
        OnnxTensor encoderResult = executeEncoder(input.getInputIDs(),input.getAttentionMask());
        android.util.Log.i("performance", "Encoder done in: " + (System.currentTimeMillis()-time) + "ms");
        //esecuzione del decoder
        final int eos = tokenizer.PieceToID("</s>");
        //int output = tokenizer.PieceToID("<s>");
        ArrayList<Integer> completeOutput = new ArrayList<Integer>();
        int output = tokenizer.getLanguageID(tgtLanguage);
        if(mode != MADLAD && mode != MADLAD_FIXED) {
            completeOutput.add(eos);
            completeOutput.add(output);
        }else{
            completeOutput.add(0);   //tokenizer.PieceToID("<s>")
        }
        int j = 1; //serve per il logger
        while(output != eos){
            time = System.currentTimeMillis();
            int[] completeOutputArray = completeOutput.stream().mapToInt(i -> i).toArray();  //converte completeOutput in un array di int (testare)
            output = executeDecoder(completeOutputArray,input.getAttentionMask(),encoderResult,j);
            completeOutput.add(output);
            android.util.Log.i("performance", "Generation of"+j+"th word done in: " + (System.currentTimeMillis()-time) + "ms");
            j++;
            //Questo codice serve solo per il debug
            int[] outputIDs = completeOutput.stream().mapToInt(i -> i).toArray();
            android.util.Log.i("result", tokenizer.decode(outputIDs));
        }
        //convertiamo gli ids di completeOutputs in una stringa e la restituiamo
        int[] completeOutputArray = completeOutput.stream().mapToInt(i -> i).toArray();  //converte completeOutput in un array di int (testare)
        encoderResult.close();  //serve a rilasciare la memoria occupata dal risultato (altrimenti di accumula e aumenta molto)
        time = System.currentTimeMillis();
        String finalResult = tokenizer.decode(completeOutputArray);
        android.util.Log.i("performance", "Detokenization done in: " + (System.currentTimeMillis()-time) + "ms");
        android.util.Log.i("performance", "TRANSLATION DONE IN: " + (System.currentTimeMillis()-initTime) + "ms");
        return finalResult;
    }

    private OnnxTensor executeEncoder(int[] inputIDs, int[] attentionMask){
        //converte inputIDs e attentionMask in tensori
        OnnxTensor inputIDsTensor = convertIntArrayToTensor(inputIDs);
        OnnxTensor attentionMaskTensor = convertIntArrayToTensor(attentionMask);
        Map<String,OnnxTensor> input = new HashMap<String,OnnxTensor>();
        input.put("input_ids",inputIDsTensor);
        input.put("attention_mask",attentionMaskTensor);
        try {
            OrtSession.Result result = encoderSession.run(input);
            Optional<OnnxValue> output = result.get("last_hidden_state");
            //Object value = output.get().getValue();   //utile solo per il debug
            return (OnnxTensor) output.get();
        } catch (OrtException e) {
            throw new RuntimeException(e);
        }
    }

    private int executeDecoder(int[] inputIDs, int[] encoderAttentionMask, OnnxTensor encoderResult, int j){
        long time = System.currentTimeMillis();
        //converte inputIDs e attentionMask in tensori
        OnnxTensor inputIDsTensor;
        if(mode == SEAMLESS){   //se si usa seamless dobbiamo riempire gli input ids col padding per arrivare a 512
            int[] inputIDsPadded = new int[512];
            Arrays.fill(inputIDsPadded, 0);
            System.arraycopy(inputIDs, 0, inputIDsPadded, 0, inputIDs.length);
            inputIDsTensor = convertIntArrayToTensor(inputIDsPadded);
        }else if(mode == NLLB_FIXED){  //se si usa NLLB Fixed dobbiamo riempire gli input ids col padding per arrivare a 256
            int[] inputIDsPadded = new int[256];
            Arrays.fill(inputIDsPadded, 0);
            System.arraycopy(inputIDs, 0, inputIDsPadded, 0, inputIDs.length);
            inputIDsTensor = convertIntArrayToTensor(inputIDsPadded);
        }else if(mode == MADLAD_FIXED){  //se si usa NLLB Fixed dobbiamo riempire gli input ids col padding per arrivare a 256
            int[] inputIDsPadded = new int[128];
            Arrays.fill(inputIDsPadded, 1);
            System.arraycopy(inputIDs, 0, inputIDsPadded, 0, inputIDs.length);
            inputIDsTensor = convertIntArrayToTensor(inputIDsPadded);
        }else{   //per NLLB o Madlad non serve (hanno gli input di dimensione dinamica)
            inputIDsTensor = convertIntArrayToTensor(inputIDs);
        }
        OnnxTensor attentionMaskTensor = convertIntArrayToTensor(encoderAttentionMask);
        Map<String,OnnxTensor> input = new HashMap<String,OnnxTensor>();
        input.put("input_ids",inputIDsTensor);
        input.put("encoder_attention_mask",attentionMaskTensor);
        input.put("encoder_hidden_states",encoderResult);
        //se si usa Seamless o NLLB Fixed dobbiamo inserire come input anche la attention mask del decoder
        if(mode == SEAMLESS || mode == NLLB_FIXED || mode == MADLAD_FIXED){
            int[] decoderAttentionMask = new int[j+1];
            Arrays.fill(decoderAttentionMask, 1);
            int[] decoderAttentionMaskPadded;
            if(mode == SEAMLESS) {
                decoderAttentionMaskPadded = new int[512];
            }else if(mode == NLLB_FIXED){
                decoderAttentionMaskPadded = new int[256];
            }else{
                decoderAttentionMaskPadded = new int[128];
            }
            Arrays.fill(decoderAttentionMaskPadded, 0);
            System.arraycopy(decoderAttentionMask, 0, decoderAttentionMaskPadded, 0, decoderAttentionMask.length);
            OnnxTensor decoderAttentionMaskTensor = convertIntArrayToTensor(decoderAttentionMaskPadded);
            input.put("attention_mask",decoderAttentionMaskTensor);
        }
        android.util.Log.i("performance", "pre-execution of"+j+"th word done in: " + (System.currentTimeMillis()-time) + "ms");
        try {
            time = System.currentTimeMillis();
            OrtSession.RunOptions options = new OrtSession.RunOptions();
            OrtSession.Result result = decoderSession.run(input,options);
            android.util.Log.i("performance", "execution of"+j+"th word done in: " + (System.currentTimeMillis()-time) + "ms");
            time = System.currentTimeMillis();
            OnnxTensor output = (OnnxTensor) result.get("logits").get();
            float[] outputValues;
            if(mode == NLLB) {
                //questo serve a invocare il metodo privato getBuffer() di output, che restituisce un riferimento al Buffer,
                //facciamo questo perchè il metodo pubblico getFloatBuffer crea una copia del buffer prima di restituirlo
                //(allocando memoria), impiengando circa 30ms, che aumenta il tempo di esecuzione complessivo di circa il %
                Method method = output.getClass().getDeclaredMethod("getBuffer");
                method.setAccessible(true);
                FloatBuffer buffer = ((ByteBuffer) method.invoke(output)).asFloatBuffer();
                buffer.position(buffer.capacity() - 256206);
                outputValues = new float[256206];
                buffer.get(outputValues, 0, 256206);
            }else  if(mode == MADLAD) {
                //questo serve a invocare il metodo privato getBuffer() di output, che restituisce un riferimento al Buffer,
                //facciamo questo perchè il metodo pubblico getFloatBuffer crea una copia del buffer prima di restituirlo
                //(allocando memoria), impiengando circa 30ms, che aumenta il tempo di esecuzione complessivo di circa il %
                Method method = output.getClass().getDeclaredMethod("getBuffer");
                method.setAccessible(true);
                FloatBuffer buffer = ((ByteBuffer) method.invoke(output)).asFloatBuffer();
                buffer.position(buffer.capacity() - 256000);
                outputValues = new float[256000];
                buffer.get(outputValues, 0, 256000);
            }else if(mode == SEAMLESS || mode == NLLB_FIXED) {
                Method method = output.getClass().getDeclaredMethod("getBuffer");
                method.setAccessible(true);
                FloatBuffer buffer = ((ByteBuffer) method.invoke(output)).asFloatBuffer();
                buffer.position(256206*j);
                outputValues = new float[256206];
                buffer.get(outputValues, 0, 256206);
            }else if(mode == MADLAD_FIXED){
                Method method = output.getClass().getDeclaredMethod("getBuffer");
                method.setAccessible(true);
                FloatBuffer buffer = ((ByteBuffer) method.invoke(output)).asFloatBuffer();
                buffer.position(256000*(j-1));
                outputValues = new float[256000];
                buffer.get(outputValues, 0, 256000);
            }else{
                //il metodo classico per prelevare il risultato per seamless (più lento), non viene mai invocato, lo tengo
                // qui solo come riferimento e per il debug
                float[][][] value = (float[][][]) output.getValue();
                outputValues = value[0][j-1];
                int length = outputValues.length;
            }
            //si prende l'indice della logit col valore maggiore
            int max = getIndexOfLargest(outputValues);
            android.util.Log.i("performance", "post-execution of"+j+"th word done in: " + (System.currentTimeMillis()-time) + "ms");
            time = System.currentTimeMillis();
            result.close(); //serve a rilasciare la memoria occupata dal risultato (altrimenti di accumula e aumenta molto)
            android.util.Log.i("performance", "release ram of"+j+"th word done in: " + (System.currentTimeMillis()-time) + "ms");
            //System.gc();
            return max;
        } catch (OrtException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private OnnxTensor convertIntArrayToTensor(int[] intArray){
        //converte intArray in un array di long in modo da rendere gli inputIDs compatibili con l'encoder (che usa int da 64bit, cioè i long) (testare)
        long[] longArray = Arrays.stream(intArray).mapToLong(i -> i).toArray();
        //converte inputIDsLong e attentionMaskLong in tensori
        long[] shape = {1,intArray.length};
        //ByteBuffer byteBuffer = ByteBuffer.allocate(longArray.length * 8);
        //byteBuffer.order(ByteOrder.nativeOrder());
        LongBuffer longBuffer = LongBuffer.wrap(longArray);
        //longBuffer.put(longArray);
        try {
            return OnnxTensor.createTensor(onnxEnv,longBuffer,shape);
        } catch (OrtException e) {
            throw new RuntimeException(e);
        }
    }

    /*private OnnxTensor convertFloat32TensorToFloat16(OnnxTensor tensorFloat32){
        //questo serve a invocare il metodo privato getBuffer() di output, che restituisce un riferimento al Buffer,
        //facciamo questo perchè il metodo pubblico getFloatBuffer crea una copia del buffer prima di restituirlo
        //(allocando memoria), impiengando circa 30ms, che aumenta il tempo di esecuzione complessivo di circa il %
        Method method = null;
        try {
            method = tensorFloat32.getClass().getDeclaredMethod("getBuffer");
            method.setAccessible(true);
            FloatBuffer buffer = ((ByteBuffer) method.invoke(tensorFloat32)).asFloatBuffer();
            float[] outputValues = new float[256206];
            buffer.get(outputValues,0, 256206);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }*/

    public int getIndexOfLargest(float[] array){
        if (array == null || array.length == 0){
            return -1;
        } // null or empty
        int largest = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[largest]){
                largest = i;
            }
        }
        return largest; // position of the largest found
    }

    public Tokenizer getTokenizer() {
        return tokenizer;
    }

    public void setTokenizer(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public OrtEnvironment getOnnxEnv() {
        return onnxEnv;
    }

    public void setOnnxEnv(OrtEnvironment onnxEnv) {
        this.onnxEnv = onnxEnv;
    }

    public OrtSession getEncoderSession() {
        return encoderSession;
    }

    public void setEncoderSession(OrtSession encoderSession) {
        this.encoderSession = encoderSession;
    }

    public OrtSession getDecoderSession() {
        return decoderSession;
    }

    public void setDecoderSession(OrtSession decoderSession) {
        this.decoderSession = decoderSession;
    }
}
