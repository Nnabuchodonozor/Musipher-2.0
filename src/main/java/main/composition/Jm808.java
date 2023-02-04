package main.composition;

import jm.JMC;
import jm.music.data.*;
import jm.music.tools.*;
import jm.midi.*;
import jm.util.*;

/**
 * An example which generates a drum kit pattern
 * and writes to a MIDI file called kit.mid
 * This version uses static methods in the one class.
 * @author Andrew Brown
 */
public final class Jm808 implements JMC{
	private int length = 16;
	Score pattern1 = new Score("jm-808"); 
	// 25 = TR808 General MIDI kit. 9 = MIDI channel 10.
	Part drums = new Part("Drums",25, 9);
	Phrase phrBD = new Phrase();
	Phrase phrSD = new Phrase();
	Phrase phrHH = new Phrase();
	CPhrase phrEnd = new CPhrase();
		
	public static void main(String[] args){
		new Jm808();
	}
    
    /* constructor */
	public Jm808() {	
		//Show GUI
//		new Jm808_GUI(this);
		// show playback window for QuickTime
		//QTPlayer.display(pattern1, 0, 100);
		this.createScore(10);
		this.saveScore("drums1.mid");

	}
	
	public void createScore(int loopNum) {
		//clean out the score and part for reuse
		pattern1.empty();
		drums.empty();
	
		//create the appropriate length phrases filled up with note objects
		int pitch = 36;
		phrBD = phraseFill(length, pitch);
		pitch = 38;	
		phrSD = phraseFill(length, pitch);
		pitch = 42;
		phrHH = phraseFill(length, pitch);
		phrEnd = EndPattern();
		
		// get the on-off values
		for (int i=0;i<length;i++) {
			if (Math.random() > 0.3) {
				phrBD.getNote(i).setPitch(REST);
			} else phrBD.getNote(i).setPitch(36);
			if (Math.random() > 0.5) {
				phrSD.getNote(i).setPitch(REST);
			} else phrSD.getNote(i).setPitch(38);
			if (Math.random() > 0.8) {
				phrHH.getNote(i).setPitch(REST);
			} else phrHH.getNote(i).setPitch(42);
		}	
		
		// loop the drum pattern
		Mod.repeat(phrBD, loopNum);
		Mod.repeat(phrSD, loopNum);
		Mod.repeat(phrHH, loopNum);
		
		// add phrases to the instrument (part)
		drums.addPhrase(phrBD);
		drums.addPhrase(phrSD);
		drums.addPhrase(phrHH);
		drums.addCPhrase(phrEnd);
		
		// add the drum part to a score.
		pattern1.addPart(drums);
	}
	
	public void saveScore(String fileName) {
		// write the score to a MIDIfile
		Write.midi(pattern1, fileName);
	}
	
	private static Phrase phraseFill(int length, int pitch) {
		Phrase phrase = new Phrase(0.0);
		for(int i=0;i<length;i++){
			Note note = new Note(pitch, SQ, (int)(Math.random()*70 + 50));
			phrase.addNote(note);
		}
		return phrase;
	}
	
	private static CPhrase EndPattern() {
		// make crash ending
		CPhrase cphrase = new CPhrase();
		int[] pitchArray1a = {36,49}; // kick and crash cymbal
		cphrase.addChord(pitchArray1a, SB);
		
		return cphrase;
	}
		
}