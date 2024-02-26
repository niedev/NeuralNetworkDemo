package com.bluetooth.communicatorexample.translation;

import java.util.Arrays;

public class Tokenizer {
    public static final int NLLB = 0;
    public static final int NLLB_FIXED = 1;
    public static final int SEAMLESS = 2;
    public static final int MADLAD = 3;
    public static final int MADLAD_FIXED = 4;
    private SentencePieceProcessorJava spProcessor;
    private final int mode;
    //languages contiene la lista di tutte le lingue supportate, ordinate in base al loro ordine dell'ID (i loro ID sono consecutivi)
    private final String[] languagesNLLB = {"ace_Arab", "ace_Latn", "acm_Arab", "acq_Arab", "aeb_Arab", "afr_Latn", "ajp_Arab", "aka_Latn", "amh_Ethi", "apc_Arab", "arb_Arab", "ars_Arab", "ary_Arab", "arz_Arab", "asm_Beng", "ast_Latn", "awa_Deva", "ayr_Latn", "azb_Arab", "azj_Latn", "bak_Cyrl", "bam_Latn", "ban_Latn", "bel_Cyrl", "bem_Latn", "ben_Beng", "bho_Deva", "bjn_Arab", "bjn_Latn", "bod_Tibt", "bos_Latn", "bug_Latn", "bul_Cyrl", "cat_Latn", "ceb_Latn", "ces_Latn", "cjk_Latn", "ckb_Arab", "crh_Latn", "cym_Latn", "dan_Latn", "deu_Latn", "dik_Latn", "dyu_Latn", "dzo_Tibt", "ell_Grek", "eng_Latn", "epo_Latn", "est_Latn", "eus_Latn", "ewe_Latn", "fao_Latn", "pes_Arab", "fij_Latn", "fin_Latn", "fon_Latn", "fra_Latn", "fur_Latn", "fuv_Latn", "gla_Latn", "gle_Latn", "glg_Latn", "grn_Latn", "guj_Gujr", "hat_Latn", "hau_Latn", "heb_Hebr", "hin_Deva", "hne_Deva", "hrv_Latn", "hun_Latn", "hye_Armn", "ibo_Latn", "ilo_Latn", "ind_Latn", "isl_Latn", "ita_Latn", "jav_Latn", "jpn_Jpan", "kab_Latn", "kac_Latn", "kam_Latn", "kan_Knda", "kas_Arab", "kas_Deva", "kat_Geor", "knc_Arab", "knc_Latn", "kaz_Cyrl", "kbp_Latn", "kea_Latn", "khm_Khmr", "kik_Latn", "kin_Latn", "kir_Cyrl", "kmb_Latn", "kon_Latn", "kor_Hang", "kmr_Latn", "lao_Laoo", "lvs_Latn", "lij_Latn", "lim_Latn", "lin_Latn", "lit_Latn", "lmo_Latn", "ltg_Latn", "ltz_Latn", "lua_Latn", "lug_Latn", "luo_Latn", "lus_Latn", "mag_Deva", "mai_Deva", "mal_Mlym", "mar_Deva", "min_Latn", "mkd_Cyrl", "plt_Latn", "mlt_Latn", "mni_Beng", "khk_Cyrl", "mos_Latn", "mri_Latn", "zsm_Latn", "mya_Mymr", "nld_Latn", "nno_Latn", "nob_Latn", "npi_Deva", "nso_Latn", "nus_Latn", "nya_Latn", "oci_Latn", "gaz_Latn", "ory_Orya", "pag_Latn", "pan_Guru", "pap_Latn", "pol_Latn", "por_Latn", "prs_Arab", "pbt_Arab", "quy_Latn", "ron_Latn", "run_Latn", "rus_Cyrl", "sag_Latn", "san_Deva", "sat_Beng", "scn_Latn", "shn_Mymr", "sin_Sinh", "slk_Latn", "slv_Latn", "smo_Latn", "sna_Latn", "snd_Arab", "som_Latn", "sot_Latn", "spa_Latn", "als_Latn", "srd_Latn", "srp_Cyrl", "ssw_Latn", "sun_Latn", "swe_Latn", "swh_Latn", "szl_Latn", "tam_Taml", "tat_Cyrl", "tel_Telu", "tgk_Cyrl", "tgl_Latn", "tha_Thai", "tir_Ethi", "taq_Latn", "taq_Tfng", "tpi_Latn", "tsn_Latn", "tso_Latn", "tuk_Latn", "tum_Latn", "tur_Latn", "twi_Latn", "tzm_Tfng", "uig_Arab", "ukr_Cyrl", "umb_Latn", "urd_Arab", "uzn_Latn", "vec_Latn", "vie_Latn", "war_Latn", "wol_Latn", "xho_Latn", "ydd_Hebr", "yor_Latn", "yue_Hant", "zho_Hans", "zho_Hant", "zul_Latn"};
    private final String[] languagesSeamless = {"ace", "ace_Latn", "acm", "acq", "aeb", "afr", "ajp", "aka", "amh", "apc", "arb", "ars", "ary", "arz", "asm", "ast", "awa", "ayr", "azb", "azj", "bak", "bam", "ban", "bel", "bem", "ben", "bho", "bjn", "bjn_Latn", "bod", "bos", "bug", "bul", "cat", "ceb", "ces", "cjk", "ckb", "crh", "cym", "dan", "deu", "dik", "dyu", "dzo", "ell", "eng", "epo", "est", "eus", "ewe", "fao", "pes", "fij", "fin", "fon", "fra", "fur", "fuv", "gla", "gle", "glg", "grn", "guj", "hat", "hau", "heb", "hin", "hne", "hrv", "hun", "hye", "ibo", "ilo", "ind", "isl", "ita", "jav", "jpn", "kab", "kac", "kam", "kan", "kas", "kas_Deva", "kat", "knc", "knc_Latn", "kaz", "kbp", "kea", "khm", "kik", "kin", "kir", "kmb", "kon", "kor", "kmr", "lao", "lvs", "lij", "lim", "lin", "lit", "lmo", "ltg", "ltz", "lua", "lug", "luo", "lus", "mag", "mai", "mal", "mar", "min", "mkd", "plt", "mlt", "mni", "khk", "mos", "mri", "zsm", "mya", "nld", "nno", "nob", "npi", "nso", "nus", "nya", "oci", "gaz", "ory", "pag", "pan", "pap", "pol", "por", "prs", "pbt", "quy", "ron", "run", "rus", "sag", "san", "sat", "scn", "shn", "sin", "slk", "slv", "smo", "sna", "snd", "som", "sot", "spa", "als", "srd", "srp", "ssw", "sun", "swe", "swh", "szl", "tam", "tat", "tel", "tgk", "tgl", "tha", "tir", "taq", "taq_Tfng", "tpi", "tsn", "tso", "tuk", "tum", "tur", "twi", "tzm", "uig", "ukr", "umb", "urd", "uzn", "vec", "vie", "war", "wol", "xho", "ydd", "yor", "yue", "cmn", "cmn_Hant", "zul"};
    private final int DICTIONARY_LENGTH = 256000;

