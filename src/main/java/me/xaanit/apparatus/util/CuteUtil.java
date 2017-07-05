package me.xaanit.apparatus.util;

import java.util.Random;

public class CuteUtil extends Util {
    final static String[] HUG_GIFS = {"https://cdn.discordapp.com/attachments/246007128987271169/326917145181159425/tumblr_oltayyHynP1sy5k7wo1_500.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326917138600427533/tumblr_n1hz1eMd6S1smz4kto1_500.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326917134120648704/tumblr_mch1saeqgl1qbvovho1_500.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326917110951313408/tumblr_marnhlHfUC1rxzudco1_500.gif",
            "https://cdn.discordapp.com/attachments/246007128987271169/326917104492085249/tenor.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326917082254147584/tenor_1.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326917070300381184/large.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326917028575445003/giphy.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326917022153834497/giphy_2.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326916990205952001/giphy_1.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326916978843582474/1461073447-335af6bf0909c799149e1596b7170475.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326916968470806528/91efba8196908bc01fc9c97e50407d1f02319f35_hq.gif"};

    final static String[] CUDDLE_GIFS = {"https://cdn.discordapp.com/attachments/246007128987271169/326921776854597632/tumblr_o8o4f8dxbs1u7esouo1_400.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326921773193101322/tenor_2.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326921768562589706/giphy_4.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326921762094841876/giphy_3.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326921752703664128/a8e1ce55aab981b76e69baf9f49de3b0.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326921733640814594/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f776174747061642d6d656469612d736572766963652f53746f.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326921697745960961/1461068547-d8d6dc7c2c74e02717c5decac5acd1c7.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326921690720370688/80e.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326921678821130251/51Q4oAg.gif"};

    final static String[] KISSES_GIFS = {"https://cdn.discordapp.com/attachments/246007128987271169/326925322689970176/8ed246945844cae5c5a1b80ef610cd47.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326925328528310272/6506423.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326925332374355969/giphy_5.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326925362380406804/giphy_7.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326925366193291276/giphy_8.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326925374636294144/large_1.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326925382500483072/large_2.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326925389647708161/tenor_3.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326925399273504768/tenor_4.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326925404176908288/tumblr_mj1h1g4WF91s5mcbxo1_500.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326925411386785792/tumblr_mjorciyHyZ1qa94xto1_500.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326925415778222080/tumblr_mze0zr0XRg1rveihgo1_500.gif"};

    final static String[] HEADPAT_GIFS = {"https://cdn.discordapp.com/attachments/246007128987271169/326927551689916416/1x6lW.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326927553900183562/9912a106df20e91d2681f7d0e3c8237c06586eab_hq.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326927556672749578/Head_071dfb_6102763.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326927558547603456/Head_ac555b_6102763.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326927560648818699/L8voKd1.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326927563165532163/laEy6LU.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326927566696873993/source.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326927569217912832/tumblr_n4rqmsZiZO1qbvovho1_500.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326927571314802688/tumblr_npeccq4y3H1rzk6edo1_500.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326927573885911040/tumblr_o2js2xhINq1tydz8to1_500.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326927598418657310/tumblr_od76a3qX4i1s9gdrpo1_500.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326927606165536779/tumblr_ok1oplyzSF1r0tp5lo1_500.gif"};

    final static String[] DANCING_GIFS = {"https://cdn.discordapp.com/attachments/246007128987271169/326932703742132224/anime-kawaii-cute-dance-animated-gif-image-2.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326932707730915328/tumblr_n21ihbNVIo1smwaj9o1_500.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326933566955192330/1z08J.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326933568209158154/2c6d619bf02f2bb9cf96651c1e877f65.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326933571258548254/1362005636694.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326933576094580746/Chibi_Sakura_Trick_Dance.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326933578178887690/Dance_016.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326933582671249428/LZ38e38.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326933584713875456/original.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326933585594679307/tenor_5.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326933590116139008/tenor_6.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326933619094323201/1408724821852.gif"};

    final static String[] HOLDING_HANDS_GIFS = {"https://cdn.discordapp.com/attachments/246007128987271169/326942185402400768/8f70714a8fc965fdcae4d7d11bc4c683.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326942189777059840/d84da12818a8e589d57e0abca64e8559.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326942191349923840/giphy_9.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326942195565461505/large_4.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326942195179454474/large_3.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326942198090301440/original_1.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326942200095178752/tumblr_ls2n1uGZqW1qhridko1_400.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326942202649509888/tumblr_mormc7ZEZf1r8oyaho1_500.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326942204851519488/tumblr_nnac538TyT1ussuioo1_500.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326942206831099904/tumblr_nvzc8fCjUQ1tjszhdo1_500.gif", "https://cdn.discordapp.com/attachments/246007128987271169/326942231984340992/tumblr_o936fi1ms51uayyjso1_500.gif"};
    private static final Random random = new Random();

    public static String getRandomGif(GifType type) {
        return type.list[random.nextInt(type.list.length)];
    }


    public enum GifType {
        HOLDING_HANDS(HOLDING_HANDS_GIFS),
        DANCING(DANCING_GIFS),
        HEAD_PAT(HEADPAT_GIFS),
        KISS(KISSES_GIFS),
        CUDDLE(CUDDLE_GIFS),
        HUG(HUG_GIFS);

        private String[] list;

        GifType(String[] list) {
            this.list = list;
        }

    }
}
