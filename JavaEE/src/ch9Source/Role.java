package ch9Source;

public class Role {
    private long id;
    private String RoleName;
    private String note;

    public Role() {
    }

    public Role(String roleName, String note) {
        this.RoleName = roleName;
        this.note = note;
    }
    public Role(long id,String roleName, String note) {
        this.id = id;
        this.RoleName = roleName;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String printInfo(){
        return "id:"+this.id+" RoleName: "+this.RoleName+" note: "+this.note;
    }
}
