package design.petterns.mediator;

public class MediatorStructure extends Mediator {
    // 首先中介机构必须知道房主和租房者
    private HouseOwner houseOwner;
    private Tenant tenant;

    public HouseOwner getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(HouseOwner houseOwner) {
        this.houseOwner = houseOwner;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public void constact(String message, Person person) {
        if (person == houseOwner) {
            houseOwner.getMessage(message);
        }else{
            tenant.getMessage(message);
        }
    }
}
