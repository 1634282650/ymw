package dxhualuo.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MapList<key, value> implements Serializable {
    private HashMap<key, List<value>> map = new HashMap<key, List<value>>();
    private List<value> values = new ArrayList<>();
    public MapList(){}

    public boolean put(key k, value v){
        if (map.containsKey(k)) {
            List<value> val = map.get(k);
            if (!val.contains(v)) {
                val.add(v);
                values.add(v);
                return true;
            }
        }
        else{
            List<value> val = new ArrayList<value>();
            val.add(v);
            map.put(k,val);
            values.add(v);
            return true;
        }
        return false;
    }

    public List<value> get(key k){
        if(!map.containsKey(k)){
            return null;
        }
        List<value> val = map.get(k);
        return val;
    }
    public value get(key k, int index){
        if(!map.containsKey(k)){
            return null;
        }
        List<value> val = map.get(k);
        if (val.size() > index) {
            return val.get(index);
        }
        return null;
    }

    public boolean containsKey(key k){
        return map.containsKey(k);
    }

    public boolean containsValue(key k, value v){
        if (map.containsKey(k)) {
            List<value> val = map.get(k);
            return val.contains(v);
        }
        return false;
    }

    public int getValueCountByKey(key k){
        if(map.containsKey(k)){
            List<value> val = map.get(k);
            return val.size();
        }
        return 0;
    }

    public List<value> values(){
        return values;
    }

    public Set<key> KeySet(){
        return map.keySet();
    }
    public int KeyCount(){
        return map.size();
    }

    public int ValueCount(){
        return values.size();
    }

    public int size(){
        return map.size();
    }

    public boolean removeKey(key k){
        if (map.containsKey(k)) {
            List<value> val = map.get(k);
            for (value v: val) {
                values.remove(v);
            }
            return true;
        }
        return false;
    }
    public boolean removeValue(key k, value v){
        if (map.containsKey(k)) {
            List<value> val = map.get(k);
            if(val.contains(v)){
                val.remove(v);
                return true;
            }
        }
        return false;
    }
}
