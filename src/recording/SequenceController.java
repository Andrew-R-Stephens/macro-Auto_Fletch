package recording;

import java.util.ArrayList;
import java.util.Random;

import exceptions.EmptySequenceException;
import exceptions.SequenceDoesNotExistException;

public class SequenceController {

	private Sequences sequences = null;
	
	private long duration = 0;
	
	public SequenceController() {
		
		sequences = new Sequences();
		
	}
	
	public boolean addToTemp(EventAction e) {
		
		return sequences.addToTemp(e);
		
	}
	
	public boolean addTempToList() throws EmptySequenceException {
		
		return sequences.addTempToList();
		
	}
	
	public Sequence getTemp() {
		
		return sequences.getTemp();
		
	}
	
	public ArrayList<Sequence> getSequenceRandomized() {
		
		ArrayList<Sequence> temp = new ArrayList<Sequence>();
		
		for(int i = 0; i < sequences.getSize(); i++) 
			temp.add(sequences.getSequenceList().get(i));
		
		ArrayList<Sequence> pattern = new ArrayList<Sequence>();
		
		while(!temp.isEmpty()) {
			
			int loc = new Random().nextInt(temp.size());
			pattern.add(temp.get(loc));
			temp.remove(loc);
			
		}
		
		return pattern;
		
	}

	public long analyzeTime() throws InterruptedException {
		
		return (duration = sequences.analyzeTime()); 
		
	}
	
	public Sequence removePrevSeq() throws EmptySequenceException, SequenceDoesNotExistException {
		
		return sequences.removeLastEventSequence();
		
	}

	public EventAction removePrevEvnt() throws EmptySequenceException, SequenceDoesNotExistException {
		
		return sequences.removeLastEventAction();
		
	}	

	public void setInterimDuration(long duration) {
	
		for(Sequence s: sequences.getSequenceList())
			s.setDuration(duration);
		
	}

	public long getDuration() {
		
		return duration;
		
	}
	
	public String toString() {
		
		return sequences.toString();
		
	}
}
