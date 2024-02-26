#include <jni.h>
#include <stdio.h>
#include <string>
#include "sentencepiece_processor.h"

using namespace sentencepiece;

std::string jstringToString(JNIEnv* env, jstring jstr);
jintArray intVectorTojintArray(JNIEnv* env, std::vector<int> vector);
std::vector<int> jintArrayTointVector(JNIEnv* env, jintArray jarray);
jstring stringToJstring(JNIEnv* env, std::string str);

extern "C" jlong
Java_com_bluetooth_communicatorexample_translation_SentencePieceProcessorJava_SentencePieceProcessorNative(JNIEnv* env,jobject){
    return (long)(new SentencePieceProcessor());
}

extern "C" void
Java_com_bluetooth_communicatorexample_translation_SentencePieceProcessorJava_LoadNative(JNIEnv* env,jobject,jlong processor, jstring vocab_file){
    SentencePieceProcessor *proc = (SentencePieceProcessor *)processor;
    std::string vocab_file_string = jstringToString(env,vocab_file);
    (*proc).Load(vocab_file_string);  //per qualche motivo non funziona
}

extern "C" jintArray
Java_com_bluetooth_communicatorexample_translation_SentencePieceProcessorJava_encodeNative(JNIEnv* env,jobject,jlong processor, jstring text){
    SentencePieceProcessor *proc = (SentencePieceProcessor *)processor;
    std::vector<int> ids(1024,0);  //decidere meglio la dimensione dell'array, in base al numero massimo di input_ids
    std::string string = jstringToString(env,text);
    (*proc).Encode(string,&ids);
    return intVectorTojintArray(env,ids);;  //vedere se, nel caso genererà errori, andrà convertito in qualche modo
}

extern "C" jint
Java_com_bluetooth_communicatorexample_translation_SentencePieceProcessorJava_PieceToIDNative(JNIEnv* env,jobject,jlong processor, jstring token){
    SentencePieceProcessor *proc = (SentencePieceProcessor *)processor;
    return (*proc).PieceToId(jstringToString(env,token));
}

extern "C" jstring
Java_com_bluetooth_communicatorexample_translation_SentencePieceProcessorJava_IDToPieceNative(JNIEnv* env,jobject,jlong processor, jint id){
    //questo metodo non funziona e non ho capito perchè, per ora tradurrò manualmente un token alla volta col metodo IdToPiece.
    SentencePieceProcessor *proc = (SentencePieceProcessor *)processor;
    int idConverted = (int) id;
    std::string outputString = (*proc).IdToPiece(idConverted);
    return stringToJstring(env,outputString);  //vedere se, nel caso genererà errori, andrà convertito in qualche modo
}

extern "C" jstring
Java_com_bluetooth_communicatorexample_translation_SentencePieceProcessorJava_decodeNative(JNIEnv* env,jobject,jlong processor, jintArray ids){
    //questo metodo non funziona, per ora tradurrò manualmente un token alla volta col metodo IdToPiece.
    /*per farlo funzionare, prima di passare gli ids a questo metodo dovrei eliminare tutti gli id dei simboli speciali
     * e i simboli contenenti codici di lingue (è questo che non fa funzionare il metodo, perchè quegli id non appartengono al dizionario di
     * sentencepiece)*/
    SentencePieceProcessor *proc = (SentencePieceProcessor *)processor;
    std::vector<int> idsConverted = jintArrayTointVector(env,ids);
    //std::string outputString((int) idsConverted.size(),'x');
    std::string outputString = (*proc).DecodeIds(idsConverted);
    std::string test = proc ->IdToPiece(idsConverted[0]);
    std::string test2 = proc ->IdToPiece(2);
    return stringToJstring(env,outputString);  //vedere se, nel caso genererà errori, andrà convertito in qualche modo
}



std::string jstringToString(JNIEnv* env, jstring jstr){
    jboolean isCopy;
    const char *convertedValue = (env)->GetStringUTFChars(jstr, &isCopy);
    //size_t length = (size_t) env->GetStringLength(jstr);
    std::string string = std::string(convertedValue);
    env->ReleaseStringUTFChars(jstr,convertedValue);
    return string;
}

jintArray intVectorTojintArray(JNIEnv* env, std::vector<int> vector){
    jintArray jarray = env->NewIntArray(vector.size());
    env->SetIntArrayRegion(jarray, 0, vector.size(), reinterpret_cast<jint*>(vector.data()));
    return jarray;
}

std::vector<int> jintArrayTointVector(JNIEnv* env, jintArray jarray){
    jsize size = env->GetArrayLength(jarray);
    std::vector<int> vector (size);
    env->GetIntArrayRegion(jarray, jsize{0}, size, &vector[0]);
    std::vector<int> vectorFinal(vector.begin(),vector.end());

    /*for (int i=0; i<vector.size();i++){

    }*/
    /*jboolean isCopy;
    int * array = env->GetIntArrayElements(jarray,&isCopy);
    //std::vector<int> vector((std::begin(array), std::end(array)));
    std::vector<int> vector(array);
    env->ReleaseIntArrayElements(array);*/
    return vectorFinal;
}

jstring stringToJstring(JNIEnv* env, std::string str){
    const char* chars = str.data();
    return env->NewStringUTF(chars);
}