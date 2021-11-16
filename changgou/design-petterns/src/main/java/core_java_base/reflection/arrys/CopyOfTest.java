package core_java_base.reflection.arrys;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CopyOfTest {
    public static void main(String[] args) {


//



//        int[] arrs = {1,2,3};
//        System.out.println(arrs.length);
//        System.out.println(Arrays.toString(arrs));
//        int[] copyArrs = Arrays.copyOf(arrs, 9);
//        System.out.println(Arrays.toString(copyArrs));




//        System.out.println(copyArrs.length);

        int[] arrs = {1,2,3};
        int[] o =(int[]) CopyOfTest.goodCopyOf(arrs, 3);
        System.out.println(Arrays.toString(o));



    }

    public static Object goodCopyOf(Object a, int newLength)
    {
        Class cl = a.getClass();
        if (!cl.isArray()) return null;
        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }

    /**
     * public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
     * 代码解释:
     * 　　Object src : 原数组
     *    int srcPos : 从元数据的起始位置开始
     * 　　Object dest : 目标数组
     * 　　int destPos : 目标数组的开始起始位置
     * 　　int length  : 要copy的数组的长度
     */
    private void testSystemArraysCopy(){
        byte[] a = new byte[]{1,2,3,4,5};
        System.out.println(Arrays.toString(a));
        byte[] b = new byte[6];
        System.arraycopy(a,0,b,0,5);
        System.out.println(Arrays.toString(b));
    }
}