    public Tokenizer(String vocab_file, int mode) {
        spProcessor = new SentencePieceProcessorJava();
        spProcessor.Load(vocab_file);
        this.mode = mode;
    }

    public TokenizerResult tokenize(String srcLanguage, String tgtLanguage, String text) {
        //per madlad di aggiunge <2tgtLanguage> all'inizio del testo (srcLanguage non viene specificata)
        if (mode == MADLAD || mode==MADLAD_FIXED){
            text = "<2"+tgtLanguage+"> "+text;
        }
        //si traduce il testo in ids tramite sentencepiece
        int[] ids = spProcessor.encode(text);

        /*Il dizionario di NLLBTokenizer ha una corrispondenza diversa per i token identificati dai primi 4 valori degli IDs (da 0 a 3),
        inoltre per gli altri ID ha un valore uguale a quelli del dizionario che abbiamo passato a sentencepiece ma con una aggiunta di 1
        (idNLLB = idSentencePiece + 1), quindi adesso facciamo gli aggiustamenti necessari*/
        /*Per il dizionario di SeamlessTokenizer vale la stessa cosa ma il + 1 vale anche per i primi 4 valori degli IDs (il valore 0, in più rappresenta il padding)*/
        if(mode != MADLAD && mode != MADLAD_FIXED) {   //MADLAD ha una corrispondenza univoca con il dizionario di sentencepiece.
            for (int i = 0; i < ids.length; i++) {
                //aggiungiamo 1 a ogni elemento di ids
                ids[i] = ids[i] + 1;
                //sostituiamo i valori da 0 a 3 con quelli corretti per NLLBTokenizer (per Seamless i valori sono già tutti corretti)
                if (mode == NLLB || mode == NLLB_FIXED) {
                    switch (ids[i]) {
                        case 1: {
                            ids[i] = 3;
                            break;
                        }
                        case 2: {
                            ids[i] = 0;
                            break;
                        }
                        case 3: {
                            ids[i] = 2;
                            break;
                        }
                    }
                }
            }
        }

        //aggiungiamo <eos> alla fine e srcLanguage all'inizio (srcLanguage non si aggiunge per Madlad)
        int eos = PieceToID("</s>");
        int srcLanguageID = getLanguageID(srcLanguage);
        int[] idsExtended;
        if(mode != MADLAD && mode != MADLAD_FIXED) {
            idsExtended = new int[ids.length + 2];
            System.arraycopy(ids, 0, idsExtended, 1, ids.length);
            idsExtended[idsExtended.length - 1] = eos;
            idsExtended[0] = srcLanguageID;
        }else{
            idsExtended = new int[ids.length + 1];
            System.arraycopy(ids, 0, idsExtended, 0, ids.length);
            idsExtended[idsExtended.length - 1] = eos;
        }


        //creiamo l'attention mask
        int[] attentionMask = new int[idsExtended.length];
        Arrays.fill(attentionMask, 1);

        /*A differenza del codice scritto alla lavagna e del codice di NLLBTokenizer, dove usano l'encode di sentencepiece
         * per dividere l'input in token e poi traducono tramite PieceToID un token alla volta (aggiungendo ogni volta 1 e sostituendo
         * i token identificati con id <= 3 con il valore corretto per NLLBTokenizer), io ho usato il metodo decode per tradurre
         * direttamente in ID i token ricavati dall'input e poi ho applicato le modifiche necessarie, questo approccio è più veloce,
         * ma si può fare solo se nell'input non sono presenti token <pad> (presente nel dizionario di NLLBTokenizer ma non in quello di
         * sentencepiece che gli abbiamo fornito), ma nel nostro caso questo token non dovrebbe mai comparire nell'input,
         * quindi il mio approccio dovrebbe andare bene.*/

        if(mode == NLLB || mode == MADLAD) {
            return new TokenizerResult(idsExtended, attentionMask);
        }else if(mode == SEAMLESS){
            //per seamless gli ids devono essere sempre 512, riempiamo gli ids vuoti con il padding (0)
            int[] idsPadded = new int[512];
            Arrays.fill(idsPadded, 0);
            System.arraycopy(idsExtended, 0, idsPadded, 0, idsExtended.length);
            //per seamless anche la attention mask deve avere sempre lunghezza 512, riempiamo il resto con 0
            int[] attentionMaskPadded = new int[512];
            Arrays.fill(attentionMaskPadded, 0);
            System.arraycopy(attentionMask, 0, attentionMaskPadded, 0, attentionMask.length);
            return new TokenizerResult(idsPadded, attentionMaskPadded);
        }else if(mode == NLLB_FIXED){
            //per NLLB Fixed gli ids devono essere sempre 256, riempiamo gli ids vuoti con il padding (0)
            int[] idsPadded = new int[256];
            Arrays.fill(idsPadded, 0);
            System.arraycopy(idsExtended, 0, idsPadded, 0, idsExtended.length);
            //anche la attention mask deve avere sempre lunghezza 256, riempiamo il resto con 0
            int[] attentionMaskPadded = new int[256];
            Arrays.fill(attentionMaskPadded, 0);
            System.arraycopy(attentionMask, 0, attentionMaskPadded, 0, attentionMask.length);
            return new TokenizerResult(idsPadded, attentionMaskPadded);
        }else{
            //per Madlad Fixed gli ids devono essere sempre 128, riempiamo gli ids vuoti con il padding (1)
            int[] idsPadded = new int[128];
            Arrays.fill(idsPadded, 1);
            System.arraycopy(idsExtended, 0, idsPadded, 0, idsExtended.length);
            //anche la attention mask deve avere sempre lunghezza 128, riempiamo il resto con 0
            int[] attentionMaskPadded = new int[128];
            Arrays.fill(attentionMaskPadded, 0);
            System.arraycopy(attentionMask, 0, attentionMaskPadded, 0, attentionMask.length);
            return new TokenizerResult(idsPadded, attentionMaskPadded);
        }
    }

