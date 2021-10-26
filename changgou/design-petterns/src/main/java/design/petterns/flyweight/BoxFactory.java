package design.petterns.flyweight;

import java.util.HashMap;

public class BoxFactory {
    private static HashMap<String, AbstractBox> map;

    public BoxFactory(){
        map = new HashMap<String, AbstractBox>();
        map.put("I", new IBox());
        map.put("L", new LBox());
        map.put("O", new OBox());


    }

    private static class SingetonHolder{
        private static final BoxFactory INSTANCE = new BoxFactory();
    }

    public static final BoxFactory getInstance(){
        return SingetonHolder.INSTANCE;
    }

    public AbstractBox getBox(String key){
        return map.get(key);
    }

}
