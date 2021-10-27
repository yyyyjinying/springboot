package design.petterns.memento.white_box;

/**
 * 白箱备忘录模式是破坏封装性的。但是通过程序员自律，同样可以在一定程度上实现模式的大部分用意。
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("------------大战Boss前------------");
        //大战Boss前
        GameRole gameRole = new GameRole();
        gameRole.initState();
        gameRole.stateDisplay();


        RoleStateCaretaker roleStateCaretaker = new RoleStateCaretaker();
        roleStateCaretaker.setRoleStateMemento(gameRole.saveState());

        System.out.println("------------大战Boss后------------");
        //大战Boss时，损耗严重
        gameRole.fight();
        gameRole.stateDisplay();

        System.out.println("------------恢复之前状态------------");
        //恢复之前状态

        RoleStateMemento roleStateMemento = roleStateCaretaker.getRoleStateMemento();
        gameRole.recoverState(roleStateMemento);
        gameRole.stateDisplay();


    }
}
