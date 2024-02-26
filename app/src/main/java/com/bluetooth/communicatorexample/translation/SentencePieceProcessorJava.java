package com.bluetooth.communicatorexample.translation;

public class SentencePieceProcessorJava {
    //array contenente i simboli che sentencepiece non traduce nel modo corretto (il loro indice equivale al loro ID)
    private final String[] specialTokens = {"<s>", "<pad>", "</s>", "<unk>"};

    //Used to load SentencePieceProcessorInterface.cpp on application startup
    static {
        //System.loadLibrary("SentencePieceProcessorInterface");
        System.loadLibrary("sentencepiece");
        //System.loadLibrary("SentencePieceProcessorInterface.cpp");
    }

    private final long spProcessorPointer;

    public SentencePieceProcessorJava(){
        spProcessorPointer=SentencePieceProcessorNative();
    }

    public void Load(String vocab_file){
        LoadNative(spProcessorPointer,vocab_file);
    }
    public int[] encode(String text){
        return encodeNative(spProcessorPointer,text);
    }
    public int PieceToID(String token){
        for (int i=0; i < specialTokens.length; i++){
            if(token.equals(specialTokens[i])){
                return i;  //specialTokens contenente i simboli che sentencepiece non traduce nel modo corretto (il loro indice equivale al loro ID)
            }
        }
        return PieceToIDNative(spProcessorPointer,token)+1;
    }

    public String IDToPiece(int id){
        return IDToPieceNative(spProcessorPointer,id);
    }

    public String decode(int[] ids){
        return decodeNative(spProcessorPointer,ids);
    }

    private native long SentencePieceProcessorNative();
    private native void LoadNative(long processor, String vocab_file);
    private native int[] encodeNative(long processor, String text);
    private native int PieceToIDNative(long processor, String token);
    public native String IDToPieceNative(long processor, int id);
    private native String decodeNative(long processor, int[] ids);
}
