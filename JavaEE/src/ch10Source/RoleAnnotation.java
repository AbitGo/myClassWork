package ch10Source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//该类是通过注释进行ioc实例化

//Component代表IOC会把这个类扫描生成Bean实例
//不写value则为小写为开头的默认类名
@Component(value = "roleAnn")
public class RoleAnnotation {
    @Value("1")
    private long id;
    @Value("2")
    private String roleName;
    @Value("2")
    private String note;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
