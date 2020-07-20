package dxhualuo.reflect;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ReflectArray extends ReflectObject implements Iterable{

    public ReflectArray(String className, int... length) throws ClassNotFoundException {
        this(ReflectClass.getClass(className), length);
    }

    public ReflectArray(String className, int length) throws ClassNotFoundException {
        this(ReflectClass.getClass(className), length);
    }

    public ReflectArray(Class<?> _class, int... length){
        super(Array.newInstance(_class, length));
    }

    public ReflectArray(Class<?> _class, int length){
        super(Array.newInstance(_class, length));
    }

    ReflectArray(ReflectObject obj){
        super(obj.getObject());
    }

    public Object get(int index){
        return Array.get(obj, index);
    }

    public Object get(int... index){
        Object object = obj;
        for (int in: index) {
            object = Array.get(object, in);
        }
        return object;
    }

    public Object set(int index, Object obj){
        Array.set(super.obj, index, obj);
        return obj;
    }

    public Object set(Object obj, int... index){
        Object object = super.obj;
        for (int i = 0; i < index.length; i++) {
            if(index.length - 1 == i){
                Array.set(object, index[i], obj);
            }else{
                Array.get(object, index[i]);
            }
        }
        return obj;
    }

    public int length(){
        return Array.getLength(obj);
    }

    public int getDim(){
        int dim = 0;
        Class cls = obj.getClass();
        while (cls.isArray()) {
            dim++;
            cls = cls.getComponentType();
        }
        return dim;
    }

    public Class<?> getComponentType(){
        return thisClass.getComponentType();
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Spliterator spliterator() {
        return null;
    }
}
