package ch9Source;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ComplexAssemly {
    // 需要进行复杂数据结构的类
    private Long id;
    private List<String> list;
    private Map<String,String> map;
    private Properties props;
    private Set<String> set;
    private String[] array;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }
    public String printInfo(){
        return "id:"+this.getId()+" array: "+this.getArray()[1]+""+this.getList()+" map: "+this.map;
    }
}
