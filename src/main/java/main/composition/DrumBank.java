package main.composition;

public class DrumBank {
    public DrumBank() {
    }
// empty drumset "................"
    private String[] r1 = new String[] { "o..oo..o..oo.oo.o..oo..o..oo.oo.",
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
            finalLayers[i] = newLayer.toString();
        }
        return finalLayers;
    }


}
