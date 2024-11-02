package recording;

import java.util.ArrayList;

import exceptions.EmptySequenceException;

public class Sequence {

	private ArrayList<EventAction> sequence = null;
	
	private long duration = 0;
	
	public Sequence() {
		
		sequence = new ArrayList<EventAction>();
		
	}
	
	public EventAction get(int index) {
		
		return sequence.get(index);
		
	}
	
	public EventAction getLast() {
		
		if(sequence.isEmpty())
			return null;
		
		return sequence.get(sequence.size()-1);
		
	}
	
	public boolean add(EventAction a) {
		
		return sequence.add(a);
		
	}
	
	public EventAction removeLast() throws EmptySequenceException {
		
		if(sequence.isEmpty())
			throw new EmptySequenceException("Cannot remove last event action. Sequence is empty!");
			
		return sequence.remove(sequence.size()-1);
		
	}
	
	public ArrayList<EventAction> getAll(){
		
		return sequence;
		
	}
	
	public int getSize() {
		
		return sequence.size();
		
	}
	
	public void setDuration(long duration) {

		getFirst().setDuration(duration);
		
	}

	public long getDuration() {
		
		return duration;
		
	}
	
	public EventAction getFirst() {
		
		return sequence.get(0);
		
	}
	
	public long analyzeTime() throws InterruptedException {
		
		for(EventAction a: sequence)
			duration += a.analyzeTime();
		
		return duration;
		
	}
	
	public boolean isEmpty() {
		
		return sequence.isEmpty();
		
	}
	
	public String toString() {
		
		String s = "";
		
		for(int i = 0; i < sequence.size(); i++) {
			s += "\t\t[Event " + i + "] "; 
			s += sequence.get(i).toString() + "\n";
		}
		return s;
		
	}

}
