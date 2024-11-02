package recording;

import java.util.ArrayList;

import exceptions.EmptySequenceException;
import exceptions.SequenceDoesNotExistException;

public class Sequences {

	private Sequence temp = null;
	private ArrayList<Sequence> sequences = null;
	
	long duration = 0;
	
	public Sequences() {
		
		sequences = new ArrayList<Sequence>();
		
	}
	
	public boolean addTempToList() throws EmptySequenceException {
		
		if(temp == null)
			throw new EmptySequenceException("Cannot store sequence: Sequence is empty!");
		
		Sequence t = new Sequence();
		for(EventAction e: temp.getAll())
			t.add(e);
		
		boolean success = sequences.add(temp);
		temp = null;
		return success;
		
	}
	
	public boolean addToTemp(EventAction e) {
		
		if(temp == null)
			temp = new Sequence();
		
		if(temp.getSize() == 0)
			return temp.add(e);
		else {
			e.addPreviousTime(temp.get((temp.getSize()-1)).getTime());
			return temp.add(e);
			
		}
	}
	
	public Sequence removeLastEventSequence() throws EmptySequenceException, SequenceDoesNotExistException {
		
		if(sequences == null)
			throw new SequenceDoesNotExistException("Cannot remove last sequence. Sequence list is null!");
		if(sequences.isEmpty())
			throw new EmptySequenceException("Cannot remove. Last Event Sequence is empty.");
		
		return sequences.remove(sequences.size()-1);
		
	}
	
	public Sequence getSequenceAt(int index) {
		
		return sequences.get(index);
		
	}
	
	public EventAction removeLastEventAction() throws EmptySequenceException, SequenceDoesNotExistException {
		
		if(temp == null)
			throw new SequenceDoesNotExistException("Cannot remove last event action. Sequence is null!");
		if(temp.isEmpty())
			throw new EmptySequenceException("Cannot remove last event action. Sequence is empty!");
		
		return temp.removeLast();

	}
	
	public ArrayList<Sequence> getSequenceList(){
		
		return sequences;
		
	}
	
	public Sequence getTemp(){
		
		return temp;
		
	}
	
	public int getSize() {
		
		return sequences.size();
		
	}
	
	public boolean isEmpty() {
		
		return sequences.isEmpty();
		
	}
	
	public long analyzeTime() throws InterruptedException {
		
		for(Sequence s: sequences)
			duration += s.analyzeTime();
		
		return duration;
	}
	
	public String toString() {
		
		String s = "Sequences: " + sequences.size() + "\n";
		
		for(int i = 0; i < sequences.size(); i++) {
			s += "\t[Sequence " + (i+1) + "]\n";
			s += sequences.get(i).toString() + "\n";
		}
		
		return s;
		
	}
	
}
