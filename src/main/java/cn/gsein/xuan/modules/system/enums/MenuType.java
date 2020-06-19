package cn.gsein.xuan.modules.system.enums;

/**
 * 菜单类型
 *
 * @author G. Seinfeld
 * @since 2020/06/18
 */
public enum MenuType {
    /**
     * 菜单
     */
    MENU("菜单"),
    /**
     * 图标
     */
    BUTTON("图标");

    private final String name;


    MenuType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
