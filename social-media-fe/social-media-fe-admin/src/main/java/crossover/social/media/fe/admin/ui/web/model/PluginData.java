package crossover.social.media.fe.admin.ui.web.model;

import crossover.social.media.domain.Setting;

import java.util.ArrayList;
import java.util.List;

/**
 * PluginData
 * Created by bazzoni on 10/07/2015.
 */
public class PluginData {

    private String id;
    private String name;
    private String type;
    private String status;
    private Setting activate;
    private List<Setting> cmsSettings = new ArrayList<>();

    public PluginData() {
    }

    public PluginData(String id, String name, String type, String status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Setting getActivate() {
        return activate;
    }

    public void setActivate(Setting activate) {
        this.activate = activate;
    }

    public List<Setting> getSettings() {
        return cmsSettings;
    }

    public void setSettings(List<Setting> cmsSettings) {
        this.cmsSettings = cmsSettings;
    }
}
