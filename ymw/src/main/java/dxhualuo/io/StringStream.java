package dxhualuo.io;

import dxhualuo.data.Array;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 *  可以把输出流输出到内部字符串，读取使用
 * @author dx_hualuo
 */
public class StringStream extends OutputStream {
    /**
     *  内部保存数据的容器
     */
    private ArrayList<Byte> bytes = new ArrayList<>();

    @Override
    public void write(int i) throws IOException {
        if(i != -1){
            byte b = (byte)i;
            bytes.add(b);
        }
    }

    /**
     *  将输出数据用默认编码转换为字符串
     * @return 输出的字符串
     */
    public String getString(){
        Byte[] bytes1 = new Byte[this.bytes.size()];
        bytes1 = bytes.toArray(bytes1);
        return new String(Array.devanningByte(bytes1));
    }

    /**
     *  使用指定编码方式将输出数据转换为字符串
     * @param charset 指定编码方式
     * @return 字符串
     */
    public String getStringUseCharset(String charset){
        Byte[] bytes1 = new Byte[this.bytes.size()];
        bytes1 = bytes.toArray(bytes1);
        return new String(Array.devanningByte(bytes1), Charset.forName(charset));
    }
    /**
     *  使用指定编码方式将输出数据转换为字符串
     * @param charset 指定编码方式
     * @return 字符串
     */
    public String getStringUseCharset(Charset charset){
        Byte[] bytes1 = new Byte[this.bytes.size()];
        bytes1 = bytes.toArray(bytes1);
        return new String(Array.devanningByte(bytes1), charset);
    }
}
