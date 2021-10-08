package design.principles.demo5;

/**
 * 迪米特法则
 */
public class Agent {
    private Star star;
    private Fans fans;
    private Company company;

    public void setStar(Star star){
        this.star = star;
    }

    public void setFans(Fans fans){
        this.fans = fans;
    }

    public void setCompany(Company company){
        this.company = company;
    }

    public void meeting(){
        System.out.println(fans.getName()+"与明星"+star.getName());
    }

    public void business(){
        System.out.println(company.getName()+"与明星"+star.getName());
    }


}