    public int PieceToID(String token){
        if(mode == NLLB || mode == NLLB_FIXED || mode==MADLAD || mode==MADLAD_FIXED) {
            return spProcessor.PieceToID(token);
        }else{
            return spProcessor.PieceToID(token)+1;
        }
    }

    public int getLanguageID(String language){
        if(mode == NLLB || mode == NLLB_FIXED) {
            for (int i = 0; i < languagesNLLB.length; i++) {
                if (languagesNLLB[i].equals(language)) {
                    return DICTIONARY_LENGTH + i + 1;
                }
            }
        }else{
            for (int i = 0; i < languagesSeamless.length; i++) {
                if (languagesSeamless[i].equals(language)) {
                    return DICTIONARY_LENGTH + i + 1;
                }
            }
        }
        return -1;
    }

    public String decode(int[] ids) {
        //aggiungere descrizione
        String output = "";
        if(mode != MADLAD && mode != MADLAD_FIXED) {
            for (int i = 0; i < ids.length; i++) {
                if (ids[i] < DICTIONARY_LENGTH && ids[i] > 3) {   //questo controllo salta i token speciali e quelli che sentencepiece non ha nel dizionario (come le lingue)
                    output = output.concat(spProcessor.IDToPiece(ids[i] - 1));
                }
            }
        }else{
            for (int i = 0; i < ids.length; i++) {
                if (ids[i] < DICTIONARY_LENGTH && ids[i] > 3) {   //questo controllo salta i token speciali e quelli che sentencepiece non ha nel dizionario (come le lingue)
                    output = output.concat(spProcessor.IDToPiece(ids[i]));
                }
            }
        }
        return output;
    }
}
