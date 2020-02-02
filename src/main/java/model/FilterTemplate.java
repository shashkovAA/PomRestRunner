package model;

public class FilterTemplate {
    private String filterName;
    TemplateJson templateJson;

    public FilterTemplate() {
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public TemplateJson getTemplateJson() {
        return templateJson;
    }

    public void setTemplateJson(TemplateJson templateJson) {
        this.templateJson = templateJson;
    }
}
