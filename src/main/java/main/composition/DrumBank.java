package main.composition;

public class DrumBank {
    public DrumBank() {
    }
// empty drumset  "................................................................"
    private String[] r1 = new String[] { "o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.",
                                         ".s.ss.s.ss.s.ss..s.s..s.ss.s.s..",
        "................................",
        "................................",
        "................................" };

    private String[] r2 = new String[] { "o..oo..o..oo.oo.o..oo..o..oo.oo.",
            ".s.ss.s.ss.s.ss..s.s..s.ss.s..s.",
            "................",
            "................",
            "................" };

    private String[] r3 = new String[] { "o..oo..o..oo.oo.o..oo..o..oo.oo.",
            ".s.ss.s.ss.s.ss..s.s..s.ss.s..s.",
            "................",
            "................",
            "................" };

    private String[] r4 = new String[] { "o..oo..o..oo.oo.o..oo..o..oo.oo.",
            ".s.ss.s.ss.s.ss..s.s..s.ss.s..s.",
            "................",
            "................",
            "................" };

    private String[] r5 = new String[] { "o..oo..o..oo.oo.o..oo..o..oo.oo.",
            ".s.ss.s.ss.s.ss..s.s..s.ss.s..s.",
            "................",
            "................",
            "................" };


    private String[] r6 = new String[] { "o..oo..o..oo.oo.o..oo..o..oo.oo.",
            ".s.ss.s.ss.s.ss..s.s..s.ss.s..s.",
            "................",
            "................",
            "................" };

    private String[] r7 = new String[] { "o..oo..o..oo.oo.o..oo..o..oo.oo.",
            ".s.ss.s.ss.s.ss..s.s..s.ss.s..s.",
            "................",
            "................",
            "................" };
//    public static

    public String[] fillLayers(String[] layers){
        String[] finalLayers = new String[layers.length];
        for(int i = 0; i < layers.length; i++){
            StringBuilder newLayer = new StringBuilder();
            char[] currentLayer = r1[i].toCharArray();
            char[] inputLayer = layers[i].toCharArray();
            int k = 0;

            for (int j = 0; j < inputLayer.length; j++){
                while (currentLayer[k]=='.' ){
                    newLayer.append('.');
                    k++;
                }
                newLayer.append(inputLayer[j]);
                k++;
            }
            while(newLayer.length()< 64){
                newLayer.append(".");
            }
            finalLayers[i] = newLayer.toString();
        }
        return finalLayers;
    }


}
