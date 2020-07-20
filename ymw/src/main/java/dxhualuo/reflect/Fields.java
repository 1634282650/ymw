package dxhualuo.reflect;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Fields implements Serializable {
    private boolean complete = false;
    private HashMap<String, Field> field;

    public Fields() {
        this.field = new HashMap<>();
    }
    public Fields(Field field) {
        this.field = new HashMap<>();
        addField(field);
    }

    public Fields(Field[] field, boolean complete) {
        this.field = new HashMap<>();
        addField(field);
        this.complete = complete;
    }


    public boolean isComplete(){
        return complete;
    }

    public void setComplete(){
        complete = true;
    }

    public Field getField(String fieldName) throws NoSuchFieldException {
        Field _field = field.get(fieldName);
        if(_field == null && this.complete){
            throw new NoSuchFieldException();
        }
        return null;
    }

    public Field[] getFields(){
        Field[] result = new Field[field.size()];
        field.values().toArray(result);
        return result;
    }

    public void addField(Field field){
        if(isComplete()){
            return;
        }
        this.field.put(field.getName(), field);
    }

    public void addField(Field[] field){
        for (Field me: field) {
            addField(me);
        }
    }
}
