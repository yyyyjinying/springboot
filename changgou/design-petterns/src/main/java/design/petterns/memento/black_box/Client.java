package design.petterns.memento.black_box;

public class Client {
    public static void main(String[] args) {
        System.out.println("------------大战Boss前------------");
        //大战Boss前
        GameRole gameRole = new GameRole();
        gameRole.initState();
        gameRole.stateDisplay();

        //保存进度
        RoleStateCaretaker roleStateCaretaker = new RoleStateCaretaker();
        roleStateCaretaker.setMemento(gameRole.saveState());

        System.out.println("------------大战Boss后------------");
        //大战Boss时，损耗严重
        gameRole.fight();
        gameRole.stateDisplay();

        System.out.println("------------恢复之前状态------------");
        //恢复之前状态
        Memento memento = roleStateCaretaker.getMemento();
        gameRole.recoverState(memento);
        gameRole.stateDisplay();


    }
}
