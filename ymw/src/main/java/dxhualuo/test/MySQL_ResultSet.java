package dxhualuo.test;

public class MySQL_ResultSet implements ResultSet {
    Object[] data;
    int index = -1;

    public MySQL_ResultSet(Object[] data){
        if(data == null){
            throw new NullPointerException();
        }
        this.data = data;
    }

    @Override
    public boolean next() {
        return data.length > ++index;
    }
}
