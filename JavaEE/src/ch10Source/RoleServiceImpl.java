package ch10Source;

import org.springframework.stereotype.Component;
//说明是一个Spring所需要的Bean
@Component
public class RoleServiceImpl implements RoleService {
    @Override
    public void printRoleInfo(RoleAnnotation roleAnnotation) {
        System.out.println("role id="+roleAnnotation.getId());
        System.out.println("role getNote="+roleAnnotation.getNote());
        System.out.println("role getRoleName="+roleAnnotation.getRoleName());
    }
}
