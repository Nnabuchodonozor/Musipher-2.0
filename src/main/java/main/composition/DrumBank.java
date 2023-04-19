package main.composition;

public class DrumBank {
    public DrumBank() {
    }
// empty drumset  "................................................................"
    private String[] r1 = new String[] { "o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.",
                                         ".s.ss.s.ss.s.ss..s.s..s.ss.s.s...s.ss.s.ss.s.ss..s.s..s.ss.s.s..",
        ".T....T..T.T....T....T..T.T....T....T....T..T.T....T....T....T..",
        ".......c.......c.......c.......c.......c.......c.......c.......c",
        "NN..NN..NN..NN..NN..NN..NN..NN..NN..NN..NN..NN..NN..NN..NN..NN."
};

    private String[] r2 = new String[] { "o..o....oo......o..o....oo......o..o....oo......o..o....oo......",
            ".s...s....s..s...s...s....s..s...s...s....s..s...s...s....s..s..",
            "...T.......T.......TT......T...................T...TT...........",
            "...............c...............c...............c...............c",
            "..N...N..N....N...N...N..N....N...N...N..N....N...N...N..N....N." };

    private String[] r3 = new String[] { "o.......o.......o.......o.......o.......o.......o.......o.......",
            "...s.......s.......s.......s.......s.......s.......s.......s....",
            ".....T.......TT..................T..............................",
            "...............................c...............................c",
            ".N.......N...........N.......N...N.......N...........N.......N.." };



//    public static

    public String[] fillLayers(String[] layers, int version){
        String[] finalLayers = new String[layers.length];
        int layerLength=0;
        for(int i = 0; i < layers.length; i++){
            StringBuilder newLayer = new StringBuilder();
            char[] currentLayer = new char[1];
            switch (version){
                case 0:
                    currentLayer = r1[i].toCharArray();
                    layerLength=64;
                    break;
                case 1:
                     currentLayer = r2[i].toCharArray();
                     layerLength=32;
                    break;
                case 2:
                    currentLayer = r3[i].toCharArray();
                    layerLength=16;
                    break;
                default:
                    break;
            }
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
//            finalLayers[i] = "...................................................." + newLayer.toString();
                        finalLayers[i] = "........" + newLayer.toString();

        }
        return finalLayers;
    }


}
