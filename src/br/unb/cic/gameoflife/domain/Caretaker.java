package br.unb.cic.gameoflife.domain;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private List<Object> list;
    
    public Caretaker() {
    	list = new ArrayList<Object>();
    }
    
    public void save(Originator originator){
        list.add(0, originator.save());
    }
     
    public void undo(Originator originator){
        originator.undoToLastSave(list.remove(0));
    }
    
    public void clear() {
    	list.clear();
    }
    
    public boolean isEmpty() {
    	return list.isEmpty();
    }
}