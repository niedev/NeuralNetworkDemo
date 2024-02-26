This is just a demo to test inference of Madlad and other translation models in android.

app/src/main/java/com/bluetooth/communicatorexample/translation/Translator.java is the class in charge of the inference of the Madlad model, the model needs to be placed in the phone's 
download/models/ folder, the name of the model needs to be specified in Translator's constructor, called in app/src/main/java/com/bluetooth/communicatorexample/fragments/ConversationFragment.java.

The Translator object is created (and the models loaded) when the send arrow is clicked without any message inserted, when the loading is finished a Toast notification will appear and you can start writing something
in english, and when you click the send buttom the translation will be done (in italian) by the translator and will appear as a message received in the app.

All models are in releases
